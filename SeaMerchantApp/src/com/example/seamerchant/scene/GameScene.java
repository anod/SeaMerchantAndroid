package com.example.seamerchant.scene;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.content.res.AssetManager;

public abstract class GameScene {

	protected SimpleBaseGameActivity mBaseActivity;
	protected Engine mEngine;
	protected Scene mScene;

	public GameScene(SimpleBaseGameActivity baseActivity) {
		mBaseActivity = baseActivity;
		mEngine = mBaseActivity.getEngine();
	}
	
	abstract protected Scene initScene();
	abstract protected void unloadResources();
	abstract public void loadResources();

	public Scene getScene() {
		return mScene;
	}
	
	public Scene loadScene() {
		mScene = initScene();
		return mScene;
	}
	
	public void unload() {
		unloadResources();
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
	
	protected AssetManager getAssets() {
		return mBaseActivity.getAssets();
	}
}
