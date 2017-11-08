import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class Data {
	private ObservableList<Person> persons = FXCollections.observableArrayList();

	// Constructor.
	Data() {
		persons.addListener(new ListChangeListener<Person>() {
			@Override
			public void onChanged(ListChangeListener.Change change) {
				while(change.next()) {
					if (change.wasAdded()) {
						System.out.println("Noget blev tilføjet");
					}
					if (change.wasUpdated()) {
						System.out.println("Persons blev opdateret");
					}
					System.out.println("Der skete noget i persons: " + change);
				}
			}
		});
		/**
		 * Simuler læsning fra disk.
		 */
		/*persons.addAll(
				new Person("Tomas", "42955911", "tomas@patina.one"),
				new Person("Hans", "95421159", "tomasx73@gmail.com")
		);*/
		// Læs fra database.
		persons.addAll(new Database().readData());
	}

	/**
	 * Returner data.
	 * @return
	 */
	public ObservableList<Person> getData(){
		return persons;
	}
}