package nc.autonomous.agent;

import nc.Mover;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Vehicule {

	protected PApplet p;
	public PVector location;
	protected PVector velocity;
	protected PVector acceleration;
	float maxSpeed;
	float maxForce;
	float r;
	
	PImage img;

	public Vehicule(PApplet p) {
		this.p = p;
		location = new PVector(p.random(p.width), p.random(p.height));
		velocity = new PVector(0, 0);
		acceleration = new PVector(0, 0);
		maxSpeed = 4;
	    maxForce = 0.1f;
	    r = 3.0f;
		img = p.loadImage( "finn.png");
	}
	
	void update() {
		velocity.add(acceleration);
		velocity.limit(maxSpeed);
		location.add(velocity);
		acceleration.mult(0);
	}
	 
	public void display(){
		p.pushMatrix();
		p.translate(location.x, location.y);
		p.rotate(velocity.heading());
		p.image(img, 0, 0);		
		p.popMatrix();
	}

	public void applyForce(PVector force) {
		acceleration.add(force);
	}

}
