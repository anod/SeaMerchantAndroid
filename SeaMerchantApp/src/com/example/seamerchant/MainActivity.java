package com.example.seamerchant;

import java.io.IOException;
import java.io.InputStream;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
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
	private TextureRegion mSplashTextureRegion;
	private TextureRegion mBackgroundTextureRegion;
	
	private Scene mMainScene;
	
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
		loadSplashSceneResources();
	}

	@Override
	protected Scene onCreateScene() {
		final Scene splashScene = initSplashScene();
		
		mEngine.registerUpdateHandler(new TimerHandler(3f, new ITimerCallback() {
			
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				mEngine.unregisterUpdateHandler(pTimerHandler);
				loadResources();
				loadScenes();
				splashScene.detachSelf();
				mEngine.setScene(mMainScene);
				mSplashTextureRegion.getTexture().unload();
			}
		}));
		
		return splashScene;
	}

	public void loadResources() 
	{
		// 1 - Set up bitmap textures
	    try {
			ITexture backgroundTexture = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
			    @Override
			    public InputStream open() throws IOException {
			        return getAssets().open("gfx/choice.png");
			    }
			});
			
			backgroundTexture.load();
			
			mBackgroundTextureRegion = TextureRegionFactory.extractFromTexture(backgroundTexture);
		} catch (IOException e) {
			Debug.e(e);
		}
	}

	private void loadScenes()
	{
	    // load your game here, you scenes
		mMainScene = new Scene();
	    final Sprite backgroundSprite = new Sprite(0, 0, mBackgroundTextureRegion, getVertexBufferObjectManager());
		mMainScene.attachChild(backgroundSprite);
	}
	
	private void loadSplashSceneResources()
	{
		// 1 - Set up bitmap textures
	    try {
			ITexture backgroundTexture = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
			    @Override
			    public InputStream open() throws IOException {
			        return getAssets().open("gfx/welcome.png");
			    }
			});
			
			backgroundTexture.load();
			
			mSplashTextureRegion = TextureRegionFactory.extractFromTexture(backgroundTexture);
		} catch (IOException e) {
			Debug.e(e);
		}
	}

	private Scene initSplashScene()
	{
	    final Scene scene = new Scene();
	    final Sprite backgroundSprite = new Sprite(0, 0, mSplashTextureRegion, getVertexBufferObjectManager());
	    scene.attachChild(backgroundSprite);
	    return scene;
	}
}
