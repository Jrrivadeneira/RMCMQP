package asteroids;

public class Projectile extends Actor {
	/**
	 * Written by Jack Rivadeneira
	 */

	private Actor parent;

	public Projectile(Actor Parent, ActorManager ACT) {
		super(20, ACT);
		speed = 0.8;
		parent = Parent;
		team = parent.team;
		x = parent.x;
		y = parent.y;
		length = 2;
	}

	protected void AI() {
		int xlim = core.Configuration.getRenderResolution()[0];
		int ylim = core.Configuration.getRenderResolution()[1];
		if (x > xlim || x < 0 || y > ylim || y < 0)
			alive = false;
		/*
		 * int i = 0; while (i < Display.act.size()){ if
		 * (contact(Display.act.get(i)) && Display.act.get(i) != parent) {
		 * Display.act.get(i).alive = false; alive = false; break; } i++; }
		 */
	}
}
