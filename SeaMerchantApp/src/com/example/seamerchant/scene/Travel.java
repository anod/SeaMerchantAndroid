package com.example.seamerchant.scene;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.AnimatedSprite;
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
import com.example.seamerchant.game.Location;

public class Travel extends Main implements OnClickListener {
	private TextureRegion mBgTextureRegion;
	private BitmapTextureAtlas mItemTexture;
	private ITiledTextureRegion mLocIsraelTextureRegion;
	private ITiledTextureRegion mLocTurkeyTextureRegion;
	private ITiledTextureRegion mLocEgyptTextureRegion;
	private ITextureRegion mBoatTextureRegion;
	
	private Scene mTravelScene;
	private Sprite mBaotSprite;
	
	public Travel(SimpleBaseGameActivity baseActivity, SideBanner sideBanner, LowerBanner lowerBanner) {
		super(baseActivity, sideBanner, lowerBanner);
	}

	@Override
	protected Scene initSceneImpl() {
		mTravelScene = new Scene();
		final Sprite backgroundSprite = new Sprite(0, 0, mBgTextureRegion, getVertexBufferObjectManager());
		mTravelScene.attachChild(backgroundSprite);
		
		final ButtonSprite israelItem = new ButtonSprite(544, 253, mLocIsraelTextureRegion, getVertexBufferObjectManager(), this);
		israelItem.setTag(Location.ISRAEL);
		mTravelScene.registerTouchArea(israelItem);
		mTravelScene.attachChild(israelItem);
		
		final ButtonSprite turkeyItem = new ButtonSprite(344, 4, mLocTurkeyTextureRegion, getVertexBufferObjectManager(), this);
		turkeyItem.setTag(Location.TURKEY);
		mTravelScene.registerTouchArea(turkeyItem);
		mTravelScene.attachChild(turkeyItem);
		
		final ButtonSprite egyptItem = new ButtonSprite(179, 320, mLocEgyptTextureRegion, getVertexBufferObjectManager(), this);
		egyptItem.setTag(Location.EGYPT);
		mTravelScene.registerTouchArea(egyptItem);
		mTravelScene.attachChild(egyptItem);
		//
		
		mBaotSprite = new Sprite(471, 217, mBoatTextureRegion, getVertexBufferObjectManager());
		mTravelScene.attachChild(mBaotSprite);
		
		
		return mTravelScene;
	}

	@Override
	protected void loadResourcesImpl() {
		mBgTextureRegion = AEUtils.createTextureRegionFromAssets("gfx/travel_bg.png", mBaseActivity);
		
		mItemTexture = new BitmapTextureAtlas(this.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		mLocTurkeyTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mItemTexture, mBaseActivity, "travel_turkey.png", 0,  0, 1, 2);
		mLocEgyptTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mItemTexture, mBaseActivity, "travel_egypt.png", 0, 96, 1, 2);
		mLocIsraelTextureRegion  = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mItemTexture, mBaseActivity, "travel_israel.png",  96,   0, 1, 2);

		mBoatTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mItemTexture, mBaseActivity, "travel_boat.png", 0, 187);
		
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
