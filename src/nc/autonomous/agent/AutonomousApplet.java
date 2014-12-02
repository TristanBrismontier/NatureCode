package nc.autonomous.agent;

import processing.core.PApplet;
import processing.core.PVector;

public class AutonomousApplet extends PApplet {
	
	Vehicule finn;
	
	
	public void setup() {
		size(800, 800);
		finn = new Vehicule(this);
	}
	
	public void draw() {
		background(0);
		PVector target = new PVector(mouseX, mouseY);
		
		finn.arrive(target);
		finn.update();
		finn.display();
		
		displayTarget(target);
	}

	private void displayTarget(PVector target) {
		fill(175,50);
		ellipse(target.x, target.y, 20, 20);
	}
	
}
