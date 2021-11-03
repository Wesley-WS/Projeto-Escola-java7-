package br.com.ebix.escola.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private Connection connection;
	
	public Connection getConnection(){
		try {
			if(this.connection == null) {
				Class.forName("com.mysql.jdbc.Driver");
				this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3310/escola?useSSL=false", "root", "uut3E26MPmxRQNd4");
			}
			return this.connection;
		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
}
