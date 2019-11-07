package control;

import javafx.scene.image.ImageView;

/**
 * Events class that allows the GameBar's buttons to be set to Modular Events
 * @author Dylan T. Downard
 * 
 */
public final class GameBarEvents {
	
	
	
	public GameBarEvents(ImageView start, ImageView save, ImageView load, ImageView exit) {
		setEvents(start,save,load,exit);
	}
	
	
	public static void setEvents(ImageView start, ImageView save, ImageView load, ImageView exit) {
		startEvent(start);
		saveEvent(save);
		loadEvent(load);
		exitEvent(exit);
	}
	
	/**
	 * 
	 * @param start - ImageView that will act as the start button
	 */
	private static void startEvent(ImageView start) {
		
	}
	
	private static void saveEvent(ImageView save) {
		
	}
	private static void loadEvent(ImageView load) {
		
	}
	private static void exitEvent(ImageView exit) {
		
	}
	
}
