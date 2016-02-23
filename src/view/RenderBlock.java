package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import asteroids.Actor;
import asteroids.ActorManager;
import asteroids.DrawData;

public class RenderBlock implements Runnable {
	private final int blockLocationX, blockLocationY;
	private int width;
	private int height;
	private BufferedImage buff;
	private Graphics2D bg;
	private ActorManager act;

	public RenderBlock(int Width, int Height, int BlockLocationX,
			int BlockLocationY, ActorManager ACT) {
		this.blockLocationX = BlockLocationX;
		this.blockLocationY = BlockLocationY;
		this.width = Width;
		this.height = Height;
		this.act = ACT;
	}

	private void handleGraphics() {
		this.buff = new BufferedImage(width, height,
				BufferedImage.TYPE_3BYTE_BGR);
		this.bg = (Graphics2D) buff.getGraphics();
		if (core.Configuration.AAEnabled) {
			this.bg.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			this.bg.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		}
	}

	public void run() {
		this.handleGraphics();
		this.draw(bg);
	}

	private void draw(Graphics2D bg) {
		for (Actor a : this.act.getActorList()) {
			this.drawActor(a.getDrawData());
		}
	}

	private boolean withinXBound(int x) {
		return !(x < this.width * this.blockLocationX || x > this.width
				+ this.width * this.blockLocationX);
	}

	private boolean withinYBound(int y) {
		return !(y < this.height * this.blockLocationY || y > this.height
				+ this.height * this.blockLocationY);
	}

	private boolean contains(DrawData a) {
		return withinXBound(a.x) && withinYBound(a.y);
	}

	private void drawActor(DrawData a) {
		if (a != null) {
			if (!this.contains(a))
				return;
			int r = 255;
			int g = 255;
			int b = 255;
			boolean rec = false;
			boolean fill = true;
			if (a.team == 2)
				fill = false;// r = b = 255;
			if (a.team == 1)
				b = 255;
			if (a.team == 6) {
				r = (int) a.lifetime;
				g = 0;
				b = 0;
			}
			if (a.team == 5) {
				int life = (int) a.lifetime;
				if (life < 256) {
					r = life;
					g = 0;
					b = 0;
				} else if (life < 511) {
					g = life - 256;
					b = 0;
				} else
					b = life % 256;
				rec = false;
			}
			int w = a.length;
			Color c = (new Color(r, g, b));
			bg.setColor(c);
			if (fill) {
				if (rec)
					bg.fillRect((int) a.x - (w / 2), (int) a.y - (w / 2), w, w);
				else
					bg.fillOval((int) a.x - (w / 2), (int) a.y - (w / 2), w, w);
			} else {
				if (rec)
					bg.drawRect((int) a.x - (w / 2), (int) a.y - (w / 2), w, w);
				else
					bg.drawOval((int) a.x - (w / 2), (int) a.y - (w / 2), w, w);
			}
		}
	}

}
