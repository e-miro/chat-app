package handlers;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import application.Connection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

// Need to set up exception handlers so the program can survive input errors.
// Also need to set up credentials checking system.
public class Connect implements EventHandler<ActionEvent> {

	BorderPane ui;
	private TextField user;
	private TextField pass;
	private TextField ip;
	private TextField port;

	public Connect(BorderPane root) {
		this.ui = root;
		setVars();
	}

	private void setVars() {
		// Assigns all variables for the program to use for stuff.
		HBox topBar = (HBox) ui.getTop();

		VBox userPass = (VBox) topBar.getChildren().get(0);
		user = (TextField) userPass.getChildren().get(0);
		pass = (TextField) userPass.getChildren().get(1);

		VBox address = (VBox) topBar.getChildren().get(1);
		ip = (TextField) address.getChildren().get(0);
		port = (TextField) address.getChildren().get(1);
	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Socket socket;
		try {
			System.out.println(ip.getText());
			socket = new Socket(ip.getText(), Integer.parseInt(port.getText()));
			new Connection(socket, ui).start();
		} catch (NumberFormatException e) {
			// Will run if the port value is malformed.
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// Will run if the IP is malformed or invalid.
			e.printStackTrace();
		} catch (IOException e) {
			// Will run if the connection fails.
			e.printStackTrace();
		} catch (Exception e) {
			// Will run if all else fails.
			e.printStackTrace();
		}
	}

}
