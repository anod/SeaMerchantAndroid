package com.example.seamerchant.scene;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.graphics.Color;
import android.graphics.Typeface;

import com.example.seamerchant.andengine.AEUtils;

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
		mBgTextureRegion = AEUtils.createTextureRegionFromAssets("gfx/sidebg.png", mBaseActivity);
		mFont = FontFactory.create(this.getFontManager(), this.getTextureManager(), 256, 256, Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD), 30,Color.WHITE);
	    mFont.load();
	}

	@Override
	public int getSelectedItem() {
		// TODO Auto-generated method stub
		return 0;
	}

}
