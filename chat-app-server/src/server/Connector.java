package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connector {

	public Connection connection = null;

	private void start() throws ClassNotFoundException, SQLException {
		String user = "root";
		String pass = "";
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/grubtalk";
		connection = DriverManager.getConnection(url, user, pass);

		java.sql.Statement test = connection.createStatement();
		ResultSet testResult = test.executeQuery("SELECT * FROM `users` ");

	}

	public Connector() {
		try {
			start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
