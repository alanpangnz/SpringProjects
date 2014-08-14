package com.aucklanduni.spring.labs.rwlock; 

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class implementinglockWithAnnotations {

	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final Lock r = rwl.readLock();
	private final Lock w = rwl.writeLock();

	@Pointcut("execution(* *.*(..)) && @annotation(com.aucklanduni.spring.labs.rwlock.protect.Reader)")
	void read() {	
	}
	@Pointcut("execution(* *.*(..)) && @annotation(com.aucklanduni.spring.labs.rwlock.protect.Writer)")
	void write() {	
	}
	
	@Before("read()")
	void readlock(){
		r.lock();
	}
	@After("read()")
	void readunlock(){
		r.unlock();
	}
	@Before("write()")
	void writelock(){
		w.lock();
	}
	@After("write()")
	void writeunlock(){
		w.unlock();
	}
}
