package com.aucklanduni.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aucklanduni.rmi.common.FullException;
import com.aucklanduni.rmi.common.Graphic;
import com.aucklanduni.rmi.common.Shape;
import com.aucklanduni.rmi.common.ShapeFactory;

/**
 * Client program that uses the RMI lookup service to acquire a remote
 * reference to the ShapeFactory object that is hosted on the server. Having
 * obtained a reference to the factory, the client program makes remote method
 * invocations on the factory to create new shape objects that also reside on
 * the server. The client expects one command line argument, the name of the 
 * machine on which the RMI lookup service runs.
 */
public class Client {
	
	private static final Logger _logger = LoggerFactory.getLogger(Client.class);
	
	public static void main(String[] args) {
		final String SERVICE_NAME = "ShapeFactory";
		final String REGISTRY_HOST = "127.0.0.1";
		final int REGISTRY_PORT = 1099;
		
		try {
			// Acquire a remote reference to the ShapeFactory object residing
			// on the server.
			ShapeFactory factory = (ShapeFactory)Naming.lookup(
					"//" + REGISTRY_HOST + ":" + REGISTRY_PORT + "/" + SERVICE_NAME);
			
			// Ask the remote factory to create a couple of Shape objects, 
			// which will also reside on the server. shapeA and shapeB refer to
			// proxy objects that represent the newly created remote Shapes.
			Shape shapeA = factory.newShape(new Graphic(10, 10, 250, 20, true));
			Shape shapeB = factory.newShape(new Graphic(35, 60, 100, 50, false));
			
			// Query new remote Shape objects.
			_logger.info("ShapeA's Id is " + shapeA.getId());
			_logger.info("ShapeB's Id is " + shapeB.getId());
			
			// Query the remote factory.
			List<Shape> remoteShapes = factory.allShapes();
			for(Shape s : remoteShapes) {
				// First iteration of this loop calls getAllstate() on the
				// same remote Shape object that shapeA acts as remote
				// reference for, the second iteration on shapeB's remote
				// object.
				_logger.info(s.getAllState().toString());
			}
			
		} catch(RemoteException e) {
			_logger.error("Communication error: " + e);
		} catch(MalformedURLException e) {
			_logger.error("Bad URL given to Naming service: " + e);
		} catch (NotBoundException e) {
			_logger.error("No remote object registered under the name " +
					SERVICE_NAME + "in the RMI lookup service running on host "
					+ REGISTRY_HOST + " and listening on port " + REGISTRY_PORT);
		} catch(FullException e) {
			_logger.info("ShapeFactory is full");
		} 
	}
}
