package controller;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import javafx.collections.ObservableList;
import static model.Constants.*;
import model.Group;
import model.Person;
import java.sql.*;
import java.util.ArrayList;

/**
 * Henter persons fra en MySQL-database.
 */
	// TODO: Tilføj gruppe-kolonne til tabel.
public class Database {		// TODO: Noget med en singleton.
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private ArrayList<Group> groups = new ArrayList<>();
    Settings settings = new Settings();
	String connectionString =
			"jdbc:mysql://"+settings.dbHost+
			"/"+ DB_NAME +
			"?user="+settings.dbUser+
			"&password="+settings.dbPwd;

	// Constructor
	public Database(){}

	public ArrayList<Group> readData() {
		Group group0 = new Group("Gruppe0");	// TODO: Kun data fra gruppe0 skal hentes fra databasen.

		try {
//			Class.forName("com.mysql.jdbc.Driver");     Forældet og unødvendigt.
			connect = DriverManager.getConnection(connectionString);
			System.out.println(connectionString);
			statement = connect.createStatement();
			resultSet = statement.executeQuery("SELECT first_name,phone,email FROM persons WHERE group_number='0'");	// Hent fra gruppe0.
			
			// Kopier resultSet til members.
			while (resultSet.next()) {
				group0.addMember(new Person(
						resultSet.getString("first_name"),
						resultSet.getString("phone"),
						resultSet.getString("email")));
			}
		} catch(CommunicationsException ce){
			// Der blev ikke oprettet forbindelse til databasen. Sikkert fordi det er første kørsel og konfigurationsfilen ikke eksisterer.
			group0.addMember(new Person("","",""));		// Tilføj en tom person til gruppe0. TODO: Det skal nok laves lidt mere elegant.
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Tilføj members til gruppe0.
		groups.add(group0);
		return groups;
	}

	public void writeData(ObservableList<Group> groups){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(connectionString);
			statement = connect.createStatement();
			for (int groupIndex = 0; groupIndex < groups.size(); groupIndex++){
				Group g = groups.get(groupIndex);
				for (Person p:g.getMembers()
					 ) {
					String sqlString ="INSERT INTO persons VALUES (" +
							"'"+p.getName()+"'," +
							"'"+p.getInitial()+"',"+
							"'"+p.getPhoneNumber()+"',"+
							"'"+p.getEmailAddress()+"',"+
							"'"+p.getGender()+"',"+
							"'"+p.getBlock()+"',"+
							"'"+Integer.valueOf(groupIndex)+"',"+
							"'"+g.getGroupName()+"')";
					System.out.println("sqlString: "+sqlString);
					statement.execute(sqlString);	// TODO: Kolonner skal tilføjes før der kan skrives til databasen den med den her sqlString.
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}