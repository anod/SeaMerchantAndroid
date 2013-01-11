package com.example.seamerchant.scene;

import org.andengine.ui.activity.SimpleBaseGameActivity;


public class GameStart extends Splash {
	private static final String GFX_GAMESTART_PNG = "gfx/gamestart.png";

	public GameStart(SimpleBaseGameActivity baseActivity) {
		super(baseActivity);
	}

	@Override
	String getSplashFile() {
		return GFX_GAMESTART_PNG;
	}

}
