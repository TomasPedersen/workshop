package treegui;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.stage.Stage;
import model.Person;

public class GuiMainWindow {
	Stage primaryStage = null;

	public GuiMainWindow(Stage treeguiStage){
		this.primaryStage = treeguiStage;
		this.primaryStage.setTitle("Workshop Organisator");
	}

	/**
	 * Denne metode kaldes for at oprette et vindue.
	 * Controller medleverer data i persons.
	 */
	public void create(ObservableList<Person> persons){
		TreeItem<Person> root = new TreeItem<Person>(new Person("Gruppe","",""));
		root.setExpanded(true);
		persons.stream().forEach((person) -> {
			root.getChildren().add(new TreeItem<>(person));
		});

		// Kolonner
		TreeTableColumn<Person, String> nameColumn = new TreeTableColumn<>("Navn");
		nameColumn.setCellValueFactory( (TreeTableColumn.CellDataFeatures<Person, String> param) ->
			new ReadOnlyStringWrapper(param.getValue().getValue().getName())
		);

		TreeTableColumn<Person, String> phoneColumn = new TreeTableColumn<>("Telefonnummer");
		phoneColumn.setCellValueFactory( (TreeTableColumn.CellDataFeatures<Person, String> param) ->
				new ReadOnlyStringWrapper(param.getValue().getValue().getPhoneNumber())
		);

		TreeTableColumn<Person, String> emailColumn = new TreeTableColumn<>("Email");
		emailColumn.setCellValueFactory( (TreeTableColumn.CellDataFeatures<Person, String> param) ->
				new ReadOnlyStringWrapper(param.getValue().getValue().getEmailAddress())
		);

		TreeTableView<Person> treeTableView = new TreeTableView<>(root);
		treeTableView.getColumns().setAll(nameColumn, phoneColumn);

		Scene scene = new Scene(treeTableView, 400, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
