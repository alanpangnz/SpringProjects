package com.aucklanduni.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import com.aucklanduni.rmi.common.FullException;
import com.aucklanduni.rmi.common.Graphic;
import com.aucklanduni.rmi.common.Shape;
import com.aucklanduni.rmi.common.ShapeFactory;

/**
 * An implementation of the ShapeFactory interface. A ShapeFactoryServant 
 * instance is a remotely accessible object that creates and stores
 * references to remotely accessible Shape objects. Within a client/server
 * application, a single ShapeFactoryServant object runs on the server; the 
 * Shape objects created by the factory also reside on the server. Clients 
 * acquire remote references to the Shape objects from the factory. 
 */
public class ShapeFactoryServant extends UnicastRemoteObject implements ShapeFactory { 

	private List<Shape> shapes;	// List of Shapes created a ShapeFactoryServant.
	private int maxShapes;      // Capacity of a ShapeFactoryImpl.
	private int port;			// Port to be used the by the server process.
	
	/**
	 * Creates a ShapeFactoryServant object. 
	 * @param maxShapes the factory's capacity in terms of the maximum number 
	 * of shape objects that can be created.
	 * @param port the port number that the RMI server process will use to
	 * handle incoming requests.
	 * @throws RemoteException if the server-side RMI run-time cannot create 
	 * the ShapeFactoryServant instance. Construction can fail if the RMI 
	 * runtime has insufficient resources to host the new object.
	 */
	public ShapeFactoryServant(int maxShapes, int port) throws RemoteException {
		super(port);
		this.shapes = new ArrayList<Shape>();
		this.maxShapes = maxShapes;
		this.port = port;
	}
	
	/**
	 * @see com.aucklanduni.rmi.common.ShapeFactory#newShape()
	 */
	public synchronized Shape newShape(Graphic graphic) throws FullException, RemoteException {
		int numberOfShapes = shapes.size();
		
		if(numberOfShapes == maxShapes) {
			throw new FullException();
		}
		Shape newShape = new ShapeServant(graphic, numberOfShapes, port);
		shapes.add(newShape);
		return newShape;
		
	}

	/**
	 * @see com.aucklanduni.rmi.common.ShapeFactory#allShapes()
	 */
	public List<Shape> allShapes() throws RemoteException {
		return shapes;
	}

	/**
	 * @see com.aucklanduni.rmi.common.ShapeFactory#shapes(int)
	 */
	public List<Shape> shapes(int index) throws RemoteException {
		return shapes.subList(index, shapes.size() - 1);
	} 

}
