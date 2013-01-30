package com.example.seamerchant.scene;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.DrawType;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import com.example.seamerchant.andengine.AEUtils;
import com.example.seamerchant.game.Game;
import com.example.seamerchant.game.Item;
import com.example.seamerchant.game.Location;
import com.example.seamerchant.game.PricedItem;

public class LowerBanner extends Base {
	private static final int MAX_CHARS = 7;
	private static final float OFFSET_TOP = 343.0f;
	private static final float OFFSET_LEFT = 23.0f;
	
	private TextureRegion mBgTextureRegion;
	private Font mFont;
	private Game mGame;
	private Text mItemEgypt;
	private Text mItemIsrael;
	private Text mItemTurkey;
	private BitmapTextureAtlas mItemTexture;
	private ITiledTextureRegion mLocIsraelTextureRegion;
	private ITiledTextureRegion mLocTurkeyTextureRegion;
	private ITiledTextureRegion mLocEgyptTextureRegion;
	private TiledSprite mTitleTurkey;
	private TiledSprite mTitleIsrael;
	private TiledSprite mTitleEgypt;
	
	public LowerBanner(SimpleBaseGameActivity baseActivity,Game game) {
		super(baseActivity);
		mGame = game;
	}

	@Override
	protected Scene initScene() {
	    final Scene scene = new Scene();
	    final Sprite backgroundSprite = new Sprite(OFFSET_LEFT, OFFSET_TOP, mBgTextureRegion, getVertexBufferObjectManager());
	    scene.attachChild(backgroundSprite);
	    setPriceTexts(scene,Item.BRONZE,37);
	    setPriceTexts(scene,Item.OLIVES,70);
	    setPriceTexts(scene,Item.WHEAT,106);
	    
	    mTitleTurkey = new TiledSprite(629, 349, mLocTurkeyTextureRegion, getVertexBufferObjectManager());
	    scene.attachChild(mTitleTurkey);
	    mTitleIsrael = new TiledSprite(368, 349, mLocIsraelTextureRegion, getVertexBufferObjectManager());
	    scene.attachChild(mTitleIsrael);
	    mTitleEgypt = new TiledSprite(140, 349, mLocEgyptTextureRegion, getVertexBufferObjectManager());
	    scene.attachChild(mTitleEgypt);
	    
	    updateSelectedLocation();
	    return scene;
	}


	private void setPriceTexts(Scene scene, int type,int offsetTop) {
		// there must be a better way to do this.
		// set bronze
		PricedItem item = (PricedItem) mGame.getLocation(Location.EGYPT).getItem(type);
		mItemEgypt = new Text(OFFSET_LEFT + 20, OFFSET_TOP + offsetTop, this.mFont, item.getPrice().toString(), MAX_CHARS, getVertexBufferObjectManager(), DrawType.DYNAMIC);
		scene.attachChild(mItemEgypt);
		item = (PricedItem) mGame.getLocation(Location.ISRAEL).getItem(type);
		mItemIsrael = new Text(OFFSET_LEFT + 279, OFFSET_TOP + offsetTop, this.mFont, item.getPrice().toString(), MAX_CHARS, getVertexBufferObjectManager(), DrawType.DYNAMIC);
		scene.attachChild(mItemIsrael);
		item = (PricedItem) mGame.getLocation(Location.TURKEY).getItem(type);
		mItemTurkey = new Text(OFFSET_LEFT + 553, OFFSET_TOP + offsetTop, this.mFont, item.getPrice().toString(), MAX_CHARS, getVertexBufferObjectManager(), DrawType.DYNAMIC);
		scene.attachChild(mItemTurkey);
	}

	@Override
	protected void unloadResources() {
		mItemTexture.unload();
		mFont.unload();
		mBgTextureRegion.getTexture().unload();
	}

	@Override
	public void loadResources() {
		if (mBgTextureRegion != null) {
			return;//Do not load twice
		}
		
		mBgTextureRegion = AEUtils.createTextureRegionFromAssets("gfx/lowerbg.png", mBaseActivity);
		
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		mItemTexture = new BitmapTextureAtlas(this.getTextureManager(), 128, 256, TextureOptions.BILINEAR);
		mLocTurkeyTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mItemTexture, mBaseActivity, "lower_turkey.png", 0,  0, 1, 2);
		mLocEgyptTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mItemTexture, mBaseActivity, "lower_egypt.png", 0, 56, 1, 2);
		mLocIsraelTextureRegion  = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mItemTexture, mBaseActivity, "lower_israel.png",  0, 112, 1, 2);
		
		mFont = AEUtils.createGameFont(mBaseActivity);
	    mFont.load();
	    mItemTexture.load();
	}

	@Override
	public int getSelectedItem() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void refresh() {
		refreshPrices(Item.BRONZE);
		refreshPrices(Item.OLIVES);
		refreshPrices(Item.WHEAT);
		updateSelectedLocation();
	}

	private void refreshPrices(int type) {
		PricedItem item = (PricedItem) mGame.getLocation(Location.EGYPT).getItem(type);
		mItemEgypt.setText(item.getPrice().toString());
		item = (PricedItem) mGame.getLocation(Location.ISRAEL).getItem(type);
		mItemIsrael.setText(item.getPrice().toString());
		item = (PricedItem) mGame.getLocation(Location.TURKEY).getItem(type);
		mItemTurkey.setText(item.getPrice().toString());
	}

	private void updateSelectedLocation() {
		int loc = mGame.getPlayer().getLocation();
		if (loc == Location.EGYPT) {
			mTitleEgypt.setCurrentTileIndex(1);
			mTitleIsrael.setCurrentTileIndex(0);
			mTitleTurkey.setCurrentTileIndex(0);
		} else if (loc == Location.ISRAEL) {
			mTitleEgypt.setCurrentTileIndex(0);
			mTitleIsrael.setCurrentTileIndex(1);
			mTitleTurkey.setCurrentTileIndex(0);
		} else {
			mTitleEgypt.setCurrentTileIndex(0);
			mTitleIsrael.setCurrentTileIndex(0);
			mTitleTurkey.setCurrentTileIndex(1);
		}
	}
}
