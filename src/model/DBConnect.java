package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {

	private Statement statement;
	private String value;
	Connection connection = null;

	public DBConnect() throws ClassNotFoundException {
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");

		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:properties.db");
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			statement.executeUpdate("drop table if exists config");
			statement
					.executeUpdate("create table config (id integer, name string, value string)");
			statement
					.executeUpdate("insert into config values(1, 'localRoleValue', 'test18.02')");
			statement
					.executeUpdate("insert into config values(2, 'passwordValue', '123456')");
			statement
					.executeUpdate("insert into config values(3, 'CSVSymbol', ';')");

//			ResultSet rs = statement.executeQuery("select * from config");
//			// ResultSet rs = statement.executeQuery("select * from config");
//			while (rs.next()) {
//				// read the result set
//				System.out.println("id = " + rs.getInt("id"));
//				System.out.println("name = " + rs.getString("name"));
//				System.out.println("value = " + rs.getString("value"));
//			}
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}
	}

	public String getValue(String name) {

		ResultSet rs;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:properties.db");
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			rs = statement.executeQuery("select * from config");

			// ResultSet rs = statement.executeQuery("select * from config");
			while (rs.next()) {
				// read the result set
//				System.out.println("id = " + rs.getInt("id"));
//				System.out.println("name = " + rs.getString("name"));
//				System.out.println("value = " + rs.getString("value"));
				if (rs.getString("name").equals(name)) {
					value = rs.getString("value");
					return rs.getString("value");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally {
				try {
					if (connection != null)
						
						connection.close();
				} catch (SQLException e) {
					// connection close failed.
					System.err.println(e);
				}
			}
		return value;
	}
	
	public void update(String name) {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:properties.db");
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			statement.executeUpdate("insert into config values(1, 'localRoleValue', 'bestanden')");
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
