package com.example.seamerchant;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.seamerchant.game.Game;

public class MainActivity extends SimpleBaseGameActivity {
	private static int CAMERA_WIDTH = 800;
	private static int CAMERA_HEIGHT = 480;
	
	private SceneManager mSceneManager;
	
	@Override
	protected void onCreate(Bundle pSavedInstanceState) {
		super.onCreate(pSavedInstanceState);
		mSceneManager = new SceneManager(new Game(), this);
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
	@Override
	public void onBackPressed()
	{
		// this overrides the regular action for back which quits the game 
		// and actually effctably turn off the back button
		// TODO handle it in some way.
		new AlertDialog.Builder(this)
			.setIcon(android.R.drawable.ic_dialog_alert)
			.setTitle(R.string.exit_title)
			.setMessage(R.string.exit_message)
			.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					finish();
				}
			})
			.setNegativeButton(R.string.no, null)
			.show();
	}

}
