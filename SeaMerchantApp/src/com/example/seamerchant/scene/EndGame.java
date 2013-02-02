package com.example.seamerchant.scene;

import java.util.ArrayList;

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

import com.example.seamerchant.andengine.AEUtils;
import com.example.seamerchant.game.ScoreHandler;
import com.example.seamerchant.game.Scores;

public class EndGame extends FullScreen implements OnClickListener {

	private static final int BTN_YES = 1;
	private static final int BTN_NO = 2;
	private int mCurrScore;
	private ArrayList<Scores> mScoresList;
	private TextureRegion mBgTextureRegion;
	private Font mFont;
	private Scene mEndGame;
	private ITextureRegion mMessageText;
	private ITiledTextureRegion mYesTextureRegion;
	private ITiledTextureRegion mNoTextureRegion;
	private BitmapTextureAtlas mObjectsTexture;
	private OnEndDialogClickListener mDialogClickListener;
	
	public interface OnEndDialogClickListener {
		public void onDialogClick(boolean result, EndGame endGame);
	}
	
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
		for (int i = 0; i<10; i++){
			final Text name = new Text(500, 73+offset, mFont, "", 100, getVertexBufferObjectManager(), DrawType.DYNAMIC);
			name.setTag(100+i);
			final Text number = new Text(200, 73+offset, mFont, "", 7, getVertexBufferObjectManager(), DrawType.DYNAMIC);
			number.setTag(200+i);
			mEndGame.attachChild(name);
			mEndGame.attachChild(number);
			offset +=37;
		}
		final Text score = new Text(50, 160, mFont, String.valueOf(mCurrScore), 100, getVertexBufferObjectManager(), DrawType.DYNAMIC);
		mEndGame.attachChild(score);
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
		mObjectsTexture.unload();
		mFont.unload();
	}

	public void showAnotherGameMessage() {
		final Sprite message = new Sprite(39, 260, mMessageText, getVertexBufferObjectManager());
		mEndGame.attachChild(message);
		final ButtonSprite yesButton = new ButtonSprite(150, 274, mYesTextureRegion, getVertexBufferObjectManager(),this);
		yesButton.setTag(BTN_YES);
		yesButton.setOnClickListener(this);
		mEndGame.registerTouchArea(yesButton);
		mEndGame.attachChild(yesButton);
		final ButtonSprite noButton = new ButtonSprite(100, 274, mNoTextureRegion, getVertexBufferObjectManager(),this);
		noButton.setTag(BTN_NO);
		noButton.setOnClickListener(this);
		mEndGame.registerTouchArea(noButton);
		mEndGame.attachChild(noButton);
		
	}

	@Override
	public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if (mDialogClickListener != null) {
			mDialogClickListener.onDialogClick((pButtonSprite.getTag() == BTN_YES), this);
		}
	}

	public void setOnEndDialogClickListener(OnEndDialogClickListener onEndDialogClickListener) {
		mDialogClickListener = onEndDialogClickListener;
	}

	public void addScore(Scores newScore) {
		mScoresList.add(newScore);
		ScoreHandler.sort(mScoresList);
		refreshScores();
	}

	private void refreshScores() {
		for(int i=0; i< mScoresList.size(); i++) {
			final Text name = (Text)mEndGame.getChildByTag(100+i);
			final Text number = (Text)mEndGame.getChildByTag(200+i);
			final Scores score = mScoresList.get(i);
			name.setText(score.getName());
			number.setText(score.getScore() + "");
		}
		
	}
}
