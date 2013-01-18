package com.example.seamerchant.game;

//TODO remove this comment - i don;t understand why this is good 
public class PricedItem extends Item{
	private int price;
	public static final int bronzeMaxPrice = 5000;
	public static final int oliveMaxPrice = 1000;
	public static final int wheatMaxPrice = 100;
	public static final int bronzeMinPrice = 2500;
	public static final int oliveMinPrice = 250;
	public static final int wheatMinPrice = 25;
	
	public PricedItem(int type, int price) {
		super(type);
		this.price = price;
	}

	
	public Integer getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
}
