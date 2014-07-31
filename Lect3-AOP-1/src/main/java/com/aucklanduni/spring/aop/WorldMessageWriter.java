
package com.aucklanduni.spring.aop;


public class WorldMessageWriter implements MessageWriter {

    public void writeMessage() {
        System.out.print("World");
    }

}