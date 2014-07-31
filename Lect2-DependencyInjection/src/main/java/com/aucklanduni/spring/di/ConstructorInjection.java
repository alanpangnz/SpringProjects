package com.aucklanduni.spring.di;


public class ConstructorInjection {

    private Dependency _dependency;
    
    public ConstructorInjection(Dependency dependency) {
        _dependency = dependency;
    }	
	
}
