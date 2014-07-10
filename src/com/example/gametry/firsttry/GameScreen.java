package com.example.gametry.firsttry;

import java.util.List;

import android.graphics.Color;

import com.example.gametry.framework.*;
import com.example.gametry.framework.Input.TouchEvent;

public class GameScreen extends Screen {
	enum GameState {
		Ready,
		Running,
		GameOver
	}
	GameState state = GameState.Ready;
	Nomove nomove;
	boolean downflag = false;
	long oldScore = 0;
	int count = 0;
	String score = "0";
	
	public GameScreen(Game game) {
		super(game);
		nomove = new Nomove();
	}
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		if (state == GameState.Ready) {
			updateReady(touchEvents);
		}
		if (state == GameState.Running) {
			updateRunning(touchEvents);
			return;
		}
		if (state == GameState.GameOver){
			updateGameOver(touchEvents);
		}
	}
	public void updateReady(List<TouchEvent> touchEvents) {
		if (touchEvents.size() > 0) state = GameState.Running;
		int len = touchEvents.size();
		for (int i = 0;i < len;i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {
				nomove.start(count++);
			}
		}
	}
	public void updateRunning(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		oldScore = nomove.getScore();
		score = Long.toString(oldScore);
		for (int i = 0;i < len;i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {
				nomove.start(count++);
			} else {
				nomove.end();
				Settings.save(game.getFileIO());
				state = GameState.GameOver;
				game.showAd();
				return;
			}
		}
	}
	public void updateGameOver(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0;i < len;i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) downflag = true; 
		}
		if (!downflag) return;
		for (int i = 0;i < len;i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (event.x > 20 && event.x < 300
						&& event.y > 250 && event.y < 290) {
					game.setScreen(new MainMenuScreen(game));
					return;
				}
				if (event.x > 20 && event.x < 300
						&& event.y > 180 && event.y < 220) {
					state = GameState.Ready;
					downflag = false;
					score = "0";
					oldScore = 0;
					count = 0;
					nomove = new Nomove();
				}
			}
		}
	}
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.clear(Color.WHITE);
		if (state == GameState.Ready)
			drawReady();
		if (state == GameState.Running) 
			drawRunning();
		if (state == GameState.GameOver) 
			drawGameOver();
		drawText(g,score,g.getWidth() / 2 - score.length() * 24 / 2,50);
	}
	public void drawReady() {
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.ready, 20, g.getHeight() / 2 - 50);
	}
	public void drawRunning() {
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.nomove,20,g.getHeight() / 2 - 25);
	}
	public void drawGameOver() {
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.tryagain,20,g.getHeight() / 2 - 60);
		g.drawPixmap(Assets.mainmenu, 20, g.getHeight() / 2 + 10);
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
	public void pause() {
		
	}
	public void resume() {
		
	}
	public void dispose() {
		
	}

}
