package controller;

import javafx.application.Application;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Print extends Application {
	Node node;

	// Constructor
	public Print(){
		node = new Label("Noget tekst som er en node");
	}
	public Print(Node node){
		this.node = node;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("Print");
		PrinterJob printerJob = PrinterJob.createPrinterJob();
//		printerJob.showPrintDialog(primaryStage);
//		printerJob.cancelJob();
		Printer printer = Printer.getDefaultPrinter();
		System.out.println(printer.getName());
//		System.out.println(printer.getPrinterAttributes().getDefaultPaperSource());
//		System.out.println(printerJob.getPrinter().getPrinterAttributes());
		if(printerJob != null){
			boolean success = printerJob.printPage(node);
//			boolean success=true;
			if(success){
				printerJob.endJob();
				System.out.println("PrintJob.endJob()");
			}else{
				System.out.println("Ingen succes");
			}
		} else{
			System.out.println("Ingen printer installeret");
		}
		primaryStage.close();
		System.out.println("primaryStage.close()");
		//System.exit(0);
	}
}