package treegui;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.stage.Stage;
import model.Group;
import model.Person;
import java.util.ArrayList;

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
	public void create(ObservableList<Group> groups){

		// Kolonner
		TreeTableColumn<Person, String> nameColumn = new TreeTableColumn<>("Navn");
		nameColumn.setCellValueFactory( (TreeTableColumn.CellDataFeatures<Person, String> param) ->
			new ReadOnlyStringWrapper(param.getValue().getValue().getName())
		);
		nameColumn.setPrefWidth(150);

		TreeTableColumn<Person, String> phoneColumn = new TreeTableColumn<>("Telefonnummer");
		phoneColumn.setCellValueFactory( (TreeTableColumn.CellDataFeatures<Person, String> param) ->
				new ReadOnlyStringWrapper(param.getValue().getValue().getPhoneNumber())
		);

		TreeTableColumn<Person, String> emailColumn = new TreeTableColumn<>("Email");
		emailColumn.setCellValueFactory( (TreeTableColumn.CellDataFeatures<Person, String> param) ->
				new ReadOnlyStringWrapper(param.getValue().getValue().getEmailAddress())
		);
/*
		// TreeItems
		// RootItem
		TreeItem<Person> rootItem = new TreeItem<>(new Person("","",""));
		rootItem.setExpanded(true);

		// groupItems
		for (Group g:groups
			 ) {
			// Lav et TreeItem og tilføj gruppens medlemmer.
			TreeItem<Person> membersOfGroup = new TreeItem<>(new Person(g.getName(),"",""));	// Sæt gruppens navn pakket ind i en Person.

			// Tilføj gruppens medlemmer.
			for (Person p:g.getMembers()
				 ) {
				membersOfGroup.getChildren().add(new TreeItem<>(p));	// Tilføj hver enkelt Person pakket ind i et TreeItem.
			}
			rootItem.getChildren().add(membersOfGroup);
		}
*/
		// TreeTableView.
		TreeTableView<Person> treeTableView = new TreeTableView<>(createRoot(groups));
		treeTableView.getColumns().setAll(nameColumn, phoneColumn, emailColumn);

		// Stage og scene.
		Scene scene = new Scene(treeTableView, 400, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	TreeItem<Person> createRoot(ObservableList<Group> groups){
		// TreeItems
		// RootItem
		TreeItem<Person> rootItem = new TreeItem<>(new Person("","",""));
		rootItem.setExpanded(true);

		// groupItems
		for (Group g:groups
				) {
			// Lav et TreeItem og tilføj gruppens medlemmer.
			TreeItem<Person> membersOfGroup = new TreeItem<>(new Person(g.getName(),"",""));	// Sæt gruppens navn pakket ind i en Person.

			// Tilføj gruppens medlemmer.
			for (Person p:g.getMembers()
					) {
				membersOfGroup.getChildren().add(new TreeItem<>(p));	// Tilføj hver enkelt Person pakket ind i et TreeItem.
			}
			rootItem.getChildren().add(membersOfGroup);
		}
		return rootItem;
	}
}
