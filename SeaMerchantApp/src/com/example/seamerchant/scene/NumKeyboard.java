package com.example.seamerchant.scene;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.font.Font;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import com.example.seamerchant.andengine.AEUtils;

public class NumKeyboard extends Base {
	private Font mFont;
	
	public NumKeyboard(SimpleBaseGameActivity baseActivity) {
		super(baseActivity);
	}

	@Override
	protected Scene initScene() {
		final HUD scene = new HUD();
		
		
		return scene;
	}

	@Override
	protected void unloadResources() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadResources() {
		mFont = AEUtils.createGameFont(mBaseActivity);
	    mFont.load();
	}

	@Override
	public int getSelectedItem() {
		// TODO Auto-generated method stub
		return 0;
	}

}
