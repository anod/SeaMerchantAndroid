package com.example.seamerchant.andengine;

import java.io.IOException;
import java.io.InputStream;

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

public class AEUtils {

	/**
	 * TODO find and replace with similar function in AndEngine
	 * @param path
	 * @param baseActivity
	 * @return
	 */
	public static TextureRegion createTextureRegionFromAssets(final String path,final SimpleBaseGameActivity baseActivity) {
		ITexture backgroundTexture;
		TextureRegion region = null;
		try {
			backgroundTexture = new BitmapTexture(baseActivity.getTextureManager(), new IInputStreamOpener() {
			    @Override
			    public InputStream open() throws IOException {
			        return baseActivity.getAssets().open(path);
			    }
			});
			
			backgroundTexture.load();
			
			region = TextureRegionFactory.extractFromTexture(backgroundTexture);
		} catch (IOException e) {
			Debug.e(e);
		}
		return region;
	}
	
	public static Font createGameFont(final SimpleBaseGameActivity baseActivity) {
		return FontFactory.create(
			baseActivity.getFontManager(), 
			baseActivity.getTextureManager(), 
			256, 256, 
			Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD), 
			28,
			Color.rgb(173, 88, 29)
		);
	}
}
