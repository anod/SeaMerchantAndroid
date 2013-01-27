package com.example.seamerchant.scene;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import com.example.seamerchant.andengine.AEUtils;
import com.example.seamerchant.game.Game;
import com.example.seamerchant.game.Location;

public class Travel extends Main implements OnClickListener {
	private TextureRegion mBgTextureRegion;
	private BitmapTextureAtlas mItemTexture;
	private ITiledTextureRegion mLocIsraelTextureRegion;
	private ITiledTextureRegion mLocTurkeyTextureRegion;
	private ITiledTextureRegion mLocEgyptTextureRegion;
	private ITextureRegion mBoatTextureRegion;
	
	private Scene mTravelScene;
	private Sprite mBoatSprite;
	private Game mGame;
	
	public Travel(SimpleBaseGameActivity baseActivity, SideBanner sideBanner, LowerBanner lowerBanner, Game game) {
		super(baseActivity, sideBanner, lowerBanner);
		mGame = game;
	}

	@Override
	protected Scene initSceneImpl() {
		mTravelScene = new Scene();
		final Sprite backgroundSprite = new Sprite(0, 0, mBgTextureRegion, getVertexBufferObjectManager());
		mTravelScene.attachChild(backgroundSprite);
		
		int selectedLoc = mGame.getPlayer().getLocation();

		createButtonSprite(516, 253, mLocIsraelTextureRegion, Location.ISRAEL, selectedLoc);
		createButtonSprite(344, 4, mLocTurkeyTextureRegion, Location.TURKEY, selectedLoc);
		createButtonSprite(179, 298, mLocEgyptTextureRegion, Location.EGYPT, selectedLoc);

		mBoatSprite = new Sprite(471, 217, mBoatTextureRegion, getVertexBufferObjectManager());
		mTravelScene.attachChild(mBoatSprite);
		
		
		return mTravelScene;
	}

	private ButtonSprite createButtonSprite(int x, int y, ITiledTextureRegion tr, int locId, int selectedLoc) {
		final ButtonSprite item = new ButtonSprite(x, y, tr, getVertexBufferObjectManager(), this);
		item.setTag(locId);
		mTravelScene.registerTouchArea(item);
		mTravelScene.attachChild(item);
		if (selectedLoc == locId) {
			item.setEnabled(false);
			item.setCurrentTileIndex(1);
		}
		return item;
	}

	@Override
	protected void loadResourcesImpl() {
		mBgTextureRegion = AEUtils.createTextureRegionFromAssets("gfx/travel_bg.png", mBaseActivity);
		
		mItemTexture = new BitmapTextureAtlas(this.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		mLocTurkeyTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mItemTexture, mBaseActivity, "travel_turkey.png", 0,  0, 1, 2);
		mLocEgyptTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mItemTexture, mBaseActivity, "travel_egypt.png", 0, 84, 1, 2);
		mLocIsraelTextureRegion  = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mItemTexture, mBaseActivity, "travel_israel.png",  96, 0, 1, 2);

		mBoatTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mItemTexture, mBaseActivity, "travel_boat.png", 147, 0);
		
		mBgTextureRegion.getTexture().load();
		mItemTexture.load();
	}

	@Override
	protected void unloadResources() {
		mBgTextureRegion.getTexture().unload();
		mItemTexture.unload();
	}

	@Override
	public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		int dest = pButtonSprite.getTag();
	}

}
