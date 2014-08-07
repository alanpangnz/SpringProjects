package com.aucklanduni.spring.labs.rwlock.protect; 

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class implementinglock {

	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final Lock r = rwl.readLock();
	private final Lock w = rwl.writeLock();

	void readlock(){
		r.lock();
	}
	void readunlock(){
		r.unlock();
	}
	void writelock(){
		w.lock();
	}
	void writeunlock(){
		w.unlock();
	}
	
	
}
