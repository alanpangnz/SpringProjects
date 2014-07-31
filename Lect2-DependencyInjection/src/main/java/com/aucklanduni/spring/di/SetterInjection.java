package com.aucklanduni.spring.di;


public class SetterInjection {

    private Dependency _dependency;
    
    public void setDependency(Dependency dependency) {
        this._dependency = dependency;
    }
}
