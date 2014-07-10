package com.example.gametry.framework;

public interface Game {
	public Input getInput();

	public FileIO getFileIO();

	public Graphics getGraphics();

	public Audio getAudio();
	
	public void showAd();

	public void setScreen(Screen scree);

	public Screen getCurrentScreen();

	public Screen getStartScreen();

}
