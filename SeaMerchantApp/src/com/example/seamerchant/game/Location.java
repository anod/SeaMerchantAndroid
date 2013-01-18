package com.example.seamerchant.game;

import java.util.Random;

public class Location {
	
	public static final int ISRAEL = 1;
	public static final int EGYPT = 2;
	public static final int TURKEY = 3;
	
	private int mType;
	private Item wheat;
	private Item olives;
	private Item bronze;
	private Weather weather;
	public Location(int type) {
		mType = type;
		weather = new Weather();
		bronze = new PricedItem(0, Item.BRONZE);
		olives = new PricedItem(0,Item.OLIVES);
		wheat = new PricedItem(0,Item.WHEAT);
	}

	public Item getWheat() {
		return wheat;
	}

	public void setWheat(Item wheat) {
		this.wheat = wheat;
	}

	public Item getOlives() {
		return olives;
	}

	public void setOlives(Item olives) {
		this.olives = olives;
	}
	
	public Item getBronze() {
		return bronze;
	}

	public void setBronze(Item bronze) {
		this.bronze = bronze;
	}
	public Item getItem(int type){
		switch (type) {
		case Item.BRONZE:
			return bronze;
		case Item.OLIVES:
			return olives;
		case Item.WHEAT:
			return wheat;
		}
		return null;
	}
	public void setItem(int type){
		// TODO will implmant if there would be need
	}
	public void setPrices() {
		 
		((PricedItem)bronze).setPrice(priceRandomiser(PricedItem.bronzeMaxPrice, PricedItem.bronzeMinPrice, PricedItem.bronzeInc));
		((PricedItem)olives).setPrice(priceRandomiser(PricedItem.oliveMaxPrice, PricedItem.oliveMinPrice, PricedItem.oliveInc));
		((PricedItem)wheat).setPrice(priceRandomiser(PricedItem.wheatMaxPrice, PricedItem.wheatMinPrice, PricedItem.wheatInc));
	}
	public void makeWeather(){
		weather.makeWeather();
	}

	public int getWeather() {
		return weather.getCurrentWeather();
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
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
