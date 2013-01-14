package com.example.seamerchant;

import org.andengine.engine.Engine;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.scene.IOnAreaTouchListener;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.controller.BaseTouchController;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.test.mock.MockApplication;
import android.view.MotionEvent;

import com.example.seamerchant.scene.Base;
import com.example.seamerchant.scene.EndDay;
import com.example.seamerchant.scene.GameStart;
import com.example.seamerchant.scene.LowerBanner;
import com.example.seamerchant.scene.NewDay;
import com.example.seamerchant.scene.Options;
import com.example.seamerchant.scene.SideBanner;
import com.example.seamerchant.scene.Weather;
import com.example.seamerchant.scene.Welcome;

public class SceneManager {

	private Engine mEngine;
	private SimpleBaseGameActivity mBaseActivity;
	private Welcome mWelcomeGameScene;
	protected SideBanner mSideBanner;
	protected LowerBanner mLowerBanner;
	private SceneType mCurrentType;
	private int mCurrentDay =1;
	public enum SceneType
	{
		WELCOME,
		GAMESTART,
		NEWDAY,
		WEATHER,
		OPTIONS,
		SELL,
		BUY,
		PIRATES,
		PIRATERESULT,
		NEXTDAY
	}

	public SceneManager(SimpleBaseGameActivity baseActivity) {
		mEngine = baseActivity.getEngine();
		mBaseActivity = baseActivity;
		mWelcomeGameScene = new Welcome(mBaseActivity);
		mSideBanner = new SideBanner(mBaseActivity);
		mLowerBanner = new LowerBanner(mBaseActivity);
	}

	public Scene getWelcomeScene() {
		final Base ws = mWelcomeGameScene;
		final Scene scene = ws.loadScene();
		scene.setOnSceneTouchListener(new IOnSceneTouchListener() {
			
			@Override
			public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
				if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
					setCurrentScene(SceneType.GAMESTART);
					return true;
					}
				return false;
			}
		});
		/*
		mEngine.registerUpdateHandler(new TimerHandler(3f, new ITimerCallback() {
			
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				mEngine.unregisterUpdateHandler(pTimerHandler);
				setCurrentScene(SceneType.GAMESTART);
			}
		}));*/
		
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
		case WEATHER:
			startWeatherScene();
			break;
		case OPTIONS:
			startOptionsScene();
			break;
		case BUY:
			break;
		case PIRATERESULT:
			break;
		case PIRATES:
			break;
		case SELL:
			break;
		case NEXTDAY:
			startNextDayScene();
			break;
		default:
			break;
		}
	}


	private void startNextDayScene() {
		final Base ed = new EndDay(mBaseActivity);
		ed.loadResourcesAndScene();
		mEngine.setScene(ed.getScene());
		Scene curr = ed.getScene();
		curr.setOnSceneTouchListener(new IOnSceneTouchListener() {
			
			@Override
			public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
				if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
					mCurrentDay++;
					setCurrentScene(SceneType.NEWDAY);
					ed.detachAndUnload();
					return true;
					}
				return false;
			}
		});
		
	}

	private void startOptionsScene() {
		final Base op = new Options(mBaseActivity, mSideBanner, mLowerBanner);
		op.loadResourcesAndScene();
		mEngine.setScene(op.getScene());
		Scene curr = op.getScene();
		int b = op.getSelectedItem();
	}

	private void startWeatherScene() {
		final Base wr = new Weather(mBaseActivity);
		wr.loadResourcesAndScene();
		mEngine.setScene(wr.getScene());
		Scene curr = wr.getScene();
		curr.setOnSceneTouchListener(new IOnSceneTouchListener() {
			
			@Override
			public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
				if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
					setCurrentScene(SceneType.OPTIONS);
					wr.detachAndUnload();
					return true;
					}
				return false;
			}
		});
		/*mEngine.registerUpdateHandler(new TimerHandler(3f, new ITimerCallback() {
			
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				mEngine.unregisterUpdateHandler(pTimerHandler);
				setCurrentScene(SceneType.OPTIONS);
				wr.detachAndUnload();
			}
		}));*/
	}

	private void startNewDayScene() {
		final Base nd = new NewDay(mBaseActivity,mCurrentDay);
		nd.loadResourcesAndScene();
		mEngine.setScene(nd.getScene());
		Scene curr = nd.getScene();
		curr.setOnSceneTouchListener(new IOnSceneTouchListener() {
			
			@Override
			public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
				if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
					setCurrentScene(SceneType.WEATHER);
					nd.detachAndUnload();
					return true;
					}
				return false;
			}
		});
		/*mEngine.registerUpdateHandler(new TimerHandler(3f, new ITimerCallback() {
			
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				mEngine.unregisterUpdateHandler(pTimerHandler);
				setCurrentScene(SceneType.WEATHER);
				nd.detachAndUnload();
			}
		}));*/
	}

	protected void startGameScene() {
		final Base gs = new GameStart(mBaseActivity);
		gs.loadResourcesAndScene();
		mEngine.setScene(gs.getScene());
		Scene curr = gs.getScene();
		curr.setOnSceneTouchListener(new IOnSceneTouchListener() {
			
			@Override
			public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
				if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
					setCurrentScene(SceneType.NEWDAY);
					gs.detachAndUnload();
					return true;
					}
				return false;
			}
		});
		
		/*mEngine.registerUpdateHandler(new TimerHandler(3f, new ITimerCallback() {
			
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				mEngine.unregisterUpdateHandler(pTimerHandler);
				setCurrentScene(SceneType.NEWDAY);
				gs.detachAndUnload();
			}
		}));*/
	}

	public void loadRecources() {
		mWelcomeGameScene.loadResources();
	}
}
