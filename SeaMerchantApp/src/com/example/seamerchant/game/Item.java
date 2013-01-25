package com.example.seamerchant.game;

public class Item {

	protected int type;
	protected int count;
	public static final int WHEAT = 1;
	public static final int OLIVES = 2;
	public static final int BRONZE = 3;
	

	public Item(int type) {
		this.type = type;
		count = 0;
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

	public void reduceCount(int decCount) {
		this.count = this.count - decCount;
		if (this.count < 0) {
			throw new IllegalArgumentException("Count cannot be negative");
		}
	}

	public void increaseCount(int incCount) {
		this.count += incCount;
	}

}
