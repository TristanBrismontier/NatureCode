package nc.oscillation.hook.law;

import processing.core.PApplet;

public class Hook extends PApplet {

	float angle = 0;
	float aVelocity = 0.05f;
	
	public void setup(){
		size(500,500);
		
	}
	
	public void draw(){
		background(255);
		
		float periode = 120;
		float amp = 100;
		float y = amp * cos(angle);
		
		angle +=aVelocity;
		
		stroke(0);
		fill(175);
		
		translate(width/2, height/2);
		line(0, -(amp+50), 0, y);
		ellipse(0, y, 20, 20);
	}
	
	
}
