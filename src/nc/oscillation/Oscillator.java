package nc.oscillation;

import javafx.scene.transform.Translate;
import processing.core.PApplet;
import processing.core.PVector;

public class Oscillator {

	PApplet p;
	
	PVector angle;
	PVector velocity;
	PVector amp;
	
	public Oscillator(final PApplet p) {
		this.p = p;
		angle = new PVector();
		velocity = new PVector(p.random(-0.05f,0.05f),p.random(-0.05f,0.05f));
		amp = new PVector(p.random(p.width/2),p.random(p.height/2));
	}
	
	public void oscillate(){
		angle.add(velocity);
	}
	
	public void display() {
		oscillate();
		float x = p.sin(angle.x)*amp.x; 
		float y = p.sin(angle.y)*amp.y; 
		
		System.out.println(x +" "+ angle.x  +" "+ amp.x);

		p.pushMatrix();
		p.translate(p.width/2, p.height/2);
		p.stroke(0);
		p.fill(175);
		p.line(0, 0, x, y);
		p.ellipse(x, y, 20, 20);
		p.popMatrix();
	}

	
}
