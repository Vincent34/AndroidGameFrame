package com.example.gametry.framework.impl;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;
import android.util.Log;

import cn.waps.AppConnect;

import com.example.gametry.framework.*;

public class AndroidGame extends Activity implements Game {
	AndroidFastRenderView renderView;
	Graphics graphics;
	Audio audio;
	Input input;
	FileIO fileIO;
	Screen screen;
	WakeLock wakeLock;
	long lastShow = 0;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
		int frameBufferWidth = isLandscape ? 480 : 320;
		int frameBufferHeight = isLandscape ? 320 : 480;
		Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,frameBufferHeight,Config.RGB_565);
		float scaleX = (float) frameBufferWidth / getWindowManager().getDefaultDisplay().getWidth();
		float scaleY = (float) frameBufferHeight / getWindowManager().getDefaultDisplay().getHeight();
		renderView = new AndroidFastRenderView(this, frameBuffer);
		graphics = new AndroidGraphics(getAssets(), frameBuffer);
		fileIO = new AndroidFileIO(this);
		audio = new AndroidAudio(this);
		input = new AndroidInput(this,renderView,scaleX,scaleY);
		screen = getStartScreen();
		AppConnect.getInstance(this);
		AppConnect.getInstance(this).initPopAd(this);
		setContentView(renderView);
		
		PowerManager powerManager = (PowerManager)getSystemService(Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");
	}
	
	public void onResume() {
		super.onResume();
		wakeLock.acquire();
		screen.resume();
		renderView.resume();
	}
	public void onPause() {
		super.onPause();
		wakeLock.release();
		renderView.pause();
		screen.pause();
		if (isFinishing()) {
			AppConnect.getInstance(this).close();
			screen.dispose();
		}
	}
	public void showAd() {
		long currenttime = System.currentTimeMillis();
		if (currenttime - lastShow < 300000) return;
		AppConnect.getInstance(this).showPopAd(this);
		lastShow = currenttime;
	}
	public Input getInput() {
		return input;
	}
	public FileIO getFileIO() {
		return fileIO;
	}
	public Graphics getGraphics() {
		return graphics;
	}
	public Audio getAudio() {
		return audio;
	}
	public void setScreen(Screen screen) {
		if (screen == null) {
			throw new IllegalArgumentException("Screen must not be null");
		}
		this.screen.pause();
		this.screen.dispose();
		screen.resume();
		screen.update(0);
		this.screen = screen;
	}
	public Screen getCurrentScreen() {
		return screen;
	}
	public Screen getStartScreen() {
		return screen;
	}

}
