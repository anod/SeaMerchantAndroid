package com.example.seamerchant.game;

import java.util.Random;

public class Weather {
	public static final int CALM = 1;
	public static final int STORM = 2;
	private final double PROABILTY = 0.8; 
	private int mCurrentWeather = CALM;
	
	public int getCurrentWeather() {
		return mCurrentWeather;
	}
	
	public void makeWeather() {
		Random rand = new Random ();
		if(rand.nextDouble() > PROABILTY){
			mCurrentWeather = STORM;
		} else {
			mCurrentWeather = CALM;
		}
	}
	
}
