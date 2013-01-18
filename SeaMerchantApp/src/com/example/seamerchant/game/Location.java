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
		Random rand = new Random();
		// i used nextDouble but maybe nextGauessian would be better (if removing the negative values)
		int randomPrice = (int)(rand.nextDouble() * PricedItem.bronzeMaxPrice);
		randomPrice = (randomPrice < PricedItem.bronzeMinPrice) ? PricedItem.bronzeMinPrice : randomPrice;
		((PricedItem)bronze).setPrice(randomPrice);
		randomPrice = (int)(rand.nextDouble() * PricedItem.oliveMaxPrice);
		randomPrice = (randomPrice < PricedItem.oliveMinPrice) ? PricedItem.oliveMinPrice : randomPrice;
		((PricedItem)olives).setPrice(randomPrice);
		randomPrice = (int)(rand.nextDouble() * PricedItem.wheatMaxPrice);
		randomPrice = (randomPrice < PricedItem.wheatMinPrice) ? PricedItem.wheatMinPrice : randomPrice;
		((PricedItem)wheat).setPrice(randomPrice);
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
}
