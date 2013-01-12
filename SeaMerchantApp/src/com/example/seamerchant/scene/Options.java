package com.example.seamerchant.scene;

import java.io.IOException;
import java.io.InputStream;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;

import android.util.Log;

public class Options extends Main implements OnClickListener {
    protected static final int MENU_BUY = 0;
    protected static final int MENU_SELL = 1;
    protected static final int MENU_TRAVEL = 2;
    protected static final int MENU_REST = 3;
    
	private TextureRegion mBgTextureRegion;
	private BitmapTextureAtlas mMenuTexture;
	private ITextureRegion mMenuBuyTextureRegion;
	private ITextureRegion mMenuSellTextureRegion;
	private ITextureRegion mMenuTravelTextureRegion;
	private ITextureRegion mMenuRestTextureRegion;

	public Options(SimpleBaseGameActivity baseActivity, SideBanner sideBanner, LowerBanner lowerBanner) {
		super(baseActivity, sideBanner, lowerBanner);
	}

	@Override
	protected Scene initSceneImpl() {
		final Scene scene = new Scene();
		final Sprite backgroundSprite = new Sprite(0, 0, mBgTextureRegion, getVertexBufferObjectManager());
		scene.attachChild(backgroundSprite);

		final ButtonSprite buyMenuItem = new ButtonSprite(313, 85, mMenuBuyTextureRegion, getVertexBufferObjectManager(), this);
		buyMenuItem.setTag(MENU_BUY);
		scene.attachChild(buyMenuItem);

		final ButtonSprite sellMenuItem = new ButtonSprite(300, 123, mMenuSellTextureRegion, getVertexBufferObjectManager(), this);
		sellMenuItem.setTag(MENU_SELL);
		scene.attachChild(sellMenuItem);
		
		final ButtonSprite travelMenuItem = new ButtonSprite(297, 159, mMenuTravelTextureRegion, getVertexBufferObjectManager(), this);
		travelMenuItem.setTag(MENU_TRAVEL);
		scene.attachChild(travelMenuItem);
		
		final ButtonSprite restMenuItem = new ButtonSprite(113, 200, mMenuRestTextureRegion, getVertexBufferObjectManager(), this);
		restMenuItem.setTag(MENU_REST);
		scene.attachChild(restMenuItem);
		
		return scene;
	}

	@Override
	protected void loadResourcesImpl() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		
	    try {
			ITexture backgroundTexture = new BitmapTexture(getTextureManager(), new IInputStreamOpener() {
			    @Override
			    public InputStream open() throws IOException {
			        return getAssets().open("gfx/choice.png");
			    }
			});
			
			backgroundTexture.load();
			
			mBgTextureRegion = TextureRegionFactory.extractFromTexture(backgroundTexture);
		} catch (IOException e) {
			Debug.e(e);
		}
	    
		mMenuTexture = new BitmapTextureAtlas(this.getTextureManager(), 512, 256, TextureOptions.BILINEAR);
		mMenuBuyTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mMenuTexture, mBaseActivity, "choice_buy_sel.png", 0, 0);
		mMenuSellTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mMenuTexture, mBaseActivity, "choice_sell_nor.png", 0, 30);
		mMenuTravelTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mMenuTexture, mBaseActivity, "choice_travel_nor.png", 0, 60);
		mMenuRestTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mMenuTexture, mBaseActivity, "choice_rest_nor.png", 0, 90);

		mMenuTexture.load();

	}

	@Override
	protected void unloadResources() {
		mBgTextureRegion.getTexture().unload();
	}

	@Override
	public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		int tag = pButtonSprite.getTag();
		if (tag == MENU_BUY) {
			Log.d("Options", "MENU_BUY pressed");
		} else if (tag == MENU_SELL) {
			Log.d("Options", "MENU_SELL pressed");
		} else if (tag == MENU_TRAVEL) {
			Log.d("Options", "MENU_TRAVEL pressed");
		} else if (tag == MENU_REST) {
			Log.d("Options", "MENU_REST pressed");
		}

	}

	
}
