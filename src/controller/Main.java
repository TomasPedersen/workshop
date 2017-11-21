package controller;

//import guione.*;	// Den ene GUI.
import treegui.*;	// Den anden GUI.
import model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {

		// Hent liste med personer.
		ObservableList<Person> persons = FXCollections.observableArrayList();
		new Database().readData(persons);

		Groups groups = new Groups(persons);
		groups.create(5,0);	// Fem grupper, find selv ud af størrelse.
		new MainWindow(primaryStage).create(groups);
	}
}