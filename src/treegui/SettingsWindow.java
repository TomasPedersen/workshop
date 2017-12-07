package treegui;

import controller.Settings;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SettingsWindow extends Application{
	private Settings settings;
	private Stage settingsStage=null;
	
	// Constructor
	public SettingsWindow(){
		settings = new Settings();
	}
	public SettingsWindow(Settings settings){
		this.settings = settings;
	}
	
	@Override
	public void start(Stage settingsStage) throws Exception {	// TODO: Catch Exception her og fjern try/catch i MainWindow.
	
	// Indstillinger: Database
	Label databaseLabel = new Label("Database");
	
	Label dbhostLabel = new Label("Host");
	TextField dbhostField = new TextField(settings.dbHost);
	HBox dbhostBox = new HBox(dbhostLabel, dbhostField);
	
	Label dbuserLabel = new Label("Brugernavn");
	TextField dbuserField = new TextField(settings.dbUser);
	HBox dbuserBox = new HBox(dbuserLabel, dbuserField);
	
	Label dbPwdLabel = new Label("Adgangskode");
	PasswordField dbPwdField = new PasswordField();	// TODO: PasswordField
		dbPwdField.setText(settings.dbPwd);
	HBox dbPwdBox = new HBox(dbPwdLabel, dbPwdField);
	
	VBox dbBox = new VBox(databaseLabel, dbhostBox, dbuserBox, dbPwdBox);
	
	// Indstillinger: Email
	Label emailLabel = new Label("Email");
	
	Label emailHostLabel = new Label("SMTP-server");
	TextField emailHostField = new TextField(settings.emailHost);
	HBox emailHostBox = new HBox(emailHostLabel, emailHostField);
	
	Label emailUserLabel = new Label("Brugernavn");
	TextField emailUserField = new TextField(settings.emailUser);
	HBox emailUserBox = new HBox(emailUserLabel, emailUserField);
	
	Label emailPwdLabel = new Label("Adgangskode");
	TextField emailPwdField = new TextField(settings.emailPwd);	// TODO: PasswordField.
	HBox emailPwdBox = new HBox(emailPwdLabel, emailPwdField);
	
	VBox emailBox = new VBox(emailLabel, emailHostBox, emailUserBox, emailPwdBox);
	
	// Knapper.
	// Knap: Ok
	Button okButton = new Button("Ok");
	okButton.setDefaultButton(true);
	okButton.setOnAction(event -> {
		// Kopier værdier fra TextFields til værdier i settings.
		// Indstillinger: Database.
		settings.dbHost = dbhostField.getText();
		settings.dbUser = dbuserField.getText();
		settings.dbPwd = dbPwdField.getText();
		// Indstillinger: Email.
		settings.emailHost = emailHostField.getText();
		settings.emailUser = emailUserField.getText();
		settings.emailPwd = emailPwdField.getText();
		
		settings.writeSettings();	// Gem indstillinger på disk.
		settingsStage.close();	// Luk vinduet.
		
		System.out.println("dbPwd: "+dbPwdField.getText());
	});
	// Knap: Annuller.
	Button cancelButton = new Button("Annuller");
	cancelButton.setCancelButton(true);
	cancelButton.setOnKeyPressed(event -> {
		if(event.getCode() == KeyCode.ENTER){handleCancelButton();}
	});
	cancelButton.setOnAction(event -> settingsStage.close());

	HBox buttonsBox = new HBox(okButton, cancelButton);

	// VBox for hele vinduet.
	VBox vbox = new VBox(dbBox, emailBox, buttonsBox);
	Scene scene = new Scene(vbox);
	
	// Stage
	settingsStage.setScene(scene);
	settingsStage.show();
	}
	
	private void handleCancelButton(){settingsStage.close();}
}