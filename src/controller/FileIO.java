package controller;

import model.Group;
import java.io.*;
import java.util.ArrayList;

/**
 * Writes data to and reads data from disk.
 */
public class FileIO implements Serializable {
	Settings settings = new Settings();
	
	// Constructor
	public FileIO() {}
	
	// Skriv data til fil.
	public void writeData(ArrayList<Group> groups) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(settings.dataFilename));
			oos.writeObject(groups);
			oos.flush();
			oos.close();
			if (settings.debugLevel > 0) System.out.println("Der blev skrevet til: " + settings.dataFilename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Læs data fra disk.
	public ArrayList<Group> readData() {
		ArrayList<Group> data = new ArrayList<>();
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(settings.dataFilename));
			data = (ArrayList<Group>) ois.readObject();
			ois.close();
			if(settings.debugLevel >0) System.out.println("Data indlæst fra: " + settings.dataFilename);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException notFound) {
			notFound.printStackTrace();
		}
		return data;
	}
}