package nc.particle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nc.Mover;
import processing.core.PApplet;
import processing.core.PVector;

public class ParticleSystem extends Mover{
	protected final List<Particle> particules;
	protected ParticuleBuilder builder;
	protected final boolean attract;
	
	public ParticleSystem(PApplet p, PVector location, ParticuleBuilder builder, boolean attract) {
		super(p, 20, location);
		this.particules = new ArrayList<Particle>();
		this.builder = builder;
		this.attract = attract;
	}
	
	public void run(boolean addmore){
		if(addmore){
			addParticle();
		}
		  Iterator<Particle> it = particules.iterator();
		  while (it.hasNext()) {
			  Particle particle = it.next();
			  if(attract){
				  particle.applyForce(attract(particle));
			  }
			  particle.run();
		      if (particle.isDead()) {
		         it.remove();
		      }
		  }
	}
	
	public void run(){
		run(true);
	}

	protected void addParticle() {
		particules.add(builder.build(p,location));
	}
}
