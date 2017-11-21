package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Groups {
	private boolean hasBeenCreated = false;
	private ObservableList<Group> groups = FXCollections.observableArrayList();
	private ObservableList<Person> persons = FXCollections.observableArrayList();

	public Groups(ObservableList<Person> persons){
		this.persons = persons;		// TODO: persons skal nok ikke bruges til noget. groups.get(0).getMembers() er det samme.
		// Opret gruppe nul og tilføj alle i persons.
		Group group = new Group("Udenfor gruppe");
		for (Person p:persons
			 ) {
			group.add(p);
		}
		groups.add(group);
	}
	
	public void create(int numGroups, int groupSize){

		// Antal grupper ikke fastlagt. Dan så mange grupper som muligt med ønsket størrelse. Der afrundes så gruppestørrelse bliver så ens som muligt.
		if(numGroups == 0){

		}
		// Gruppestørrelse ikke fastlagt. Dan det ønskede antal grupper. Så ens i størrelse som muligt.
		if(groupSize == 0){
			// Opret grupper
			for(int i = 1; i <= numGroups; i++){
				groups.add(new Group("Gruppe"+i));
			}
			// Gennemløb liste over grupper. Tag en tilfældig person og sæt ind i gruppen.
			int groupIndex = 1;
			while(groups.get(0).getMembers().size() > 0){	// Så længe der er personer i gruppe0/udenfor gruppe.
				groups.get(groupIndex).add(groups.get(0).getMembers().get(0));	// TODO: Sidste nul skal ændres til random.
				System.out.println("Tilføjer: "+groups.get(0).getMembers().get(0));
				System.out.println("Antal i gruppe 0: "+groups.get(0).getMembers().size());
				groups.get(0).remove(0);	// TODO: remove(0) skal være remove(random).
				groupIndex++;
				if(groupIndex == groups.size()){groupIndex = 1;}
			}
		}
		
		// Begge er fastlagt. Check om det er muligt. Giv alternative forslag.
/*		if(numGroups * groupSize > persons.size()){
			System.out.println("For mange");
			// Ikke muligt. Foreslå alternativer.
		}*/
		if(numGroups * groupSize < persons.size()){
			System.out.println("Mindre end");
			// For mange personer. Øg gruppestørrelse eller antal grupper.
		}
		if(numGroups * groupSize == persons.size()){
			System.out.println("Det går op");
			// Det går op. Lav nogle grupper.
		}
	}

	// Getters
	public ObservableList<Group> getGroups(){return groups; }
	public ObservableList<Person> getPersons(){
		return persons;
	}
	public boolean hasBeenCreated(){ return hasBeenCreated; }
}