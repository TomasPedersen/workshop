import javafx.collections.ObservableList;

public class Groups {
	public void create(int numGroups, int groupSize){
		ObservableList<Person> persons = new Data().getData();

		// Antal grupper ikke fastlagt. Dan så mange grupper som muligt med ønsket størrelse. Der afrundes så gruppestørrelse bliver så ens som muligt.
		if(numGroups == 0){

		}
		// Gruppestørrelse ikke fastlagt. Dan det ønskede antal grupper. Så ens i størrelse som muligt.
		if(groupSize == 0){

		}
		// Begge er fastlagt. Check om det er muligt. Giv alternative forslag.
		if(numGroups * groupSize > persons.size()){
			System.out.println("For mange");
			// Ikke muligt. Foreslå alternativer.
		}
		if(numGroups * groupSize < persons.size()){
			System.out.println("Mindre end");
			// For mange personer. Øg gruppestørrelse eller antal grupper.
		}
		if(numGroups * groupSize == persons.size()){
			System.out.println("Det går op");
			// Det går op. Lav nogle grupper.
		}
	}
}

