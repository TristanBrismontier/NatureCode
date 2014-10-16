package nc.oscillation.asteroids;

import processing.core.PApplet;
import processing.core.PVector;
import nc.particle.Particle;
import nc.particle.ParticleFactoryI;

public class BurstParticle extends Particle {

	public BurstParticle(PApplet p, PVector location) {
		super(p, location);
		this.mass = 5;
		this.lifespan=100;
		this.acceleration = new PVector(0,0);
		this.velocity = new PVector(p.random(-1.5f, 1.5f), p.random(0.01f, 2f));
	}
	static public class BurstParticleFactory implements ParticleFactoryI{
		public BurstParticle buildParticle(PApplet p,PVector location){
			return new BurstParticle(p,location);
		}
	}
		
	@Override
	public void display(){
		p.stroke(0,lifespan);
		p.fill(0,lifespan);
		p.pushMatrix();
		p.translate(location.x, location.y);
		p.ellipse(0, 0, mass, mass);
		p.popMatrix();
	}
	
	
}
