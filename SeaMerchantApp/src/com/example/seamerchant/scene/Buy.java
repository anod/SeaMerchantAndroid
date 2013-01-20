package com.example.seamerchant.scene;

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
import org.andengine.util.debug.Debug;

import com.example.seamerchant.andengine.AEUtils;
import com.example.seamerchant.scene.NumKeyboard.OnNumKeyboardUpdateListener;

public class Buy extends Main implements OnClickListener {
	public static final int ITEM_WHEAT = 0;
	public static final int ITEM_BRONZE = 1;
	public static final int ITEM_OLIVES = 2;

	private TextureRegion mBgTextureRegion;
	private BitmapTextureAtlas mItemTexture;
	private ITiledTextureRegion mItemWheatTextureRegion;
	private ITiledTextureRegion mItemBronzeTextureRegion;
	private ITiledTextureRegion mItemOlivesTextureRegion;
	private ITextureRegion mBuyInputTextureRegion;
	private Scene mItemsScene;
	private NumKeyboard mNumKeyboard;
	private Sprite mBuyInput;
	
	private OnNumKeyboardUpdateListener mNumKeyboardListener = new OnNumKeyboardUpdateListener() {
		
		@Override
		public void onNumberUpdate(int num, NumKeyboard kb) {
			Debug.d("Update - Num: "+num);
		}
		
		@Override
		public void onNumberEnter(int num, NumKeyboard kb) {
			Debug.d("Enter - Num: "+num);
		}
		
		@Override
		public void onNumberEmpty(NumKeyboard kb) {
			Debug.d("Empty - Num: ");

		}
	};
	
	public interface OnBuyItemListener {
		void onBuyItem();
	}
	
	
	public Buy(SimpleBaseGameActivity baseActivity, SideBanner sideBanner, LowerBanner lowerBanner) {
		super(baseActivity, sideBanner, lowerBanner);
		mNumKeyboard = new NumKeyboard(400, 25,baseActivity, mNumKeyboardListener);
	}

	@Override
	protected Scene initSceneImpl() {
		mItemsScene = new Scene();
		final Sprite backgroundSprite = new Sprite(0, 0, mBgTextureRegion, getVertexBufferObjectManager());
		mItemsScene.attachChild(backgroundSprite);

		final ButtonSprite wheatTextItem = new ButtonSprite(427, 205, mItemWheatTextureRegion, getVertexBufferObjectManager(), this);
		wheatTextItem.setTag(ITEM_WHEAT);
		mItemsScene.registerTouchArea(wheatTextItem);
		mItemsScene.attachChild(wheatTextItem);

		final ButtonSprite bronzeTextItem = new ButtonSprite(424, 4, mItemBronzeTextureRegion, getVertexBufferObjectManager(), this);
		bronzeTextItem.setTag(ITEM_BRONZE);
		mItemsScene.registerTouchArea(bronzeTextItem);
		mItemsScene.attachChild(bronzeTextItem);
		
		final ButtonSprite oliveTextItem = new ButtonSprite(18, 4, mItemOlivesTextureRegion, getVertexBufferObjectManager(), this);
		oliveTextItem.setTag(ITEM_OLIVES);
		mItemsScene.registerTouchArea(oliveTextItem);
		mItemsScene.attachChild(oliveTextItem);
		
		mItemsScene.setTouchAreaBindingOnActionDownEnabled(true);
		
		mBuyInput = new Sprite(75, 255, mBuyInputTextureRegion, getVertexBufferObjectManager());
		
		mNumKeyboard.loadScene();
		
		return mItemsScene;
	}

	@Override
	protected void loadResourcesImpl() {
		mBgTextureRegion = AEUtils.createTextureRegionFromAssets("gfx/buy_bg.png", mBaseActivity);
		
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		mItemTexture = new BitmapTextureAtlas(this.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		mItemWheatTextureRegion  = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mItemTexture, mBaseActivity, "buy_item_wheat.png",  0,   0, 1, 2);
		mItemBronzeTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mItemTexture, mBaseActivity, "buy_item_bronze.png", 0,  84, 1, 2);
		mItemOlivesTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mItemTexture, mBaseActivity, "buy_item_olives.png", 0, 168, 1, 2);

		mBuyInputTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mItemTexture, mBaseActivity, "buy_input.png", 0, 276);
		
		mBgTextureRegion.getTexture().load();
		mItemTexture.load();
		mNumKeyboard.loadResources();
	}

	@Override
	protected void unloadResources() {
		mBgTextureRegion.getTexture().unload();
		mItemTexture.unload();
		mNumKeyboard.unloadResources();
	}

	@Override
	public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		//int tag = pButtonSprite.getTag();
		pButtonSprite.setCurrentTileIndex(1);
		mItemsScene.attachChild(mBuyInput);
		mItemsScene.setChildScene(mNumKeyboard.getScene());

	}


}
