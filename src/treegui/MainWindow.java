package treegui;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import model.*;
import controller.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainWindow extends Application {
	// Klassedata.
	Data data;

	// Stage initialiseres her, så exit() kan lukke den.
	Stage stage;

	// Scenegrafen
	// Den overordnede box.
	VBox vbox;

	// MenuBar
	MenuBar menubar;
	MenuItem createGroupsMI;

	// Tabel
	TreeTableView table;

	// Inputarea
	Node inputarea;
	TextField nameField;
	TextField phoneField;
	TextField emailField;
	Label nameError;
	Label phoneError;
	Label emailError;
	// Validator til indtastet tekst.
	Valid valid = new Valid();

	// Chooser. Vælg antal grupper.
	HBox chooser;
	ComboBox chooserCB = new ComboBox();

	// Constructors
	public MainWindow(){}
	public MainWindow(Data data) {
		this.data = data;

		// Opret de fire elementer, der udgår vinduet.
		menubar = createMenubar();
		table = createTable();
		inputarea = createInputarea();
		chooser = createChooser();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		stage.setTitle("Workshop Organisator");
		stage.setOnCloseRequest(event -> handleExit());  // Kør exitrutine når vinduet lukkes, eg. med alt+F4.

		// VBox. Hele vinduet.
		vbox = new VBox(menubar, table, inputarea, chooser);

		// Scene
		Scene scene = new Scene(vbox, 600, 800);
		
		// Stage
		stage.setScene(scene);
		stage.show();
	}

	/**
	 *
	 * @return Menubjælke.
	 */
	public MenuBar createMenubar(){
		// Der er kun en menubar. Til en Menubar hører en eller flere Menu. Til hver Menu et antal MenuItem.
		menubar = new MenuBar();
		menubar.setVisible(true);
		
		// Menu: Filer.
		Menu fileMenu = new Menu("_Filer");
		MenuItem printMi = new MenuItem("_Print");
		printMi.setDisable(true);
		printMi.setOnAction(event -> new Print().start(new Stage()));
		MenuItem messageMI = new MenuItem("_Send besked");
		messageMI.setDisable(true);
		MenuItem settingsMI = new MenuItem("_Indstillinger");
		settingsMI.setOnAction(event -> {
			try {
				new SettingsWindow().start(new Stage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		MenuItem exitMI = new MenuItem("_Afslut");
		exitMI.setOnAction(event -> handleExit());
		fileMenu.getItems().addAll(printMi, messageMI, settingsMI, exitMI);
		
		// Menu: Rediger
		Menu editMenu = new Menu("_Rediger");
		MenuItem lockMI = new MenuItem("_Indtastning");
		lockMI.setOnAction(event -> {
			if(inputarea.isVisible()){
				lockMI.setText("_Indtastning");
				inputarea.setVisible(false);
			} else {
				lockMI.setText("_Lås tabel");
				inputarea.setVisible(true);
				nameField.requestFocus();
			}
		});
		createGroupsMI = new MenuItem("_Dan grupper");
		createGroupsMI.setOnAction(event -> {
			chooser.setVisible(true);
			chooserCB.setItems(data.numGroupsChoices());
		});
		editMenu.getItems().addAll(lockMI, createGroupsMI);
		
		menubar.getMenus().addAll(fileMenu, editMenu);
		return menubar;
	}
	
	public void handleExit(){
		data.saveData();
		stage.close();
	}
	public void handleCreateGroupsMI(){
	}

	/**
	 * @return TreeTableView
	 */
	TreeTableView createTable(){
		// Kolonner.
		// Navn.
		TreeTableColumn<Person, String> nameColumn = new TreeTableColumn<>("Navn");
		nameColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Person, String> param) ->
				new ReadOnlyStringWrapper(param.getValue().getValue().getName())
		);
		nameColumn.setPrefWidth(150);

		// Telefon.
		TreeTableColumn<Person, String> phoneColumn = new TreeTableColumn<>("Telefonnummer");
		phoneColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Person, String> param) ->
				new ReadOnlyStringWrapper(param.getValue().getValue().getPhoneNumber())
		);

		// Email.
		TreeTableColumn<Person, String> emailColumn = new TreeTableColumn<>("Email");
		emailColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Person, String> param) ->
				new ReadOnlyStringWrapper(param.getValue().getValue().getEmailAddress())
		);
		emailColumn.setPrefWidth(200);
		// TODO: Sæt CellFactory så celler kan opdateres/værdier rettes.

		// TreeTableView.
		TreeTableView treeTableView = new TreeTableView<>(createRoot());
		treeTableView.setShowRoot(false);		// Vis ikke den øverste enlige pil.
		treeTableView.getColumns().setAll(nameColumn, phoneColumn, emailColumn);
		treeTableView.setPrefHeight(550);
		treeTableView.setFocusTraversable(false);
		return treeTableView;
	}


	// TODO: Lav en listener på groups der fyrer setRoot(createRoot()) af.
	/**
	 * Brug denne metode til at opdatere tabellen: setRoot(createRoot())
	 * @return TreeItem root.
	 */
	public TreeItem<Person> createRoot(){
		// TreeItems
		// Root. Der er én root.
		TreeItem<Person> root = new TreeItem<>(new Person("NoName", "NoPhone", "NoMail"));	// Vises ikke i tabel.
		// GroupItems. Der er en groupRoot for hver gruppe.
		for (Group g:data.getGroups()	// Gennemløb listen af grupper.	// TODO: Lav som Listener, skal opdateres når der laves nye grupper.
				) {
			// Lav en groupRoot.
			TreeItem<Person> groupRoot = new TreeItem<>(new Person(g.getGroupName(),"",""));
			groupRoot.setExpanded(false);

			// Tilføj gruppens medlemmer til groupRoot.
			for (Person p:g.getMembers()
					) {
				groupRoot.getChildren().add(new TreeItem<>(p));
			}
			// Tilføj groupRoot til root.
			root.getChildren().add(groupRoot);
		}
		// Gruppe0 vises expanded så det er synligt hvem der ikke er i gruppe.
		root.getChildren().get(0).setExpanded(true);
		return root;
	}

	/**
	 * Indtastningsområde til placering under tabellen.
	 * @return Node
	 */
	Node createInputarea(){
		double prefHight = 25;	// Højde af Labels og Fields. Skal være ens, det ser pænest ud.

		// Navn
		Label nameLabel = new Label("Navn");
		nameLabel.setPrefHeight(prefHight);
		nameField = new TextField();
		nameField.setPrefHeight(prefHight);
		nameField.setOnKeyPressed(event -> {
			if(!valid.name(nameField.getText())) {
				nameError.setVisible(true);	// Vis nameError hvis der er fejl i navn.
			}
			else {
				nameError.setVisible(false);
			}
			if(event.getCode() == KeyCode.ENTER) phoneField.requestFocus();	// FLyt til indtastning af telefonnummer ved tryk på enter. TODO: Check også om alle andre felter er fyldt og gå til submit.
		});
		nameError = new Label("Ugyldigt navn");
		nameError.setPrefHeight(prefHight);
		nameError.setStyle("-fx-text-fill: red");
		nameError.setVisible(false);

		// Telefon
		Label phoneLabel = new Label("Telefon");
		phoneLabel.setPrefHeight(prefHight);
		phoneField = new TextField();
		phoneField.setPrefHeight(prefHight);
		phoneField.setOnKeyPressed(event -> {
			if(!valid.phone(phoneField.getText())) {
				phoneError.setVisible(true);	// Vis phoneError hvis der er fejl i telefonnummer.
			} else {
				phoneError.setVisible(false);
			}
			if(event.getCode() == KeyCode.ENTER) emailField.requestFocus(); // Gå til indtastning af email.
		});
		phoneError = new Label("Ugyldigt telefonnummer");
		phoneError.setPrefHeight(prefHight);
		phoneError.setStyle("-fx-text-fill: red");
		phoneError.setVisible(false);

		// Email
		Label emailLabel = new Label("Email");
		emailLabel.setPrefHeight(prefHight);
		emailField = new TextField();
		emailField.setPrefHeight(prefHight);
		emailField.setOnKeyPressed(event -> {
			if(!valid.email(emailField.getText())){
				emailError.setVisible(true);
			} else{
				emailError.setVisible(false);
			}
			if(event.getCode() == KeyCode.ENTER) { // Sidste felt. Her er ENTER det samme som tryk på Ok.
				nameField.requestFocus();
				handleOkButton();
			}
		});
		emailError = new Label("Ugyldig emailadresse");
		emailError.setPrefHeight(prefHight);
		emailError.setStyle("-fx-text-fill: red");
		emailError.setVisible(false);

		// TODO: Felt til køn.
		// TODO: Felt til alder.
		// TODO: Felt til blokering.

		// Knapper
		// Knap: Ok
		Button okButton = new Button("Ok");
		okButton.setOnAction(event -> handleOkButton());
		okButton.setOnKeyPressed(event -> {
			if(event.getCode() == KeyCode.ENTER) handleOkButton();
		});
		// Knap: Ryd felter
		Button clearButton = new Button("Ryd felter");
		clearButton.setOnAction(event -> handleClearButton());
		clearButton.setOnKeyPressed(event -> {
			if(event.getCode() == KeyCode.ENTER) handleClearButton();
		});

		// Boxe
		// HBox til knapper
		HBox buttons = new HBox(okButton, clearButton);

		// Vboxe til labels, felter og errorlabels.
		VBox labels = new VBox(nameLabel, phoneLabel, emailLabel);
		VBox fields = new VBox(nameField, phoneField, emailField);
		VBox errors = new VBox(nameError, phoneError, emailError);
		HBox hbox = new HBox(labels, fields, errors);

		VBox vbox = new VBox(hbox, buttons);
		HBox inputAreaHB = new HBox(vbox);
		inputAreaHB.setVisible(false);

		return inputAreaHB;
	}

	// Handle buttons.
	// Handle okButton.
	private void handleOkButton(){
		if(valid.name(nameField.getText()) && (valid.phone(phoneField.getText())||valid.email(emailField.getText())) ){		// Alle input er gyldige: Tilføj data til gruppe0, ryd felter og errorLabels.
			// Tilføj en ny Person til gruppe0.
			data.getGroups().get(0).addMember(new Person(nameField.getText(), phoneField.getText(), emailField.getText()));
			table.setRoot(createRoot());	// Opdater tabellen.
			handleClearButton();
		} else {
			nameField.requestFocus();	// TODO: Ret til øverste felt hvor der faktisk er en fejl.
		}
	}
	// Handle clearButton.
	private void handleClearButton() {
		nameField.setText("");
		phoneField.setText("");
		emailField.setText("");

		nameError.setVisible(false);
		phoneError.setVisible(false);
		emailError.setVisible(false);

		nameField.requestFocus();
	}

	/**
	 * Vælg grupper
	 * @return HBox
	 */
	public HBox createChooser() {
		// ComboBox til valg af antal grupper.
		chooserCB = new ComboBox(data.numGroupsChoices());
		chooserCB.setPromptText("Antal grupper");
		chooserCB.setOnAction( event -> {
			data.destroyGroups();
			data.createGroups((int)chooserCB.getValue());
			table.setRoot(createRoot());
		});

		Button okButton = new Button("Ok");
		okButton.setOnAction(event -> {
			chooser.setVisible(false);
			createGroupsMI.setDisable(true);
			data.saveData();
		});
		HBox hbox = new HBox(chooserCB, okButton);
		hbox.setVisible(false);		// Skal først være synlig når Dan grupper vælges i menuen.
		return hbox;
	}
}