package com.example.seamerchant.scene;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.PathModifier.Path;
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
import org.andengine.util.modifier.IModifier;
import org.andengine.util.modifier.IModifier.IModifierListener;

import com.example.seamerchant.andengine.AEUtils;
import com.example.seamerchant.game.Game;
import com.example.seamerchant.game.Location;

public class Travel extends Main implements OnClickListener, IEntityModifierListener {
	private TextureRegion mBgTextureRegion;
	private BitmapTextureAtlas mItemTexture;
	private ITiledTextureRegion mLocIsraelTextureRegion;
	private ITiledTextureRegion mLocTurkeyTextureRegion;
	private ITiledTextureRegion mLocEgyptTextureRegion;
	private ITextureRegion mBoatTextureRegion;
	
	private Scene mTravelScene;
	private Sprite mBoatSprite;
	private Game mGame;
	private int mPlayerLoc;
	private int mDestLoc;
	private ButtonSprite mIsraelButton;
	private ButtonSprite mTurkeyButton;
	private ButtonSprite mEgyptButton;
	
	public Travel(SimpleBaseGameActivity baseActivity, SideBanner sideBanner, LowerBanner lowerBanner, Game game) {
		super(baseActivity, sideBanner, lowerBanner);
		mGame = game;
	}

	@Override
	protected Scene initSceneImpl() {
		mTravelScene = new Scene();
		final Sprite backgroundSprite = new Sprite(0, 0, mBgTextureRegion, getVertexBufferObjectManager());
		mTravelScene.attachChild(backgroundSprite);
		
		mPlayerLoc = mGame.getPlayer().getLocation();

		mIsraelButton = createButtonSprite(516, 253, mLocIsraelTextureRegion, Location.ISRAEL, mPlayerLoc);
		mTurkeyButton = createButtonSprite(344, 4, mLocTurkeyTextureRegion, Location.TURKEY, mPlayerLoc);
		mEgyptButton = createButtonSprite(179, 298, mLocEgyptTextureRegion, Location.EGYPT, mPlayerLoc);

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
		mDestLoc = pButtonSprite.getTag();
		Path path = getBoatPath(mPlayerLoc, mDestLoc);
		mBoatSprite.registerEntityModifier(new PathModifier(10, path, this));
	}

	private Path getBoatPath(int source, int dest) {
		Path path = null;
		if (source == Location.ISRAEL && dest == Location.TURKEY) {
			path = new Path(5);
			path
				.to(471, 217)
				.to(471, 131)
				.to(471, 73)
				.to(440, 62)
				.to(353, 59)
			;
		} else if (source == Location.ISRAEL && dest == Location.EGYPT) {
			path = new Path(3);
			path
				.to(471, 217)
				.to(343, 232)
				.to(215, 250)
			;
		} else if (source == Location.TURKEY && dest == Location.ISRAEL) {
			path = new Path(5);
			path
				.to(353, 59)
				.to(440, 62)
				.to(471, 73)
				.to(471, 131)
				.to(471, 217)
			;
		} else if (source == Location.EGYPT && dest == Location.ISRAEL) {
			path = new Path(3);
			path
				.to(215, 250)
				.to(343, 232)
				.to(471, 217)
			;
		} else if (source == Location.TURKEY && dest == Location.EGYPT) {
			path = new Path(4);
			path
				.to(353, 59)
				.to(286, 89)
				.to(233, 159)
				.to(215, 250)
			;
		} else if (source == Location.EGYPT && dest == Location.TURKEY) {
			path = new Path(4);
			path
				.to(215, 250)
				.to(233, 159)
				.to(286, 89)
				.to(353, 59)
			;
		}
		return path;
	}

	@Override
	public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
		
	}

	@Override
	public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
		ButtonSprite srcButton = (ButtonSprite) mTravelScene.getChildByTag(mPlayerLoc);
		ButtonSprite dstButton = (ButtonSprite) mTravelScene.getChildByTag(mDestLoc);
		mPlayerLoc = mDestLoc;
		srcButton.setEnabled(true);
		srcButton.setCurrentTileIndex(0);
		dstButton.setEnabled(false);
		dstButton.setCurrentTileIndex(1);
		//mGame.getPlayer().setLocation(mDestLoc);
		
	}

}
