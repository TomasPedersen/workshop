package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

/**
 * Denne klasse henter data fra en databaseserver for at aflevere det i klassen Data.
 */
public class Database {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

	/**
	 * Ineffektiv. Der laves en kopi af data blot for at kunne returnere det.
	 * Brug hellere readData(ObservableList<Person> persons)
	 * @return
	 */
	public ObservableList<Person> readData(){
		ObservableList<Person> persons = FXCollections.observableArrayList();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://patina.dyndns.dk/workshop?user=workshop&password=WorkshopPassword");
			statement = connect.createStatement();
			resultSet = statement.executeQuery("SELECT first_name,phone,email FROM persons");

			// Kopier resultset til ObservableList og returner.
			while(resultSet.next()) {
				persons.addAll(new Person(resultSet.getString("first_name"), resultSet.getString("phone"), resultSet.getString("email")));
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return persons;
	}

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