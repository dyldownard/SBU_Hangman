package view;

import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class HangmanView extends Pane{

	//hanger
	private Line base;
	private Line pole;
	private Line ext;
	private Line hang;
	
	//person
	private Circle head;
	private Line torso;
	private Line rarm1;
	private Line rarm2;
	private Line larm1;
	private Line larm2;
	private Line rleg1;
	private Line rleg2;
	private Line lleg1;
	private Line lleg2;
	
	
	private int count = 0;
	public HangmanView() {
		super();
		this.setPrefSize(300, 300);
		
		base = new Line(32,260,268,260);
		
		pole = new Line(83,43,83,260);
		ext = new Line(83,43,180,43);
		hang = new Line(180,43,180,65);
		
		head = new Circle(180,83,17);
		torso = new Line(180,100,180,144);
		
		rarm1 = new Line(180,110,160,120);
		rarm2 = new Line(160,120,150,130);
		
		larm1 = new Line(180,110,200,120);
		larm2 = new Line(200,120,210,130);
		
		rleg1 = new Line(180,144,160,160);
		rleg2 = new Line(160,160,150,180);
		
		lleg1 = new Line(180,144,200,160);
		lleg2 = new Line(200,160,210,180);
		
		Tooltip.install(head, new Tooltip("Head"));
		Tooltip.install(torso, new Tooltip("Torso"));
		Tooltip.install(rarm1, new Tooltip("RightArm(1)"));
		Tooltip.install(rarm2, new Tooltip("RightArm(2)"));
		Tooltip.install(larm1, new Tooltip("LeftArm(1)"));
		Tooltip.install(larm2, new Tooltip("LeftArm(2)"));
		Tooltip.install(rleg1, new Tooltip("RightLeg(1)"));
		Tooltip.install(rleg2, new Tooltip("RightLeg(2)"));
		Tooltip.install(lleg1, new Tooltip("LeftLeg1"));
		Tooltip.install(lleg2, new Tooltip("LeftLeg2"));
		
		
		this.setWidth(300);
		this.setHeight(300);
		this.getChildren().addAll(base,pole,ext,hang,head,torso,rarm1,rarm2,larm1,larm2,rleg1,rleg2,lleg1,lleg2);
		
		head.setVisible(false);
		torso.setVisible(false);
		
		rarm1.setVisible(false);
		rarm2.setVisible(false);
		larm1.setVisible(false);
		larm2.setVisible(false);
		rleg1.setVisible(false);
		rleg2.setVisible(false);
		lleg1.setVisible(false);
		lleg2.setVisible(false);
	}
	
	public void advanceGame() {
		switch (count) {
			case 0 : head.setVisible(true); break;
			case 1 : torso.setVisible(true); break;
			case 2 : rarm1.setVisible(true); break;
			case 3 : rarm2.setVisible(true); break;
			case 4 : larm1.setVisible(true); break;
			case 5 : larm2.setVisible(true); break;
			case 6 : rleg1.setVisible(true); break;
			case 7 : rleg2.setVisible(true); break;
			case 8 : lleg1.setVisible(true); break;
			case 9 : lleg2.setVisible(true); break;
			default: System.out.println("Hangman already at full display!");
		}
		count++;
	}
	
	/**
	 * Updates visibility of each part of the hangman based on the count, however
	 * due to the positive-bound nature of count, is overkill for updating (see HangmanView.advanceGame())
	 * Can still be used in order to update the view regardless on count stepping order
	 */
	@Deprecated
	public void update() {
		if (count >0) {
			head.setVisible(true);
		}else {
			head.setVisible(false);
			torso.setVisible(false);
			rarm1.setVisible(false);
			rarm2.setVisible(false);
			larm1.setVisible(false);
			larm2.setVisible(false);
			rleg1.setVisible(false);
			rleg2.setVisible(false);
			lleg1.setVisible(false);
			lleg2.setVisible(false);
			return;
		}
		if (count >1) {
			torso.setVisible(true);
		}else {
			torso.setVisible(false);
			rarm1.setVisible(false);
			rarm2.setVisible(false);
			larm1.setVisible(false);
			larm2.setVisible(false);
			rleg1.setVisible(false);
			rleg2.setVisible(false);
			lleg1.setVisible(false);
			lleg2.setVisible(false);
			return;
		}
		if (count >2) {
			rarm1.setVisible(true);
		}else {
			rarm1.setVisible(false);
			rarm2.setVisible(false);
			larm1.setVisible(false);
			larm2.setVisible(false);
			rleg1.setVisible(false);
			rleg2.setVisible(false);
			lleg1.setVisible(false);
			lleg2.setVisible(false);
			return;
		}
		if (count >3) {
			rarm2.setVisible(true);
		}else {
			rarm2.setVisible(false);
			larm1.setVisible(false);
			larm2.setVisible(false);
			rleg1.setVisible(false);
			rleg2.setVisible(false);
			lleg1.setVisible(false);
			lleg2.setVisible(false);
			return;
		}
		if (count >4) {
			larm1.setVisible(true);
		}else {
			larm1.setVisible(false);
			larm2.setVisible(false);
			rleg1.setVisible(false);
			rleg2.setVisible(false);
			lleg1.setVisible(false);
			lleg2.setVisible(false);
			return;
		}
		if (count >5) {
			larm2.setVisible(true);
		}else {
			larm2.setVisible(false);
			rleg1.setVisible(false);
			rleg2.setVisible(false);
			lleg1.setVisible(false);
			lleg2.setVisible(false);
			return;
		}
		if (count >6) {
			rleg1.setVisible(true);
		}else {
			rleg1.setVisible(false);
			rleg2.setVisible(false);
			lleg1.setVisible(false);
			lleg2.setVisible(false);
			return;
		}
		if (count >7) {
			rleg2.setVisible(true);
		}else {
			rleg2.setVisible(false);
			lleg1.setVisible(false);
			lleg2.setVisible(false);
			return;
		}
		if (count >8) {
			lleg1.setVisible(true);
		}else {
			lleg1.setVisible(false);
			lleg2.setVisible(false);
			return;
		}
		if (count >9) {
			lleg2.setVisible(true);
		}else {
			lleg2.setVisible(false);
			return;
		}
	}
	
	public void reset() {
		head.setVisible(false);
		torso.setVisible(false);
		rarm1.setVisible(false);
		rarm2.setVisible(false);
		larm1.setVisible(false);
		larm2.setVisible(false);
		rleg1.setVisible(false);
		rleg2.setVisible(false);
		lleg1.setVisible(false);
		lleg2.setVisible(false);
		
		count = 0;
	}
}
