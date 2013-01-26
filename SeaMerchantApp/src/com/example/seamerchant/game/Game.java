package com.example.seamerchant.game;

import java.util.Random;

/**
 * Controls game process, send events about change of the game state
 * @author alex
 *
 */
public class Game {
	private static final int LAST_DAY = 7;
	private static final int MAX_TRAVAL = 3;
	public static final int LONG_TRAVEL = 2;
	public static final int SHORT_TRAVEL = 1;
	private int mCurrentDay = 0;
	private Player mPlayer;
	private Location mLocIsrael;
	private Location mLocTurkey;
	private Location mLocEgypt;
	private Weather mWeather;
	private int mStormyWeather = 0;
	private OnGameChangeListener mListener;
	private int mTravelTimes;
	
	
	public interface OnGameChangeListener {
		void onPiratesAttack();
		void onGameFinish();
		//TODO: more
		
	}
	
	public Game() {
		mPlayer = new Player(0, Location.ISRAEL);
		mLocIsrael = new Location(Location.ISRAEL);
		mLocTurkey = new Location(Location.TURKEY);
		mLocEgypt = new Location(Location.EGYPT);
		mWeather = new Weather();
		init();
	}
	
	private void init() {
		//setWeather();
		//setPrices();
	}

	public Player getPlayer() {
		return mPlayer;
	}
	
	public int getCurrentWeather(int locationID) {
		switch (locationID) {
			case Location.ISRAEL:
				return mLocIsrael.getWeather();
			case Location.EGYPT:
				return mLocEgypt.getWeather();
			case Location.TURKEY:
				return mLocTurkey.getWeather();
			default:
				break;
		}
		return Weather.CALM;
	}
	
	/**
	 * TODO: Check what it means.
	 * ?
	 */
	private void setWeather()
	{
		mWeather.makeWeather();
		mLocIsrael.setWeather(new Weather());
		mLocEgypt.setWeather(new Weather());
		mLocTurkey.setWeather(new Weather());
		if (mWeather.getCurrentWeather() == Weather.STORM) {
			Random rand = new Random();
			switch (rand.nextInt(3)) {
			case 0:
				mLocIsrael.setWeather(mWeather);
				setStormyWeather(Location.ISRAEL);
				break;
			case 1:
				mLocEgypt.setWeather(mWeather);
				setStormyWeather(Location.EGYPT);
				break;
			case 2:
				mLocTurkey.setWeather(mWeather);
				setStormyWeather(Location.TURKEY);
				break;
			default:
				setStormyWeather(0);
				break;
			}
		}else {
			setStormyWeather(0);
		}
	}
	public void setGameChangeListener(OnGameChangeListener listener) {
		mListener = listener;
	}

	public int getCurrentDay() {
		return mCurrentDay;
	}
	
	public Location getCurrentLocation() {
		return getLocation(mPlayer.getLocation());
	}
	
	public void nextDay() {
		if (mCurrentDay == LAST_DAY) {
			finish();
			return;
		}
		mCurrentDay++;
		setWeather();
		setPrices();
		mTravelTimes = 0;
	}

	private void setPrices() {
		mLocIsrael.setPrices();
		mLocEgypt.setPrices();
		mLocTurkey.setPrices();
	}

	private void finish() {
		mListener.onGameFinish();
	}

	public Location getLocation(int location) {
		switch (location) {
		case Location.ISRAEL:
			return mLocIsrael;
		case Location.EGYPT:
			return mLocEgypt;
		case Location.TURKEY:
			return mLocTurkey;
		default:
			break;
		}
		return null;
	}

	public int getStormyWeather() {
		return mStormyWeather;
	}

	public void setStormyWeather(int mStormyWeather) {
		this.mStormyWeather = mStormyWeather;
	}

	public void buyItem(PricedItem item, int count) {
		int cost = item.getPrice() * count;
		item.reduceCount(count);
		Item playerItem = mPlayer.getItem(item.getType());
		playerItem.increaseCount(count);
		mPlayer.reduceMoney(cost);
		
	} 
	public void sellItem(PricedItem item, int count)
	{
		int cost = item.getPrice() * count;
		Item playerItem = mPlayer.getItem(item.getType());
		item.increaseCount(count);
		playerItem.reduceCount(count);
		// Basically i can simply use reduce money with negative values.
		mPlayer.increaseMoney(cost);
	}

	public boolean canTravel(){
		if(mTravelTimes < MAX_TRAVAL)
			return true;
		return false;
	}
	public void travel(int travelType)
	{
		mTravelTimes += travelType;
	}
}
