package nc.box2d;

import processing.core.PApplet;
import processing.core.PVector;

public class Box {
	private final PApplet p;
	private PVector location;
	private float gray;
	private float size;
	
	public Box(PApplet p, PVector location) {
		super();
		this.p = p;
		this.location = location;
		this.gray = 175;
		this.size = 20;
	}
	
	public void display() {
		p.fill(gray);
		p.stroke(0);
		p.rectMode(p.CENTER);
		p.rect(location.x, location.y, size, size);
	}

}
