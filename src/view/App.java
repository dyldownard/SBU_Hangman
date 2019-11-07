package view;

import control.GameController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.GameState;

public class App extends Application{

	BorderPane pane;
	
	public static Scene scene;
	
	public static GameController gc;
	
	public static Hangman hangman;
	GameBar gBar;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
	
		
		pane = new BorderPane();
		scene = new Scene(pane, 600,400);
		gBar = new GameBar();
		pane.setTop(gBar);
		GameState gs = new GameState("Hello");
		gc = new GameController(gs);
		VBox vbox = new VBox();
		hangman = new Hangman();
		KeyboardView upper = gc.kViewCorrect;
		KeyboardView kv = gc.kViewLeft;
		
		vbox.getChildren().addAll(upper,kv);
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(50);
		
		hbox.getChildren().addAll(hangman,vbox);
		
		pane.setCenter(hbox);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Hangman");
		primaryStage.getIcons().add(new Image("/resources/Exit.png"));
		primaryStage.show();
	}
	
	public static void main(String[] args) { launch(args); }
}
