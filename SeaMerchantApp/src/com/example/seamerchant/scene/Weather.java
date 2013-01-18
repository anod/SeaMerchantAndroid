package com.example.seamerchant.scene;

import org.andengine.ui.activity.SimpleBaseGameActivity;

public class Weather extends Splash {
	
	
	private static final String FILE_PATTERN = "gfx/weather_%s%s.png";
	private static final String CALM = "calm";
	private static final String STORM = "storm";
	private static final String ISRAEL = "_israel";
	private static final String EGYPT = "_egypt";
	private static final String TURKEY = "_turkey";
	private String mCurrent = CALM;
	private String mLocation = ""; 

	public Weather(SimpleBaseGameActivity baseActivity) {
		super(baseActivity);
	}

	public void setWeather(int weather,int location) {
		if (weather == com.example.seamerchant.game.Weather.CALM) {
			mCurrent = CALM;
			mLocation = "";
		} else {
			mCurrent = STORM;
			switch (location) {
			case com.example.seamerchant.game.Location.ISRAEL:
				mLocation = ISRAEL;
				break;
			case com.example.seamerchant.game.Location.EGYPT:
				mLocation = EGYPT;
				break;
			case com.example.seamerchant.game.Location.TURKEY:
				mLocation = TURKEY;
				break;
			default:
				mLocation = "";
				break;
			}
		}
		
	}


	@Override
	String getSplashFile(String param) {
		return String.format(FILE_PATTERN, mCurrent,mLocation);
	}


	@Override
	public int getSelectedItem() {
		return 0;
	}

}
