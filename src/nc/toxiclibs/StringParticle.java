package nc.toxiclibs;

import java.util.ArrayList;
import java.util.List;

import com.sun.beans.decoder.StringElementHandler;

import processing.core.PApplet;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletPhysics2D;
import toxi.physics2d.VerletSpring2D;

public class StringParticle {
	final PApplet p;
	final List<Particle> stringElments;
	static final float elas = 0.5f;
	
	public StringParticle(PApplet p){
		this.p = p;
		stringElments = new ArrayList<Particle>();
	}
	
	public StringParticle(PApplet p, VerletPhysics2D physics,Vec2D pos,float nbP,float apart) {
		this.p = p;
		stringElments = new ArrayList<Particle>();
		
		float x = pos.x;
		float y = pos.y;
		
		Particle previous = new Particle(p,new Vec2D(x, y));
		physics.addParticle(previous);
		stringElments.add(previous);
		for (int i = 0; i < nbP; i++) {
			x +=apart;
			Particle nextParticle = new Particle(p,new Vec2D(x, y));
			final VerletSpring2D spring = new VerletSpring2D(previous, nextParticle, apart, elas);
			physics.addParticle(nextParticle);
			stringElments.add(nextParticle);
			physics.addSpring(spring);
			previous = nextParticle;			
		}
	}
	
	public void attachStartEnd(VerletPhysics2D physics, Particle pStart, Particle pEnd){
		final VerletSpring2D springStart = new VerletSpring2D(pStart, stringElments.get(0), 0, 1);
		final VerletSpring2D springEnd = new VerletSpring2D(pEnd, stringElments.get(stringElments.size()-1), 0, 1);
		
		physics.addSpring(springStart);
		physics.addSpring(springEnd);		
	}
	
	public void addParticle(VerletPhysics2D physics, Particle par, float apart){
		if(!stringElments.isEmpty()){
			final VerletSpring2D spring = new VerletSpring2D(stringElments.get(stringElments.size()-1), par, apart, elas);
			physics.addSpring(spring);
		}
		stringElments.add(par);
	}

	public void display(){
		p.stroke(255);
		p.noFill();
		p.beginShape();
		stringElments.forEach(e -> p.vertex(e.x,e.y));
		p.endShape();
	}

	public List<Particle> getStringElments() {
		return stringElments;
	}
	
	
}
