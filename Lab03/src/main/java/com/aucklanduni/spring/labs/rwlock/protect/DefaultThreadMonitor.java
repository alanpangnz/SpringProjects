package com.aucklanduni.spring.labs.rwlock.protect;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Class to help test whether its instances are being correctly protected against
 * concurrent threads according to the semantics of Read-Write locking.
 */
public class DefaultThreadMonitor implements ThreadMonitor {
	// Each thread that operates on a DefultThreadMonitor object has its own Random
	// generator for calculating the time it sleeps while executing get() or set().
	private Map<Thread,Random> _randomGenerators;
	
	// Set to store the reader threads that are concurrently executing get().
	private Set<Thread> _readerThreads;
	
	// A reference to the writer thread that is currently executing set(); null if
	// there is no such thread.
	private Thread _writerThread;
	
	// Set to true if this DefaultThreadMonitor object has been used contrary to the 
	// principles of Read-Write locking, false otherwise.
	private boolean _error;
	
	// The maximum number of reader threads that have executed get() concurrently.
	private int _readerThreadCount;

	
	public DefaultThreadMonitor() {
		_randomGenerators = Collections.synchronizedMap(new HashMap<Thread,Random>());
		_readerThreads = Collections.synchronizedSet(new HashSet<Thread>());
		_writerThread = null;
		_error = false;
		_readerThreadCount = 0;
	}
	
	@Writer
	public void set() {
		if((!_readerThreads.isEmpty()) || (_writerThread != null)) {
			// Error detected - at least one reader thread or a writer thread is 
			// already active in this object.
			_error = true;
		}
		
		// Record that a writer thread is now executing this method.
		_writerThread = Thread.currentThread();
		
		// Sleep for some randomly generated number of ms.
		try {
			Thread.sleep(getSleepTime());
		} catch(InterruptedException ignore) {
		}
		
		// Record that a writer thread has completed executing.
		_writerThread = null;
	}

	@Reader
	public int get() {
		// Record that a reader thread is now executing this method.
		_readerThreads.add(Thread.currentThread());
		
		if(_writerThread != null) {
			// Error detected - a writer is active.
			_error = true;
		}
		
		// Determine whether the number of concurrently executing reader threads
		// is greater than the number that has been detected previously. If so, 
		// update _readerThreadCount.
		int numberOfReaderThreads = _readerThreads.size();
		synchronized(this) {
			if(numberOfReaderThreads > _readerThreadCount) {
				_readerThreadCount = numberOfReaderThreads;
			}
		}
		// Sleep for some randomly generated number of ms.
		try {
			Thread.sleep(getSleepTime());
		} catch(InterruptedException ignore) {
		}
		
		// Record that a reader thread has completed executing.
		_readerThreads.remove(Thread.currentThread());
		
		// As a getter method, need to return something.
		return 0;
	}
	
	@Override
	public void report() {
		System.out.println("Success: " + !_error);
		System.out.println("Max concurrent readers: " + _readerThreadCount);
	}
	
	private int getSleepTime() {
		Thread thisThread = Thread.currentThread();
		Random rand = _randomGenerators.get(thisThread);
		if(rand == null) {
			rand = new Random();
			_randomGenerators.put(thisThread,rand);
		}
		return rand.nextInt(100);
	}
}
