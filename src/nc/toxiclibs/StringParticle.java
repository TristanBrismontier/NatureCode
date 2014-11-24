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
	
	public StringParticle(PApplet p, VerletPhysics2D physics,Particle startParticle,Particle endParticle) {
		this.p = p;
		stringElments = new ArrayList<Particle>();
		
		final float xAli = p.width/2;
		float y = startParticle.y;
		
		Particle previous = new Particle(p,new Vec2D(xAli, y));
		physics.addParticle(previous);
		stringElments.add(previous);
		previous.lock();
		for (int i = 0; i < 20; i++) {
			y +=5;
			Particle nextParticle = new Particle(p,new Vec2D(xAli, y));
			final VerletSpring2D spring = new VerletSpring2D(previous, nextParticle, 5, 1f);
			physics.addParticle(nextParticle);
			stringElments.add(nextParticle);
			physics.addSpring(spring);
			previous = nextParticle;			
		}
		final VerletSpring2D spring = new VerletSpring2D(previous, endParticle, 5, 1f);
		physics.addSpring(spring);
	}

	public void display(){
		p.stroke(255);
		p.noFill();
		p.beginShape();
		stringElments.forEach(e -> p.vertex(e.x,e.y));
		p.endShape();
	}
}
