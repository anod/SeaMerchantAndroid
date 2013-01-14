package com.example.seamerchant.scene;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.graphics.Typeface;

abstract public class Main extends Base {
	private static final float OFFSET_LEFT = 16.0f;
	protected SideBanner mSideBanner;
	protected LowerBanner mLowerBanner;
	
	
	public Main(SimpleBaseGameActivity baseActivity, SideBanner sideBanner, LowerBanner lowerBanner) {
		super(baseActivity);
		mSideBanner = sideBanner; 
		mLowerBanner = lowerBanner;
	}

	@Override
	protected Scene initScene() {

		final Scene mainScene = new Scene();
		mainScene.setBackground(new Background(0.34901960784314f, 0.67058823529412f, 0.05490196078431f));
		
		Scene scene = initSceneImpl();
		scene.setPosition(OFFSET_LEFT, 0);
		Scene sideScene = mSideBanner.initScene();
		Scene lowerScene = mLowerBanner.initScene();
	    scene.setBackgroundEnabled(false);
	    sideScene.setBackgroundEnabled(false);
	    lowerScene.setBackgroundEnabled(false);

		mainScene.attachChild(scene);
		scene.attachChild(sideScene);
		scene.attachChild(lowerScene);
		return mainScene;
	}


	@Override
	public void loadResources() {

		mSideBanner.loadResources();
		mLowerBanner.loadResources();
		loadResourcesImpl();
	}

	abstract protected Scene initSceneImpl();
	abstract protected void loadResourcesImpl();
}
