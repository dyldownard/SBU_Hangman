package control;

import javafx.scene.image.ImageView;

public final class GameBarEvents {
	
	@Deprecated
	public GameBarEvents(ImageView start, ImageView save, ImageView load, ImageView exit) {
		System.out.println("NOTE: GameBarEvents should not be instanced. Use setEvents(..) instead of the constructor.");
		setEvents(start,save,load,exit);
	}
	
	
	public static void setEvents(ImageView start, ImageView save, ImageView load, ImageView exit) {
		startEvent(start);
		saveEvent(save);
		loadEvent(load);
		exitEvent(exit);
	}
	
	
	private static void startEvent(ImageView start) {
		
	}
	private static void saveEvent(ImageView save) {
		
	}
	private static void loadEvent(ImageView load) {
		
	}
	private static void exitEvent(ImageView exit) {
		
	}
	
}
