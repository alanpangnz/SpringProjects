package com.aucklanduni.rmi.common;

import java.io.Serializable;

/**
 * Class to represent the state of a Shape object. Graphic instances are 
 * intended to be passed between clients and servers and so Graphic necessarily
 * implements the Serializable interface.
 */
public class Graphic implements Serializable {
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean isFilled;

	/**
	 * Creates a Graphic object with given values.
	 * @param x the x coordinate of the Graphic.
	 * @param y the y coordinate of the Graphic.
	 * @param width the Graphic's width.
	 * @param height the Graphic's height.
	 * @param isFilled true if the Graphic should be rendered as a solid shape,
	 * false for an outline.
	 */
	public Graphic(int x, int y, int width, int height, boolean isFilled) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.isFilled = isFilled;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public boolean isFilled() {
		return isFilled;
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(getClass().getName());
		buffer.append(":x=");
		buffer.append(x);
		buffer.append(",y=");
		buffer.append(y);
		buffer.append(",width=");
		buffer.append(width);
		buffer.append(",height=");
		buffer.append(height);
		buffer.append(",isFilled=");
		buffer.append(isFilled);
		buffer.append("]");
		return buffer.toString();
	}
}
