package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread extends Thread {
	Socket s;

	public ClientThread(Socket s) {
		this.s = s;
	}

	public void run() {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String response = input.readLine();
			System.out.println(response);
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
