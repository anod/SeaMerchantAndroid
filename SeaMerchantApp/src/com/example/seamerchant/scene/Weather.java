package com.example.seamerchant.scene;

import java.util.Calendar;

import org.andengine.ui.activity.SimpleBaseGameActivity;

public class Weather extends Splash {
	
	
	private static final String FILE_PATTERN = "gfx/weather_%s.png";
	private static final String CALM = "calm";
	private static final String STORM = "storm";


	public Weather(SimpleBaseGameActivity baseActivity) {
		super(baseActivity);
		// TODO Auto-generated constructor stub
	}


	@Override
	String getSplashFile(String param) {
		if(param == STORM){
		return String.format(FILE_PATTERN, STORM);
		}
		return String.format(FILE_PATTERN, CALM);
	}


	@Override
	public int getSelectedItem() {
		// TODO Auto-generated method stub
		return 0;
	}

}
