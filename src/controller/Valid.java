package controller;

/**
 * 	Check for gyldige værdier.
 */
public class Valid {
	// Gyldigt: Navn.
	public boolean name(String name){
		// Tag den del af strengen som er før første mellemrum.
		String fornavn;
		if(name.indexOf(" ") >0){	// Hvis der er et mellemrum.
			fornavn = name.substring(0, name.indexOf(" "));
		}else{ 						// Ellers lad hele strengen være navnet.
			fornavn = name;
		}

		// Der er mindst to bogstaver i et fornavn.
		if(fornavn.length() >= 2) { return true; }
		else{ return false; }
	}
	// Gyldigt: Telefonnummer.
	public boolean phone(String phoneNumber){
		// TODO: Check for gyldigt telefonnummer
		return false;
	}
	// Gyldigt: Email
	public boolean email(String emailAddress){
		boolean result = true;
// TODO: Check om emailadresse er gyldigt format.
	return result;
	}
	// TODO: Initial.
	// TODO: alder.
	// TODO: blokering.
}
