package communications;

import java.net.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

public class RobotServer implements Runnable {

	private int portNumber;
	private boolean alive;
	private boolean isCore;
	private ArrayList<RobotServer> clientHandlers;

	public RobotServer(boolean core, int port) {
		this.portNumber = port;
		alive = true;
		this.isCore = core;
		clientHandlers = new ArrayList<RobotServer>();
	}

	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(portNumber,8);
			while (alive) {
				Socket clientSocket = serverSocket.accept();
				PrintWriter out = new PrintWriter(
						clientSocket.getOutputStream(), true);
				BufferedReader in;
				in = new BufferedReader(new InputStreamReader(
						clientSocket.getInputStream()));
				String inputLine;

				// if this is the central server then we need to handle this
				// client on another port.
				if (this.isCore) {
					portNumber++;
					out.println(portNumber);
					RobotServer handler = new RobotServer(false, portNumber);
					new Thread(handler).start();
					System.out.println("Handler opened on " + portNumber);
					continue;
				}

				while ((inputLine = in.readLine()) != null) {
					if (inputLine.startsWith("r")) {
						Random r = new Random();
						String message = "";
						message = "" + r.nextInt();

						out.println(message);
					} else {
						out.println(inputLine);
					}
				}
			}
			serverSocket.close();
		} catch (IOException e) {
			System.out
					.println("Exception caught when trying to listen on port "
							+ portNumber + " or listening for a connection");
			System.out.println(e.getMessage());
		}
		for (RobotServer handle : this.clientHandlers) {
			handle.kill();
		}
	}

	private void kill() {
		this.alive = false;
	}

	public static void main(String[] args) throws IOException {
		Thread t = new Thread(new RobotServer(true, 8000));
		t.start();
	}

}
