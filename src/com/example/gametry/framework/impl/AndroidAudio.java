package com.example.gametry.framework.impl;

import java.io.IOException;
import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import com.example.gametry.framework.Audio;
import com.example.gametry.framework.Sound;
import com.example.gametry.framework.Music;

public class AndroidAudio implements Audio {
	AssetManager assets;
	SoundPool soundPool;
	
	public AndroidAudio(Activity activity) {
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.assets = activity.getAssets();
		this.soundPool = new SoundPool(20,AudioManager.STREAM_MUSIC,0);
	}
	public Music newMusic(String fileName) {
		try {
			AssetFileDescriptor assetFileDescriptor = assets.openFd(fileName);
			return new AndroidMusic(assetFileDescriptor);
		} catch (IOException e) {
			throw new RuntimeException("Couldn't load music '" + fileName + "'");
		}
	}
	public Sound newSound(String fileName) {
		try {
			AssetFileDescriptor assetFileDescriptor = assets.openFd(fileName);
			int soundID = soundPool.load(assetFileDescriptor,0);
			return new AndroidSound(soundPool,soundID);
		} catch (IOException e) {
			throw new RuntimeException("Couldn't load sound '" + fileName + "'");
		}
	}

}
