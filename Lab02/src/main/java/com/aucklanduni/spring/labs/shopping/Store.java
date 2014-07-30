package com.aucklanduni.spring.labs.shopping;

import java.util.ArrayList;
import java.util.List;


public class Store {
	private List<Product> _products;
	
	public Store(List<Product> products) {
		_products = new ArrayList<Product>(products);
	}
	
	public void addProduct(Product product) {
		_products.add(product);
	}
	
	public Product getProduct(int index) {
		return _products.get(index);
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		
		for(Product product : _products) {
			buffer.append(product);
			buffer.append("\n");
		}
		return buffer.toString();
	}
}
