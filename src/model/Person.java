package model;

import java.io.Serializable;

public class Person implements Serializable{
	private String name;
	private String phoneNumber;
	private String emailAddress;
	private enum Gender {M,K,NA}
	private Gender gender;
	private Person block;	// Hvem denne person ikke må være i gruppe med.

	public Person (String name, String phoneNumber, String emailAddress, Person block, Gender gender){
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.gender = gender;
		this.block = block;
	}
	public Person (String name, String phoneNumber, String emailAddress){
		this(name, phoneNumber, emailAddress, null, Gender.NA);	// Kald constructer, der tager fem paramtre med block som null og gender som NA.
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
	
	public Person getBlock() {
		return block;
	}
	
	public void setBlock(Person block) {
		this.block = block;
	}
}
