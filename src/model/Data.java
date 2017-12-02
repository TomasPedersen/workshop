package model;

import controller.Database;
import controller.FileIO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Her bor data.
 */
public class Data implements Serializable{
	private ArrayList<Group> groups = new ArrayList<>();
	Database database = new Database();
	FileIO fileio = new FileIO();
	
	/**
	 * Indlæs data fra disk eller fra database.
	 */
	public Data(){
		// Hent data fra fil og læg i groups.
		groups.addAll(fileio.readData());
		// Hent data fra database og læg i groups.
		//groups.addAll(database.readDBdata());
	}
	
	// Dan grupper.
	public void createGroups(int numGroups, int groupSize){

		// Opret grupper
		for(int i = 1; i <= numGroups; i++){
			groups.add(new Group("Gruppe"+i));
		}
		// Gennemløb liste over grupper. Tag en tilfældig person og sæt ind i gruppen.
		int groupIndex = 1;
		while(groups.get(0).getMembers().size() > 0){	// Så længe der er personer i gruppe0/udenfor gruppe.
			groups.get(groupIndex).add(groups.get(0).getMembers().get(0));	// TODO: Sidste nul skal ændres til random.
			groups.get(0).remove(0);	// TODO: remove(0) skal være remove(random).
			groupIndex++;
			if(groupIndex == groups.size()){groupIndex = 1;}  // Løkke af grupper, fortsæt med første gruppe.
		}
	}

	// Getters
	public ArrayList<Group> getGroups(){return groups; }
	
	// Gem data på disk.
	public void saveToDisk(){
		fileio.writeData(groups);
	}
}