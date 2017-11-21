package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * En gruppe består af et navn og en liste af Person.
 */
public class Group {
	private String groupName;
	private ObservableList<Person> members;

	// Constructors
	public Group(){
		this("");	// Opret en gruppe uden navn.
	}
	public Group(String name){
		System.out.println("Group.constructor");
		this.groupName = name;
		this.members = FXCollections.observableArrayList();
	}

	// Tilføj et medlem.
	public void add(Person member){
		this.members.add(member);
	}

	// Slet et medlem.
	public void remove(int index){
		System.out.println("Slettes: "+ groupName +"   "+members.get(index)+"  Size: "+members.size());
		this.members.remove(index);
		System.out.println("Der er nu "+members.size()+" medlemmer i "+groupName);
	}

	// Setters
	public void setGroupName(String groupName){
		this.groupName = groupName;
	}
	public void setMembers(ObservableList<Person> members){
		this.members = members;
	}

	// Getters
	public String getGroupName(){
		return this.groupName;
	}
	public ObservableList<Person> getMembers() { return members; }
}