package com.aucklanduni.rmi.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aucklanduni.rmi.common.ShapeFactory;

/**
 * Server program to create a remotely accessible ShapeFactory object and 
 * register its proxy with the RMI Naming service.
 */
public class Server {
	
	private static Registry _registry;
	
	private static final Logger _logger = LoggerFactory.getLogger(Server.class);
	
	public static void main(String[] args) {
		final String REGISTRY_HOST = "127.0.0.1";
		final int REGISTRY_PORT = 1099;
		final int SERVER_PORT = 0;
		final String SERVICE_NAME = "ShapeFactory";
		final int FACTORY_CAPACITY = 10;
		
		try {
			// Start the Registry
			_registry = LocateRegistry.createRegistry(1099);
		} catch(RemoteException ignore) {
			// Ignore any exception indicating that the Registry is already started.
		}
		
		try {
			// Attempt to create a remotely accessible factory object and bind
			// it into the server-side RMI run-time.
			ShapeFactory factory = new ShapeFactoryServant(FACTORY_CAPACITY, SERVER_PORT);
			
			// Advertise the factory object in the RMI lookup service.
			Naming.rebind("//" + REGISTRY_HOST + ":" + REGISTRY_PORT + "/" + 
					SERVICE_NAME, factory);
			
			_logger.info("Shape factory created and ready for action...");
		} catch(RemoteException e) {
			_logger.error("Error creating shape factory: " + e);
		} catch(MalformedURLException e) {
			_logger.error("Bad URL given to Naming service:" + e);
		}
	}
}
