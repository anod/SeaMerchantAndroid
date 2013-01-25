package com.example.seamerchant.scene;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.DrawType;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.graphics.Color;
import android.graphics.Typeface;

import com.example.seamerchant.andengine.AEUtils;
import com.example.seamerchant.game.Game;
import com.example.seamerchant.game.Item;
import com.example.seamerchant.game.Location;
import com.example.seamerchant.game.PricedItem;

public class LowerBanner extends Base {
	private static final int MAX_CHARS = 7;
	private static final float OFFSET_TOP = 343.0f;
	private static final float OFFSET_LEFT = 23.0f;
	
	private TextureRegion mBgTextureRegion;
	private Font mFont;
	private Game mGame;
	private Text mItemEgypt;
	private Text mItemIsrael;
	private Text mItemTurkey;
	
	public LowerBanner(SimpleBaseGameActivity baseActivity,Game game) {
		super(baseActivity);
		mGame = game;
	}

	@Override
	protected Scene initScene() {
	    final Scene scene = new Scene();
	    final Sprite backgroundSprite = new Sprite(OFFSET_LEFT, OFFSET_TOP, mBgTextureRegion, getVertexBufferObjectManager());
	    scene.attachChild(backgroundSprite);
	    setPriceTexts(scene,Item.BRONZE,37);
	    setPriceTexts(scene,Item.OLIVES,70);
	    setPriceTexts(scene,Item.WHEAT,106);
	    return scene;
	}

	private void setPriceTexts(Scene scene, int type,int offsetTop) {
		// there must be a better way to do this.
		// set bronze
		PricedItem item = (PricedItem) mGame.getLocation(Location.EGYPT).getItem(type);
		mItemEgypt = new Text(OFFSET_LEFT + 20, OFFSET_TOP + offsetTop, this.mFont, item.getPrice().toString(), MAX_CHARS, getVertexBufferObjectManager(), DrawType.DYNAMIC);
		scene.attachChild(mItemEgypt);
		item = (PricedItem) mGame.getLocation(Location.ISRAEL).getItem(type);
		mItemIsrael = new Text(OFFSET_LEFT + 279, OFFSET_TOP + offsetTop, this.mFont, item.getPrice().toString(), MAX_CHARS, getVertexBufferObjectManager(), DrawType.DYNAMIC);
		scene.attachChild(mItemIsrael);
		item = (PricedItem) mGame.getLocation(Location.TURKEY).getItem(type);
		mItemTurkey = new Text(OFFSET_LEFT + 553, OFFSET_TOP + offsetTop, this.mFont, item.getPrice().toString(), MAX_CHARS, getVertexBufferObjectManager(), DrawType.DYNAMIC);
		scene.attachChild(mItemTurkey);
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
		mFont = AEUtils.createGameFont(mBaseActivity);
	    mFont.load();
	}

	@Override
	public int getSelectedItem() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void refresh() {
		refreshPrices(Item.BRONZE);
		refreshPrices(Item.OLIVES);
		refreshPrices(Item.WHEAT);
	}

	private void refreshPrices(int type) {
		PricedItem item = (PricedItem) mGame.getLocation(Location.EGYPT).getItem(type);
		mItemEgypt.setText(item.getPrice().toString());
		mItemIsrael.setText(item.getPrice().toString());
		mItemTurkey.setText(item.getPrice().toString());
	}
}
