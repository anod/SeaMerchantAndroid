package com.example.seamerchant.scene;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.DrawType;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.debug.Debug;

import com.example.seamerchant.andengine.AEUtils;
import com.example.seamerchant.game.Game;
import com.example.seamerchant.game.Item;
import com.example.seamerchant.game.PricedItem;
import com.example.seamerchant.scene.NumKeyboard.OnNumKeyboardUpdateListener;

public class Sell extends Main implements OnClickListener, OnNumKeyboardUpdateListener {
	private static final int MAX_CHARS = 7;
	private TextureRegion mBgTextureRegion;
	private BitmapTextureAtlas mItemTexture;
	private ITiledTextureRegion mItemWheatTextureRegion;
	private ITiledTextureRegion mItemBronzeTextureRegion;
	private ITiledTextureRegion mItemOlivesTextureRegion;
	private ITextureRegion mSellInputTextureRegion;
	private Scene mItemsScene;
	private NumKeyboard mNumKeyboard;
	private Sprite mSellInput;
	private Font mFont;
	private Text mQuantityText;
	private Game mGame;
	private OnSellItemListener mListener;
	private PricedItem mSelectedItem;
	private int mPurchasableCount;
	
	public interface OnSellItemListener {
		void onSellItem(PricedItem item, int count, Sell sell);
	}
	
	public Sell(SimpleBaseGameActivity baseActivity, SideBanner sideBanner, LowerBanner lowerBanner, Game game, OnSellItemListener listener) {
		super(baseActivity, sideBanner, lowerBanner);
		mNumKeyboard = new NumKeyboard(400, 25,baseActivity, this);
		mGame = game;
		mListener = listener;
	}

	@Override
	protected Scene initSceneImpl() {
		mItemsScene = new Scene();
		final Sprite backgroundSprite = new Sprite(0, 0, mBgTextureRegion, getVertexBufferObjectManager());
		mItemsScene.attachChild(backgroundSprite);

		final ButtonSprite wheatTextItem = new ButtonSprite(46, 120, mItemWheatTextureRegion, getVertexBufferObjectManager(), this);
		wheatTextItem.setTag(Item.WHEAT);
		mItemsScene.registerTouchArea(wheatTextItem);
		mItemsScene.attachChild(wheatTextItem);

		final ButtonSprite bronzeTextItem = new ButtonSprite(46, 50, mItemBronzeTextureRegion, getVertexBufferObjectManager(), this);
		bronzeTextItem.setTag(Item.BRONZE);
		mItemsScene.registerTouchArea(bronzeTextItem);
		mItemsScene.attachChild(bronzeTextItem);
		
		final ButtonSprite oliveTextItem = new ButtonSprite(46, 85, mItemOlivesTextureRegion, getVertexBufferObjectManager(), this);
		oliveTextItem.setTag(Item.OLIVES);
		mItemsScene.registerTouchArea(oliveTextItem);
		mItemsScene.attachChild(oliveTextItem);
		
		mItemsScene.setTouchAreaBindingOnActionDownEnabled(true);
		
		mSellInput = new Sprite(75, 255, mSellInputTextureRegion, getVertexBufferObjectManager());
	    
	    mQuantityText = new Text(110, 295, mFont, "0", MAX_CHARS, getVertexBufferObjectManager(), DrawType.DYNAMIC);

	    
		mNumKeyboard.loadScene();
		
		return mItemsScene;
	}

	@Override
	protected void loadResourcesImpl() {
		mBgTextureRegion = AEUtils.createTextureRegionFromAssets("gfx/sell_bg.png", mBaseActivity);
		
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		mItemTexture = new BitmapTextureAtlas(this.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		mItemWheatTextureRegion  = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mItemTexture, mBaseActivity, "sell_item_wheat.png",  0,   0, 1, 2);
		mItemBronzeTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mItemTexture, mBaseActivity, "sell_item_bronze.png", 0,  48, 1, 2);
		mItemOlivesTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mItemTexture, mBaseActivity, "sell_item_olives.png", 0, 96, 1, 2);

		mSellInputTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mItemTexture, mBaseActivity, "sell_input.png", 0, 276);
		
		mBgTextureRegion.getTexture().load();
		mItemTexture.load();
		mNumKeyboard.loadResources();
		mFont = AEUtils.createGameFont(mBaseActivity);
	    mFont.load();
	}

	@Override
	protected void unloadResources() {
		mBgTextureRegion.getTexture().unload();
		mItemTexture.unload();
		mNumKeyboard.unloadResources();
	}

	@Override
	public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		//int tag = pButtonSprite.getTag();
		pButtonSprite.setCurrentTileIndex(1);
		int itemId = pButtonSprite.getTag();
		mSelectedItem = mGame.getCurrentLocation().getItem(itemId);
		
		Debug.d("Money: "+mGame.getPlayer().getMoney()+", Price: "+mSelectedItem.getPrice()+", Available:" + mPurchasableCount);		
		mItemsScene.attachChild(mSellInput);
		
		mItemsScene.attachChild(mQuantityText);
		mItemsScene.setChildScene(mNumKeyboard.getScene());

	}

	@Override
	public boolean onNumberUpdate(Integer num, NumKeyboard kb) {
		if (num == null) {
			mQuantityText.setText("0");
			return false;
		}
		if(num <= mGame.getPlayer().getItem(mSelectedItem.getType()).getCount())
		{
			mQuantityText.setText(num + "");
			return true;
		}
		return false;

	}

	@Override
	public void onNumberEnter(Integer num, NumKeyboard kb) {
		mItemsScene.clearChildScene();
		mItemsScene.detachChild(mSellInput);
		mItemsScene.detachChild(mQuantityText);
		if (num != null ) {
			mListener.onSellItem(mSelectedItem, num, this);
		} else {
			//Unpress button
		
			getSpriteByItemId(mSelectedItem.getType()).setCurrentTileIndex(0);
		}
		mSelectedItem = null;
	}
	
	private ButtonSprite getSpriteByItemId(int itemId) {
		return (ButtonSprite)mItemsScene.getChildByTag(itemId);
	}

}
