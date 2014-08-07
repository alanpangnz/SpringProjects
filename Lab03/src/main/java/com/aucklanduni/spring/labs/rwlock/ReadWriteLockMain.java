package com.aucklanduni.spring.labs.rwlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aucklanduni.spring.labs.rwlock.protect.ThreadMonitor;


public class ReadWriteLockMain {
	
	private static final int NUMBER_OF_READER_THREADS = 20;
	private static final int NUMBER_OF_WRITER_THREADS = 10;
	
	public static void main(String[] args) {
	    ApplicationContext context = 
	        new ClassPathXmlApplicationContext("rw-lock.xml"); 
	    
	    // Retrieve ThreadMonitor beans.
	    final ThreadMonitor monitor1 = context.getBean("monitor_1", ThreadMonitor.class);
	    final ThreadMonitor monitor2 = context.getBean("monitor_2", ThreadMonitor.class);
	    
	    // Create a List to store all threads being used by the test program.
	    List<Thread> allThreads = new ArrayList<Thread>();
	    
	    // Create the reader threads; each reader thread will call get() on the two 
	    // ThreadMoitor objects. 
	    Thread[] readers = new Thread[NUMBER_OF_READER_THREADS];
	    for(int i = 0; i < NUMBER_OF_READER_THREADS; i++) {
	    	readers[i] = new Thread( new Runnable() {
	    		public void run() {
	    			monitor1.get();
	    			monitor2.get();
	    		}
	    	});
	    	allThreads.add(readers[i]);
	    }
	    
	    // Create the writer threads; each writer thread will call set() on the two
	    // ThreadMonitor objects.
	    Thread[] writers = new Thread[NUMBER_OF_WRITER_THREADS];
	    for(int i = 0; i < NUMBER_OF_WRITER_THREADS; i++) {
	    	writers[i] = new Thread( new Runnable() {
	    		public void run() {
	    			monitor1.set();
	    			monitor2.set();
	    		}
	    	});
	    	allThreads.add(writers[i]);
	    }
	    
	    // Randomly select a thread and start it executing. Repeat until all reader
	    // and writer threads have been started. Randomly starting the threads will
	    // help to ensure a mix readers and writers executing concurrently.
	    Random rand = new Random();
	    do {
	    	int threadIndex = rand.nextInt(allThreads.size());
	    	Thread thread = allThreads.remove(threadIndex);
	    	thread.start();
	    } while(!allThreads.isEmpty());
	    
	    // Wait for all threads to finish executing.
	    try {
	    	for(int i = 0; i < NUMBER_OF_WRITER_THREADS; i++) {
	    		writers[i].join();
	    	}
	    	for(int i = 0; i < NUMBER_OF_READER_THREADS; i++) {
	    		readers[i].join();
	    	}
	    } catch(InterruptedException ignore) {
	    }
	    
	    // Ask the ThreadMonitor objects to report how they were used.
	    monitor1.report();
	    monitor2.report();
	    
	  }
}
