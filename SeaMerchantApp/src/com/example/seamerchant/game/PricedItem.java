package com.example.seamerchant.game;

import java.util.Random;

//TODO remove this comment - i don;t understand why this is good
// Players items do not have price because he owns them,
// but items in location have price

public class PricedItem extends Item{
	public static final int MAX_PRICE_BRONZE = 5000;
	public static final int MAX_PRICE_OLIVE = 1000;
	public static final int MAX_PRICE_WHEAT = 100;
	public static final int MIN_PRICE_BRONZE = 1500;
	public static final int MIN_PRICE_OLIVE = 250;
	public static final int MIN_PRICE_WHEAT = 25;
	public static final int INC_BRONZE = 100;
	public static final int INC_OLIVE = 10;
	public static final int INC_WHEAT = 5;
	
	private int price;
	public PricedItem(int type, int price) {
		super(type);
		this.price = price;
		this.count = 100;//TODO init counter
	}

	
	public Integer getPrice() {
		return price;
	}
	
	public void setPriceRandom() {
		switch(this.type) {
		case BRONZE:
			this.price = priceRandomiser(PricedItem.MAX_PRICE_BRONZE, PricedItem.MIN_PRICE_BRONZE, PricedItem.INC_BRONZE);
			break;
		case OLIVES:
			this.price = priceRandomiser(PricedItem.MAX_PRICE_OLIVE, PricedItem.MIN_PRICE_OLIVE, PricedItem.INC_OLIVE);
			break;
		case WHEAT:
			this.price = priceRandomiser(PricedItem.MAX_PRICE_WHEAT, PricedItem.MIN_PRICE_WHEAT, PricedItem.INC_WHEAT);
			break;
		default:
			throw new IllegalAccessError("Not valid type");
		}
	}
	
	private int priceRandomiser(int max,int min,int inc){
		Random rand = new Random();
		// i used nextDouble but maybe nextGauessian would be better (if removing the negative values)
		// i think the calculation is like this random(0 to (max-min)/Inc) *Inc + min 
		int randomPrice = rand.nextInt((max-min)/inc) * inc + min;
		if(randomPrice > max)
			return priceRandomiser(max, min, inc);
		return randomPrice;
	}
}
