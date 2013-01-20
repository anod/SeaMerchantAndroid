package com.example.seamerchant.scene;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.FontManager;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.content.res.AssetManager;

public abstract class Base {

	protected SimpleBaseGameActivity mBaseActivity;
	protected Engine mEngine;
	protected Scene mScene;
	
	public interface OnActionDownListener {
		void onAcionDown(Base base);
	}
	
	public Base(SimpleBaseGameActivity baseActivity) {
		mBaseActivity = baseActivity;
		mEngine = mBaseActivity.getEngine();
	}
	
	abstract protected Scene initScene();
	abstract protected void unloadResources();
	abstract public void loadResources();
	public int getSelectedItem() {
		return 0;
	}
	public Scene getScene() {
		return mScene;
	}
	
	public void loadResourcesAndScene() {
		loadResources();
		loadScene();
	}
	
	public Scene loadScene() {
		mScene = initScene();
		return mScene;
	}
	
	public void unload() {
		unloadResources();
	}

	public void detachSelf() {
		mScene.detachSelf();
	}
	
	public void detachAndUnload() {
		mScene.detachSelf();
		unload();
		
	}

	protected VertexBufferObjectManager getVertexBufferObjectManager() {
		return mEngine.getVertexBufferObjectManager();
	}
	
	protected TextureManager getTextureManager() {
		return mEngine.getTextureManager();
	}
	protected FontManager getFontManager() {
		return mEngine.getFontManager();
	}
	protected AssetManager getAssets() {
		return mBaseActivity.getAssets();
	}

	public void setOnActionDown(final OnActionDownListener listener) {
		mScene.setOnSceneTouchListener(new IOnSceneTouchListener() {
			
			@Override
			public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
				if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
					listener.onAcionDown(Base.this);
					return true;
				}
				return false;
			}
		});
	}
}
