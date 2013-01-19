package com.example.seamerchant.game;

import java.util.Random;

public class Location {
	
	public static final int ISRAEL = 1;
	public static final int EGYPT = 2;
	public static final int TURKEY = 3;
	
	private int mType;
	private PricedItem wheat;
	private PricedItem olives;
	private PricedItem bronze;
	private Weather weather;
	public Location(int type) {
		mType = type;
		weather = new Weather();
		bronze = new PricedItem(Item.BRONZE,0);
		olives = new PricedItem(Item.OLIVES,0);
		wheat = new PricedItem(Item.WHEAT,0);
	}

	public Item getWheat() {
		return wheat;
	}

	public Item getOlives() {
		return olives;
	}

	public Item getBronze() {
		return bronze;
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
	
	public void setPrices() {	 
		bronze.setPriceRandom();
		olives.setPriceRandom();
		wheat.setPriceRandom();
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
