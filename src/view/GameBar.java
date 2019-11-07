package view;

import control.GameBarEvents;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class GameBar extends Pane {
	
	private ImageView start;
	private ImageView save;
	private ImageView load;
	private ImageView exit;
	
	private ColorAdjust greyout;
	private ColorAdjust norm;
	
	
	public GameBar() {
		super();
		this.setStyle("-fx-background-color: #808080"); //background gray
		HBox hbox = new HBox();
		
		greyout = new ColorAdjust();
		greyout.setBrightness(-0.75);
		
		norm = new ColorAdjust();
		norm.setBrightness(0);
		
		hbox.setMinHeight(75);
		hbox.prefHeight(100);
		hbox.setAlignment(Pos.CENTER_LEFT);
		hbox.setPadding(new Insets(10));
		hbox.setSpacing(10);
		
		start = new ImageView();
		start.setImage(getImage("New"));
		Tooltip.install(start, new Tooltip("Start"));
		start.setFitHeight(50);
		start.setPreserveRatio(true);
		
		
		save = new ImageView();
		save.setImage(getImage("Save"));
		Tooltip.install(save, new Tooltip("Save"));
		save.setFitHeight(50);
		save.setPreserveRatio(true);		
		
		
		load = new ImageView();
		load.setImage(getImage("Load"));
		Tooltip.install(load, new Tooltip("Load"));
		load.setFitHeight(50);
		load.setPreserveRatio(true);
		
		exit = new ImageView();
		exit.setImage(getImage("Exit"));
		Tooltip.install(exit, new Tooltip("Exit"));
		exit.setFitHeight(50);
		exit.setPreserveRatio(true);
		
		
		hbox.getChildren().addAll(start,load,save,exit);
		this.getChildren().add(hbox);
		
		
		disallowSave();
		GameBarEvents.setEvents(start,save,load,exit);
	}
	
	private static Image getImage(String name) {
		return new Image("/resources/" + name + ".png");
	}
	
	public ImageView getStart() {
		return start;
	}
	public ImageView getSave() {
		return save;
	}
	public ImageView getLoad() {
		return load;
	}
	public ImageView getExit() {
		return exit;
	}
	public void allowSave() {
		GameBarEvents.ableSave = true;
		this.save.setEffect(norm);
	}
	public void disallowSave() {
		GameBarEvents.ableSave = false;
		this.save.setEffect(greyout);
	}

}
