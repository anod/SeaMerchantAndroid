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

import com.example.seamerchant.andengine.AEUtils;

public class LowerBanner extends Base {
	private static final float OFFSET_TOP = 343.0f;
	private static final float OFFSET_LEFT = 23.0f;
	private TextureRegion mBgTextureRegion;

	public LowerBanner(SimpleBaseGameActivity baseActivity) {
		super(baseActivity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Scene initScene() {
	    final Scene scene = new Scene();
	    final Sprite backgroundSprite = new Sprite(OFFSET_LEFT, OFFSET_TOP, mBgTextureRegion, getVertexBufferObjectManager());
	    scene.attachChild(backgroundSprite);
	    return scene;
	}

	@Override
	protected void unloadResources() {

	}

	@Override
	public void loadResources() {
		if (mBgTextureRegion != null) {
			return;//Do not load twice
		}
		
		mBgTextureRegion = AEUtils.createTextureRegionFromAssets("gfx/lowerbg.png", mBaseActivity);
	}

	@Override
	public int getSelectedItem() {
		// TODO Auto-generated method stub
		return 0;
	}

}
