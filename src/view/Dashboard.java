package view;

import java.awt.*;
import java.awt.event.*;
import asteroids.Control;

public class Dashboard extends Panel {

	/**
	 * Written by Jack Rivadeneira
	 */
	private static final long serialVersionUID = -1761774388143094586L;

	Image buff;
	Graphics2D bg;
	int gen = 0;
	float uiSpace = 0.15f;
	public static boolean run = true;

	public Dashboard() {
		this.setBackground(Color.BLACK);
		this.addKeyListener(new Control());
		this.setVisible(true);
	}

	public void paint(Graphics g) {
		if (run) {
			newFrame();
			drawDashboard();
			repaint();
		}
	}

	private void drawDashboard() {
		bg.setColor(Color.GREEN);
		drawDashValue(200, 200, (int)(Math.sqrt(gen*30)) % 360, "Speed");
	}

	private void drawDashValue(int x, int y, int value, String name) {
		Color Before = bg.getColor();
		bg.drawString(name, x, y);
		bg.fillArc(x, y, 150, 150, 180 - value, value);
		bg.setColor(Color.BLACK);
		bg.fillArc(x + 25, y + 25, 100, 100, 179 - value, 360);
		bg.setColor(Before);
		bg.drawString((value * 100) / 360 + "%", x + 65, y + 75);
	}

//	private int[] percentToPosition(float f) {
//		double x = core.Configuration.getRenderResolution()[0] * f;
//		double y = core.Configuration.getRenderResolution()[1] * f;
//		int[] r = { (int) x, (int) y };
//		return r;
//	}

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

	public void setWeirdResolution() {
		core.Configuration.resolutions[core.Configuration.currentResolution][0] = this
				.getWidth();
		core.Configuration.resolutions[core.Configuration.currentResolution][1] = this
				.getHeight();

	}

	public void update(Graphics g) {
		setWeirdResolution();
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

	public static void main(String[] args) {
		System.out.println("Dashboard Start!");
		Frame f = new Frame();
		f.setBackground(Color.BLACK);
		f.setVisible(true);
		Dashboard a = new Dashboard();

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				f.dispose();
			}
		});
		a.setBackground(Color.BLACK);
		f.add(a);
		f.setVisible(true);
		run = true;
	}
}
