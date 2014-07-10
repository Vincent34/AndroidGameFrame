package com.example.gametry.framework;

import com.example.gametry.framework.Graphics.PixmapFormat;

public interface Pixmap {
	public int getWidth();

	public int getHeight();

	public PixmapFormat getFormat();

	public void dispose();

}
