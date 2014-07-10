package com.example.gametry.firsttry;

import android.util.Log;

import com.example.gametry.framework.Screen;
import com.example.gametry.framework.impl.AndroidGame;

public class firsttry extends AndroidGame {
	public Screen getStartScreen() {
		return new LoadingScreen(this);
	}
}
