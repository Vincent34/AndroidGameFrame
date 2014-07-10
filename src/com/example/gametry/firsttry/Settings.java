package com.example.gametry.firsttry;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import android.util.Log;

import com.example.gametry.framework.FileIO;

public class Settings {
	public static long[] highScores = new long[] {3000,2000,1000,500,200};
	public static void load(FileIO files) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(files.readFile(".nomove")));
			for (int i = 0;i < 5;i++) {
				String str = in.readLine();
				highScores[i] = Long.parseLong(str);
			}
			Log.d("file","file read");
		} catch (IOException e) {
			Log.d("file","file not found");
			//use the default
		} catch (NumberFormatException e) {
			//use the default
		} finally {
			try {
				if (in != null) in.close();
			} catch (IOException e) {

			}
		}
	}
	public static void save(FileIO files) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(files.writeFile(".nomove")));
			for (int i = 0;i < 5;i++) {
				out.write(Long.toString(highScores[i]) + '\n');
				Log.d("file","file writted");
			}
		} catch (IOException e) {
			Log.d("file","file not found");
			
		} finally {
			try {
				if (out != null)
					out.close();
			}catch (IOException e) {
				
			}
		}
	}
	public static void addScore(long score) {
		for (int i = 0;i < 5;i++) {
			if (highScores[i] < score) {
				for (int j = 4;j > i;j--) {
					highScores[j] = highScores[j - 1];
				}
				highScores[i] = score;
				break;
			}
		}
	}

}
