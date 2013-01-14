package com.example.seamerchant.scene;

import org.andengine.ui.activity.SimpleBaseGameActivity;

public class EndDay extends Splash {

	private static final String FILE_PATTERN = "gfx/end_day.png";

	public EndDay(SimpleBaseGameActivity baseActivity) {
		super(baseActivity);
	}

	@Override
	String getSplashFile(String param) {
		return String.format(FILE_PATTERN);
	}

	@Override
	public int getSelectedItem() {
		// TODO Auto-generated method stub
		return 0;
	}

}
