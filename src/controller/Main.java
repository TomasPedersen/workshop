package controller;

//import guione.*;	// Den ene GUI.
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import treegui.*;	// Den anden GUI.
import model.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Group group;
		ObservableList<Group> groups = FXCollections.observableArrayList();

		// Dan grupper.
		ObservableList<Person> persons = new Data().getData();
		while(persons.size() > 5) {
			group = new Group("Gruppe");
			for (int i = 0; i < 5; i++) {
				group.add( new Person("Kasper", "42959511","") ); 	// Tag sidste element i listen.
				persons.remove( 0 );			// Slet sidste element i listen.
			}
			groups.add(group);
		}
		for (Group g:groups
			 ) {
			System.out.println("Gruppe: "+g.getName().toString());
			System.out.println(g.getMembers().get(0).getName());
		}
		new GuiMainWindow(primaryStage).create(groups);
	}
}