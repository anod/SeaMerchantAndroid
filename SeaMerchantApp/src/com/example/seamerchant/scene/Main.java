package com.example.seamerchant.scene;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.ui.activity.SimpleBaseGameActivity;

abstract public class Main extends Base {
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
		mainScene.setBackground(new Background(0.0f, 0.7098f, 0.05882f));
		
		Scene scene = initSceneImpl();
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
