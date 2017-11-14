package treegui;

import javafx.scene.control.TreeItem;
import model.Group;
import model.Person;

public class TreeItemRoot {
	TreeItem<Person> root = null;

	// Constructors
	public TreeItemRoot(Group group){
		System.out.println("Gruppe: "+group.getName());
		TreeItem<Person> root = new TreeItem<>(new Person(group.getName(), "", ""));
		root.setExpanded(true);
		group.getMembers().stream().forEach((member) -> {
			root.getChildren().add(new TreeItem<>(member));
			System.out.println("Tilføjet i TreeItemRoot(): "+member);
		});
	}

	// Getter
	public TreeItem<Person> getRoot(){
		return root;
	}
}