package control;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import model.GameState;
import view.Hangman;
import view.HangmanView;
import view.KeyboardView;
/**
 * Handles all user-game interaction in the form of setting up events and handling view elements.
 * @author Dylan T. Downard
 *
 */
public class GameController {

	public GameState currentState;
	private Label[] charactersLeft;
	private Label[] charactersCorrect;
	public KeyboardView kViewLeft;
	public KeyboardView kViewCorrect;
	public boolean gameInProgress = false;

	public GameController(GameState newState) {
		this.currentState = newState;
		this.charactersLeft = newState.getCharactersLeft();
		this.charactersCorrect = newState.getCharactersCorrect();
		setCharacterEvents();
		kViewLeft = new KeyboardView(charactersLeft);
		kViewCorrect = new KeyboardView(charactersCorrect);
		kViewCorrect.fixWidth();
		gameInProgress = true;
		Hangman.gBar.allowSave();
	}

	
	private void setCharacterEvents() {
		CharacterEvents.setLabelsEvents(charactersLeft);
		CharacterEvents.setKeyEvents(Hangman.scene);
	}

	/**
	 * Main Driver for the program; updates all changing view elements.
	 */
	public void update() {
		//TODO set up win/lose conditions
		this.charactersLeft = currentState.getCharactersLeft();
		this.charactersCorrect = currentState.getCharactersCorrect();
		kViewLeft.setKeys(charactersLeft);
		kViewCorrect.setKeys(charactersCorrect);
		CharacterEvents.setLabelsEvents(charactersLeft);
		Hangman.remaining.setText("Guesses Remaining: " + (10 - currentState.getAmountWrong()));
		
		if (currentState.isGameLost()) {
			Hangman.gBar.disallowSave();
			this.gameInProgress = false;
			kViewCorrect.setKeys(currentState.getCharactersCorrectLoss());
			Alert loss = new Alert(AlertType.WARNING);
			loss.setTitle("Game over");
			loss.setGraphic(null);
			loss.setHeaderText("");
			loss.setContentText("You have lost! The word was (" + currentState.getWord() + ")");
			loss.showAndWait();
			
		} else if (currentState.isGameWon()) {
			//TODO
			Hangman.gBar.disallowSave();
			this.gameInProgress = false;
			kViewCorrect.setKeys(currentState.getCharactersCorrectWin());
			Alert won = new Alert(AlertType.INFORMATION);
			won.setTitle("Game over");
			won.setGraphic(null);
			won.setHeaderText("");
			won.setContentText("You have won! The word was (" + currentState.getWord() + ")");
			won.showAndWait();
		}	
	}
	
	public static void setUpNew() {
		GameState gs;
		try {
			gs = new GameState(getRandomWord());
		} catch (FileNotFoundException e2) {
			System.out.println("hi");
			gs = new GameState("Hello");
		}
		Hangman.gc = new GameController(gs);
		Hangman.hangman = new HangmanView();
		KeyboardView upper = Hangman.gc.kViewCorrect;
		KeyboardView kv = Hangman.gc.kViewLeft;
		Hangman.remaining = new Label();
		Hangman.remaining.setText("Guesses Remaining: 10");
		
		Hangman.vbox.getChildren().clear();
		Hangman.hbox.getChildren().clear();
		Hangman.vbox.getChildren().addAll(Hangman.remaining, upper,kv);
		VBox vb = new VBox(Hangman.hangman);
		vb.setAlignment(Pos.CENTER);
		Hangman.hbox.getChildren().addAll(vb,Hangman.vbox);
		Hangman.rootPane.setCenter(Hangman.hbox);
	}
	
	public static void setUpLoad(GameState newGS) {
		Hangman.gc = new GameController(newGS);
		Hangman.hangman = new HangmanView();
		KeyboardView upper = Hangman.gc.kViewCorrect;
		KeyboardView kv = Hangman.gc.kViewLeft;
		for (int i = 0; i < Hangman.gc.currentState.getAmountWrong(); i++) {
			Hangman.hangman.advanceGame();
		}
		Hangman.remaining = new Label();
		Hangman.remaining.setText("Guesses Remaining: " + (10- Hangman.gc.currentState.getAmountWrong()));
		
		Hangman.vbox.getChildren().clear();
		Hangman.hbox.getChildren().clear();
		Hangman.vbox.getChildren().addAll(Hangman.remaining, upper,kv);
		VBox vb = new VBox(Hangman.hangman);
		vb.setAlignment(Pos.CENTER);
		Hangman.hbox.getChildren().addAll(vb,Hangman.vbox);
		Hangman.rootPane.setCenter(Hangman.hbox);
		Hangman.gBar.disallowSave();
	}
	
	private static String getRandomWord() throws FileNotFoundException {
		Scanner input = new Scanner(new File("src/resources/words.txt"));
		String words = "";
		while (input.hasNext()) {
			words += input.next() + " ";
		}
		input.close();
		String[] wordsAr = words.split(" ");
		int rand = (int) (Math.random() * wordsAr.length);
		
		return wordsAr[rand];
	}

}
