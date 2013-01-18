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

import android.graphics.Color;
import android.graphics.Typeface;

import com.example.seamerchant.andengine.AEUtils;
import com.example.seamerchant.game.Game;
import com.example.seamerchant.game.Item;
import com.example.seamerchant.game.Location;
import com.example.seamerchant.game.PricedItem;

public class LowerBanner extends Base {
	private static final float OFFSET_TOP = 343.0f;
	private static final float OFFSET_LEFT = 23.0f;
	private static final float OFFSET_TEXT_LEFT = 23.0f;
	
	private TextureRegion mBgTextureRegion;
	private Font mFont;
	private Game mGame;
	
	public LowerBanner(SimpleBaseGameActivity baseActivity,Game game) {
		super(baseActivity);
		mGame = game;
	}

	@Override
	protected Scene initScene() {
	    final Scene scene = new Scene();
	    final Sprite backgroundSprite = new Sprite(OFFSET_LEFT, OFFSET_TOP, mBgTextureRegion, getVertexBufferObjectManager());
	    scene.attachChild(backgroundSprite);
	    SetPriceTexts(scene,Item.BRONZE,37);
	    SetPriceTexts(scene,Item.OLIVES,70);
	    SetPriceTexts(scene,Item.WHEAT,106);
	    return scene;
	}

	private void SetPriceTexts(Scene scene, int type,int offsetTop) {
		// there must be a better way to do this.
		// set bronze
		PricedItem item = (PricedItem)mGame.getLocation(Location.EGYPT).getItem(type);
	    Text ItemEgypt = new Text(OFFSET_LEFT+20, OFFSET_TOP+offsetTop, this.mFont,item.getPrice().toString(), getVertexBufferObjectManager());
	    scene.attachChild(ItemEgypt);
	    item = (PricedItem)mGame.getLocation(Location.ISRAEL).getItem(type);
	    Text ItemIsrael = new Text(OFFSET_LEFT+279, OFFSET_TOP+offsetTop, this.mFont,item.getPrice().toString(), getVertexBufferObjectManager());
	    scene.attachChild(ItemIsrael);
	    item = (PricedItem)mGame.getLocation(Location.TURKEY).getItem(type);
	    Text ItemTurkey = new Text(OFFSET_LEFT+553, OFFSET_TOP+offsetTop, this.mFont,item.getPrice().toString(), getVertexBufferObjectManager());
	    scene.attachChild(ItemTurkey);
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
		mFont = FontFactory.create(this.getFontManager(), this.getTextureManager(), 256, 256, Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD), 30,Color.WHITE);
	    mFont.load();
	}

	@Override
	public int getSelectedItem() {
		// TODO Auto-generated method stub
		return 0;
	}

}
