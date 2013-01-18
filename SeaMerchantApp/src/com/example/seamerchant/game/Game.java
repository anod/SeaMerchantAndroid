package com.example.seamerchant.game;

/**
 * Controls game process, send events about change of the game state
 * @author alex
 *
 */
public class Game {
	private static final int LAST_DAY = 7;
	private int mCurrentDay =1;
	private Player mPlayer;
	private Weather mWeather;
	private Location mLocIsrael;
	private Location mLocTurkey;
	private Location mLocEgypt;

	private OnGameChangeListener mListener;
	
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
	}

	public int getCurrentWeather() {
		return mWeather.getCurrentWeather();
	}
	
	public void setGameChangeListener(OnGameChangeListener listener) {
		mListener = listener;
	}

	public int getCurrentDay() {
		return mCurrentDay;
	}
	
	public void nextDay() {
		if (mCurrentDay == LAST_DAY) {
			finish();
			return;
		}
		mCurrentDay++;
		mWeather.makeWeather();
	}

	private void finish() {
		mListener.onGameFinish();
	}
}
