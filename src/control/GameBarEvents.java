package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.GameState;
import view.GameBar;
import view.Hangman;

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
		setHover(start,save,load,exit);
	}
	
	private static void setHover(ImageView... views) {
		for (ImageView view : views) {
			view.setOnMouseEntered(e -> {
				if (view != views[1] || ableSave) {
					GameBar.hover(view);
				}
			});
			view.setOnMouseExited(e -> {
				if (view != views[1] || ableSave) {
					GameBar.unhover(view);
				}
			});
		}
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
				GameController.setUpNew();
			});
			bt.setOnKeyPressed(e1 -> {
				if (e1.getCode().equals(KeyCode.SPACE)) {
					GameController.setUpNew();
				}
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
					GameController.setUpLoad(newGS);
				});
				bt.setOnKeyPressed(e1 -> {
					if (e1.getCode().equals(KeyCode.SPACE)) {
						GameController.setUpLoad(newGS);
					}
				});
				Hangman.rootPane.setCenter(bt);
				Hangman.gBar.disallowSave();
			} catch (FileNotFoundException e1s) {
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
}
