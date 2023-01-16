package server;

import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {

	private Socket socket;

	public ServerThread(Socket s) {
		this.socket = s;
	}

	// Responsible for running a single client's interactions with the server.
	public void run() {
		try {
			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
			System.out.println("Client request received on socket: " + socket);
			output.println(new java.util.Date().toString());
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
