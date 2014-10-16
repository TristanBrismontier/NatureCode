package nc.particle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nc.Mover;
import processing.core.PApplet;
import processing.core.PVector;

public class ParticleSystem extends Mover	 {
	final List<Particle> particules;
	
	public ParticleSystem(PApplet p, PVector location) {
		super(p, 20, location);
		this.particules = new ArrayList<Particle>();
	}
	
	public void run(){
		addParticle();
		  Iterator<Particle> it = particules.iterator();
		  while (it.hasNext()) {
			  Particle particle = it.next();
			  particle.applyForce(attract(particle));
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
