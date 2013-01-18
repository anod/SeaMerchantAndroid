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

	public Item getWheat() {
		return wheat;
	}

	public void setWheat(Item wheat) {
		this.wheat = wheat;
	}

	public Item getOlives() {
		return olives;
	}

	public void setOlives(Item olives) {
		this.olives = olives;
	}

	public Item getBronze() {
		return bronze;
	}

	public void setBronze(Item bronze) {
		this.bronze = bronze;
	}
	
	
	
}
