package com.example.seamerchant;

import java.io.IOException;
import java.io.InputStream;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;

public class MainActivity extends SimpleBaseGameActivity {
	private static int CAMERA_WIDTH = 800;
	private static int CAMERA_HEIGHT = 480;
	private TextureRegion mBackgroundTextureRegion;
	
	@Override
	public EngineOptions onCreateEngineOptions() {
		final Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new EngineOptions(
			true, ScreenOrientation.LANDSCAPE_FIXED, 
			new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera
		);
	}

	@Override
	protected void onCreateResources() {
	    // 1 - Set up bitmap textures
	    try {
			ITexture backgroundTexture = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
			    @Override
			    public InputStream open() throws IOException {
			        return getAssets().open("gfx/welcome.png");
			    }
			});
			
			backgroundTexture.load();
			
			mBackgroundTextureRegion = TextureRegionFactory.extractFromTexture(backgroundTexture);
		} catch (IOException e) {
			Debug.e(e);
		}
	}

	@Override
	protected Scene onCreateScene() {
		final Scene scene = new Scene();
		Sprite backgroundSprite = new Sprite(0, 0, mBackgroundTextureRegion, getVertexBufferObjectManager());
		scene.attachChild(backgroundSprite);
		return scene;
	}

}
