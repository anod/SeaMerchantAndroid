package com.example.seamerchant;

import org.andengine.engine.Engine;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import com.example.seamerchant.scene.Base;
import com.example.seamerchant.scene.GameStart;
import com.example.seamerchant.scene.Welcome;

public class SceneManager {

	private Engine mEngine;
	private SimpleBaseGameActivity mBaseActivity;
	private Base mWelcomeGameScene;
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
		final Base ws = mWelcomeGameScene;
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
			startNewDayScene();
			break;
		case BUY:
			break;
		case MAIN:
			break;
		case PIRATERESULT:
			break;
		case PIRATES:
			break;
		case SELL:
			break;
		case WEATHER:
			break;
		default:
			break;
		}
	}


	private void startNewDayScene() {
		// TODO Auto-generated method stub
		
	}

	protected void startGameScene() {
		final Base gs = new GameStart(mBaseActivity);
		gs.loadResourcesAndScene();
		mEngine.setScene(gs.getScene());
		mEngine.registerUpdateHandler(new TimerHandler(3f, new ITimerCallback() {
			
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				mEngine.unregisterUpdateHandler(pTimerHandler);
				setCurrentScene(SceneType.NEWDAY);
				gs.detachAndUnload();
			}
		}));
	}

	public void loadRecources() {
		mWelcomeGameScene = new Welcome(mBaseActivity);
		mWelcomeGameScene.loadResources();
	}

}
