package model;

import static model.Constants.*;
import controller.Database;
import controller.FileIO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.Serializable;

/**
 * Her bor data.
 */
// TODO: Det giver formentlig mening at lave denne klasse som singleton.
public class Data implements Serializable{
	private ObservableList<Group> groups = FXCollections.observableArrayList();

	Database database = new Database();		// TODO: Læs fra database efter behov/Settings.
	FileIO fileio = new FileIO();			// TODO: Læs fra disk efter behov/Settings.

	// Constructor.
	public Data(){
		// Hent data fra fil og læg i groups.
		// groups.addAll(fileio.readData());

		// Hvis der ikke er data på disk.
		if(groups.size() == 0) groups.add(new Group("Udenfor gruppe"));

		if(DEBUG)System.out.println("groups.size(): "+groups.size());
		if(DEBUG)System.out.println("groups.get(0).getMembers().size(): "+groups.get(0).getMembers().size());

		// Hent data fra database og læg i group0.
		groups.get(0).addAll(database.readData().get(0).getMembers());
	}
	
	// Gem data på disk eller i database.
	public void saveData(){
		// Gem alle data på disk.
		fileio.writeData(groups);

		// Gem data i database.
		//database.writeData(groups);	// TODO: Skriv kun ændringer til database. At skrive alle data tager alt for lang tid.
	}
	
	// Dan grupper.
	public void createGroups(int numGroups){
		// TODO: Når grupper allerede er dannet, skal medlemmer i gruppe0 fordeles i eksisterende grupper.
		// Opret grupper
		for(int i = 1; i <= numGroups; i++){
			groups.add(new Group("Gruppe"+i));
		}
		// Gennemløb liste over grupper. Tag en tilfældig person og sæt ind i gruppen.
		int groupIndex = 1;
		while(groups.get(0).getMembers().size() > 0){	// Så længe der er personer i gruppe0/udenfor gruppe.
			groups.get(groupIndex).addMember(groups.get(0).getMembers().get(0));	// TODO: Sidste nul skal ændres til random.
			groups.get(0).removeMember(0);	// TODO: removeMember(0) skal være removeMember(random).
			groupIndex++;
			if(groupIndex == groups.size()){groupIndex = 1;}  // Løkke af grupper, fortsæt med gruppe nummer 1.
		}
	}
	// Opløs grupper.
	public void destroyGroups(){
		// Gennemløb grupper
		// Tag hver person i gruppe.
		// Tilføj til gruppe0
		// Fjern person fra gruppe.
		int index;
		for (index = 1; index < groups.size(); index++) {	// Gruppe0 bevares, medlemmer fra andre grupper tilføjes gruppe0
			groups.get(0).getMembers().addAll(groups.get(index).getMembers());	// Tilføj members fra nedlagt gruppe til gruppe0
		}
		// Slet alle grupper på nær gruppe0.
		groups.remove(1, index);
		System.out.println("groups.size(): "+groups.size());
		System.out.println("groups.get(0).getMembers().size(): "+groups.get(0).getMembers().size());
	}

	// Opdater med nye i databasen.
	public void update(){

	}

	// Getters
	public ObservableList<Group> getGroups(){return groups; }
	/**
	 * Beregner valgmuligheder for antal grupper.
	 * @return ObservableList til brug i en ComboBox.
	 */
	public ObservableList<Integer> numGroupsChoices(){
		ObservableList<Integer> choises = FXCollections.observableArrayList();
		// Der er mindst tre personer i en gruppe.
		// Der er højest 10 grupper.
		// Sådan cirka. Står i Constants.
		
		// Hvor mange personer er ikke i gruppe/er i gruppe0.
		// Divider med mindste antal personer i en gruppe.
		// Lav en liste med heltal fra 2..ANTAL
		// TODO: Hvad med afrunding? Hvis 25 personer og mindst to i hver, 12 eller 13 grupper?
		// TODO: Sæt begrænsning for MAXGROUPS antal grupper.
		int numGroups = groups.get(0).getMembers().size()/Constants.MIN_GROUP_SIZE;
		if(numGroups > Constants.MAXGROUPS) numGroups = Constants.MAXGROUPS;

		for(int i=Constants.MINGROUPS; i <= numGroups; i++) choises.add(i);
		if(choises == null)choises.add(2);
		System.out.println("numGroupsChoices groups.size(): "+groups.size());
		System.out.println("numGroupsChoices Dette er valgmulighederne: "+choises);
		return choises;
	}
}