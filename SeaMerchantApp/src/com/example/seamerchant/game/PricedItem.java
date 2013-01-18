package com.example.seamerchant.game;

public class PricedItem extends Item{
	private int price;
	
	public PricedItem(int type, int price) {
		super(type);
		this.price = price;
	}

	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
}
