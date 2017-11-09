package treegui;

import javafx.scene.control.TreeItem;
import model.Group;
import model.Person;

public class TreeItemRoot {
	TreeItem<Person> root = null;

	// Constructors
	public TreeItemRoot(Group group){
		TreeItem<Person> root = new TreeItem<>(new Person(group.getName(), "", ""));
		root.setExpanded(true);
		group.getMembers().stream().forEach((member) -> {
			root.getChildren().add(new TreeItem<>(member));
		});
	}

	// Getter
	public TreeItem<Person> getRoot(){
		return root;
	}
}
