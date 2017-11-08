import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	private ObservableList<Person> persons = null;
	private TextField addName = null;
	private TextField addPhone = null;
	private TextField addEmail = null;
	private Button editButton = null;
	private TableView table = null;
	private Label entriesLabel = new Label("");
	private Label debugLabel = new Label("Debug");
	private ObservableList<String> retVal = FXCollections.observableArrayList();

	@Override
	public void start(Stage primaryStage) throws Exception {
		persons = new Data().getData();

		table = new TableView(persons);
		table.setEditable(false);

		TableColumn nameCol = new TableColumn("Navn");
		nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		nameCol.setMinWidth(150);
		TableColumn phoneNumberCol = new TableColumn("Telefon");
		phoneNumberCol.setCellFactory(TextFieldTableCell.forTableColumn());
		phoneNumberCol.setMinWidth(150);
		TableColumn emailAddressCol = new TableColumn("Emailadresse");
		emailAddressCol.setCellFactory(TextFieldTableCell.forTableColumn());
		emailAddressCol.setMinWidth(200);

		table.getColumns().addAll(nameCol, phoneNumberCol, emailAddressCol);

		nameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
		phoneNumberCol.setCellValueFactory(new PropertyValueFactory<Person, String>("phoneNumber"));
		emailAddressCol.setCellValueFactory(new PropertyValueFactory<Person, String>("emailAddress"));

		// Felter og knap til at tilføje data.
		addName = new TextField();
		addName.setPromptText("Navn");
		addName.setPrefWidth(nameCol.getPrefWidth());

		addPhone = new TextField();
		addPhone.setPromptText("Telefon");
		addPhone.setPrefWidth(phoneNumberCol.getPrefWidth());

		addEmail = new TextField();
		addEmail.setPromptText("email");
		addEmail.setPrefWidth(emailAddressCol.getPrefWidth());

		Button addButton = new Button("Tilføj");
		// Action når der trykkes på knappen.
		addButton.setOnAction(event -> commitEntries());

		editButton = new Button();
		table.setEditable(true);	// Sættes til edit, fordi handleEditButtonAction toggler. TODO: Lav det mere elegant.
		toggleEditButton();	// Kaldes for at sætte tekst på knappen.
		editButton.setOnAction(event -> { toggleEditButton(); });

		// Listeners til textfields. Sæt indhold af label til ingenting når fokus fjernes.
		addName.focusedProperty().addListener((obs, oldval, newval) -> entriesLabel.setText( newval ? addName.getPromptText() : "" ));
		addPhone.focusedProperty().addListener((obs, oldval, newval) -> entriesLabel.setText( newval ? addPhone.getPromptText() : "" ));
		addEmail.focusedProperty().addListener((obs, oldval, newval) -> entriesLabel.setText( newval ? addEmail.getPromptText() : "" ));

		entriesLabel.setText(addName.getPromptText());

		addName.setOnKeyPressed((event) -> {	// Skift til næste tekstfelt, telefon, når der trykkes enter, så tab ikke er nødvendigt.
			if (event.getCode() == KeyCode.ENTER || event.getCode()==KeyCode.TAB) {
				addPhone.requestFocus();
				entriesLabel.setText(addPhone.getPromptText());
			}
		});
		// Skift til næste tekstfelt, email, når der trykkes enter.
		addPhone.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB) {
				addEmail.requestFocus();
				entriesLabel.setText(addEmail.getPromptText());
			}
		});
		// Gør det samme som addButton når der trykkes på enter i sidste tekstfelt.
		addEmail.setOnKeyPressed((event) -> {
			if (event.getCode() == KeyCode.ENTER) {
				commitEntries();
				entriesLabel.setText(addName.getPromptText());
			}
		});

		HBox hbox = new HBox(addName, addPhone, addEmail, addButton, editButton);
		VBox vbox = new VBox(createMenu(primaryStage), table, hbox, entriesLabel);

		Scene scene = new Scene(vbox, 800, 800);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Workshop Organiser");
		primaryStage.show();

		// Start med fokus sat til indtastning.
		addName.requestFocus();
	}

	void commitEntries(){
		persons.add( new Person(addName.getText(), addPhone.getText(), addEmail.getText()) );
		addName.clear(); addPhone.clear(); addEmail.clear();
		addName.requestFocus();		// Flyt cursor, klar til ny indtastning.
	}
	/**
	 * Toggle editable state for table. Change text on button accordingly.
	 */
	void toggleEditButton(){
		if (table.isEditable()) {
			table.setEditable(false);
			editButton.setText("Ret");
		} else {
			table.setEditable(true);
			editButton.setText("Lås");
		}
	}
	MenuBar createMenu(Stage stage){
		MenuBar menuBar = new MenuBar();

		// Menu File
		Menu fileMenu = new Menu("_File");
		fileMenu.setMnemonicParsing(true);
		// Definer menuelementer.
		MenuItem edit = new MenuItem("_Rediger");
		MenuItem lock = new MenuItem("_Lås menu");
		MenuItem createGroups = new MenuItem("_Dan grupper");
		MenuItem exitMI = new MenuItem("_Exit");

		fileMenu.getItems().addAll(edit, lock, createGroups, exitMI);
		// Sæt actions for menuelementer.
		edit.setOnAction(event -> {
			if(!table.isEditable()){
				table.setEditable(true);
			}
		});

		// Menupunkt: Dan grupper.
		GuiCreateGroups gcg = new GuiCreateGroups();
		//TODO: Det skal nok være noget med en observable.
		/*retVal.addListener(new ListChangeListener<String>() {
			@Override
			public void onChanged(ListChangeListener.Change change){
				while(change.next()){
					System.out.println("Change: "+change);
				}
			}
		});*/
		retVal.addListener((ListChangeListener) (ListChangeListener.Change event) -> {	//TODO: Annotations.xml, aner ikke hvad det går ud på.
			while(event.next()){
				System.out.println(event);
			}
		});

		// Kald nyt vindue.
		createGroups.setOnAction(event -> {
			gcg.create(retVal);
			createGroups.setDisable(true);
		});
		exitMI.setOnAction(event -> {
			stage.close();
		});

		// Menu Indstillinger
		Menu settingsMenu = new Menu("_Indstillinger");
		MenuItem databaseMI = new MenuItem("_Database");
		settingsMenu.getItems().addAll(databaseMI);

		menuBar.getMenus().addAll(fileMenu,settingsMenu);

		return menuBar;
	}
}