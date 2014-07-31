
package com.aucklanduni.spring.aop.introduction.annotation;


public class TargetBeanImpl implements TargetBean {

    private String _name;
    
    public void setName(String name) {
        _name = name;
    }
    
    public String getName() {
        return _name;
    }
}
