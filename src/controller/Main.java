package controller;

import guione.*;	// Den ene GUI.
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import treegui.*;	// Den anden GUI.
import model.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		// En gruppe til brug i løkkerne.
		Group group;
		// En liste til grupper.
		ObservableList<Group> groups = FXCollections.observableArrayList();
		// Hent liste med personer.
		ObservableList<Person> persons = FXCollections.observableArrayList();
		new Database().readData(persons);

		// Gennemløb listen med personer. TODO: Skal laves om til tilfældigt udvalgt.
		int groupIndex = 1;
		int index = 0;
		while(index < persons.size()){
			group = new Group("Gruppe"+groupIndex++);	// Sæt gruppenavn med nummer og øg nummer.
			// Tilføj medlemmer til gruppe.
			for(int i = 0; i < 5; i++){
				if(index < persons.size()){
					group.add(persons.get(index++));		// Tilføj medlem fra persons og øg index med en.
				}
			}
			groups.add(group);	// Tilføj gruppen til listen af grupper.
		}
		new GuiMainWindow(primaryStage).create(persons);
		//new GuiMainWindow(primaryStage).create(groups);
	}
}