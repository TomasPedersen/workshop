package model;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * En gruppe består af et navn og en liste af Person.
 */
public class Group {
	private String groupName;
	private ObservableList<Person> members = FXCollections.observableArrayList();

	// Constructors
	public Group(){
		this("");	// Opret en gruppe uden navn.
	}
	public Group(String name){
		this.groupName = name;
	}

	// Tilføj et medlem.
	public void addMember(Person member){
		this.members.add(member);
	}

	// Tilføj medlemmer.
	// Medlemmer som ObservableList.
	public void addAll(ObservableList<Person> persons){
		members.addAll(persons);
	}
	// Medlemmer som ArrayList.
	public void addAll(ArrayList<Person> persons){
		members.addAll(persons);
	}

	// Slet et medlem fra gruppen.
	public void removeMember(int index){
		this.members.remove(index);
	}

	// Setters
	public void setGroupName(String groupName){
		this.groupName = groupName;
	}

	// Getters
	public String getGroupName(){
		return this.groupName;
	}
	public ObservableList<Person> getMembers() {
		return members;
	}
	public ArrayList<Person> getMembersArrayList(){
		// Konverter ObservableList members til ArrayList.
		ArrayList<Person> returnValue = new ArrayList<>();
		for (Person p:members
			 ) {
			returnValue.add(p);
		}
		return returnValue;
	}
}