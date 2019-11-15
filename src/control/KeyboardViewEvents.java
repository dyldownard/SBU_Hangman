package control;

import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import view.KeyboardView;

public class KeyboardViewEvents {

	public static void setKeyboardHover(KeyboardView key) {
		for (Label lab : key.labs) {
			lab.setOnMouseEntered(e -> {
				if (lab.getBackground()==null) {
				ColorAdjust hover = new ColorAdjust();
				hover.setBrightness(0.5);
				lab.setEffect(hover);
				}
			});
			lab.setOnMouseExited(e -> {
				ColorAdjust norm = new ColorAdjust();
				norm.setBrightness(0);
				lab.setEffect(norm);
			});
		}
	}
	
	
}
