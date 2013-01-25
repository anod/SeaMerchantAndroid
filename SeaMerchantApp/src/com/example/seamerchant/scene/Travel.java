package com.example.seamerchant.scene;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import com.example.seamerchant.andengine.AEUtils;

public class Travel extends Main {
	private TextureRegion mBgTextureRegion;
	private Scene mTravelScene;
	
	public Travel(SimpleBaseGameActivity baseActivity, SideBanner sideBanner, LowerBanner lowerBanner) {
		super(baseActivity, sideBanner, lowerBanner);
	}

	@Override
	protected Scene initSceneImpl() {
		mTravelScene = new Scene();
		final Sprite backgroundSprite = new Sprite(0, 0, mBgTextureRegion, getVertexBufferObjectManager());
		mTravelScene.attachChild(backgroundSprite);
		
		return mTravelScene;
	}

	@Override
	protected void loadResourcesImpl() {
		mBgTextureRegion = AEUtils.createTextureRegionFromAssets("gfx/travel_bg.png", mBaseActivity);
		mBgTextureRegion.getTexture().load();
	}

	@Override
	protected void unloadResources() {
		mBgTextureRegion.getTexture().unload();
	}

}
