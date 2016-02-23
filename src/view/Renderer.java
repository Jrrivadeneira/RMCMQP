package view;

import java.awt.image.BufferedImage;

import core.Configuration;

public class Renderer implements Runnable {
	BufferedImage buff;

	public void run() {
		int cr = Configuration.currentResolution;
		int r[][] = Configuration.resolutions;
		buff = new BufferedImage(r[cr][0], r[cr][1], BufferedImage.TYPE_3BYTE_BGR);
	}
}
