package com.aucklanduni.spring.labs.shopping;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private String _owner;
	private List<Product> _contents;
	
	public Cart() {
		_contents = new ArrayList<Product>();
	}
	
	public void setOwner(String name) {
		_owner = name;
	}
	
	public String getOwner() {
		return _owner;
	}
	
	public void addItem(Product product) {
		_contents.add(product);
	}
}
