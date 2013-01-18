package com.example.seamerchant.scene;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import com.example.seamerchant.andengine.AEUtils;

abstract public class Splash extends Base {
	public Splash(SimpleBaseGameActivity baseActivity) {
		super(baseActivity);
	}

	protected TextureRegion mSplashTextureRegion;

	abstract String getSplashFile(String param);
	
	@Override
	protected Scene initScene()
	{
		final Scene scene = new Scene();
		final Sprite backgroundSprite = new Sprite(0, 0, mSplashTextureRegion, getVertexBufferObjectManager());
		scene.attachChild(backgroundSprite);
		return scene;
	}

	@Override
	public void loadResources()
	{
		
		mSplashTextureRegion = AEUtils.createTextureRegionFromAssets(getSplashFile(null), mBaseActivity);
	}

	@Override
	protected void unloadResources() {
		mSplashTextureRegion.getTexture().unload();
	}

}
