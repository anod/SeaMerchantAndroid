package com.example.seamerchant.scene;

import java.util.ArrayList;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import com.example.seamerchant.andengine.AEUtils;
import com.example.seamerchant.game.Game;
import com.example.seamerchant.game.Scores;
import com.example.seamerchant.scene.Base.OnActionDownListener;

public class EndGame extends FullScreen implements OnClickListener {

	private static final int BTN_YES = 0;
	private static final int BTN_NO = 1;
	private int mCurrScore;
	private ArrayList<Scores> mScoresList;
	private TextureRegion mBgTextureRegion;
	private Font mFont;
	private Scene mEndGame;
	private ITextureRegion mMessageText;
	private ITiledTextureRegion mYesTextureRegion;
	private ITiledTextureRegion mNoTextureRegion;
	private BitmapTextureAtlas mObjectsTexture;
	
	public EndGame(SimpleBaseGameActivity baseActivity, int currScore,ArrayList<Scores> highScores) {
		super(baseActivity);
		mCurrScore = currScore;
		mScoresList = highScores;
	}

	@Override
	protected Scene initSceneImpl() {
		mEndGame = new Scene();
		final Sprite backgroundSprite = new Sprite(0, 0, mBgTextureRegion, getVertexBufferObjectManager());
		mEndGame.attachChild(backgroundSprite);
		int offset = 0;
		for (Scores score : mScoresList) {
			final Text name = new Text(500, 74+offset, mFont, score.getName(), this.getVertexBufferObjectManager());
			final Text number = new Text(200, 74+offset, mFont, score.getScore().toString(), this.getVertexBufferObjectManager());
			mEndGame.attachChild(name);
			mEndGame.attachChild(number);
			offset +=37;
		}
		
		mEndGame.setTouchAreaBindingOnActionDownEnabled(true);
		return mEndGame;
	}

	@Override
	protected void loadResourcesImpl() {
		mBgTextureRegion = AEUtils.createTextureRegionFromAssets("gfx/GameEnd.png", mBaseActivity);
		mFont = AEUtils.createGameFont(mBaseActivity);
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		mObjectsTexture = new BitmapTextureAtlas(this.getTextureManager(), 1024, 512, TextureOptions.BILINEAR);
		mYesTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mObjectsTexture, mBaseActivity, "btn_yes.png",    0,   0, 1, 2);
		mNoTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mObjectsTexture, mBaseActivity, "btn_no.png",    0,   32, 1, 2);
		mMessageText = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mObjectsTexture, mBaseActivity, "msg_anotherGame.png", 0, 64);
		mBgTextureRegion.getTexture().load();
		mFont.load();
		mObjectsTexture.load();
	}

	@Override
	protected void unloadResources() {
		mBgTextureRegion.getTexture().unload();
		
	}

	public void showAnotherGameMessage() {
		final Sprite message = new Sprite(39, 260, mMessageText, getVertexBufferObjectManager());
		mEndGame.attachChild(message);
		final ButtonSprite yesButton = new ButtonSprite(100, 274, mYesTextureRegion, getVertexBufferObjectManager(),this);
		yesButton.setTag(BTN_YES);
		yesButton.setOnClickListener(this);
		mEndGame.registerTouchArea(yesButton);
		mEndGame.attachChild(yesButton);
		final ButtonSprite noButton = new ButtonSprite(113, 274, mNoTextureRegion, getVertexBufferObjectManager(),this);
		yesButton.setTag(BTN_NO);
		yesButton.setOnClickListener(this);
		mEndGame.registerTouchArea(noButton);
		mEndGame.attachChild(yesButton);
		
	}

	@Override
	public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX,
			float pTouchAreaLocalY) {
		// TODO Auto-generated method stub
		
	}
}
