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
	protected PVector wanderingPos;
	float maxSpeed;
	float maxForce;
	float r;

	float wandering;
	float wAngle;
	float noise;
	
	
	
	PImage img;

	public Vehicule(PApplet p) {
		this.p = p;
		wandering = 50;
		location = new PVector(p.random(p.width), p.random(p.height));
		velocity = new PVector(0, 0);
		wanderingPos = PVector.add(location, new PVector(p.cos(velocity.heading())*wandering,p.sin(velocity.heading())*wandering));
		acceleration = new PVector(0, 0);
		maxSpeed = 4;
		maxForce = 0.1f;
		r = 20;
		img = p.loadImage("finn.png");
		noise = 100;
	}

	void update() {
		velocity.add(acceleration);
		velocity.limit(maxSpeed);
		location.add(velocity);
		acceleration.mult(0);
		wanderingPos = PVector.add(location, new PVector(p.cos(velocity.heading())*wandering,p.sin(velocity.heading())*wandering));
		checkLimit();
	}

	 private void checkLimit() {
		if(location.x >p.width){
			location.x = 0;
		}
		if(location.y >p.width){
			location.y = 0;
		}
	}

	void arrive() {
		 wAngle += p.map(p.random(-10,10), -10, 10, -0.1f, 0.1f);
		 	PVector target = PVector.add(wanderingPos, new PVector(p.cos(wAngle)*r, p.sin(wAngle)*r));
		    PVector desired = PVector.sub(target,location);

		    float d = desired.mag();
		    desired.normalize();
		    if (d < 100) {
		      float m = p.map(d,0,100,0,maxSpeed);
		      desired.mult(m);
		    } else {
		      desired.mult(maxSpeed);
		    }

		    PVector steer = PVector.sub(desired,velocity);
		    steer.limit(maxForce);
		    applyForce(steer);
		  }

	public void display() {
		p.pushMatrix();
		p.translate(location.x, location.y);
		p.rotate(velocity.heading()+p.PI/2);
		p.image(img, -img.width/2, -img.height/2);
		p.popMatrix();
		p.stroke(255);
		p.line(location.x, location.y, wanderingPos.x, wanderingPos.y);
		p.noFill();
		p.ellipse(wanderingPos.x, wanderingPos.y, r*2, r*2);
	 	PVector target = PVector.add(wanderingPos, new PVector(p.cos(wAngle)*r, p.sin(wAngle)*r));
		p.line(target.x, target.y, wanderingPos.x, wanderingPos.y);
		p.ellipse(target.x, target.y, 2, 2);
	}

	public void applyForce(PVector force) {
		acceleration.add(force);
	}

}
