package com.example.seamerchant.game;

public class Item {

	private int type;
	private int price;
	public static final int WHEAT = 1;
	public static final int OLIVES = 2;
	public static final int BRONZE = 3;
	
	public Item(int type, int price) {
		super();
		this.type = type;
		this.price = price;
	}

	public int getType() {
		return type;
	}

	public int getPrice() {
		return price;
	}
	
	
}
