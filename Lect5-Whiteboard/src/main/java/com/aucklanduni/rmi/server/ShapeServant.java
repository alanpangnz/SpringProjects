package com.aucklanduni.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.aucklanduni.rmi.common.Graphic;
import com.aucklanduni.rmi.common.Shape;

/**
 * An implementation of the Shape interface. A ShapeServant instance is a 
 * remotely accessible object that represents a particular Shape on a shared 
 * whiteboard. 
 */
public class ShapeServant extends UnicastRemoteObject implements Shape {

	private Graphic state;
	private int id;
	
	/**
	 * Creates a new ShapeServant instance. 
	 * @param graphic the state of the new ShapeServant object.
	 * @param id the unique ID of the new ShapeServant object.
	 * @param port the port number to be used by the RMI server.
	 * @throws RemoteException if the ShapeServant instance cannot be created. 
	 * This can happen if the RMI run-time does not have sufficient resources
	 * (e.g. sockets) to host an additional remote object.
	 */
	public ShapeServant(Graphic graphic, int id, int port) throws RemoteException {
		super(port);
		this.state = graphic;
		this.id = id;
	}

	/**
	 * @see com.aucklanduni.rmi.common.Shape#getAllState()
	 */
	public Graphic getAllState() throws RemoteException {
		return state;
	}

	/**
	 * @see com.aucklanduni.rmi.common.Shape#getId()
	 */
	public int getId() throws RemoteException {
		return id;
	}
}
