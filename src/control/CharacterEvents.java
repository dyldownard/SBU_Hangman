package control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.UnexpectedException;
import java.util.Optional;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
				if (character.equals(KeyCode.ESCAPE)) {
					if (GameBarEvents.ableSave) {
						Alert saveAlert = new Alert(AlertType.WARNING,"Game in progress. Would you like to save?", ButtonType.NO, ButtonType.CANCEL, ButtonType.OK);
						saveAlert.setTitle("Save Warning");
						saveAlert.setHeaderText("");
						
						Optional<ButtonType> buttons = saveAlert.showAndWait();
						if (buttons.get() == ButtonType.OK) {
							FileChooser fc = new FileChooser();
							fc.getExtensionFilters().add(new ExtensionFilter("Hangman files (*.hng)", "*.hng"));
							File hangFile = fc.showSaveDialog(Hangman.stage);
							try {
								ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(hangFile));
								oos.writeObject(Hangman.gc.currentState);
								oos.close();
								Hangman.gBar.disallowSave();
							} catch (FileNotFoundException e1) {
								System.out.println("exited filechooser");
							} catch (IOException e1) {
								e1.printStackTrace();
							}catch (NullPointerException e1) {
								System.out.println("no choice taken");
							}
							System.exit(0);
						}else if (buttons.get() == ButtonType.NO) {
							Hangman.stage.close();
							System.exit(0);
						}
					}else {
						System.exit(0);
					}
				}
			}
		});
	}

}
