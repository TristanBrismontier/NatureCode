package nc.particle.shatter;

import processing.core.PApplet;
import processing.core.PVector;
import nc.particle.Particle;

public class SquareParticle extends Particle{
	
	boolean shatter;
	static float s = 5;

	public SquareParticle(PApplet p, PVector location) {
		super(p, location);
		this.mass = s;
		this.lifespan=500;
		this.acceleration = new PVector(0,0);
		this.velocity =  new PVector(0,0);
		this.shatter = false; 
	}
		
	@Override
	public void display(){
		p.noStroke();
		p.fill(0,lifespan);
		p.pushMatrix();
		p.translate(location.x, location.y);
		p.rect(0, 0, mass, mass);
		p.popMatrix();
	}

	public void setShatter(PVector velocity) {
		this.shatter = true;
		this.velocity = velocity.get();
	}

	@Override
	public void run(){
		if(shatter){
			update();
		}
		display();
	}

}
