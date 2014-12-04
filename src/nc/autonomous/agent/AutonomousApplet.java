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
				
		finn.arrive();
		finn.update();
		finn.display();
		
		
	}
	
}
