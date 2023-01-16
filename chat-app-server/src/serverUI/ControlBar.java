package serverUI;

import handlers.ToggleServer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import server.ServerHandler;

public class ControlBar extends HBox {

	// So these are accessible by other parts of the program.
	private ServerHandler server;
	private TextField portInput;
	private Button startStop;
	private Main m;

	public ControlBar(Main m) {
		this.m = m;
		// Sets up port information.
		VBox portInfo = new VBox();
		Label portLabel = new Label("Port #");
		portInput = new TextField();
		portInfo.getChildren().addAll(portLabel, portInput);
		this.getChildren().add(portInfo);

		// Sets up start/stop button.
		startStop = new Button("Start Server");
		startStop.setOnAction(new ToggleServer(this));
		this.getChildren().add(startStop);
	}

	// Getters below
	//////////////////////////////////

	public ServerHandler getServer() {
		return server;
	}

	public TextField getPortInput() {
		return portInput;
	}

	public Button getStartStop() {
		return startStop;
	}

	public Main getMain() {
		return m;
	}

}
