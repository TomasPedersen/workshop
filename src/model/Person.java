package model;

public class Person {
	private String name;
	private String phoneNumber;
	private String emailAddress;
	private enum Gender {M,K,NA}
	private Gender gender;

	public Person (String name, String phoneNumber, String emailAddress, Gender gender){
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.gender = gender;
	}
	public Person (String name, String phoneNumber, String emailAddress){
		this(name, phoneNumber, emailAddress, Gender.NA);	// Kald constructer med fire paramtre og tilføj gender som ikke kendt.
	}

	@Override
	public String toString() {
		return name+" "+phoneNumber+" "+emailAddress;
	}

	// Getters and setters.
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
