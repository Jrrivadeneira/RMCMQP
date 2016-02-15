package view;

import java.awt.*;
import java.awt.event.*;
import asteroids.Actor;
import asteroids.ActorManager;
import asteroids.Control;
import asteroids.DrawData;

public class BrokenArrow extends Panel {

	/**
	 * Written by Jack Rivadeneira
	 */
	
	private static final long serialVersionUID = 6327066632666023126L;
	Image buff;
	Graphics2D bg;
	int gen = 0;
	float uiSpace = 0.15f;
	public ActorManager act = new ActorManager(800);
	public static boolean run = false;

	public BrokenArrow() {
		this.setBackground(Color.BLACK);
		this.addKeyListener(new Control());
		Thread t = new Thread(act);
		t.start();
	}

	public void paint(Graphics g) {
		if (run) {
			newFrame();
			drawActors();
			drawField();
			drawDashboard();
			drawDatastream();

			repaint();
		}
	}

	private void drawDatastream() {

	}

	private void drawDashboard() {
		int speed = act.get(0).getSpeed();
		int[] speedPosition = percentToPosition(0.88f);
		speedPosition[0] /= 2;

		drawDashValue(speedPosition[0], speedPosition[1], speed * 2);
	}

	private void drawDashValue(int x, int y, int value) {
		bg.fillArc(x, y, 150, 150, 180 - value, value);
	}

	private void drawField() {
		bg.setColor(Color.GREEN);
		float xwidth = 1 - (2 * uiSpace);
		int[] start = percentToPosition(uiSpace);
		int[] size = percentToPosition(xwidth);

		bg.drawRect(start[0], start[1], size[0], size[1]);
	}

	private int[] percentToPosition(float f) {
		double x = core.Configuration.getRenderResolution()[0] * f;
		double y = core.Configuration.getRenderResolution()[1] * f;
		int[] r = { (int) x, (int) y };
		return r;

	}

	private void newFrame() {
		if (this.isVisible()) {
			int imgWidth = core.Configuration.resolutions[core.Configuration.currentResolution][0];
			int imgHeight = core.Configuration.resolutions[core.Configuration.currentResolution][1];
			buff = this.getParent().createImage(imgWidth, imgHeight);
			bg = (Graphics2D) buff.getGraphics();
			if (core.Configuration.AAEnabled) {
				bg.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				bg.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
						RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			}
		}
	}

	public void update(Graphics g) {
		g.drawImage(
				buff,
				0,
				0,
				core.Configuration.resolutions[core.Configuration.currentResolution][0],
				core.Configuration.resolutions[core.Configuration.currentResolution][1],
				0,
				0,
				core.Configuration.resolutions[core.Configuration.renderResolution][0],
				core.Configuration.resolutions[core.Configuration.renderResolution][1],
				this);
		if (gen % 10 == 0)
			delay(5);
		newFrame();
		gen++;
		paint(g);
	}

	private void delay(long t) {
		try {
			Thread.sleep(t);
		} catch (Exception ex) {
		}
	}

	public void drawActors() {
		gen++;
		int i = 0;
		while (i < act.size()) {
			Actor a = act.get(i);
			if (a != null)
				drawActor(act.get(i).getDrawData());
			i++;
		}
	}

	private void drawActor(DrawData a) {
		if (a != null) {
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

	public static void main(String[] args) {
		System.out.println("BROKEN ARROW START!");
		Frame f = new Frame();
		f.setBackground(Color.BLACK);
		f.setVisible(true);
		BrokenArrow a = new BrokenArrow();

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				f.dispose();
				a.act.kill();
			}
		});
		a.setBackground(Color.BLACK);
		f.add(a);
		f.setVisible(true);
		run = true;
	}
}
