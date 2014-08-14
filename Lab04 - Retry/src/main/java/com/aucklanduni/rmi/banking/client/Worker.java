package com.aucklanduni.rmi.banking.client;

import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.concurrent.BlockingQueue;

import com.aucklanduni.rmi.banking.common.BankAccount;
import com.aucklanduni.rmi.banking.common.ExcessiveAmountException;
import com.aucklanduni.rmi.banking.common.IllegalMoneyException;
import com.aucklanduni.rmi.banking.common.Money;
import com.aucklanduni.rmi.banking.common.NegativeAmountException;

/**
 * Class whose instances are intended to be run in separate threads. A Worker
 * is responsible for retrieving commands from a BlockingQueue object and for
 * making appropriate RMI calls on remote BankAccount objects. 
 */
public class Worker implements Runnable {

	/* BlockingQueue object from where commands are retrieved. */
	private BlockingQueue<String[]> fQueue;

	/* 
	 * Hashtable used to store BankAccount proxy objects. The key is the 
	 * account number.
	 */
	private Hashtable<String,BankAccount> fAccounts;

	/**
	 * Creates a Worker instance.
	 */
	public Worker(BlockingQueue<String[]> queue, Hashtable<String,BankAccount> accounts) {
		this.fQueue = queue;
		this.fAccounts = accounts;
	}

	/**
	 * Until interrupted, iteratively processes commands held in the 
	 * BlockingQueue. The Worker can be blocked when the queue is empty. 
	 * Processing each command involves a RMI call to the remote BankAccount
	 * object to which the command applies. 
	 */
	public void run() {
		boolean finished = false;
		while(!finished) {
			try {
				// Retrieve command to process.
				String[] command = fQueue.take();

				// Process command.
				processCommand(command);
			} catch(InterruptedException e) {
				finished = true;
			}
		}
	}

	/*
	 * Implementation method that interprets a command and which makes the 
	 * necessary RMI call.
	 */
	private void processCommand(String[] commandTokens) {
		// === YOUR CODE HERE ===
		
			//how to get the proxyobject
			try {
				if (commandTokens[0].equals("balance")) {
					fAccounts.get(commandTokens[1]).getBalance();
				} else if (commandTokens[0].equals("name")) {
					fAccounts.get(commandTokens[1]).getName();
				} else if (commandTokens[0].equals("deposit")) {
					fAccounts.get(commandTokens[1]).deposit(new Money(commandTokens[2], commandTokens[3]));
				} else { //withdraw
					fAccounts.get(commandTokens[1]).withdraw(new Money(commandTokens[2], commandTokens[3]));
				}
			} catch (RemoteException | NumberFormatException| NegativeAmountException | IllegalMoneyException | ExcessiveAmountException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		
		
		
		
	}
}
