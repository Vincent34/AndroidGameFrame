package com.example.gametry.firsttry;

import java.util.List;

import android.graphics.Color;

import com.example.gametry.framework.Game;
import com.example.gametry.framework.Graphics;
import com.example.gametry.framework.Screen;
import com.example.gametry.framework.Input.TouchEvent;

public class HighscoreScreen extends Screen {
	String lines[] = new String[5];
	
	public  HighscoreScreen(Game game) {
		super(game);
		for (int i = 0;i < 5;i++) {
			lines[i] = "" + Integer.toString(i + 1) + "  " + Long.toString(Settings.highScores[i]);
		}
	}
	public void drawText(Graphics g,String line,int x,int y) {
		int len = line.length();
		for (int i = 0;i < len;i++) {
			char ch = line.charAt(i);
			if (ch == ' ') {
				x += 24;
				continue;
			}
			int srcX = 0;
			int srcWidth = 0;
			srcX = (ch - '0') * 24;
			srcWidth = 24;
			g.drawPixmap(Assets.number,x,y,srcX,0,srcWidth,34);
			x += srcWidth;
		}
	}
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		int len = touchEvents.size();
		for (int i = 0;i < len;i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP)
				if (event.x < 60 && event.y > 420) {
					game.setScreen(new MainMenuScreen(game));
					return;
				}
		}
	}
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.clear(Color.WHITE);
		g.drawPixmap(Assets.highscores, 20, 20);
		g.drawPixmap(Assets.backbtn,10,430);
		int y = 100;
		 for (int i = 0;i < 5;i++) {
			 drawText(g,lines[i],20,y);
			 y += 50;
		 }
	}
	public void pause() {
		
	}
	public void resume() {
		
	}
	public void dispose() {
		
	}

}
