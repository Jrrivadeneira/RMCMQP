package core;

public class Configuration {
	public static boolean AAEnabled = true;
	public static int[][] resolutions = { { 1280, 720 }, { 1600, 900 },
			{ 1920, 1080 } };
	public static int currentResolution = 2;
	public static int renderResolution = 2;

	public static int[] getCurrentResolution() {
		return resolutions[currentResolution];
	}

	public static int[] getRenderResolution() {
		return resolutions[renderResolution];
	}

}
