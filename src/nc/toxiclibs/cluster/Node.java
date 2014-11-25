package nc.toxiclibs.cluster;

import processing.core.PApplet;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletParticle2D;

public class Node extends VerletParticle2D {
	final PApplet p;
	float size;
	boolean partOfShape;

	public Node(final PApplet p,Vec2D arg0) {
		super(arg0);
		this.p = p;
		this.size = 20;
		this.partOfShape=true;
	}
	
	public void display(){
		p.fill(175);
		p.stroke(255);
		p.ellipse(x, y, size,size);
	}

	public void setSize(float size) {
		this.size = size;
	}

	public boolean isPartOfShape() {
		return partOfShape;
	}

	public void setPartOfShape(boolean partOfShape) {
		this.partOfShape = partOfShape;
	}
	
}
