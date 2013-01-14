package com.example.seamerchant.scene;

import java.io.IOException;
import java.io.InputStream;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;

import android.graphics.Typeface;

public class SideBanner extends Base {

	private static final float OFFSET_LEFT = 610.0f;
	private TextureRegion mBgTextureRegion;
	private Font mFont;
	public SideBanner(SimpleBaseGameActivity baseActivity) {
		super(baseActivity);
	}

	@Override
	protected Scene initScene() {
	    final Scene scene = new Scene();
	    final Sprite backgroundSprite = new Sprite(OFFSET_LEFT, 0, mBgTextureRegion, getVertexBufferObjectManager());
	    scene.attachChild(backgroundSprite);
	    
	    Text Money = new Text(OFFSET_LEFT+50, 300f, this.mFont, "5000", getVertexBufferObjectManager());
	    scene.attachChild(Money);
	    Text wheat = new Text(OFFSET_LEFT+30, 222f, this.mFont, "0", getVertexBufferObjectManager());
	    scene.attachChild(wheat);
	    Text olives = new Text(OFFSET_LEFT+30, 189f, this.mFont, "0", getVertexBufferObjectManager());
	    scene.attachChild(olives);
	    Text bronze = new Text(OFFSET_LEFT+30, 152f, this.mFont, "0", getVertexBufferObjectManager());
	    scene.attachChild(bronze);
	    return scene;
	}

	@Override
	protected void unloadResources() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadResources() {
		if (mBgTextureRegion != null) {
			return;//Do not load twice
		}
	    try {
			ITexture backgroundTexture = new BitmapTexture(getTextureManager(), new IInputStreamOpener() {
			    @Override
			    public InputStream open() throws IOException {
			        return getAssets().open("gfx/sidebg.png");
			    }
			});
			
			backgroundTexture.load();
			
			mBgTextureRegion = TextureRegionFactory.extractFromTexture(backgroundTexture);
		} catch (IOException e) {
			Debug.e(e);
		}
		mFont = FontFactory.create(this.getFontManager(), this.getTextureManager(), 256, 256, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 30);
	    mFont.load();
	}

	@Override
	public int getSelectedItem() {
		// TODO Auto-generated method stub
		return 0;
	}

}
