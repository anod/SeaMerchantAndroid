package com.example.seamerchant.game;

public class Weather {
	public static final int CALM = 1;
	public static final int STORM = 2;
	
	private int mCurrentWeather = CALM;
	
	public int getCurrentWeather() {
		return mCurrentWeather;
	}
	
	public void makeWeather() {
		//TODO random
		if (mCurrentWeather == CALM) {
			mCurrentWeather = STORM;
		} else {
			mCurrentWeather = CALM;
		}
	}
	
}
