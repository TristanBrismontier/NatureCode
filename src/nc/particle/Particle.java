package nc.particle;

import nc.Mover;
import processing.core.PApplet;
import processing.core.PVector;

public class Particle extends Mover {
	
	float angle;
	float aVelo;
	protected float lifespan;
	
	public Particle(PApplet p,PVector location) {
		super(p, 20, location);
		this.angle = 0;
		this.aVelo = 0;
		this.lifespan=255;
		this.acceleration = new PVector(0f, 0.05f);
		this.velocity = new PVector(p.random(-1.5f,1.5f),p.random(-1.5f,1.5f));
	}
	
	@Override
	public void update() {
		aVelo = p.constrain((velocity.x) / 10, -1f, 1f);
		angle += aVelo;
		super.update();
		lifespan -=2;
	}
	
	@Override
	public void display() {
		p.stroke(0,lifespan);
		p.fill(0,lifespan);
		
		p.pushMatrix();
		p.translate(location.x, location.y);
	
		float size = mass;
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
}
