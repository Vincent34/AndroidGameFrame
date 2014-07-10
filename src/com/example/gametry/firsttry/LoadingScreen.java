package com.example.gametry.firsttry;

import android.util.Log;

import com.example.gametry.framework.Game;
import com.example.gametry.framework.Graphics;
import com.example.gametry.framework.Screen;
import com.example.gametry.framework.Graphics.PixmapFormat;
public class LoadingScreen extends Screen {
	public LoadingScreen(Game game) {
		super(game);
	}
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		Assets.firsttry = g.newPixmap("firsttry.png",PixmapFormat.ARGB8888);
		Assets.nomove = g.newPixmap("nomove.png", PixmapFormat.ARGB8888);
		Assets.classical = g.newPixmap("classical.png", PixmapFormat.ARGB8888);
		Assets.challenge = g.newPixmap("challenge.png", PixmapFormat.ARGB8888);
		Assets.highscores = g.newPixmap("highscores.png", PixmapFormat.ARGB8888);
		Assets.medal = g.newPixmap("medal.png", PixmapFormat.ARGB8888);
		Assets.play = g.newPixmap("play.png",PixmapFormat.ARGB8888);
		Assets.ready = g.newPixmap("ready.png", PixmapFormat.ARGB8888);
		Assets.tryagain = g.newPixmap("tryagain.png", PixmapFormat.ARGB8888);
		Assets.mainmenu = g.newPixmap("mainmenu.png", PixmapFormat.ARGB8888);
		Assets.number = g.newPixmap("number.png", PixmapFormat.ARGB8888);
		Assets.backbtn = g.newPixmap("backbtn.png", PixmapFormat.ARGB8888);
		Assets.biu = game.getAudio().newSound("biu.mp3");
		Settings.load(game.getFileIO());
		game.setScreen(new MainMenuScreen(game));
	}
	public void present(float deltaTime) {
	}
	
	public void pause() {
		
	}
	public void resume() {
		
	}
	public void dispose() {
		
	}
}
