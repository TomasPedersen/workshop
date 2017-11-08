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
	private ObservableList<String> retVal = FXCollections.observableArrayList();

	@Override
	public void start(Stage primaryStage) throws Exception {
		new GuiMainWindow(primaryStage).create(new Data().getData());
	}
}