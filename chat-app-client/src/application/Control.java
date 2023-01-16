package application;

import handlers.Connect;
import handlers.Register;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

// This class will run the business logic of the client component of the chat app.
public class Control {

	// Mostly UI elements that the logic will need to use to do stuff.
	BorderPane ui;

	TextField user;
	TextField pass;
	TextField ip;
	TextField port;

	TextField message;
	Button connect;

	public Control(BorderPane root) {
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

		VBox buttons = (VBox) topBar.getChildren().get(2);
		connect = (Button) buttons.getChildren().get(0);
		connect.setOnAction(new Connect(ui));

		Button register = (Button) buttons.getChildren().get(1);
		register.setOnAction(new Register(ui));
	}

}
