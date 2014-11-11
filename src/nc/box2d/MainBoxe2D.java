package nc.box2d;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jbox2d.common.Vec2;

import nc.box2d.shiffman.box2d.Box2DProcessing;
import nc.particle.Particle;
import processing.core.PApplet;
import processing.core.PVector;

public class MainBoxe2D extends PApplet {

	final List<LinkParticule> boxes = new ArrayList<LinkParticule>();
	Surface surface;
	Box2DProcessing box2D;
	
	public void setup() {
		size(800,800);
		box2D = new Box2DProcessing(this);
		box2D.createWorld();
		surface = new Surface(this, box2D);
	}

	public void draw() {
		background(255);
		box2D.step();
		boxes.forEach(box -> box.display());
		surface.display();
		if(mousePressed){
				boxes.add(new LinkParticule(this, new PVector(mouseX, mouseY), box2D));
		}
	}
	
	@Override
	public void keyPressed() {
		 Iterator<LinkParticule> it = boxes.iterator();
		  while (it.hasNext()) {
			  LinkParticule body = it.next();
			  body.destroy();
			  it.remove();
		  }
	}
}
