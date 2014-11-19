package nc.box2d;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import nc.box2d.shiffman.box2d.Box2DProcessing;
import nc.particle.Particle;
import processing.core.PApplet;
import processing.core.PVector;

public class MainBoxe2D extends PApplet {

	final List<LinkParticule> boxes = new ArrayList<LinkParticule>();
	
	Box2DProcessing box2D;
	Surface surface;
	Cell cell;
	
	
	public void setup() {
		size(800,800);
		box2D = new Box2DProcessing(this);
		box2D.createWorld();
		surface = new Surface(this, box2D);
		cell = new Cell(this, box2D, new PVector(200,200),20, BodyType.KINEMATIC, true);
		
	}

	public void draw() {
		background(0);
		Vec2 pos = cell.body.getWorldCenter();
		Vec2 target = box2D.coordPixelsToWorld(mouseX, mouseY);
		Vec2 v = target.sub(pos);
		cell.body.setLinearVelocity(v);
		box2D.step();
		surface.display();
		cell.display();
		boxes.forEach(box -> box.display());
//		if(mousePressed){
//				boxes.add(new LinkParticule(this, new PVector(mouseX, mouseY), box2D));
//		}
	}
	
	@Override
	public void mousePressed() {
	}
	
	@Override
	public void mouseReleased() {
	}
	@Override
	public void keyPressed() {
		cell = new Cell(this, box2D, new PVector(200,200),20, BodyType.KINEMATIC, true);
		
		 Iterator<LinkParticule> it = boxes.iterator();
		  while (it.hasNext()) {
			  LinkParticule body = it.next();
			  body.destroy();
			  it.remove();
		  }
	}
}
