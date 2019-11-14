package view;

import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

public class KeyboardView extends FlowPane{
	public Label[] labs;
	
	
	public KeyboardView(Label[] newLabs) {
		super();
		labs = newLabs;
		setKeys(newLabs);
		
		this.setPrefWidth(275);
		this.setMaxWidth(275);
	}
	
	public void fixWidth() {
		this.setPrefWidth(labs.length * 25);
		this.setMaxWidth(labs.length * 25);
	}
	
	public void setKeys(Label[] newLabs) {
		this.getChildren().removeAll(this.getChildren());
		this.getChildren().addAll(newLabs);
	}

}
