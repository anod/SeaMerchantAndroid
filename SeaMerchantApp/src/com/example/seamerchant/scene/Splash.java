package com.example.seamerchant.scene;

import java.io.IOException;
import java.io.InputStream;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;

abstract public class Splash extends Base {
	public Splash(SimpleBaseGameActivity baseActivity) {
		super(baseActivity);
	}

	protected TextureRegion mSplashTextureRegion;

	abstract String getSplashFile();
	
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
		// 1 - Set up bitmap textures
	    try {
			ITexture backgroundTexture = new BitmapTexture(getTextureManager(), new IInputStreamOpener() {
			    @Override
			    public InputStream open() throws IOException {
			        return getAssets().open(getSplashFile());
			    }
			});
			
			backgroundTexture.load();
			
			mSplashTextureRegion = TextureRegionFactory.extractFromTexture(backgroundTexture);
		} catch (IOException e) {
			Debug.e(e);
		}
	}

	@Override
	protected void unloadResources() {
		mSplashTextureRegion.getTexture().unload();
	}

}
