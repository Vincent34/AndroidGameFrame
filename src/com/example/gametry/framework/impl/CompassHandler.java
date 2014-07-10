package com.example.gametry.framework.impl;

import android.hardware.SensorEventListener;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;

public class CompassHandler implements SensorEventListener {
	float yaw,pitch,roll;

	public CompassHandler(Context context) {
		SensorManager manager = (SensorManager) context
				.getSystemService(Context.SENSOR_SERVICE);
		if (manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
			Sensor accelerometer = manager.getSensorList(
					Sensor.TYPE_ACCELEROMETER).get(0);
			manager.registerListener(this, accelerometer,
					SensorManager.SENSOR_DELAY_GAME);
		}
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	public void onSensorChanged(SensorEvent event) {
		yaw = event.values[0];
		pitch = event.values[1];
		roll = event.values[2];
	}

	public float getyaw() {
		return yaw;
	}

	public float getpitch() {
		return pitch;
	}

	public float getroll() {
		return roll;
	}
}
