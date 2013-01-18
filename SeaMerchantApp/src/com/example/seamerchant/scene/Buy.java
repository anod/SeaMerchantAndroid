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

import com.example.seamerchant.andengine.AEUtils;

public class Buy extends Main implements OnClickListener {
	private TextureRegion mBgTextureRegion;
	private BitmapTextureAtlas mItemTexture;
	private ITiledTextureRegion mItemWheatTextureRegion;
	private ITiledTextureRegion mItemBronzeTextureRegion;
	private ITiledTextureRegion mItemOlivesTextureRegion;
	private Scene mItemsScene;
	
	public interface OnBuyItemListener {
		void onBuyItem();
	}
	
	public Buy(SimpleBaseGameActivity baseActivity, SideBanner sideBanner, LowerBanner lowerBanner) {
		super(baseActivity, sideBanner, lowerBanner);
	}

	@Override
	protected Scene initSceneImpl() {
		mItemsScene = new Scene();
		final Sprite backgroundSprite = new Sprite(0, 0, mBgTextureRegion, getVertexBufferObjectManager());
		mItemsScene.attachChild(backgroundSprite);

		final ButtonSprite buyMenuItem = new ButtonSprite(427, 205, mItemWheatTextureRegion, getVertexBufferObjectManager(), this);
		//buyMenuItem.setTag(MENU_BUY);
		mItemsScene.registerTouchArea(buyMenuItem);
		mItemsScene.attachChild(buyMenuItem);

		final ButtonSprite sellMenuItem = new ButtonSprite(424, 4, mItemBronzeTextureRegion, getVertexBufferObjectManager(), this);
		//sellMenuItem.setTag(MENU_SELL);
		mItemsScene.registerTouchArea(sellMenuItem);
		mItemsScene.attachChild(sellMenuItem);
		
		final ButtonSprite travelMenuItem = new ButtonSprite(18, 4, mItemOlivesTextureRegion, getVertexBufferObjectManager(), this);
		//travelMenuItem.setTag(MENU_TRAVEL);
		mItemsScene.registerTouchArea(travelMenuItem);
		mItemsScene.attachChild(travelMenuItem);
		
		mItemsScene.setTouchAreaBindingOnActionDownEnabled(true);
		//scene.setOnAreaTouchListener(this);
		return mItemsScene;
	}

	@Override
	protected void loadResourcesImpl() {
		mBgTextureRegion = AEUtils.createTextureRegionFromAssets("gfx/buy_bg.png", mBaseActivity);
		
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		mItemTexture = new BitmapTextureAtlas(this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
		mItemWheatTextureRegion  = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mItemTexture, mBaseActivity, "buy_item_wheat.png",    0,   0, 1, 2);
		mItemBronzeTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mItemTexture, mBaseActivity, "buy_item_bronze.png",   0,  84, 1, 2);
		mItemOlivesTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mItemTexture, mBaseActivity, "buy_item_olives.png", 0, 168, 1, 2);

		mBgTextureRegion.getTexture().load();
		mItemTexture.load();
	}

	@Override
	protected void unloadResources() {
		mBgTextureRegion.getTexture().unload();
		mItemTexture.unload();
	}

	@Override
	public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		// TODO Auto-generated method stub
		
	}


}
