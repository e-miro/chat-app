package handlers;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Send implements EventHandler<ActionEvent> {
	private Socket socket;

	private TextField message;
	private TextField user;
	private PrintWriter output;
	Button get;

	private TextField interlocutor;

	public Send(Socket socket, TextField message, TextField user, TextField interlocutor, PrintWriter output,
			Button get) throws IOException {
		this.socket = socket;
		this.message = message;
		this.user = user;
		this.output = output;
		this.interlocutor = interlocutor;
		this.get = get;

	}

	@Override
	public void handle(ActionEvent arg0) {
		output.println("sent" + "," + user.getText() + "," + interlocutor.getText() + "," + message.getText());
		message.setText("Message");
		this.get.fire();
	}

}
