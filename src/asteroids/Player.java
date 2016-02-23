package asteroids;

import java.util.Random;

import core.Configuration;

public final class Player extends Actor {
	/**
	 * Written by Jack Rivadeneira
	 */
	private double t, ts;
	private double ta;
	public static int lives;
	private boolean gunReady;
	private Actor front;
	private ActorManager act;

	public Player(ActorManager ACT) {
		super(100, ACT);
		speed = 0.06;
		team = 1;
		front = new Ball(100, ACT);
		front.team = 1;
		front.length = 15;
		t = Math.PI / 2;
		ys = .01;
		ta = 0.00005;
		gunReady = true;
		lives = 3;
		this.act = ACT;
	}

	public Player(int Lives, ActorManager ACT) {
		super(100, ACT);
		speed = 0.003;
		team = 1;
		front = new Ball(100, ACT);
		front.team = 1;
		front.length = 15;
		t = -Math.PI / 2;
		ys = .01;
		// Display.act.add(front);
		ta = 0.00001;
		gunReady = true;
		lives = Lives;
		this.act = ACT;
	}

	protected void AI() {
		if (!front.alive)
			kill();
		control();
		keepIn();
	}

	public Actor getFront() {
		return this.front;
	}

	private void control() {
		if (Control.mouseControl)
			t = Math.atan2(x - Control.mouseX, Control.mouseY - y) + Math.PI
					/ 2;

		if (Control.keyW) {
			xs += speed * Math.cos(t);
			ys -= speed * Math.sin(t);
			showRocketTrail();
		}
		if (Control.keyA)
			ts -= ta;
		// if (Display.keyS) {
		ts *= 0.99;
		xs *= .99;
		ys *= .99;
		// }
		if (Control.keyD)
			ts += ta;
		if (Control.mouseOne)
			shoot();
		else
			gunReady = true;
		// keyboard control
		front.x = x + 30 * Math.cos(t);
		front.y = y + 30 * Math.sin(t);
		// mouse control

	}

	protected void move() {
		super.move();
		t += ts;
	}

	private void keepIn() {
		int xlim = Configuration.resolutions[Configuration.renderResolution][0];
		int ylim = Configuration.resolutions[Configuration.renderResolution][1];
		if (x < 0 || x > xlim)
			x = x > xlim ? xlim : 0;// ((int) (x / 800)) * (798) + 1;
		if (y < 0 || y > ylim)
			y = y > ylim ? ylim : 0;// ((int) (y / 600)) * (598) + 1;
	}

	private void shoot() {
		if (!gunReady)
			return;
		Projectile p = new Projectile(this, act);
		p.moveToward(front);
		act.add(p);
		gunReady = false;
	}

	private void showRocketTrail() {
		if (gen % 8 == 0) {
			Particle p = new Particle(200, 765, act);
			p.setLocation(x, y);
			p.speed = -1;
			Random r = new Random();

			p.moveToward(front);
			p.xs -= (r.nextDouble() / 4) - 0.125;
			p.ys -= (r.nextDouble() / 4) - 0.125;
			act.add(p);
		}
	}

	protected void kill() {
		int i = 0;
		while (i < 50) {
			Particle p = new Particle(50, 765, act);
			p.setLocation(x, y);
			p.speed = -1;
			Random r = new Random();
			p.moveToward(front);
			p.xs = r.nextDouble() - 0.5;
			p.ys = r.nextDouble() - 0.5;
			p.length = 10;
			act.add(p);
			i++;
		}
		alive = false;
		front.alive = false;
		lives--;
		if (lives == 0)
			Control.gameOver = true;
	}
}
