package com.example.seamerchant.scene;

import org.andengine.ui.activity.SimpleBaseGameActivity;

public class Welcome extends Splash {
	private static final String GFX_WELCOME_PNG = "gfx/welcome.png";

	public Welcome(SimpleBaseGameActivity baseActivity) {
		super(baseActivity);
	}

	@Override
	String getSplashFile() {
		return GFX_WELCOME_PNG;
	}

}
