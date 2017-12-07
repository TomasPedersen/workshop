package controller;

import java.io.*;
import static model.Constants.SETTINGS_FILENAME;		// Således slipper man for at skrive Constants.SETTINGS_FILENAME længere nede i koden.

public class Settings {
	// Indstillinger der kan ændres i SettingsWindow.
	// Værdierne tildelt her vises i SettingsWindow når der ikke er indlæst noget fra disk.
	public String dbHost="";
	public String dbUser="";
	public String dbPwd="";
	public String emailHost="";
	public String emailUser="";
	public String emailPwd="";
	
	// Constructor
	public Settings(){
		readSettings();
	}
	
	// Læs fra disk
	public void readSettings(){
		FileInputStream fis=null;
		ObjectInputStream ois=null;
		
		try{
			fis = new FileInputStream(SETTINGS_FILENAME);
			ois = new ObjectInputStream(fis);
			
			dbHost = (String)ois.readObject();
			dbUser = (String)ois.readObject();
			dbPwd = (String)ois.readObject();
			emailHost = (String)ois.readObject();
			emailUser = (String)ois.readObject();
			emailPwd = (String)ois.readObject();
		} catch(FileNotFoundException fnf){
			System.out.println("Konfigurationsfil ikke fundet: "+ SETTINGS_FILENAME);
		} catch (IOException ioe){
			ioe.printStackTrace();
		} catch(ClassNotFoundException cnfe) {
			System.out.println("Class not found: " + cnfe.getException());
			try {
				ois.close();
				fis.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
	
	// Skriv indstillinger til disk
	public void writeSettings(){
		FileOutputStream fos=null;
		ObjectOutputStream oos=null;
		
		try{
			fos = new FileOutputStream(SETTINGS_FILENAME);
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(dbHost);
			oos.writeObject(dbUser);
			oos.writeObject(dbPwd);
			oos.writeObject(emailHost);
			oos.writeObject(emailUser);
			oos.writeObject(emailPwd);
		} catch(IOException ioe){
			ioe.printStackTrace();
		}
		try{
			oos.close();
			fos.close();
		} catch (IOException ioe){ioe.printStackTrace();}
	}
}