package com.aucklanduni.rmi.banking.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aucklanduni.rmi.banking.common.IllegalMoneyException;
import com.aucklanduni.rmi.banking.common.Money;
import com.aucklanduni.rmi.banking.common.BankAccount;

public class Server {

	private static final Logger _logger = LoggerFactory.getLogger(Server.class);
	private static Registry _registry;
	
	public static void main(String[] args) {
		
		String registryHost = "127.0.0.1";
		String registryPort = "1099";
		int serverPort = 0;
		
		try {
			// Start the Registry.
			try {
				_registry = LocateRegistry.createRegistry(1099);
			} catch(RemoteException e) {
				// Ignore any exception indicating that the registry is already running.
			}
			
			// Create remote BankAccount instances.
			BankAccount acc1 = new BankAccountServant(
					new Money(), "Brent, D.", "67832189", new Money(1500, 00));
			BankAccount acc2 = new BankAccountServant(
					new Money(), "Tinsley, D.", "69826344", new Money(100, 00));
			BankAccount acc3 = new BankAccountServant(
					new Money(), "Keenan, G.", "61198701", new Money(250, 00));
			
			// Register BankAccounts with the lookup service.
			// === YOUR CODE HERE ===
			
			System.out.println("Bank account objects exported.");
		} catch(RemoteException e) {
			e.printStackTrace();
		} catch(IllegalMoneyException e) {
			e.printStackTrace();
		} catch(MalformedURLException e) {
			e.printStackTrace();
		}
	}
}