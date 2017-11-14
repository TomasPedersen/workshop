package guione;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class CustomCellFactory<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {
	CustomCellFactory(){}

	@Override
	public TableCell<S, T> call(TableColumn<S, T> p){
		TableCell<S, T> cell = new TableCell<S, T>(){
			@Override
			protected void updateItem(Object item, boolean empty){
				String tableRowItem = getTableRow().getItem().toString();
				System.out.println(tableRowItem);
				getStyleClass().add("-fx-text-color red");
				setText(tableRowItem.toString());
			}
		};
		return cell;
	}
}

