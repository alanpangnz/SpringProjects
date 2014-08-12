package com.aucklanduni.spring.labs.rwlock.protect; 

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class implementinglockWithAnnotations {

	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final Lock r = rwl.readLock();
	private final Lock w = rwl.writeLock();

	@Before("execution(* *.get(..))")
	void readlock(){
		r.lock();
	}
	
	@After("execution(* *.get(..))")
	void readunlock(){
		r.unlock();
	}

	@Before("execution(* *.set(..))")
	void writelock(){
		w.lock();
	}
	
	@After("execution(* *.set(..))")
	void writeunlock(){
		w.unlock();
	}
}
