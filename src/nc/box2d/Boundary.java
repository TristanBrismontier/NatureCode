package nc.box2d;

import org.jbox2d.dynamics.BodyType;

import nc.box2d.shiffman.box2d.Box2DProcessing;
import processing.core.PApplet;
import processing.core.PVector;

public class Boundary extends Body2D {
	
	private PVector location;

	public Boundary(PApplet p, Box2DProcessing box2d, PVector location, float whith, float height) {
		super(p, box2d, location, whith, height, BodyType.STATIC);
		this.location = location;
	}

	@Override
	public void display() {
		p.fill(0);
		p.stroke(0);
		p.rectMode(p.CENTER);
		p.rect(location.x,location.y,whith,height);
	}
}
