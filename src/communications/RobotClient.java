package communications;

import java.io.*;
import java.net.*;

public class RobotClient implements Runnable {
	String hostName;
	int port;
	boolean alive;

	public RobotClient(int portNumber) {
		hostName = "localhost";
		port = portNumber;
		alive = true;
	}

	public static RobotClient connectToRobot() {

		// //////////////////////////////////////////
		String hostName = "localhost";
		int portNumber = 8000;
		int newPort = 0;
		try {
			Socket echoSocket = new Socket(hostName, portNumber);
			BufferedReader in;
			in = new BufferedReader(new InputStreamReader(
					echoSocket.getInputStream()));
			newPort = Integer.parseInt(in.readLine());
			System.out.println("Port: " + newPort);
			echoSocket.close();
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to "
					+ hostName);
			System.exit(1);
		}
		// ////////////////////////////////////////
		RobotClient rc = new RobotClient(newPort);
		Thread t = new Thread(rc);
		t.start();
		return rc;
	}

	public static void main(String[] args) throws IOException {
		connectToRobot();
	}

	public void run() {
		try {
			Socket socket = new Socket(this.hostName, this.port);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(
					System.in));
			String userInput;
			while ((userInput = stdIn.readLine()) != null) {
				out.println(userInput);
				System.out.println(in.readLine());
			}
			socket.close();
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to "
					+ hostName);
			System.exit(1);
		}
	}
}