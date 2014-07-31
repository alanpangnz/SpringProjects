package com.aucklanduni.spring.aop.knights;

public class QuestException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  
  public QuestException() {
	  super();
  }
  
  public QuestException(String message) {
	  super(message);
  }
}
