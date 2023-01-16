package handlers;

import java.io.IOException;
import java.net.UnknownHostException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import server.ServerHandler;
import serverUI.ControlBar;

public class ToggleServer implements EventHandler<ActionEvent> {

	Button startStop;
	TextField portInput;
	ServerHandler server;
	ControlBar setup;

	// Feeds the objects the event handler will need to work with in.
	public ToggleServer(ControlBar setup) {
		this.startStop = setup.getStartStop();
		this.portInput = setup.getPortInput();
		this.server = setup.getServer();
		this.setup = setup;
	}

	@Override
	public void handle(ActionEvent arg0) {
		// This is tasked with toggling the server on and off.
		if (startStop.getText().equals("Start Server")) {
			int serverPort = Integer.parseInt(portInput.getText());
			server = new ServerHandler(serverPort);
			server.start();
			startStop.setText("Stop Server");
		} else {
			server.cease();
			try {
				new ClientMain("localhost", server.getPort());
			} catch (UnknownHostException e) {
				// This happens if the server IP address or port number is incorrectly set
				// in the ClientMain.
				e.printStackTrace();
			} catch (IOException e) {
				// Network connectivity issues? IDK.
				e.printStackTrace();
			}
			startStop.setText("Start Server");
		}
	}

}
