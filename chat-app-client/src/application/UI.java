package application;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UI {

	public UI(BorderPane root) {
		doAddressBar(root);
		doChatBox(root);
	}

	private void doAddressBar(BorderPane root) {
		// Sets up fields required for user to connect to server. User creds, IP, port.

		// This is the address bar HBox.
		HBox addressBar = new HBox();
		root.setTop(addressBar);

		// First section with user credential information.
		VBox userInfo = new VBox();
		TextField username = new TextField("User Name");
		TextField password = new TextField("Password");
		userInfo.getChildren().addAll(username, password);

		// Second section with server address information.
		VBox serverInfo = new VBox();
		TextField ip = new TextField("IP Address");
		TextField port = new TextField("Port");
		serverInfo.getChildren().addAll(ip, port);

		// Third section with buttons.
		VBox buttons = new VBox();
		Button connect = new Button("Connect");
		Button register = new Button("Register");
		buttons.getChildren().addAll(connect, register);

		addressBar.getChildren().addAll(userInfo, serverInfo, buttons);

	}

	private void doChatBox(BorderPane root) {
		// Sets up the sent/received message areas, and the send/receive message
		// buttons.
		VBox chatBox = new VBox();
		root.setCenter(chatBox);

		ScrollPane conversation = new ScrollPane();
		VBox conv = new VBox();
		conversation.setContent(conv);

		TextField interlocutor = new TextField("Interlocutor");
		Button get = new Button("Get Messages");
		HBox convoManager = new HBox();
		convoManager.getChildren().addAll(interlocutor, get);

		HBox newMessageHolder = new HBox();
		TextField messageInput = new TextField("Message");
		Button send = new Button("->");
		newMessageHolder.getChildren().addAll(messageInput, send);

		chatBox.getChildren().addAll(conversation, convoManager, newMessageHolder);

	}

}
