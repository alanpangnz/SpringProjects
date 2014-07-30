package com.aucklanduni.spring.labs.shopping;

public class Product {
	private String _name;
	private double _price;
	
	public void setName(String name) {
		_name = name;
	}
	
	public void setPrice(double price) {
		_price = price;
	}
	
	public String getName() {
		return _name;
	}
	
	public double getPrice() {
		return _price;
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Product name: ");
		buffer.append(_name);
		buffer.append(", price: ");
		buffer.append(_price);
		return buffer.toString();
	}
}
