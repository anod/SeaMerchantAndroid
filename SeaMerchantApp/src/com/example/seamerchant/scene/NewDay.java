package com.example.seamerchant.scene;

import org.andengine.ui.activity.SimpleBaseGameActivity;

public class NewDay extends Splash {

	private static final String FILE_PATTERN = "gfx/newday_%d.png";
	private int daynumber;
	public NewDay(SimpleBaseGameActivity baseActivity,int day) {
		super(baseActivity);
		daynumber = day;
	}

	@Override
	String getSplashFile(String param) {
		return String.format(FILE_PATTERN, daynumber);
	}

	@Override
	public int getSelectedItem() {
		// TODO Auto-generated method stub
		return 0;
	}

}
