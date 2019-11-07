package view;

import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

public class KeyboardView extends FlowPane{
	Label[] labs;
	
	public KeyboardView(Label[] newLabs) {
		super();
		setKeys(newLabs);
		
		this.setPrefWidth(275);
		this.setMaxWidth(275);
		
	}
	
	public void setKeys(Label[] newLabs) {
		this.getChildren().removeAll(this.getChildren());
		this.getChildren().addAll(newLabs);
	}
	
}
