package handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

// Idea for storing each message as a Text item in a VBox found here:
// https://stackoverflow.com/questions/38598020/javafx-how-to-store-and-format-multiple-paragraphs-in-some-kind-of-text-area
public class Get implements EventHandler<ActionEvent> {

	private TextField user;
	private TextField interlocutor;
	private PrintWriter output;

	private VBox conv;
	private BufferedReader input;

	public Get(TextField user, TextField interlocutor, PrintWriter output, BufferedReader input, VBox conv)
			throws IOException {
		this.user = user;
		this.output = output;
		this.input = input;
		this.interlocutor = interlocutor;
		this.conv = conv;

	}

	@Override
	public void handle(ActionEvent arg0) {
		output.println("get" + "," + user.getText() + "," + interlocutor.getText());
		try {
			String message = input.readLine();
			conv.getChildren().clear();
			while (!message.equals("finished")) {
				// In here do thing for VBox to hold
				Text text = new Text(message);
				conv.getChildren().add(text);
				message = input.readLine();
			}
//			System.out.println("Conversation history received");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
