package com.example.gametry.firsttry;

import java.util.List;

import android.graphics.Color;
import android.widget.*;
import android.util.Log;

import com.example.gametry.framework.Game;
import com.example.gametry.framework.Screen;
import com.example.gametry.framework.Graphics;
import com.example.gametry.framework.Input.TouchEvent;
public class MainMenuScreen extends Screen {
	public MainMenuScreen(Game game) {
		super(game);
	}
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		int len = touchEvents.size();
		for (int i = 0;i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event,20,50,280,120)) {
					Assets.biu.play(1);
				}
				if (inBounds(event,20,260,280,40)) {
					game.setScreen(new GameScreen(game));
					
				}
				if (inBounds(event,20,380,280,40)) {
					game.setScreen(new HighscoreScreen(game));
				}
			}
		}
		
	}
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.clear(Color.WHITE);
		g.drawPixmap(Assets.nomove, 20, 50);
		g.drawPixmap(Assets.play,20,260);
		g.drawPixmap(Assets.highscores, 20, 380);
		g.drawPixmap(Assets.medal, 0, 380);
		g.drawPixmap(Assets.medal, 300, 380);
	}
	public void pause() {
		
	}
	public void resume() {
		
	}
	public void dispose() {
		
	}
	private boolean inBounds(TouchEvent event,int x,int y,int width,int height) {
		if (event.x > x && event.x < x + width - 1 &&
				event.y > y && event.y < y + height - 1)
			return true;
		else 
			return false;
		
	}

}
