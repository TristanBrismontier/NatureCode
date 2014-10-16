package nc.particle;

import processing.core.*;

public class Particle {
	
	final PApplet p;
	PVector location;
	PVector velocity;
	PVector acceleration;
	float angle;
	float aVelo;
	float mass;
	float lifespan;
	
	public Particle(PApplet p,PVector location) {
		this.location = location.get();
		this.p = p;
		this.angle = 0;
		this.aVelo = 0;
		this.mass = 5; 
		this.lifespan=255;
		this.acceleration = new PVector(0f, 0.05f);
		this.velocity = new PVector(p.random(-2,2),p.random(-2,2));
	}
		
	private void update() {
		aVelo = p.constrain((velocity.x) / 10, -1f, 1f);
		angle += aVelo;
		velocity.add(acceleration);
		location.add(velocity);
		acceleration.mult(0);
		lifespan -=2;
	}
	
	private void display(){
		p.stroke(0,lifespan);
		p.fill(0,lifespan);
		
		p.pushMatrix();
		p.translate(location.x, location.y);
	
		float size = mass*5;
		p.ellipse(0, 0, size, size);
		p.rotate(angle);
		p.arc(0, 0,   size,  size, 0 ,p.HALF_PI);	
		p.arc(0, 0,   size,  size,p.PI ,p.PI+p.HALF_PI);
		p.popMatrix();
	}
	
	public boolean isDead(){
		return lifespan<=0;
	}

	public void run() {
		update();
		display();
	}

	public void applyForce(PVector force) {
		PVector f = PVector.div(force, mass);
		acceleration.add(f);
	}

}
