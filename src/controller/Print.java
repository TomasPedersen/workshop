package controller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Data;
import model.Group;
import model.Person;

import java.util.Observable;

public class Print extends Application {
	private Data data;
	private Stage printStage;

	// Constructor
	public Print(){this.data = new Data();}		// Bruges under udvikling.
	public Print(Data data){
		this.data = data;
	}

	@Override
	public void start(Stage stage) {
		this.printStage = stage;

		data.createGroups(4);
		printOnScreen(createNode());

		//printStage.show(); printStage.close();
		//System.out.println("printStage.close()");
	}

	/**
	 * Layout af siden der skal printes.
	 * @return Node. Kan sendes til scene() eller printer.
	 */
	private Node createNode(){
		// Gennemløb groups.
		// Lav en liste for hver Group.
		// Marker overskrift for hver gruppe på hver sin side.
		// Placer liste på papir.
		// Lav lige så mange sider som grupper.
		ObservableList<String> listItems=null;

		for (Group g:data.getGroups()
			 ) {
			listItems = FXCollections.observableArrayList();

			listItems.add(g.getGroupName());
			for (Person p:g.getMembers()
				 ) {
				listItems.add(p.getName());
			}
		}
		Node node = new Label("Dummy");

		return node;
	}

	/**
	 * Metode til brug under udvikling. Brug print() for at printe på papir.
	 * @param node
	 */
	private void printOnScreen(Node node){
		Pane parent = new Pane(node);
		Scene scene = new Scene(parent, 500, 500);
		printStage.setScene(scene);
		printStage.show();
	}

	/**
	 * Send node til standardprinter.
	 * @param node
	 */
	private void print(Node node){
		System.out.println("Print");
		PrinterJob printerJob = PrinterJob.createPrinterJob();
//		printerJob.showPrintDialog(printStage);
		printerJob.showPageSetupDialog(printStage);

		Printer printer = Printer.getDefaultPrinter();
		System.out.println(printer.getName());
		if(printerJob != null){
			boolean success = printerJob.printPage(createNode());
			if(success){
				printerJob.endJob();
				System.out.println("PrintJob.endJob()");
			}else{
				System.out.println("Ingen succes");
			}
		} else{
			System.out.println("Ingen printer installeret");
		}
	}
}