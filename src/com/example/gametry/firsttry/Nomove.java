package com.example.gametry.firsttry;

import java.util.ArrayList;
import java.util.List;

public class Nomove {
	public static int count;
	private long score;
	public static int endIndex;
	long[] startTime;
	public static int[] startTouchX, startTouchY;
	public static int[] endTouchX, endTouchY;

	public Nomove() {
		count = 0;
		startTime = new long[10];
	}

	public void start(int index) {
		if (index < count) return;
		count++;
		startTime[index] = System.currentTimeMillis();
	}

	public void end() {
		Settings.addScore(getScore());
	}

	public long getScore() {
		score = 0;
		long currentTime = System.currentTimeMillis();
		for (int i = 0;i < count;i++) 
			score += (currentTime - startTime[i]) * (i + 1);
		return score;
	}

	public int getEndIndex() {
		return endIndex;
	}
}
