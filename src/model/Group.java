package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * En gruppe består af et navn og en liste af Person.
 */
public class Group {
	private String name;
	private ObservableList<Person> members;

	// Constructors
	public Group(){
		this("");	// Opret en gruppe uden navn.
	}
	public Group(String name){
		this.name = name;
		this.members = FXCollections.observableArrayList();
	}

	// Tilføj et medlem.
	public void add(Person member){
		this.members.add(member);
	}

	// Setters
	public void setName(String name){
		this.name = name;
	}
	public void setMembers(ObservableList<Person> members){
		this.members = members;
	}

	// Getters
	public String getName(){
		return this.name;
	}
	public ObservableList<Person> getMembers() {
		if(members.size() == 0)members.add(new Person("","",""));
		return members;
	}
}