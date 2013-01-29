package com.example.seamerchant.game;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Scores> {
	@Override
	public int compare(Scores lhs, Scores rhs) {
		return (lhs.getScore() < rhs.getScore() ) ? -1: (lhs.getScore() > rhs.getScore()) ? 1:0 ;
	}
	
}

