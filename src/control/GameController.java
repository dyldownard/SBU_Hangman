package control;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import model.GameState;
import view.Hangman;
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

}
