package com.example.seamerchant.scene;

import org.andengine.entity.scene.Scene;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import com.example.seamerchant.andengine.AEUtils;
import com.example.seamerchant.game.Game;

public class StormToss extends Main {

	private Game mGame;
	private TextureRegion mBgTextureRegion;
	private BitmapTextureAtlas mItemTexture;
	private ITiledTextureRegion mLocTextureRegion;

	public StormToss(SimpleBaseGameActivity baseActivity,
			SideBanner sideBanner, LowerBanner lowerBanner, Game game) {
		super(baseActivity, sideBanner, lowerBanner);
		mGame = game;
	}

	@Override
	protected Scene initSceneImpl() {
		final Scene scene = new Scene();
		return scene;
	}

	@Override
	protected void loadResourcesImpl() {
		mBgTextureRegion = AEUtils.createTextureRegionFromAssets("gfx/storm_flush_bg.png", mBaseActivity);
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		mItemTexture = new BitmapTextureAtlas(this.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		mLocTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mItemTexture, mBaseActivity, "side_location.png",  0,   0, 1, 3);
		
		mBgTextureRegion.getTexture().load();
		mItemTexture.load();
	}

	@Override
	protected void unloadResources() {
		mBgTextureRegion.getTexture().unload();
		mItemTexture.unload();
	}

}
