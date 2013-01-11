package com.example.seamerchant.game;

public class Player {
	private int ship;
	private int location;
	private int money;
	private int guard;
	private Item item1;
	private Item item2;
	private Item item3;
	public Player(int ship, int location) {
		super();
		this.ship = ship;
		this.location = location;
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
	public Item getItem1() {
		return item1;
	}
	public void setItem1(Item item1) {
		this.item1 = item1;
	}
	public Item getItem2() {
		return item2;
	}
	public void setItem2(Item item2) {
		this.item2 = item2;
	}
	public Item getItem3() {
		return item3;
	}
	public void setItem3(Item item3) {
		this.item3 = item3;
	}
	
	
}
