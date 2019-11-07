package model;

import java.rmi.UnexpectedException;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import view.App;


public class GameState {
	
	private String WORD;
	private boolean[] charactersCorrect;
	private int amountWrong;
	private final int MAX_WRONG = 10;
	private boolean[] guessedCharacters;

	
	public GameState(String word) {
		this.WORD = word;
		this.amountWrong = 0;
		guessedCharacters = new boolean[26];	//automatically 26 falses
		charactersCorrect = new boolean[WORD.length()];
	}
	
	
	public boolean guessLetter(char C) throws UnexpectedException {
		int spot = getValue(C);
		if (guessedCharacters[spot]==false) {
			guessedCharacters[spot] = true;
			//guessed character is available, see what the answer is
			if (getOccurances(WORD, C) > 0) {
				correct(C);
			}else {
				incorrect(C);
			}
		}
		return false;	//false means that the letter was already guessed
	}
	
	
	private void correct(char C) {
		for (int i = 0; i < charactersCorrect.length; i++) {
			if (WORD.charAt(i) == C || WORD.charAt(i) == ((char) C +32)) {
				charactersCorrect[i] = true;
			}
		}
		App.gc.update();
	}
	
	
	public void incorrect(char C) {
		amountWrong++;
		App.gc.update();
	}
	/**
	 * Takes in Character C, and based off of alphabetic position, returns 0-26. 
	 * Treats upper and lower cases the same.
	 * 
	 * @param C - character inputed to get the 0-26 value
	 * @return value of letter
	 */
	private int getValue(char C) {
		if (C >= 97) {
			C-= 32;
		}
		if (C<65) {
			System.out.println("unexpected character");
			C=65;
		}
		return (C-65);
	}
	
	private int getOccurances(String word, char C) {
		int occ = 0;
		
		for(char c : word.toCharArray()) {
			if (c == C || c == (char) (C+32)) {
				occ++;
			}
		}
		return occ;
	}
	
	public Label[] getCharactersCorrect() {
		Label[] labels = new Label[WORD.length()];
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new Label();
			if (charactersCorrect[i] == true) {
				labels[i].setText((char) WORD.charAt(i) + "");
			}else {
				labels[i].setText(" ");
			}
			labels[i].setPrefWidth(25);
			labels[i].setAlignment(Pos.CENTER);
			labels[i].setFont(new Font(20));
			labels[i].setBorder(Border.EMPTY);
		}
		return labels;
	}
	
	public Label[] getCharactersCorrectLoss() {
		Label[] labels = new Label[WORD.length()];
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new Label();
			if (charactersCorrect[i] == true) {
				labels[i].setText((char) WORD.charAt(i) + "");
			}else {
				labels[i].setText((char) WORD.charAt(i) + "");
				labels[i].setStyle("-fx-background-color: #CD5C5C");
				labels[i].setTextFill(Color.WHITE);
			}
			labels[i].setPrefWidth(25);
			labels[i].setAlignment(Pos.CENTER);
			labels[i].setFont(new Font(20));
			labels[i].setBorder(Border.EMPTY);
		}
		return labels;
	}
	
	public Label[] getCharactersLeft() {
		Label[] labels = new Label[26];
		for (int i = 0; i<labels.length; i++) {
			labels[i] = new Label();
			labels[i].setText(((char) (i + 65)) + ""); 
			labels[i].setStyle("-fx-border-color: black;");
			if (guessedCharacters[i] == true) {
				labels[i].setStyle("-fx-background-color: #D3D3D3; -fx-border-color: black;");
			}
			labels[i].setPrefWidth(25);
			labels[i].setAlignment(Pos.CENTER);
			labels[i].setFont(new Font(20));
			labels[i].setBorder(Border.EMPTY);
			
		}
		return labels;
	}
	
	public boolean isGameLost() {
		return (amountWrong>=MAX_WRONG);
	}
	public boolean isGameWon() {
		for (boolean b : charactersCorrect) {
			if (!b) {
				return false;
			}
		}
		return true;
	}
	
}
