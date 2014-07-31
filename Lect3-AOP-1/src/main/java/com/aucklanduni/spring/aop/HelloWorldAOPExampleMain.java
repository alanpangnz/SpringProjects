
package com.aucklanduni.spring.aop;

import org.springframework.aop.framework.ProxyFactory;


public class HelloWorldAOPExampleMain {

    public static void main(String[] args) {
        MessageWriter target = new WorldMessageWriter();
        
        // create the proxy
        ProxyFactory pf = new ProxyFactory();
        pf.setInterfaces(new Class[] {MessageWriter.class});
        
        pf.addAdvice(new MessageDecorator());
        pf.setTarget(target);


        MessageWriter proxy = (MessageWriter) pf.getProxy();
        
        System.out.println("Proxy class: " + proxy.getClass().getName());
        
        java.lang.reflect.InvocationHandler handler = ((java.lang.reflect.Proxy)proxy).getInvocationHandler(proxy);
        System.out.println("Handler class: " + handler.getClass().getName());
        
        // write the messages
        target.writeMessage();
        System.out.println("");
        proxy.writeMessage();
    }
}