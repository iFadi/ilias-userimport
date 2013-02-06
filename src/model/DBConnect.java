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
			statement.executeUpdate("create table config (id integer, name string, value string)");
			statement.executeUpdate("insert into config values(0, 'studip', '0')");
			statement.executeUpdate("insert into config values(1, 'localRoleValue', 'test18.02')");
			statement.executeUpdate("insert into config values(2, 'passwordValue', '123456')");
			statement.executeUpdate("insert into config values(3, 'CSVSymbol', ';')");
			statement.executeUpdate("insert into config values(4, 'loginPrefix', 'klausur')");
			statement.executeUpdate("insert into config values(5, 'numberOfUsers', '10')");
			statement.executeUpdate("insert into config values(6, 'timeLimitUnlimited', '0')");
			statement.executeUpdate("insert into config values(7, 'timeLimitFrom', '01.02.2013')");
			statement.executeUpdate("insert into config values(8, 'timeLimitUntil', '01.03.2013')");
			
			// For StudIP CSV
			statement.executeUpdate("insert into config values(9, 'lastNameLabel', 'Nachname')");
			statement.executeUpdate("insert into config values(10, 'firstNameLabel', 'Vorname')");
			statement.executeUpdate("insert into config values(11, 'loginLabel', 'Nutzernamen')");
			statement.executeUpdate("insert into config values(12, 'passwordLabel', 'Password')");
			statement.executeUpdate("insert into config values(13, 'titleLabel', 'Titel')");
			statement.executeUpdate("insert into config values(14, 'matriculationLabel', 'matrikelnummer')");
			statement.executeUpdate("insert into config values(15, 'emailLabel', 'E-Mail')");
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
			connection = DriverManager
					.getConnection("jdbc:sqlite:properties.db");
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			rs = statement.executeQuery("select * from config");

			// ResultSet rs = statement.executeQuery("select * from config");
			while (rs.next()) {
				if (rs.getString("name").equals(name)) {
					value = rs.getString("value");
					return rs.getString("value");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
}
