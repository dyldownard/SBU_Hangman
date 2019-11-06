package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application{

	BorderPane pane;
	GameBar gBar;
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		pane = new BorderPane();
		gBar = new GameBar();
		
		pane.setTop(gBar);
		
		Scene scene = new Scene(pane, 600,400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Hangman");
		primaryStage.getIcons().add(new Image("/resources/Exit.png"));
		primaryStage.show();
		
	}
	
	
	
	
	public static void main(String[] args) { launch(args); }
}
