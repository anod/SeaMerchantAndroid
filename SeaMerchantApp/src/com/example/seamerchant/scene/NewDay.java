package com.example.seamerchant.scene;

import org.andengine.ui.activity.SimpleBaseGameActivity;

public class NewDay extends Splash {

	private static final String FILE_PATTERN = "gfx/newday_%d.png";

	public NewDay(SimpleBaseGameActivity baseActivity) {
		super(baseActivity);
		// TODO Auto-generated constructor stub
	}

	@Override
	String getSplashFile() {
		return String.format(FILE_PATTERN, 1);
	}

}
