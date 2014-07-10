package com.example.gametry.framework.impl;

import android.media.SoundPool;
import com.example.gametry.framework.Sound;

public class AndroidSound implements Sound {
	int soundID;
	SoundPool soundPool;

	public AndroidSound(SoundPool soundPool, int soundID) {
		this.soundID = soundID;
		this.soundPool = soundPool;
	}

	public void play(float volume) {
		soundPool.play(soundID, volume, volume, 0, 0, 1);
	}

	public void dispose() {
		soundPool.unload(soundID);
	}

}
