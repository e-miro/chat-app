package handlers;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMain {

	public ClientMain(String address, int port) throws UnknownHostException, IOException {
		Socket socket = new Socket(address, port);
		new ClientThread(socket).start();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int count = 0;
		while (count < 5) {
			try {
				new ClientMain("localhost", 45678);
				count++;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
