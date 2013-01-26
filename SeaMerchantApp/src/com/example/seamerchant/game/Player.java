package com.example.seamerchant.game;

public class Player {
	private static final int DEFAULT_GUARD = 0;
	private static final int DEFAULT_MONEY = 5000;
	private int ship;
	private int location;
	private int money;
	private int guard;
	private Item wheat;
	private Item olives;
	private Item bronze;

	public Player(int ship, int location) {
		this.ship = ship;
		this.location = location;
		this.wheat = new Item(Item.WHEAT);
		this.olives = new Item(Item.OLIVES);
		this.bronze = new Item(Item.BRONZE);
		this.money = DEFAULT_MONEY;
		this.guard = DEFAULT_GUARD;
	}
	
	/**
	 * check if player has goods to sell
	 * @return
	 */
	public boolean hasGoods() {
		if (wheat.getCount() > 0) {
			return true;
		}
		if (olives.getCount() > 0) {
			return true;
		}
		if (bronze.getCount() > 0) {
			return true;
		}
		return false;
	}
	
	public int getShip() {
		return ship;
	}
	public void setShip(int ship) {
		this.ship = ship;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getGuard() {
		return guard;
	}
	public void setGuard(int guard) {
		this.guard = guard;
	}
	public Item getWheat() {
		return wheat;
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

	public Item getOlives() {
		return olives;
	}

	public Item getItem(int type) {
		switch(type) {
		case Item.WHEAT:
			return this.wheat;
		case Item.OLIVES:
			return this.olives;
		case Item.BRONZE:
			return this.bronze;
		}
		return null;
	}

	public void reduceMoney(int cost) {
		this.money -= cost;
		if (this.money < 0) {
			throw new IllegalArgumentException("Money cannot be negative");
		}
	}

	public void increaseMoney(int cost) {
		this.money += cost;
	}
	
	
}
