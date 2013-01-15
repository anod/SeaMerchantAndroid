package com.example.seamerchant.scene;

import org.andengine.ui.activity.SimpleBaseGameActivity;

public class Weather extends Splash {
	
	
	private static final String FILE_PATTERN = "gfx/weather_%s.png";
	private static final String CALM = "calm";
	private static final String STORM = "storm";

	private String mCurrent = CALM;
	

	public Weather(SimpleBaseGameActivity baseActivity) {
		super(baseActivity);
	}
	
	public void setWeather(int weather) {
		if (weather == com.example.seamerchant.game.Weather.CALM) {
			mCurrent = CALM;
		} else {
			mCurrent = STORM;
		}
	}


	@Override
	String getSplashFile(String param) {
		return String.format(FILE_PATTERN, mCurrent);
	}


	@Override
	public int getSelectedItem() {
		return 0;
	}

}
