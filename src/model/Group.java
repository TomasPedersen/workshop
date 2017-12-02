package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * En gruppe består af et navn og en liste af Person.
 */
public class Group implements Serializable{
	private String groupName;
//	private ObservableList<Person> members;
	private ArrayList<Person> members = new ArrayList<>();

	// Constructors
	public Group(){
		this("");	// Opret en gruppe uden navn.
	}
	public Group(String name){
		this.groupName = name;
//		this.members = FXCollections.observableArrayList();
	}

	// Tilføj et medlem.
	public void add(Person member){
		this.members.add(member);
	}

	// Slet et medlem fra gruppen.
	public void remove(int index){
		this.members.remove(index);
	}

	// Setters
	public void setGroupName(String groupName){
		this.groupName = groupName;
	}
/*	public void setMembers(ObservableList<Person> members){
		this.members = members;
	}
*/
	// Getters
	public String getGroupName(){
		return this.groupName;
	}
//	public ObservableList<Person> getMembers() { return members; }
	
	public ArrayList<Person> getMembers() {
		return members;
	}
}