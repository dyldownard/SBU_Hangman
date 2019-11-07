package control;

import java.rmi.UnexpectedException;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import view.Hangman;

public final class CharacterEvents {

	public static void setLabelsEvents(Label[] labs) {
		for (Label l : labs) {
			labelPress(l);
		}
	}

	private static void labelPress(Label lab) {
		lab.setOnMouseClicked(e -> {
			try {
				if (Hangman.gc.gameInProgress) {
					Hangman.gc.currentState.guessLetter(lab.getText().charAt(0));
				}
			} catch (UnexpectedException e1) {
				e1.printStackTrace();
			}
		});
	}

	public static void setKeyEvents(Scene scene) {
		scene.setOnKeyPressed(e -> {
			if (Hangman.gc.gameInProgress) {
				KeyCode character = e.getCode();
				if (character.isLetterKey()) {
					try {
						Hangman.gc.currentState.guessLetter(character.getName().charAt(0));
					} catch (UnexpectedException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}

}
