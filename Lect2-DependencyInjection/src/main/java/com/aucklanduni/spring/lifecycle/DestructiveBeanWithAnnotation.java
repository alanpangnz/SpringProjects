package com.aucklanduni.spring.lifecycle;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.support.GenericXmlApplicationContext;


public class DestructiveBeanWithAnnotation {

    private InputStream _is = null;

    public String _filePath = null;

    @PostConstruct
    public void afterPropertiesSet() throws Exception {

        System.out.println("Initializing Bean");

        if (_filePath == null) {
            throw new IllegalArgumentException(
                    "You must specify the filePath property of " + DestructiveBean.class);
        }

        _is = new FileInputStream(_filePath);
    }

    @PreDestroy
    public void destroy() {

        System.out.println("Destroying Bean");

        if (_is != null) {
            try {
                _is.close();
                _is = null;
            } catch (IOException ex) {
                System.err.println("WARN: An IOException occured"
                        + " trying to close the InputStream");
            }
        }
    }

    public void setFilePath(String filePath) {
        this._filePath = filePath;
    }

    public static void main(String[] args) throws Exception {
    	
    	GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
    	ctx.load("classpath:lifecycle/disposeAnnotation.xml");
    	ctx.refresh();

    	DestructiveBeanWithAnnotation bean = (DestructiveBeanWithAnnotation) ctx.getBean("destructiveBean");
        
        System.out.println("Calling destroy()");
        ctx.destroy();
        System.out.println("Called destroy()");
    }

}