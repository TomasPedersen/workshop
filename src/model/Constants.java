package model;

public class Constants {
	public static final boolean DEBUG = true;
	// Indstillinger der ikke skal ændres i SettingsWindow
	public static final String DATA_FILENAME = "workshop.data";
	public static final String DB_NAME = "workshop";
	public static final String SETTINGS_FILENAME = "workshop.conf";
	public static final int MINGROUPS = 2;		// Der kan ikke laves mindre end to grupper.
	public static final int MAXGROUPS = 10;		// Det maksimale antal grupper der kan dannes.
	public static final int MIN_GROUP_SIZE = 3;	// Hvor mange personer der mindst skal være i hver gruppe.
}
