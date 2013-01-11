package com.example.seamerchant;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.os.Bundle;

public class MainActivity extends SimpleBaseGameActivity {
	private static int CAMERA_WIDTH = 800;
	private static int CAMERA_HEIGHT = 480;
	
	private SceneManager mSceneManager;
	
	@Override
	protected void onCreate(Bundle pSavedInstanceState) {
		super.onCreate(pSavedInstanceState);
		mSceneManager = new SceneManager(this);
	}

	@Override
	public EngineOptions onCreateEngineOptions() {
		final Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new EngineOptions(
			true, ScreenOrientation.LANDSCAPE_FIXED, 
			new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera
		);
	}

	@Override
	protected void onCreateResources() {
		mSceneManager.loadRecources();
	}

	@Override
	protected Scene onCreateScene() {
		
		return mSceneManager.getWelcomeScene();
	}

}
