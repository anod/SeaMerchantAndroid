package com.example.seamerchant;

import org.andengine.engine.Engine;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import com.example.seamerchant.scene.GameScene;
import com.example.seamerchant.scene.GameStartScene;
import com.example.seamerchant.scene.WelcomeScene;

public class SceneManager {

	private Engine mEngine;
	private SimpleBaseGameActivity mBaseActivity;
	private GameScene mWelcomeGameScene;
	private SceneType mCurrentType;
	
	public enum SceneType
	{
		WELCOME,
		GAMESTART,
		NEWDAY,
		WEATHER,
		MAIN,
		SELL,
		BUY,
		PIRATES,
		PIRATERESULT
	}

	public SceneManager(SimpleBaseGameActivity baseActivity) {
		mEngine = baseActivity.getEngine();
		mBaseActivity = baseActivity;
	}

	public Scene getWelcomeScene() {
		final GameScene ws = mWelcomeGameScene;
		final Scene scene = ws.loadScene();
		
		mEngine.registerUpdateHandler(new TimerHandler(3f, new ITimerCallback() {
			
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				mEngine.unregisterUpdateHandler(pTimerHandler);
				setCurrentScene(SceneType.GAMESTART);
			}
		}));
		
		return scene;
	}
	
	//Method allows you to set the currently active scene
	private void setCurrentScene(SceneType scene) {
		mCurrentType = scene;
		switch (scene)
		{
		case WELCOME:
			break;
		case GAMESTART:
			startGameScene();
			mWelcomeGameScene.detachAndUnload();
			break;
		case NEWDAY:
			break;
		}
	}


	protected void startGameScene() {
		final GameScene gs = new GameStartScene(mBaseActivity);
		gs.loadResources();
		gs.loadScene();
		mEngine.setScene(gs.getScene());
	}

	public void loadRecources() {
		mWelcomeGameScene = new WelcomeScene(mBaseActivity);
		mWelcomeGameScene.loadResources();
	}

}
