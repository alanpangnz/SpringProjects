package com.aucklanduni.spring.aop.introduction;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class CoolFeatureMain {
	private static final Logger _logger = LoggerFactory.getLogger(CoolFeatureMain.class);

	public static void main(String[] args) {
		BasicConfigurator.configure();
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"introduction.xml");

		// Acquire a reference to the first TargetBean and operate on it using
		// methods in both the TargetBean and CoolFeature interfaces. 
		TargetBean bean = (TargetBean) context.getBean("target");
		_logger.info("Bean name: " + bean.getName());
		CoolFeature feature = (CoolFeature) bean;
		feature.doTheCoolThing();

		// Do similarly for the second TargetBean. Notice that each of the 
		// TargetBeans has its own state for the introduced CoolFeature interface.
		TargetBean bean2 = (TargetBean) context.getBean("target2");
		_logger.info("Bean name: " + bean2.getName());
		feature = (CoolFeature) bean2;
		feature.doTheCoolThing();
		feature.doTheCoolThing();
		
		// Demonstrate that the bean has been proxied by a DynamicProxy that implements both the
		// TargetBean and CoolFeature interfaces.
		if(bean2 == feature) {
			Class<? extends CoolFeature> cls = feature.getClass();
			StringBuffer buffer = new StringBuffer();
			buffer.append("The proxy object is an instance of: ");
			buffer.append(cls.getName());
			buffer.append(" ... and this implements the following interfaces: ");
			Class<?>[] interfaces = cls.getInterfaces();
			for(int i = 0; i < interfaces.length; i++) {
				buffer.append(interfaces[i].getCanonicalName() + ", ");
			}
			buffer.delete(buffer.length() - 2, buffer.length() - 1);
			_logger.info(buffer.toString());
		} 
	}
}
