package model;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * Denne klasse eksisterer fordi det er her data bor.
 *
 */
public class Data {
	private ObservableList<Person> persons = null;

	// Constructor.
	public Data() {
		persons = FXCollections.observableArrayList();
		persons.addListener((ListChangeListener) (ListChangeListener.Change change) -> {
			while (change.next()) {
				System.out.println("Der skete noget i persons: "+change);
			}
		});
		new Database().readData(persons);	//TODO: Den her linie skal flyttes over i controller.
	}
	public Data(ObservableList<Person> persons){
		this.persons = persons;
	}

	/**
	 * Returner data.
	 * @return
	 */
	public ObservableList<Person> getData(){
		return persons;
	}
}