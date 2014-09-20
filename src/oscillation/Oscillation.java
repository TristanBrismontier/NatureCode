package oscillation;

import processing.core.PApplet;
import processing.core.PVector;

public class Oscillation extends PApplet {
	
	final float size= 300;
	final float stickLenght = 50;
	int angle = 0;
	
	public void setup(){
		size((int)size, (int)size);
		background(255);
	}
	
	public void draw() {
		background(255);
		translate(size/2, size/2);
		rotate(radians(angle+=5));
		
		final PVector firstLocation = new PVector(-stickLenght/2, 0);
		final PVector secondLocation = new PVector(stickLenght/2, 0);

		line(firstLocation, secondLocation);
		ellipse(firstLocation,10);
		ellipse(secondLocation,10);
		
	}
	
	private void ellipse(PVector v1, int i) {
		strokeWeight(2);
		fill(125);
		ellipse(v1.x, v1.y, 10, 10);
	}

	private void line(final PVector v1, final PVector v2){
		strokeWeight(2);
		line(v1.x, v1.y, v2.x, v2.y);
	}
}
