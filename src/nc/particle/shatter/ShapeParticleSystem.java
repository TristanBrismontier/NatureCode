package nc.particle.shatter;

import java.util.Iterator;

import nc.particle.Particle;
import nc.particle.ParticleSystem;
import nc.particle.ParticuleBuilder;
import processing.core.PApplet;
import processing.core.PVector;
import nc.particle.shatter.SquareParticle;

public class ShapeParticleSystem extends ParticleSystem {

	public ShapeParticleSystem(PApplet p, PVector location,	ParticuleBuilder builder, int side) {
		super(p, location, builder, false);
		final float sizeParticle = SquareParticle.s;
		PVector locationComp = location.get();
		locationComp.mult(0.5f);
		for (int i = 0; i < side; i++) {
			for (int j = 0; j < side; j++) {
				PVector particleLocation = new PVector(i*sizeParticle, j*sizeParticle);
				particleLocation.add(locationComp);
				addParticle(particleLocation);
			}
		}
	}

	@Override
	public void run(){
		 Iterator<Particle> it = particules.iterator();
		  while (it.hasNext()) {
			  Particle particle = it.next();
			  particle.run();
		      if (particle.isDead()) {
		         it.remove();
		      }
		  }
	}
	
	public void shatter(PVector location){
		for (Particle particle : particules) {
			PVector velocityShatter = location.get();
			velocityShatter.sub(particle.location);
			velocityShatter.normalize();
			velocityShatter.mult(-1);
			velocityShatter.x = velocityShatter.x *p.random(1.5f);
			velocityShatter.y = velocityShatter.y *p.random(1.5f);
			SquareParticle part = (SquareParticle) particle;
			part.setShatter(velocityShatter);
		}
	}
	
	private void addParticle(PVector particleLocation) {
		particules.add(builder.build(p,particleLocation));
	}

}
