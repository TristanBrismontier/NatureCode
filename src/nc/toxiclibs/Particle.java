package nc.toxiclibs;

import processing.core.PApplet;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletParticle2D;

public class Particle extends VerletParticle2D {
	
	PApplet p;

	public Particle(PApplet p,Vec2D arg0) {
		super(arg0);
		this.p = p;
		
	}
	
	public void display(){
		p.fill(175);
		p.stroke(255);
		p.ellipse(x, y, 20,20);
	}

}
