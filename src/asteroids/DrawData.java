package asteroids;

public class DrawData {
	public int x;
	public int y;
	public double mass;
	public int length;
	public int team;
	public int lifetime;

	public DrawData(int X, int Y, double MASS, int LENGTH, int TEAM,
			int LIFETIME) {
		x = X;
		y = Y;
		mass = MASS;
		length = LENGTH;
		team = TEAM;
		lifetime = LIFETIME;
	}

	public DrawData(int X, int Y, double MASS, int LENGTH, int TEAM) {
		x = X;
		y = Y;
		mass = MASS;
		length = LENGTH;
		team = TEAM;
	}

}
