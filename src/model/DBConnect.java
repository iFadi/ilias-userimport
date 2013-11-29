package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Fadi M. H. Asbih
 * @email fadi_asbih@yahoo.de
 * @copyright 2013
 * 
 * TERMS AND CONDITIONS:
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
public class DBConnect {

	private Statement statement;
	private String value;
	Connection connection = null;

	public DBConnect() throws ClassNotFoundException {
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");

		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:IUI.db");
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			
//			statement.executeUpdate("drop table if exists config");
			statement.executeUpdate("create table if not exists config (id integer, name string, value string)");
			statement.executeUpdate("insert into config values(0, 'studip', '0')");
			statement.executeUpdate("insert into config values(1, 'localRoleValue', 'test18.02')");
			statement.executeUpdate("insert into config values(2, 'passwordValue', '123456')");
			statement.executeUpdate("insert into config values(3, 'CSVSymbol', ';')");
			statement.executeUpdate("insert into config values(4, 'loginPrefix', 'klausur')");
			statement.executeUpdate("insert into config values(5, 'numberOfUsers', '10')");
			statement.executeUpdate("insert into config values(6, 'timeLimitUnlimited', '0')");
			statement.executeUpdate("insert into config values(7, 'timeLimitFrom', '01.02.2013')");
			statement.executeUpdate("insert into config values(8, 'timeLimitUntil', '01.03.2013')");
			statement.executeUpdate("insert into config values(9, 'lastNameLabel', 'Nachname')");
			statement.executeUpdate("insert into config values(10, 'firstNameLabel', 'Vorname')");
			statement.executeUpdate("insert into config values(11, 'loginLabel', 'Nutzernamen')");
			statement.executeUpdate("insert into config values(12, 'passwordLabel', 'Password')");
			statement.executeUpdate("insert into config values(13, 'titleLabel', 'Titel')");
			statement.executeUpdate("insert into config values(14, 'matriculationLabel', 'matrikelnummer')");
			statement.executeUpdate("insert into config values(15, 'emailLabel', 'E-Mail')");
			statement.executeUpdate("insert into config values(16, 'studipLogin', 'matriculation')");
			statement.executeUpdate("insert into config values(17, 'generateDummy', '0')");
			statement.executeUpdate("insert into config values(18, 'globalRoleValue', 'User')");
			statement.executeUpdate("insert into config values(19, 'generateOutput', '0')");
			statement.executeUpdate("insert into config values(20, 'generatePassword', '0')");


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

	/**
	 * 
	 * @param name
	 * @return settings value from the DB.
	 */
	public String getValue(String name) {

		ResultSet rs;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:IUI.db");
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
	
	/**
	 * Set new value for the setting.
	 * 
	 * @param name
	 * @param value
	 */
	public void setValue(String name, String value) {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:IUI.db");
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			statement.executeUpdate("update config set value="+"'"+value+"'"+"where name = '"+name+"' ");
//			System.out.println("name: "+name+" value= "+value);

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
	}
}
