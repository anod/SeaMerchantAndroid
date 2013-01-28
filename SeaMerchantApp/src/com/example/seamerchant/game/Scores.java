package com.example.seamerchant.game;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Comparator;

import android.content.Context;

public class Scores implements Comparable<Scores> {
	public static final String FILENAME = "scores.xml";
	private String mName;
	private int mScore;
	public Scores(String name,int score) {
		mName = name;
		Score(score);

	}
	
	public void writeScore(Context ctx){
		DataOutputStream out;
		try {
		    out = new DataOutputStream(ctx.openFileOutput(FILENAME, Context.MODE_APPEND));
	        out.writeInt(Score());
		    out.writeChar(' ');
	        out.writeUTF(mName);
	        out.close();
	        } catch (IOException e){
	        	}
	}
	@Override
	public int compareTo(Scores another) {
		if(this.Score() > another.Score())
			return 1;
		return -1;
	}

	public int Score() {
		return mScore;
	}

	public void Score(int mScore) {
		this.mScore = mScore;
	}
}
