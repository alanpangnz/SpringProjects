package com.aucklanduni.rmi.banking.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.regex.PatternSyntaxException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Hashtable;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aucklanduni.rmi.banking.common.BankAccount;
import com.aucklanduni.rmi.banking.common.Money;

/**
 * Class to represent a client process in a RMI banking application. The client
 * loads a set of account transactions from a data file and makes RMI calls on
 * corresponding remote account objects. The client is multi-threaded.
 */
public class Client {
	
	private static final Logger _logger = LoggerFactory.getLogger(Client.class);

	public static void main(String[] args) {
		
		BasicConfigurator.configure();
		
		final int THREAD_POOL_SIZE = 5;
		final String DATA_FILE = "/Users/l93burgess/Desktop/github/SpringProjects/Lab04 - Retry/src/main/resources/operations.dat";
		final String REGISTRY_HOST = "127.0.0.1";
		final int REGISTRY_PORT = 1099;

		try {
			long start = System.currentTimeMillis();

			/* Lookup remote BankAccount objects. */
			Hashtable<String, BankAccount> accounts = lookupRemoteAccounts(REGISTRY_HOST, "1099");
			if (accounts.isEmpty()) {
				_logger.error("Unable to acquire proxy objects, program terminating.");
				System.exit(1);
			}

			long acquiredProxies = System.currentTimeMillis();
			_logger.info("Time taken to acquire proxies: "
					+ (acquiredProxies - start) / 1000.0 + "sec.");

			/*
			 * Create a parser and queue to store commands read from the data
			 * file.
			 */
			BankCommandParser parser = new BankCommandParser();
			BlockingQueue<String[]> queue = new ArrayBlockingQueue<String[]>(
					100);

			/* Create a pool of worker (consumer) threads to process the queue. */
			List<Thread> workers = new ArrayList<Thread>();
			for (int i = 0; i < THREAD_POOL_SIZE; i++) {
				Thread thread = new Thread(new Worker(queue, accounts));
				thread.start();
				workers.add(thread);
			}

			/* Start a producer thread that deposits commands into the queue. */
			Thread producerThread = new Thread(new Producer(DATA_FILE, parser,
					queue));
			producerThread.start();

			/* Wait for the producer thread to finish. */
			producerThread.join();
			_logger.info("Producer thread finished ...");
			
			/* Start a thread to monitor the queue and wait for it to become empty. */
			Thread queueMonitorThread = new Thread(new QueueMonitor<String[]>(queue));
			queueMonitorThread.start();
			queueMonitorThread.join();
			_logger.info("Buffer is now empty ...");

			/* Ask the workers to terminate gracefully. */
			for (Thread t : workers) {
				t.interrupt();
			}

			/* Wait for the workers to terminate. */
			for (Thread t : workers) {
				t.join();
			}
			_logger.info("Worker threads finished ...");

			/* Request final balances. */
			Enumeration<String> e = accounts.keys();
			BankAccount account = null;
			while (e.hasMoreElements()) {
				String accountNumber = e.nextElement();
				account = accounts.get(accountNumber);
				Money balance = account.getBalance();
				_logger.info(accountNumber + ": " + balance);
			}

			long stop = System.currentTimeMillis();
			_logger.info("Total elapsed time: " + (stop - start) / 1000.0
					+ "sec.");
		} catch (PatternSyntaxException e) {
			_logger.error("Grammar is invalid ..." + e.getMessage());
			System.exit(1);
		} catch (InterruptedException e) {
			_logger.error("Main application thread interrupted unexpectedly.");
		} catch (RemoteException e) {
			_logger.error("Communication error.");
		}
	}

	/**
	 * Returns a hashtable of <account-number, BankAccount proxy object> pairs.
	 * The hashtable contains one entry for each remotely accessible BankAccount
	 * object registered with the RMI Registry.
	 * 
	 * @param registryHost
	 *            the name of the host machine on which the RMI Registry is
	 *            expected to be running.
	 * @param registryPort
	 * 			  the port the RMI Registry is using to listen for incoming
	 *            invocations.
	 */
	private static Hashtable<String, BankAccount> lookupRemoteAccounts(
			String registryHost, String registryPort) {
		Hashtable<String, BankAccount> accounts = new Hashtable<String, BankAccount>();

		// === YOUR CODE HERE ===
		try {
			BankAccount factory1 = (BankAccount)Naming.lookup(
				"//" + registryHost + ":" + registryPort + "/" + "67832189");
			BankAccount factory2 = (BankAccount)Naming.lookup(
				"//" + registryHost + ":" + registryPort + "/" + "69826344");
			BankAccount factory3 = (BankAccount)Naming.lookup(
				"//" + registryHost + ":" + registryPort + "/" + "61198701");
			
			//System.out.println("Acc name: "+ factory1.getNumber());
			
			accounts.put( factory1.getNumber(), factory1);
			accounts.put( factory2.getNumber(), factory2);
			accounts.put( factory3.getNumber(), factory3);
			
			return accounts;
		
		} catch (RemoteException e) {
			return null;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}