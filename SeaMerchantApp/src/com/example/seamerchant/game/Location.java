package com.example.seamerchant.game;

public class Location {
	
	public static final int ISRAEL = 1;
	public static final int EGYPT = 2;
	public static final int TURKEY = 3;
	
	private int mType;
	private Item wheat;
	private Item olives;
	private Item bronze;
	
	public Location(int type) {
		mType = type;
	}
}
