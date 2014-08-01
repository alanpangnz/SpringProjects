package com.aucklanduni.spring.labs.rwlock.protect;

public interface ThreadMonitor {
	void set();
	int get();
	
	void report();
}
