package communications;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class RobotServer {

	public RobotServer() {
		try {
			ServerSocket s = new ServerSocket(8000);
			Socket client = s.accept();
			PrintWriter outbound = new PrintWriter(client.getOutputStream(),
					true);
			BufferedReader inbound = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			String ln = "";
			while ((ln = inbound.readLine()) != null) {
				System.out.println(ln);
				if (ln.equals("bye"))
					break;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("Server Start!");
		
	}

}
