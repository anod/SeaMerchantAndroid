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


public class GameStart extends Base {
	public GameStart(SimpleBaseGameActivity baseActivity) {
		super(baseActivity);
	}

	private TextureRegion mBackgroundTextureRegion;
	@Override
	protected Scene initScene() {
	    // load your game here, you scenes
		final Scene scene = new Scene();
	    final Sprite backgroundSprite = new Sprite(0, 0, mBackgroundTextureRegion, getVertexBufferObjectManager());
	    scene.attachChild(backgroundSprite);
		return scene;
	}

	@Override
	protected void unloadResources() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadResources() {

		// 1 - Set up bitmap textures
	    try {
			ITexture backgroundTexture = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
			    @Override
			    public InputStream open() throws IOException {
			        return getAssets().open("gfx/gamestart.png");
			    }
			});
			
			backgroundTexture.load();
			
			mBackgroundTextureRegion = TextureRegionFactory.extractFromTexture(backgroundTexture);
		} catch (IOException e) {
			Debug.e(e);
		}
	}

}
