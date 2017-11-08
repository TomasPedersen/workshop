import javafx.beans.property.SimpleStringProperty;

public class Person {
	private SimpleStringProperty name;
	private SimpleStringProperty phoneNumber;
	private SimpleStringProperty emailAddress;
	private enum Gender {M,K,NA}
	Gender gender;

	Person (String name, String phoneNumber, String emailAddress, Gender gender){
		this.name = new SimpleStringProperty(name);
		this.phoneNumber = new SimpleStringProperty(phoneNumber);
		this.emailAddress = new SimpleStringProperty(emailAddress);
		this.gender = gender;
	}
	Person (String name, String phoneNumber, String emailAddress){
		this(name, phoneNumber, emailAddress, Gender.NA);	// Kald constructer med fire paramtre og tilføj gender som ikke kendt.
	}
	
	public String getName() {
		return name.get();
	}
	
	public SimpleStringProperty nameProperty() {
		return name;
	}
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	public String getPhoneNumber() {
		return phoneNumber.get();
	}
	
	public SimpleStringProperty phoneNumberProperty() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.set(phoneNumber);
	}
	
	public String getEmailAddress() {
		return emailAddress.get();
	}
	
	public SimpleStringProperty emailAddressProperty() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress.set(emailAddress);
	}
}
