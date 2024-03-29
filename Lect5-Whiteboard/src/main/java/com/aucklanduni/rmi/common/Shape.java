package com.aucklanduni.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface to represent remotely accessible Shapes. This interface is 
 * intended to be implemented by classes whose instances' methods can be 
 * invoked remotely.
 */
public interface Shape extends Remote {
	
	/**
	 * Returns a local reference to a Graphic object whose attributes describe
	 * the state of the Remote object this method is called on.
	 */
	Graphic getAllState() throws RemoteException;
	
	/**
	 * Returns the unique ID of the Shape object. The ShapeFactory that created
	 * this Shape object assigned the value; the ShapeFactory assigns a unique 
	 * ID which is a count of the number of shapes it has previously created.
	 * Hence, the first Shape created by a ShapeFactory will have a unique ID of
	 * zero, with Shapes created subsequently having IDs of 1 through n-1.
	 */
	int getId() throws RemoteException;
}
