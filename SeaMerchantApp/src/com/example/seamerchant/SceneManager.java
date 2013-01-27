package com.example.seamerchant;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import com.example.seamerchant.game.Game;
import com.example.seamerchant.game.Game.OnGameChangeListener;
import com.example.seamerchant.game.PricedItem;
import com.example.seamerchant.scene.Base;
import com.example.seamerchant.scene.Base.OnActionDownListener;
import com.example.seamerchant.scene.Buy;
import com.example.seamerchant.scene.Buy.OnBuyItemListener;
import com.example.seamerchant.scene.EndDay;
import com.example.seamerchant.scene.GameStart;
import com.example.seamerchant.scene.LowerBanner;
import com.example.seamerchant.scene.NewDay;
import com.example.seamerchant.scene.Options;
import com.example.seamerchant.scene.Options.OnOptionClickListener;
import com.example.seamerchant.scene.Rest;
import com.example.seamerchant.scene.Sell;
import com.example.seamerchant.scene.Sell.OnSellItemListener;
import com.example.seamerchant.scene.SideBanner;
import com.example.seamerchant.scene.Travel;
import com.example.seamerchant.scene.Weather;
import com.example.seamerchant.scene.Welcome;

public class SceneManager implements OnOptionClickListener, OnGameChangeListener, OnBuyItemListener, OnSellItemListener {

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
		REST,
		TRAVEL
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
			startSellScene();
			break;
		case NEXTDAY:
			startNextDayScene();
			break;
		case REST:
			startRestScene();
			break;
		case TRAVEL:
			startTravelScene();
		default:
			break;
		}
	}


	private void startTravelScene() {
		final Travel tl = new Travel(mBaseActivity, mSideBanner, mLowerBanner, mGame);
		tl.loadResourcesAndScene();
		mEngine.setScene(tl.getScene());
	}

	private void startBuyScene() {
		final Buy buy = new Buy(mBaseActivity, mSideBanner, mLowerBanner, mGame, this);
		buy.loadResourcesAndScene();
		mEngine.setScene(buy.getScene());
	}
	private void startSellScene() {
		final Sell sell = new Sell(mBaseActivity, mSideBanner, mLowerBanner, mGame, this);
		sell.loadResourcesAndScene();
		mEngine.setScene(sell.getScene());
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
		mSideBanner.refresh();
		mLowerBanner.refresh();
	}

	private void startWeatherScene() {
		final Weather wr = new Weather(mBaseActivity);
		wr.setWeather(mGame.getCurrentWeather(mGame.getStormyWeather()),mGame.getStormyWeather());
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
		mGame.nextDay();
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
			// TODO consider adding a message for buying if you have no money
			setCurrentScene(SceneType.BUY);
			options.detachAndUnload();
			break;
		case Options.MENU_SELL:
			if (!mGame.getPlayer().hasGoods()) {
				options.showNoGoodsMessage();
			} else {
				setCurrentScene(SceneType.SELL);
				options.detachAndUnload();
			}
			break;
		case Options.MENU_TRAVEL:
			if(!mGame.canTravel()){
				options.showCantTravelMessage();
			}
			else{
			setCurrentScene(SceneType.TRAVEL);
			options.detachAndUnload();
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

	@Override
	public void onBuyItem(PricedItem item, int count, Buy buy) {
		mGame.buyItem(item, count);
		setCurrentScene(SceneType.OPTIONS);
		buy.detachAndUnload();
	}

	@Override
	public void onSellItem(PricedItem item, int count, Sell sell) {
		mGame.sellItem(item, count);
		setCurrentScene(SceneType.OPTIONS);
		sell.detachAndUnload();	}
	}
