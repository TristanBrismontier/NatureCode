package nc.particule;

import processing.core.*;

public class Particule {
	
	final PApplet p;
	PVector location;
	PVector velocite;
	PVector acceleration;
	float wieght;
	
	public Particule(PApplet p,PVector location) {
		this.location = location;
		this.p = p;
		this.wieght = 10; 
	}
		
	public void update() {
		location.add(velocite);
		velocite.add(acceleration);
	}
	
	public void display(){
		p.stroke(0);
		p.fill(175);
		p.ellipse(location.x, location.y,wieght,wieght);
	}

}
