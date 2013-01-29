package com.example.seamerchant.scene;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import com.example.seamerchant.andengine.AEUtils;
import com.example.seamerchant.game.Game;

public class HighScore extends FullScreen {

	private Game mGame;
	private TextureRegion mBgTextureRegion;
	private Scene mHighScore;
	
	public HighScore(SimpleBaseGameActivity baseActivity, Game game) {
		super(baseActivity);
		mGame = game;
	}

	@Override
	protected Scene initSceneImpl() {
		mHighScore = new Scene();
		final Sprite backgroundSprite = new Sprite(0, 0, mBgTextureRegion, getVertexBufferObjectManager());
		mHighScore.attachChild(backgroundSprite);
		mHighScore.setTouchAreaBindingOnActionDownEnabled(true);
		return mHighScore;
	}

	@Override
	protected void loadResourcesImpl() {
		mBgTextureRegion = AEUtils.createTextureRegionFromAssets("gfx/GameEnd.png", mBaseActivity);
		mBgTextureRegion.getTexture().load();
	}

	@Override
	protected void unloadResources() {
		mBgTextureRegion.getTexture().unload();
		
	}


}
