package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

/**
 * En gruppe består af et navn og en liste af Person.
 */
public class Group {
	private SimpleStringProperty name;
	private ObservableList<Person> members;

	// Constructors
	public Group(){
		this("");	// Opret en gruppe uden navn.
	}
	public Group(String name){
		setName(name);
	}

	// Tilføj et medlem.
	public void add(Person member){
		this.members.add(member);
	}

	// Setters
	public void setName(String name){
		this.name.set(name);
	}
	public void setMembers(ObservableList<Person> members){
		this.members = members;
	}

	// Getters
	public String getName(){
		return this.name.toString();
	}
	public ObservableList<Person> getMembers() {
		return members;
	}
}