package com.example.seamerchant.scene;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import com.example.seamerchant.andengine.AEUtils;

public class NumKeyboard extends Base implements OnClickListener {
	private static final int MAX_LENGTH_NUM = 7;
	private static final int ET = -1;
	private static final int BS = -2;

	private TextureRegion mBgTextureRegion;
	private BitmapTextureAtlas mNumpadTexture;
	private ITiledTextureRegion mNumpad1TextureRegeion;
	private ITiledTextureRegion mNumpad2TextureRegeion;
	private ITiledTextureRegion mNumpad3TextureRegeion;
	private ITiledTextureRegion mNumpad4TextureRegeion;
	private ITiledTextureRegion mNumpad5TextureRegeion;
	private ITiledTextureRegion mNumpad6TextureRegeion;
	private ITiledTextureRegion mNumpad7TextureRegeion;
	private ITiledTextureRegion mNumpad8TextureRegeion;
	private ITiledTextureRegion mNumpad9TextureRegeion;
	private ITiledTextureRegion mNumpad0TextureRegeion;
	private ITiledTextureRegion mNumpadETextureRegeion;
	private ITiledTextureRegion mNumpadBTextureRegeion;
	private OnNumKeyboardUpdateListener mListener;
	private StringBuilder mCurrentNumber;
	private int mOffsetX;
	private int mOffsetY;
	
	public interface OnNumKeyboardUpdateListener {
		boolean onNumberUpdate(Integer num, NumKeyboard kb);
		void onNumberEnter(Integer num, NumKeyboard kb);
	}

	public NumKeyboard(int x, int y, SimpleBaseGameActivity baseActivity, OnNumKeyboardUpdateListener listener) {
		super(baseActivity);
		mListener = listener;
		mCurrentNumber = new StringBuilder();
		mOffsetX = x;
		mOffsetY = y;
	}

	@Override
	protected Scene initScene() {
		final HUD scene = new HUD();
		scene.setCamera(mEngine.getCamera());
		final Sprite backgroundSprite = new Sprite(mOffsetX, mOffsetY, mBgTextureRegion, getVertexBufferObjectManager());
		scene.attachChild(backgroundSprite);

		// 1st column
		
		addButton( 1, mOffsetX+16, mOffsetY+ 10, mNumpad1TextureRegeion, scene);
		addButton( 4, mOffsetX+16, mOffsetY+ 75, mNumpad4TextureRegeion, scene);
		addButton( 7, mOffsetX+16, mOffsetY+142, mNumpad7TextureRegeion, scene);
		addButton(ET, mOffsetX+16, mOffsetY+206, mNumpadETextureRegeion, scene);

		// 2nd column

		addButton( 2, mOffsetX+88, mOffsetY+ 10, mNumpad2TextureRegeion, scene);
		addButton( 5, mOffsetX+88, mOffsetY+ 75, mNumpad5TextureRegeion, scene);
		addButton( 8, mOffsetX+88, mOffsetY+142, mNumpad8TextureRegeion, scene);
		addButton( 0, mOffsetX+88, mOffsetY+206, mNumpad0TextureRegeion, scene);

		// 3rd column
		
		addButton( 3, mOffsetX+163, mOffsetY+ 10, mNumpad3TextureRegeion, scene);
		addButton( 6, mOffsetX+163, mOffsetY+ 75, mNumpad6TextureRegeion, scene);
		addButton( 9, mOffsetX+163, mOffsetY+142, mNumpad9TextureRegeion, scene);
		addButton(BS, mOffsetX+163, mOffsetY+206, mNumpadBTextureRegeion, scene);

		return scene;
	}
	
	private void addButton(int tag, int x, int y, ITiledTextureRegion texturedRegion, Scene scene) {
		final ButtonSprite btn = new ButtonSprite(x, y, texturedRegion, getVertexBufferObjectManager(), this);
		btn.setTag(tag);
		scene.registerTouchArea(btn);
		scene.attachChild(btn);
	}

	@Override
	protected void unloadResources() {
		mBgTextureRegion.getTexture().unload();
		mNumpadTexture.unload();

	}

	@Override
	public void loadResources() {
		mBgTextureRegion = AEUtils.createTextureRegionFromAssets("gfx/numpad_bg.png", mBaseActivity);
		
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		mNumpadTexture = new BitmapTextureAtlas(this.getTextureManager(), 1024, 512, TextureOptions.BILINEAR);
		mNumpad1TextureRegeion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mNumpadTexture, mBaseActivity, "numpad_1.png",     0,   0, 1, 2);
		mNumpad2TextureRegeion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mNumpadTexture, mBaseActivity, "numpad_2.png",    59,   0, 1, 2);
		mNumpad3TextureRegeion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mNumpadTexture, mBaseActivity, "numpad_3.png",   118,   0, 1, 2);
		mNumpad4TextureRegeion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mNumpadTexture, mBaseActivity, "numpad_4.png",   177,   0, 1, 2);
		mNumpad5TextureRegeion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mNumpadTexture, mBaseActivity, "numpad_5.png",   236,   0, 1, 2);
		mNumpad6TextureRegeion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mNumpadTexture, mBaseActivity, "numpad_6.png",   295,   0, 1, 2);
		mNumpad7TextureRegeion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mNumpadTexture, mBaseActivity, "numpad_7.png",   354,   0, 1, 2);
		mNumpad8TextureRegeion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mNumpadTexture, mBaseActivity, "numpad_8.png",   413,   0, 1, 2);
		mNumpad9TextureRegeion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mNumpadTexture, mBaseActivity, "numpad_9.png",   472,   0, 1, 2);
		mNumpad0TextureRegeion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mNumpadTexture, mBaseActivity, "numpad_0.png",     0,  108, 1, 2);
		mNumpadETextureRegeion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mNumpadTexture, mBaseActivity, "numpad_enter.png", 59, 108, 1, 2);
		mNumpadBTextureRegeion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mNumpadTexture, mBaseActivity, "numpad_bkspc.png", 118,108, 1, 2);

		
		mBgTextureRegion.getTexture().load();
		mNumpadTexture.load();

	}


	@Override
	public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		int tag = pButtonSprite.getTag();
		if (tag == ET) { //ENTER
			if (mCurrentNumber.length() > 0) {
				mListener.onNumberEnter(Integer.parseInt(mCurrentNumber.toString()), this);
				this.detachSelf();
			} else {
				mListener.onNumberEnter(null, this);
				this.detachSelf();
			}
			return;
		}
		if (tag == BS) { //BACKSPACE
			if (mCurrentNumber.length() > 0) {
				mCurrentNumber.deleteCharAt(mCurrentNumber.length() - 1);
				if (mCurrentNumber.length() > 0) {
					mListener.onNumberUpdate(Integer.parseInt(mCurrentNumber.toString()), this);
				} else {
					mListener.onNumberUpdate(null, this);
				}
			}
			return;
		}
		if (mCurrentNumber.length() < MAX_LENGTH_NUM) {
			mCurrentNumber.append(tag);
			if (!mListener.onNumberUpdate(Integer.parseInt(mCurrentNumber.toString()), this)) {
				mCurrentNumber.deleteCharAt(mCurrentNumber.length() - 1);
			}
		}
	}

}
