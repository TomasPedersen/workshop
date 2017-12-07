package controller;

import debug.Debug;
import static model.Constants.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Group;
import model.Person;

import java.io.*;
import java.util.ArrayList;

/**
 * Writes data to and reads data from disk.
 */
public class FileIO implements Serializable {
	
	// Skriv data til fil.
	public void writeData(ObservableList<Group> groups) {
		// Konverter ObservableList<Group> til ArrayList<ArrayGroup>
		ArrayList<ArrayGroup> arrayGroups = new ArrayList<>();
		for (Group g: groups
			 ) {
			ArrayGroup ag = new ArrayGroup(g.getGroupName());	// Lav en midlertidig ArrayGroup og sæt gruppenavn.
			ag.addAll(g.getMembersArrayList());					// Tilføj medlemmer som ArrayList.
			arrayGroups.add(ag);								// Tilføj midlertidig gruppe til ArrayList med grupper.
		}

		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILENAME));
			oos.writeObject(arrayGroups);
			oos.flush();
			oos.close();
			if (Debug.debugLevel > 0) System.out.println("Der blev skrevet til: " + DATA_FILENAME);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Læs data fra disk.
	public ObservableList<Group> readData() {
		ObservableList<Group> groups = FXCollections.observableArrayList();
		ArrayList<ArrayGroup> groupsFromDisk = new ArrayList<>();

		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILENAME));
			groupsFromDisk = (ArrayList<ArrayGroup>) ois.readObject();	// Der læses en ArrayList fra disk.
			ois.close();
			if(Debug.debugLevel >0) System.out.println("Data indlæst fra: " + DATA_FILENAME);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException notFound) {
			notFound.printStackTrace();
		}
		// Konverter ArrayList<ArrayGroup> til ObservableList<Group>
		for (ArrayGroup ag:groupsFromDisk
			 ) {
			Group tempGroup = new Group(ag.getGroupName());		// Opret midlertidig Group og sæt gruppenavn.
			tempGroup.addAll(ag.getArrayMembers());				// Tilføj medlemmer.
			groups.add(tempGroup);								// Tilføj tempGroup til returværdi.
		}
		return groups;
	}

	private class ArrayGroup implements Serializable{
		private String groupName = "";
		private ArrayList<Person> arrayMembers = new ArrayList<>();

		// Constructor.
		ArrayGroup(String groupName){
			this.groupName = groupName;
		}

		// Adders
		public void addMember(Person member){
			this.arrayMembers.add(member);
		}
		public void addAll(ArrayList<Person> members){
			this.arrayMembers.addAll(members);
		}
		public ArrayList<Person> getArrayMembers(){
			return arrayMembers;
		}

		// Getters
		public String getGroupName(){
			return groupName;
		}
	}
}