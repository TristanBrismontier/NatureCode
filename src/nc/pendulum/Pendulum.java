package nc.pendulum;

import processing.core.PApplet;
import processing.core.PVector;

public class Pendulum {

	PApplet p;
	PVector location;
	PVector origin;
	float r;
	float angle;
	float aVelocity;
	float aAcceleration;
	float damping;

	public Pendulum(PApplet p, PVector pVector, float r) {
		this.p = p;
		this.origin = pVector.get();
	    this.location = new PVector();
	    this.r = r;
	    angle = p.PI/4;
	 
	    aVelocity = 0.0f;
	    aAcceleration = 0.0f;
	    damping = 0.995f;
	}

	public void go() {
		 update();
		 display();

	}

	private void display() {
		location.set(r*p.sin(angle),r*p.cos(angle),0);
		location.add(origin);
		p.stroke(0);
		
		p.line(origin.x, origin.y, location.x, location.y);
		p.fill(175);
		p.ellipse(location.x, location.y, 20, 20);

		
	}

	private void update() {
		final float gravity = 0.4f;
		aAcceleration = (-1f * gravity/r)*p.sin(angle);
		
		aVelocity +=aAcceleration;
		angle += aVelocity;
		
		aVelocity *=damping;
	}

}
