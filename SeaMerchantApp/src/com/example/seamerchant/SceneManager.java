package com.example.seamerchant;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import com.example.seamerchant.game.Game;
import com.example.seamerchant.game.Game.OnGameChangeListener;
import com.example.seamerchant.scene.Base;
import com.example.seamerchant.scene.EndDay;
import com.example.seamerchant.scene.GameStart;
import com.example.seamerchant.scene.LowerBanner;
import com.example.seamerchant.scene.NewDay;
import com.example.seamerchant.scene.Options;
import com.example.seamerchant.scene.Options.OnOptionClickListener;
import com.example.seamerchant.scene.SideBanner;
import com.example.seamerchant.scene.Weather;
import com.example.seamerchant.scene.Welcome;

public class SceneManager implements OnOptionClickListener, OnGameChangeListener {

	private Engine mEngine;
	private SimpleBaseGameActivity mBaseActivity;
	private Welcome mWelcomeGameScene;
	protected SideBanner mSideBanner;
	protected LowerBanner mLowerBanner;
	private SceneType mCurrentType;
	private Game mGame;

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

	public SceneManager(Game game, SimpleBaseGameActivity baseActivity) {
		mEngine = baseActivity.getEngine();
		mBaseActivity = baseActivity;
		mWelcomeGameScene = new Welcome(mBaseActivity);
		mSideBanner = new SideBanner(mBaseActivity);
		mLowerBanner = new LowerBanner(mBaseActivity);
		mGame = game;
		mGame.setGameChangeListener(this);
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
					mGame.nextDay();
					setCurrentScene(SceneType.NEWDAY);
					ed.detachAndUnload();
					return true;
					}
				return false;
			}
		});
		
	}

	private void startOptionsScene() {
		final Options op = new Options(mBaseActivity, mSideBanner, mLowerBanner);
		op.loadResourcesAndScene();
		op.setOnOptionClickListener(this);
		mEngine.setScene(op.getScene());
	}

	private void startWeatherScene() {
		final Weather wr = new Weather(mBaseActivity);
		wr.loadResourcesAndScene();
		wr.setWeather(mGame.getCurrentWeather());
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
	}

	private void startNewDayScene() {
		final Base nd = new NewDay(mBaseActivity,mGame.getCurrentDay());
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

	}

	public void loadRecources() {
		mWelcomeGameScene.loadResources();
	}

	@Override
	public void onOptionClick(int option) {
		//TODO
		//Change game state
		//Change scene
	}

	@Override
	public void onPiratesAttack() {
		// TODO Auto-generated method stub
		
	}
}
