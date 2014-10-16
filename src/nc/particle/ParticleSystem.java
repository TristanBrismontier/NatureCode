package nc.particle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nc.Mover;
import processing.core.PApplet;
import processing.core.PVector;

public class ParticleSystem<T extends Particle> extends Mover	 {
	final List<T> particules;
	ParticleFactoryI factory;
	
	public ParticleSystem(PApplet p, PVector location, ParticleFactoryI factory) {
		super(p, 20, location);
		this.particules = new ArrayList<T>();
		this.factory = factory;
	}
	
	public void run(boolean addmore){
		if(addmore){
			addParticle();
		}
		  Iterator<T> it = particules.iterator();
		  while (it.hasNext()) {
			  Particle particle = it.next();
			  particle.applyForce(attract(particle));
			  particle.run();
		      if (particle.isDead()) {
		         it.remove();
		      }
		  }
	}
	
	
	
	public void run(){
		run(true);
	}

	private void addParticle() {
		particules.add((T) factory.buildParticle(p,location));
	}
	
}
