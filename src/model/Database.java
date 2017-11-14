package model;

import javafx.collections.ObservableList;
import java.sql.*;

/**
 * Henter persons fra en MySQL database.
 */
public class Database {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

	public void readData(ObservableList<Person> persons) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://patina.dyndns.dk/workshop?user=workshop&password=WorkshopPassword");
			statement = connect.createStatement();
			resultSet = statement.executeQuery("SELECT first_name,phone,email FROM persons");

			// Kopier resultset til ObservableList, der blev givet som parameter.
			while (resultSet.next()) {
				persons.addAll(new Person(resultSet.getString("first_name"), resultSet.getString("phone"), resultSet.getString("email")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}