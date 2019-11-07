package view;

import control.GameController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Hangman extends Application{

	BorderPane rootPane;
	
	public static Stage stage;
	public static Scene scene;
	public static VBox vbox;
	public static HBox hbox;
	public static GameController gc;
	public static HangmanView hangman;
	public static GameBar gBar;
	public static Label remaining;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		stage = primaryStage;
		rootPane = new BorderPane();
		scene = new Scene(rootPane, 600,400);
		
		
		vbox = new VBox();

		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(50);
		
		hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		rootPane.setCenter(hbox);
		
		gBar = new GameBar();
		rootPane.setTop(gBar);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Hangman");
		primaryStage.getIcons().add(new Image("/resources/Exit.png"));
		primaryStage.show();
	}
	
	public static void main(String[] args) { launch(args); }
}
