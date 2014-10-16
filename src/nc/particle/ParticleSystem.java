package nc.particle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;

public class ParticleSystem {

	final PApplet p;
	final List<Particle> particules;
	PVector location;
	PVector velocity;
	PVector acceleration;
	

	public ParticleSystem(PApplet p) {
		this.p = p;
		this.particules = new ArrayList<Particle>();
	}
	
	public void run(){
		addParticle();
		  Iterator<Particle> it = particules.iterator();
		  while (it.hasNext()) {
			  Particle particle = it.next();
			  particle.run();
		      if (particle.isDead()) {
		         it.remove();
		      }
		  }
	}

	private void addParticle() {
		particules.add(new Particle(p,location));
	}


}
