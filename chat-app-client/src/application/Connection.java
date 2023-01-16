package application;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import handlers.Get;
import handlers.Send;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Connection extends Thread {

	private Socket socket;
	private Output output;
	private Input input;
	private BorderPane ui;
	private TextField user;
	private TextField pass;
	private TextField ip;
	private TextField port;

	private AES aes;

	private VBox conv;
	private TextField message;
	private TextField interlocutor;

	public Connection(Socket socket, BorderPane root) throws Exception {
		// Assigns the fields required for the rest of the thread.
		this.socket = socket;
		try {
			output = new Output(socket.getOutputStream(), true);
			input = new Input(new InputStreamReader(socket.getInputStream()));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.ui = root;
		setVars();
		sendKey();
		login();

	}

	public Connection(Socket socket, BorderPane ui, TextField user, TextField pass) throws IOException {
		// Constructor for if user is registering a new account. Assigns relevant
		// fields.
		this.socket = socket;
		try {
			output = new Output(socket.getOutputStream(), true);
			input = new Input(new InputStreamReader(socket.getInputStream()));
			output.println("register," + user.getText() + "," + pass.getText());
			if (!input.readLine().contains("created")) {
				Alert accountTaken = new Alert(AlertType.ERROR, "Please try different credentials, or log in.");
				accountTaken.setHeaderText("Username Taken!");
				accountTaken.showAndWait();
				socket.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.ui = ui;
		setVars();
		sendKey();
		login();
	}

	private void sendKey() {
		// TODO Auto-generated method stub
		aes = new AES();
		input.setAES(aes);
		output.setAES(aes);
		try {
			aes.sendKey(socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("sendkey complete");
	}

	private void login() throws IOException {
		// Checks user credentials against server record.
		System.out.println("login started");
		output.println("login," + user.getText() + "," + pass.getText());

		String response = input.readLine();
		if (response.contains("Invalid")) {
			// Closes everything down, and alerts user.
			Alert loginFail = new Alert(AlertType.ERROR);
			loginFail.setContentText("Incorrect Username or Password.");
			loginFail.setHeaderText("Login Failed!");
			loginFail.show();
			socket.close();
		} else {
			// To stop user from messing with credentials after they're in.
			System.out.println("Login successful.");
			user.setEditable(false);
			pass.setEditable(false);
			ip.setEditable(false);
			port.setEditable(false);
		}
	}

	public void run() {

	}

	// Utility methods below
	///////////////////////////////////////////

	private void setVars() {
		// Assigns all variables for the program to use for stuff. Bloody vital.
		HBox topBar = (HBox) ui.getTop();

		VBox userPass = (VBox) topBar.getChildren().get(0);
		user = (TextField) userPass.getChildren().get(0);
		pass = (TextField) userPass.getChildren().get(1);

		VBox addresses = (VBox) topBar.getChildren().get(1);
		ip = (TextField) addresses.getChildren().get(0);
		port = (TextField) addresses.getChildren().get(1);

		VBox chat = (VBox) ui.getCenter();
		ScrollPane conversation = (ScrollPane) chat.getChildren().get(0);
		conv = (VBox) conversation.getContent();

		HBox convoManager = (HBox) chat.getChildren().get(1);
		interlocutor = (TextField) convoManager.getChildren().get(0);
		HBox messageAndButton = (HBox) chat.getChildren().get(2);
		message = (TextField) messageAndButton.getChildren().get(0);

		Button getButton = (Button) convoManager.getChildren().get(1);
		try {
			getButton.setOnAction(new Get(user, interlocutor, output, input, conv));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Get function failed to assign to button");
			e.printStackTrace();
		}

		Button send = (Button) messageAndButton.getChildren().get(1);
		try {
			send.setOnAction(new Send(socket, message, user, interlocutor, output, getButton));
		} catch (IOException e) {
			// Will need to handle when the send function can't connect.
			System.out.println("Send function failed to assign to button");
			e.printStackTrace();
		}

		// This runs the regular client updates.
		new Heartbeat(getButton).start();

	}

}
