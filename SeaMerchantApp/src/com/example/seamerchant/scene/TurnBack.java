package com.example.seamerchant.scene;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import com.example.seamerchant.andengine.AEUtils;
import com.example.seamerchant.game.Location;

public class TurnBack extends Main {

	private int mDest;
	private int mOrig;
	private TiledSprite mDestLocation;
	private TiledSprite mOrigLocation;
	private TextureRegion mBgTextureRegion;
	private BitmapTextureAtlas mItemTexture;
	private ITiledTextureRegion mLocTextureRegion;
	
	public TurnBack(SimpleBaseGameActivity baseActivity, SideBanner sideBanner,
			LowerBanner lowerBanner,int dest,int start) {
		super(baseActivity, sideBanner, lowerBanner);
		mDest = dest;
		mOrig = start;
	}

	@Override
	protected Scene initSceneImpl() {
	    final Scene scene = new Scene();
	    final Sprite backgroundSprite = new Sprite(0, 0, mBgTextureRegion, getVertexBufferObjectManager());
	    scene.attachChild(backgroundSprite);
	    mDestLocation = new TiledSprite(400, 308, mLocTextureRegion, getVertexBufferObjectManager());
	    mOrigLocation = new TiledSprite(150, 308, mLocTextureRegion, getVertexBufferObjectManager());
	    setLocationString();
	    scene.attachChild(mDestLocation);
	    scene.attachChild(mOrigLocation);
	    return scene;
	}

	@Override
	protected void loadResourcesImpl() {
		mBgTextureRegion = AEUtils.createTextureRegionFromAssets("gfx/stormBack.png", mBaseActivity);
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
	private void setLocationString() {
		switch (mOrig) {
		case Location.EGYPT:
			mOrigLocation.setCurrentTileIndex(1);
			break;
		case Location.TURKEY:
			mOrigLocation.setCurrentTileIndex(0);
			break;
		case Location.ISRAEL:
			mOrigLocation.setCurrentTileIndex(2);
			break;
		default:
			break;
		}
		switch (mDest) { // too late i will think of recreating the function later
		case Location.EGYPT:
			mDestLocation.setCurrentTileIndex(1);
			break;
		case Location.TURKEY:
			mDestLocation.setCurrentTileIndex(0);
			break;
		case Location.ISRAEL:
			mDestLocation.setCurrentTileIndex(2);
			break;
		default:
			break;
		}
	}
}
