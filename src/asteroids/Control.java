package asteroids;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Control implements MouseListener, KeyListener, MouseMotionListener {
	/**
	 * Written by Jack Rivadeneira
	 */

	// private static ArrayList<DrawData> drawQueue = new ArrayList<DrawData>();
	public static int clickX, clickY;
	public static int mouseX, mouseY;

	public static boolean mouseControl = false;

	public static boolean keyW, keyA, keyS, keyD, keyQ, mouseOne;

	public static boolean gameOver;

	public static int Score = 0;

	public Control() {

	}

	public void delay(int time) {
		try {
			Thread.sleep(time);
		} catch (Exception ex) {
		}

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			Control.mouseOne = true;
			Control.clickX = e.getX();
			Control.clickY = e.getY();
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			Control.mouseOne = false;
	}

	public void keyPressed(KeyEvent e) {
		String c = "" + e.getKeyChar();
		if (c.equalsIgnoreCase("w"))
			Control.keyW = true;
		if (c.equalsIgnoreCase("a"))
			Control.keyA = true;
		if (c.equalsIgnoreCase("s"))
			Control.keyS = true;
		if (c.equalsIgnoreCase("d"))
			Control.keyD = true;
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
	}

	public void keyReleased(KeyEvent e) {
		String c = "" + e.getKeyChar();
		if (c.equalsIgnoreCase("w"))
			Control.keyW = false;
		if (c.equalsIgnoreCase("a"))
			Control.keyA = false;
		if (c.equalsIgnoreCase("s"))
			Control.keyS = false;
		if (c.equalsIgnoreCase("d"))
			Control.keyD = false;
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		String c = "" + e.getKeyChar();
		if (c.equalsIgnoreCase("m"))
			Control.mouseControl = !Control.mouseControl;

	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {
		Control.mouseX = e.getX();
		Control.mouseY = e.getY();
	}
}
