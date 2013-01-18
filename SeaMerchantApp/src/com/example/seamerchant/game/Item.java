package com.example.seamerchant.game;

public class Item {

	protected int type;
	protected int count;
	public static final int WHEAT = 1;
	public static final int OLIVES = 2;
	public static final int BRONZE = 3;
	

	public Item(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}


	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}

}
