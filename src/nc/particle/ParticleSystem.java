package nc.particle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nc.Mover;
import processing.core.PApplet;
import processing.core.PVector;

public class ParticleSystem extends Mover	 {
	final List<Particle> particules;
	ParticuleBuilder builder;
	
	public ParticleSystem(PApplet p, PVector location, ParticuleBuilder builder) {
		super(p, 20, location);
		this.particules = new ArrayList<Particle>();
		this.builder = builder;
	}
	
	public void run(boolean addmore){
		if(addmore){
			addParticle();
		}
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
	
	public void run(){
		run(true);
	}

	private void addParticle() {
		particules.add(builder.build(p,location));
	}
}
