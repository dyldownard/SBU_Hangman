package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.GameState;
import view.App;
import view.Hangman;
import view.KeyboardView;

/**
 * Events class that allows the GameBar's buttons to be set to Modular Events
 * @author Dylan T. Downard
 * 
 */
public final class GameBarEvents {
	
	public static boolean ableSave = false;
	
	public GameBarEvents(ImageView start, ImageView save, ImageView load, ImageView exit) {
		setEvents(start,save,load,exit);
	}
	
	
	public static void setEvents(ImageView start, ImageView save, ImageView load, ImageView exit) {
		startEvent(start);
		saveEvent(save);
		loadEvent(load);
		exitEvent(exit);
	}
	
	/**
	 * 
	 * @param start - ImageView that will act as the start button
	 */
	private static void startEvent(ImageView start) {
		//TODO set up word generator
		start.setOnMouseClicked(e -> {
			if (ableSave) {
				Alert saveAlert = new Alert(AlertType.WARNING,"Game in progress. Would you like to save?", ButtonType.NO, ButtonType.CANCEL, ButtonType.OK);
				saveAlert.setTitle("Save Warning");
				saveAlert.setHeaderText("");
				
				Optional<ButtonType> buttons = saveAlert.showAndWait();
				if (buttons.get() == ButtonType.OK) {
					FileChooser fc = new FileChooser();
					fc.getExtensionFilters().add(new ExtensionFilter("Hangman files (*.hng)", "*.hng"));
					File hangFile = fc.showSaveDialog(App.stage);
					try {
						ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(hangFile));
						oos.writeObject(App.gc.currentState);
						oos.close();
						App.gBar.disallowSave();
					} catch (FileNotFoundException e1) {
						System.out.println("exited filechooser");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}else if (buttons.get() == ButtonType.CANCEL) {
					return;
				}
			}
			GameState gs;
			try {
				gs = new GameState(getRandomWord());
			} catch (FileNotFoundException e1) {
				System.out.println("hi");
				gs = new GameState("Hello");
			}
			App.gc = new GameController(gs);
			App.hangman = new Hangman();
			KeyboardView upper = App.gc.kViewCorrect;
			KeyboardView kv = App.gc.kViewLeft;
			App.remaining = new Label();
			App.remaining.setText("Guesses Remaining: 10");
			
			App.vbox.getChildren().clear();
			App.hbox.getChildren().clear();
			App.vbox.getChildren().addAll(App.remaining, upper,kv);
			App.hbox.getChildren().addAll(App.hangman,App.vbox);
		});
	}
	
	private static void saveEvent(ImageView save) {
		save.setOnMouseClicked(e -> {
			if (ableSave) {
				FileChooser fc = new FileChooser();
				fc.getExtensionFilters().add(new ExtensionFilter("Hangman files (*.hng)", "*.hng"));
				File hangFile = fc.showSaveDialog(App.stage);
				try {
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(hangFile));
					oos.writeObject(App.gc.currentState);
					oos.close();
					App.gBar.disallowSave();
				} catch (FileNotFoundException e1) {
					System.out.println("exited filechooser");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	private static void loadEvent(ImageView load) {
		load.setOnMouseClicked(e -> {
			if (ableSave) {
				Alert saveAlert = new Alert(AlertType.WARNING,"Game in progress. Would you like to save?", ButtonType.NO, ButtonType.CANCEL, ButtonType.OK);
				saveAlert.setTitle("Save Warning");
				saveAlert.setHeaderText("");
				
				Optional<ButtonType> buttons = saveAlert.showAndWait();
				if (buttons.get() == ButtonType.OK) {
					FileChooser fc = new FileChooser();
					fc.getExtensionFilters().add(new ExtensionFilter("Hangman files (*.hng)", "*.hng"));
					File hangFile = fc.showSaveDialog(App.stage);
					try {
						ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(hangFile));
						oos.writeObject(App.gc.currentState);
						oos.close();
						App.gBar.disallowSave();
					} catch (FileNotFoundException e1) {
						System.out.println("exited filechooser");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}else if (buttons.get() == ButtonType.CANCEL) {
					return;
				}
			}
			FileChooser fc = new FileChooser();
			fc.setSelectedExtensionFilter(new ExtensionFilter("Hangman files (*.hng)", "*.hng"));
			File hangFile = fc.showOpenDialog(App.stage);
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(hangFile));
				GameState newGS = (GameState) ois.readObject();
				App.gc = new GameController(newGS);
				App.hangman = new Hangman();
				KeyboardView upper = App.gc.kViewCorrect;
				KeyboardView kv = App.gc.kViewLeft;
				
				App.remaining = new Label();
				App.remaining.setText("Guesses Remaining: " + (10- App.gc.currentState.getAmountWrong()));
				
				App.vbox.getChildren().clear();
				App.hbox.getChildren().clear();
				App.vbox.getChildren().addAll(upper,kv);
				App.hbox.getChildren().addAll(App.hangman,App.vbox);
				ois.close();
				App.gBar.disallowSave();
			} catch (FileNotFoundException e1) {
				System.out.println("exited filechooser");
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		});
	}
	private static void exitEvent(ImageView exit) {
		exit.setOnMouseClicked(e -> {
			if (ableSave) {
				Alert saveAlert = new Alert(AlertType.WARNING,"Game in progress. Would you like to save?", ButtonType.NO, ButtonType.CANCEL, ButtonType.OK);
				saveAlert.setTitle("Save Warning");
				saveAlert.setHeaderText("");
				
				Optional<ButtonType> buttons = saveAlert.showAndWait();
				if (buttons.get() == ButtonType.OK) {
					FileChooser fc = new FileChooser();
					fc.getExtensionFilters().add(new ExtensionFilter("Hangman files (*.hng)", "*.hng"));
					File hangFile = fc.showSaveDialog(App.stage);
					try {
						ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(hangFile));
						oos.writeObject(App.gc.currentState);
						oos.close();
						App.gBar.disallowSave();
					} catch (FileNotFoundException e1) {
						System.out.println("exited filechooser");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					System.exit(0);
				}else if (buttons.get() == ButtonType.NO) {
					App.stage.close();
					System.exit(0);
				}
			}else {
				System.exit(0);
			}
		});
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
