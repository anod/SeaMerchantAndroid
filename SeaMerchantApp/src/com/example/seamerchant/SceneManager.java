package com.example.seamerchant;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.os.storage.StorageManager;

import com.example.seamerchant.game.Game;
import com.example.seamerchant.game.Game.OnGameChangeListener;
import com.example.seamerchant.scene.Base;
import com.example.seamerchant.scene.Base.OnActionDownListener;
import com.example.seamerchant.scene.Buy;
import com.example.seamerchant.scene.EndDay;
import com.example.seamerchant.scene.GameStart;
import com.example.seamerchant.scene.LowerBanner;
import com.example.seamerchant.scene.NewDay;
import com.example.seamerchant.scene.Options;
import com.example.seamerchant.scene.Options.OnOptionClickListener;
import com.example.seamerchant.scene.Rest;
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
		NEXTDAY,
		REST
	}

	public SceneManager(Game game, SimpleBaseGameActivity baseActivity) {
		mEngine = baseActivity.getEngine();
		mBaseActivity = baseActivity;
		mWelcomeGameScene = new Welcome(mBaseActivity);
		mGame = game;
		mGame.setGameChangeListener(this);
		mSideBanner = new SideBanner(mBaseActivity,mGame);
		mLowerBanner = new LowerBanner(mBaseActivity,mGame);
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
			startBuyScene();
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
		case REST:
			startRestScene();
			break;
		default:
			break;
		}
	}


	private void startBuyScene() {
		final Buy buy = new Buy(mBaseActivity, mSideBanner, mLowerBanner);
		buy.loadResourcesAndScene();
		mEngine.setScene(buy.getScene());
	}

	private void startRestScene() {
		final Rest rt = new Rest(mBaseActivity, mSideBanner, mLowerBanner);
		rt.loadResourcesAndScene();
		mEngine.setScene(rt.getScene());
		rt.setOnActionDown(new OnActionDownListener() {
			@Override
			public void onAcionDown(Base base) {
				mGame.nextDay();
				setCurrentScene(SceneType.NEWDAY);
				rt.detachAndUnload();
			}
		});
		
	}

	private void startNextDayScene() {
		final Base ed = new EndDay(mBaseActivity);
		ed.loadResourcesAndScene();
		mEngine.setScene(ed.getScene());
		ed.setOnActionDown(new OnActionDownListener() {
			@Override
			public void onAcionDown(Base base) {
				mGame.nextDay();
				setCurrentScene(SceneType.NEWDAY);
				ed.detachAndUnload();
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
		wr.setWeather(mGame.getCurrentWeather(mGame.StormyWeather),mGame.StormyWeather);
		wr.loadResourcesAndScene();
		mEngine.setScene(wr.getScene());
		wr.setOnActionDown(new OnActionDownListener() {
			@Override
			public void onAcionDown(Base base) {
				setCurrentScene(SceneType.OPTIONS);
				wr.detachAndUnload();
			}
		});
	}

	private void startNewDayScene() {
		final Base nd = new NewDay(mBaseActivity,mGame.getCurrentDay());
		nd.loadResourcesAndScene();
		mEngine.setScene(nd.getScene());
		nd.setOnActionDown(new OnActionDownListener() {
			@Override
			public void onAcionDown(Base base) {
				setCurrentScene(SceneType.WEATHER);
				nd.detachAndUnload();
			}
		});
	}

	protected void startGameScene() {
		final Base gs = new GameStart(mBaseActivity);
		gs.loadResourcesAndScene();
		mEngine.setScene(gs.getScene());
		gs.setOnActionDown(new OnActionDownListener() {
			@Override
			public void onAcionDown(Base base) {
				setCurrentScene(SceneType.NEWDAY);
				gs.detachAndUnload();
			}
		});
	}

	public void loadRecources() {
		mWelcomeGameScene.loadResources();
	}

	@Override
	public void onOptionClick(final Options options, int option) {
		switch (option) {
		case Options.MENU_REST:
			setCurrentScene(SceneType.REST);
			options.detachAndUnload();
			break;
		case Options.MENU_BUY:
			setCurrentScene(SceneType.BUY);
			options.detachAndUnload();
			break;
		case Options.MENU_SELL:
			if (!mGame.getPlayer().hasGoods()) {
				options.showNoGoodsMessage();
			} else {
				setCurrentScene(SceneType.SELL);
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void onPiratesAttack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameFinish() {
		// TODO Auto-generated method stub
		
	}
}
