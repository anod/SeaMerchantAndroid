package com.example.seamerchant.scene;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.util.Log;

import com.example.seamerchant.andengine.AEUtils;

public class Options extends Main implements OnClickListener {
	public static final int MENU_BUY = 0;
	public static final int MENU_SELL = 1;
	public static final int MENU_TRAVEL = 2;
	public static final int MENU_REST = 3;
    
	private TextureRegion mBgTextureRegion;
	private BitmapTextureAtlas mMenuTexture;
	private ITiledTextureRegion mMenuBuyTextureRegion;
	private ITiledTextureRegion mMenuSellTextureRegion;
	private ITiledTextureRegion mMenuTravelTextureRegion;
	private ITiledTextureRegion mMenuRestTextureRegion;
	private ITextureRegion mNoGoods;
	private int mSelected;
	private Scene mOptionsScene;
	
	public interface OnOptionClickListener {
		void onOptionClick(final Options options, int option);
	}
	
	private OnOptionClickListener mListener;
	
	public Options(SimpleBaseGameActivity baseActivity, SideBanner sideBanner, LowerBanner lowerBanner) {
		super(baseActivity, sideBanner, lowerBanner);
	}

	public void setOnOptionClickListener(OnOptionClickListener listener) {
		mListener = listener;
	}
	
	@Override
	protected Scene initSceneImpl() {
		mOptionsScene = new Scene();
		final Sprite backgroundSprite = new Sprite(0, 0, mBgTextureRegion, getVertexBufferObjectManager());
		mOptionsScene.attachChild(backgroundSprite);

		final ButtonSprite buyMenuItem = new ButtonSprite(313, 85, mMenuBuyTextureRegion, getVertexBufferObjectManager(), this);
		buyMenuItem.setTag(MENU_BUY);
		mOptionsScene.registerTouchArea(buyMenuItem);
		mOptionsScene.attachChild(buyMenuItem);

		final ButtonSprite sellMenuItem = new ButtonSprite(300, 123, mMenuSellTextureRegion, getVertexBufferObjectManager(), this);
		sellMenuItem.setTag(MENU_SELL);
		mOptionsScene.registerTouchArea(sellMenuItem);
		mOptionsScene.attachChild(sellMenuItem);
		
		final ButtonSprite travelMenuItem = new ButtonSprite(297, 159, mMenuTravelTextureRegion, getVertexBufferObjectManager(), this);
		travelMenuItem.setTag(MENU_TRAVEL);
		mOptionsScene.registerTouchArea(travelMenuItem);
		mOptionsScene.attachChild(travelMenuItem);
		
		final ButtonSprite restMenuItem = new ButtonSprite(113, 200, mMenuRestTextureRegion, getVertexBufferObjectManager(),this);
		restMenuItem.setTag(MENU_REST);
		restMenuItem.setOnClickListener(this);
		mOptionsScene.registerTouchArea(restMenuItem);
		mOptionsScene.attachChild(restMenuItem);
		
		mOptionsScene.setTouchAreaBindingOnActionDownEnabled(true);
		//scene.setOnAreaTouchListener(this);
		return mOptionsScene;
	}

	@Override
	protected void loadResourcesImpl() {
		mBgTextureRegion = AEUtils.createTextureRegionFromAssets("gfx/choice.png", mBaseActivity);
		
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		mMenuTexture = new BitmapTextureAtlas(this.getTextureManager(), 1024, 512, TextureOptions.BILINEAR);
		mMenuBuyTextureRegion    = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mMenuTexture, mBaseActivity, "choice_buy.png",    0,   0, 1, 2);
		mMenuSellTextureRegion   = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mMenuTexture, mBaseActivity, "choice_sell.png",   0,  54, 1, 2);
		mMenuTravelTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mMenuTexture, mBaseActivity, "choice_travel.png", 0, 108, 1, 2);
		mMenuRestTextureRegion   = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mMenuTexture, mBaseActivity, "choice_rest.png",   0, 162, 1, 2);

		mNoGoods = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mMenuTexture, mBaseActivity, "msg_no_goods.png", 0, 216);
		
		mBgTextureRegion.getTexture().load();
		mMenuTexture.load();

	}

	@Override
	protected void unloadResources() {
		mBgTextureRegion.getTexture().unload();
	}

	@Override
	public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		int tag = pButtonSprite.getTag();
		mSelected = tag;
		if (tag == MENU_BUY) {
			Log.d("Options", "MENU_BUY pressed");
		} else if (tag == MENU_SELL) {
			Log.d("Options", "MENU_SELL pressed");
		} else if (tag == MENU_TRAVEL) {
			Log.d("Options", "MENU_TRAVEL pressed");
		} else if (tag == MENU_REST) {
			Log.d("Options", "MENU_REST pressed");
		} else {
			return;
		}
		if (mListener != null) {
			mListener.onOptionClick(this, tag);
		}
	}
	
	public void showNoGoodsMessage() {
		final Sprite noGoods = new Sprite(39, 260, mNoGoods, getVertexBufferObjectManager());
		mOptionsScene.attachChild(noGoods);
		mEngine.registerUpdateHandler(new TimerHandler(3f, new ITimerCallback() {
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				noGoods.detachSelf();
			}
		}));
	}
	

	@Override
	public int getSelectedItem() {
		return mSelected;
	}

	
}
