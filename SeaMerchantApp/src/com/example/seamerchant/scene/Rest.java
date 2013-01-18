package com.example.seamerchant.scene;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import com.example.seamerchant.andengine.AEUtils;

public class Rest extends Main {
	private TextureRegion mBgTextureRegion;
	private int mSelected;
	
	public Rest(SimpleBaseGameActivity baseActivity, SideBanner sideBanner, LowerBanner lowerBanner) {
		super(baseActivity, sideBanner, lowerBanner);
	}
	
	@Override
	protected Scene initSceneImpl() {
		final Scene scene = new Scene();
		final Sprite backgroundSprite = new Sprite(0, 0, mBgTextureRegion, getVertexBufferObjectManager());
		scene.attachChild(backgroundSprite);
		return scene;
	}

	@Override
	protected void loadResourcesImpl() {
		mBgTextureRegion = AEUtils.createTextureRegionFromAssets("gfx/rest.png", mBaseActivity);
		mBgTextureRegion.getTexture().load();

	}

	@Override
	protected void unloadResources() {
		mBgTextureRegion.getTexture().unload();
	}

	@Override
	public int getSelectedItem() {
		return mSelected;
	}


	
}
