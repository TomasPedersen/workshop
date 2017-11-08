import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GuiCreateGroups {
	Label resultLabel = null;
	ComboBox groupsBox = null;
	ComboBox sizeBox = null;
	Stage gcgStage = null;

	public void create(ObservableList<String> retVal)  {
		resultLabel = new Label();
		gcgStage = new Stage();
		gcgStage.setTitle("Dan grupper");
		// Der skal være to elementer i retVal, ellers går der ged i det.
		retVal.add("0");
		retVal.add("0");
		
		
		// ComboBoxe til gruppestørrelse.
		ObservableList<Integer> groupsOptions = FXCollections.observableArrayList(
				3,4,5,6
		);
		groupsBox = new ComboBox(groupsOptions);
		groupsBox.setPromptText("Antal grupper");
		groupsBox.setOnAction(event -> {
			calculateProduct();
			retVal.add(0, groupsBox.getValue().toString() );
		});
		
		// ComboBox til antal grupper.
		ObservableList<Integer> sizeOptions = FXCollections.observableArrayList(
				3,4,5,6
		);
		sizeBox = new ComboBox(sizeOptions);
		sizeBox.setPromptText("Gruppestørrelse");
		//sizeBox.setValue(sizeOptions.get(0));	// Sæt valgt værdi til den første på listen.
		sizeBox.setOnAction(event -> {
			calculateProduct();
			retVal.add(1, sizeBox.getValue().toString() );
		});

		Button buttonOk = new Button("Ok");
		buttonOk.setOnKeyPressed(event -> {
			if(event.getCode() == KeyCode.ENTER){handleOkButton();}
		});
		buttonOk.setOnAction(event -> handleOkButton());

		// Menuer
		Menu fileMenu = new Menu("_File");
		MenuItem exitMI = new MenuItem("_Exit");
		fileMenu.getItems().add(exitMI);
		exitMI.setOnAction(event -> gcgStage.close() );

		MenuBar menubar = new MenuBar();
		menubar.getMenus().addAll(fileMenu);

		VBox vbox = new VBox();
		HBox hbox = new HBox(groupsBox, sizeBox, buttonOk);
		vbox.getChildren().addAll(menubar,hbox,resultLabel);
		Scene scene = new Scene(vbox, 400,400);
		
		gcgStage.setScene(scene);
		gcgStage.show();
	}

	private void handleOkButton(){
		gcgStage.close();	//TODO: Klik på OK skal ikke blot lukke vinduet. Det skal også starte dannelse af grupper med de valgte parametre.
		//new Groups().create(Integer.valueOf(sizeBox.getValue().toString()), Integer.valueOf(groupsBox.getValue().toString()));
	}
	private void calculateProduct(){
		if( (groupsBox.getValue() != null) && (sizeBox.getValue() != null) ){
			resultLabel.setText(String.valueOf(Integer.valueOf(groupsBox.getValue().toString()) * Integer.valueOf(sizeBox.getValue().toString())));
		}
	}
}