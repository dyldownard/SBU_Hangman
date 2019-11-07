package control;

import javafx.scene.control.Label;
import model.GameState;
import view.App;
import view.KeyboardView;

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
	}

	private void setCharacterEvents() {
		CharacterEvents.setLabelsEvents(charactersLeft);
		CharacterEvents.setKeyEvents(App.scene);
	}

	public void update() {
		//TODO set up win/lose conditions
		this.charactersLeft = currentState.getCharactersLeft();
		this.charactersCorrect = currentState.getCharactersCorrect();
		kViewLeft.setKeys(charactersLeft);
		kViewCorrect.setKeys(charactersCorrect);
		CharacterEvents.setLabelsEvents(charactersLeft);
		if (currentState.isGameLost()) {
			this.gameInProgress = false;
			kViewCorrect.setKeys(currentState.getCharactersCorrectLoss());
		} else if (currentState.isGameWon()) {
			//TODO
			this.gameInProgress = false;
		}	
	}

}
