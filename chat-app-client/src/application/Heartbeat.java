package application;

import javafx.application.Platform;
import javafx.scene.control.Button;

// This is responsible for the message updating thing. It's very cool.
public class Heartbeat extends Thread {

	private Button get;

	public Heartbeat(Button get) {
		this.get = get;
	}

	// Found out about "Runnable" from here:
	// https://stackoverflow.com/questions/20497845/constantly-update-ui-in-java-fx-worker-thread
	public void run() {
		while (true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					get.fire();
				}
			});
		}
	}
}
