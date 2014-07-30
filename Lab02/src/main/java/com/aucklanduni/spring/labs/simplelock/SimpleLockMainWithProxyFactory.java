package com.aucklanduni.spring.labs.simplelock;

import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.ProxyFactory;


public class SimpleLockMainWithProxyFactory {
	
	private static final int NUMBER_OF_THREADS = 100;
	private static final int INVOCATIONS_PER_THREAD = 1000;
	
	  public static void main(String[] args) {
		// Create the target object and the advice.
	    Counter target = new DefaultCounter();
	    
	    // Set up the ProxyFactory, specifying the interface that proxy objects should implement,
	    // the target object and advice.
	    ProxyFactory factory = new ProxyFactory();
	    factory.setInterfaces(new Class[] {Counter.class});
	    factory.setTarget(target);
	    
	    Counter proxy = (Counter)factory.getProxy();
	    
	    
	    testCounter(proxy, NUMBER_OF_THREADS, INVOCATIONS_PER_THREAD);
	    
	    int finalValue = proxy.value();
	    
	    System.out.println("Final value: " + finalValue + ", expected: " + NUMBER_OF_THREADS * INVOCATIONS_PER_THREAD);
	  }
	  
	  private static void testCounter(final Counter counter, int numberOfThreads, final int invocationsPerThread) {
		  Thread[] threads = new Thread[numberOfThreads];
		  
		  // Create and start threads.
		  for(int i = 0; i < numberOfThreads; i++) {
			  Thread t = new Thread( new Runnable() {
				  public void run() {
					  for(int i = 0; i < invocationsPerThread; i++) {
						  counter.increment();
					  }
				  }
			  });
			  threads[i] = t;
			  t.start();
		  }
		  
		  // Wait for threads to complete.
		  for(int i = 0; i < numberOfThreads; i++) {
			  try {
				  threads[i].join();
			  } catch(InterruptedException ignore) {				  
			  }
		  }
	  }
	}
