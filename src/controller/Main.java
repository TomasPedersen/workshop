package controller;

//import guione.*;	// Den ene GUI.
import javafx.application.Application;
import treegui.*;	// Den anden GUI.
import model.*;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {

		// Opret dataobjekt. Henter data fra disk eller database.
		Data data = new Data();
		new MainWindow(data).start(new Stage());

		// Test at der skrives til disk.
//		data.saveToDisk();
//		primaryStage.show(); primaryStage.close();	// For at afslutte når MainWindow ikke startes.
	}
}