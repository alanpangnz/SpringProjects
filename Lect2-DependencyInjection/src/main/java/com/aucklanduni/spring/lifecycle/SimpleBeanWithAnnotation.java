package com.aucklanduni.spring.lifecycle;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class SimpleBeanWithAnnotation {

    private static final String DEFAULT_NAME = "Luke Skywalker";

    private String _name = null;

    private int _age = Integer.MIN_VALUE;

    public void setName(String name) {
        this._name = name;
    }

    public void setAge(int age) {
        this._age = age;
    }

	@PostConstruct
    public void init() throws Exception {
        System.out.println("Initializing bean");

        if (_name == null) {
            System.out.println("Using default name");
            _name = DEFAULT_NAME;
        }

        if (_age == Integer.MIN_VALUE) {
            throw new IllegalArgumentException(
                    "You must set the age property of any beans of type " + SimpleBean.class);
        }
    }

    public String toString() {
        return "Name: " + _name + "\nAge: " + _age;
    }

    public static void main(String[] args) {
    	GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
    	ctx.load("classpath:lifecycle/initAnnotation.xml");
    	ctx.refresh();

        SimpleBeanWithAnnotation simpleBean1 = getBean("simpleBean1", ctx);
        SimpleBeanWithAnnotation simpleBean2 = getBean("simpleBean2", ctx);
        SimpleBeanWithAnnotation simpleBean3 = getBean("simpleBean3", ctx);
    }

    private static SimpleBeanWithAnnotation getBean(String beanName,
            ApplicationContext ctx) {
        try {
            SimpleBeanWithAnnotation bean = (SimpleBeanWithAnnotation) ctx.getBean(beanName);
            System.out.println(bean);
            return bean;
        } catch (BeanCreationException ex) {
            System.out.println("An error occured in bean configuration: "
                    + ex.getMessage());
            return null;
        }
    }

}