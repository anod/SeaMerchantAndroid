package com.example.seamerchant.game;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Scores> {
	@Override
	public int compare(Scores lhs, Scores rhs) {
		return (lhs.Score() < rhs.Score() ) ? -1: (lhs.Score() > rhs.Score()) ? 1:0 ;
	}
	
}

