package com.example.seamerchant.scene;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.ui.activity.SimpleBaseGameActivity;

abstract public class FullScreen extends Base {

	private static final float OFFSET_LEFT = 16.0f;

	public FullScreen(SimpleBaseGameActivity baseActivity) {
		super(baseActivity);
		
	}

	@Override
	protected Scene initScene() {
		final Scene FSscene = new Scene();
		FSscene.setBackground(new Background(0.34901960784314f, 0.67058823529412f, 0.05490196078431f));
		
		Scene scene = initSceneImpl();
		FSscene.setChildScene(scene);
		scene.setPosition(OFFSET_LEFT, 0);
		FSscene.attachChild(scene);
		return FSscene;
	}

	@Override
	public void loadResources() {
		loadResourcesImpl();

	}
	abstract protected Scene initSceneImpl();
	abstract protected void loadResourcesImpl();

}
