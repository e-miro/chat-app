package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerHandler extends Thread {
	private int port = 9009;
	private boolean running = true;

	public ServerHandler(int port) {
		this.port = port;
	}

	public void run() {
		System.out.println("Server started on port: " + port);
		System.out.println("Starting connection listener...");
		ServerSocket listener;
		try {
			listener = new ServerSocket(port);
			try {
				while (running) {
					System.out.println("Waiting for connection.");
					Socket socket = listener.accept();
					new ServerThread(socket).start();
				}
			} finally {
				listener.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Server shutting down.");
	}

	// Stops the server when called.
	public void cease() {
		this.running = false;
	}

	// Getters and setters below
	///////////////////////////////////////

	public int getPort() {
		return port;
	}

}
