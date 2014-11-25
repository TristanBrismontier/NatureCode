package nc.toxiclibs.cluster;

import processing.core.PApplet;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletParticle2D;

public class Node extends VerletParticle2D {
	final PApplet p;
	float size;

	public Node(final PApplet p,Vec2D arg0) {
		super(arg0);
		this.p = p;
		this.size = 20; 
	}
	
	public void display(){
		p.fill(175);
		p.stroke(255);
		p.ellipse(x, y, size,size);
	}

	public void setSize(float size) {
		this.size = size;
	}
	
}
