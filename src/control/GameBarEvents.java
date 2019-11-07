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

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.GameState;
import view.Hangman;
import view.HangmanView;
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
		start.setOnMouseClicked(e -> {
			if (ableSave) {
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
					}
					
				}else if (buttons.get() == ButtonType.CANCEL) {
					return;
				}
			}
			
			
			Button bt = new Button("Start Playing");
			bt.setOnMouseClicked(e1 -> {
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
			});
			Hangman.rootPane.setCenter(bt);
			
		});
	}
	
	private static void saveEvent(ImageView save) {
		save.setOnMouseClicked(e -> {
			if (ableSave) {
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
					
				}else if (buttons.get() == ButtonType.CANCEL) {
					return;
				}
			}
			FileChooser fc = new FileChooser();
			fc.getExtensionFilters().add(new ExtensionFilter("Hangman files (*.hng)", "*.hng"));
			File hangFile = fc.showOpenDialog(Hangman.stage);
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(hangFile));
				
				GameState newGS = (GameState) ois.readObject();
				ois.close();
				Button bt = new Button("Start Playing");
				bt.setOnMouseClicked(e1 -> {
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
				});
				Hangman.rootPane.setCenter(bt);
				Hangman.gBar.disallowSave();
			} catch (FileNotFoundException e1) {
				System.out.println("exited filechooser");
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (NullPointerException e1) {
				System.out.println("no choice taken");
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
