package com.aucklanduni.spring.labs.shopping;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ShoppingMain {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:shopping.xml");
		
		Store store = (Store)context.getBean("store");
		
		// List the store's products.
		System.out.println(store);
		
		// Acquire references to Products.
		Product product1 = store.getProduct(0);
		Product product2 = store.getProduct(1);
		
		// Create a shopping cart for one user.
		Cart cart1 = (Cart)context.getBean("shoppingCart");
		cart1.setOwner("Sean Connery");
		cart1.addItem(product1);
		
		// Create a shopping cart for a second user.
		Cart cart2 = (Cart)context.getBean("shoppingCart");
		cart2.setOwner("Bob Hoskin");
		cart2.addItem(product1);
		
		if(cart1 == cart2) {
			System.out.println("There is only a single shopping cart that is being shared!");
		} else {
			System.out.println("Each user has its own cart.");
		}
	}
}
