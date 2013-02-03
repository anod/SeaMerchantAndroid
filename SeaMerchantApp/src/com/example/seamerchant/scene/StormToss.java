package com.example.seamerchant.scene;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import com.example.seamerchant.andengine.AEUtils;
import com.example.seamerchant.game.Game;
import com.example.seamerchant.game.Item;

public class StormToss extends Main {

	private Game mGame;
	private TextureRegion mBgTextureRegion;
	private BitmapTextureAtlas mItemTexture;
	private ITiledTextureRegion mLocTextureRegion;
	private Font mFont;
	private Scene mTossScene;
	private Text mAmountText;
	private ITextureRegion mMessageBox;

	public StormToss(SimpleBaseGameActivity baseActivity,
			SideBanner sideBanner, LowerBanner lowerBanner, Game game) {
		super(baseActivity, sideBanner, lowerBanner);
		mGame = game;
	}

	@Override
	protected Scene initSceneImpl() {
		mTossScene = new Scene();
		final Sprite backgroundSprite = new Sprite(0, 0, mBgTextureRegion, getVertexBufferObjectManager());
		mTossScene.attachChild(backgroundSprite);
		
		Item item =  mGame.getPlayer().getItemFromStorage();
		if(item == null) {
			return mTossScene; // should never enter here.
		}
		final Sprite message = new Sprite(5, 20, mMessageBox, getVertexBufferObjectManager());
		mTossScene.attachChild(message);
		int amount = mGame.itemLost(item.getType(),item.getCount());
		mAmountText = new Text(270,43 , mFont, String.valueOf(amount), getVertexBufferObjectManager());
		mTossScene.attachChild(mAmountText);
		TiledSprite itemKind = new TiledSprite(95, 43, mLocTextureRegion, getVertexBufferObjectManager());
		itemKind.setCurrentTileIndex(item.getType()-1);
		mTossScene.attachChild(itemKind);
		return mTossScene;
	}
	@Override
	protected void loadResourcesImpl() {
		mBgTextureRegion = AEUtils.createTextureRegionFromAssets("gfx/storm_flush_bg.png", mBaseActivity);
		mFont = AEUtils.createGameFont(mBaseActivity);
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		mItemTexture = new BitmapTextureAtlas(this.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
		mLocTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mItemTexture, mBaseActivity, "item_names.png",  0,   0, 1, 3);
		mMessageBox = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mItemTexture, mBaseActivity, "msg_storm_flush.png", 0, 80);
		mBgTextureRegion.getTexture().load();
		mItemTexture.load();
		mFont.load();
	}

	@Override
	protected void unloadResources() {
		mBgTextureRegion.getTexture().unload();
		mItemTexture.unload();
		mFont.unload();
	}

}
