package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	public Connection connection = null;

	public Connection getconConnection() {
		System.out.println("mysql Connection Testing");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "1234");
			System.out.println("Driver Registered\n");
			if (connection != null) {
				System.out.println("You Made It");
			} else {
				System.out.println("Failed");
			}
		} catch (ClassNotFoundException ex) {
			System.out.println("Where is MySql Driver");
			ex.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Connection Failed");
			e.printStackTrace();
		}
		return connection;
	}

}
