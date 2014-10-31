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

	final List<Body2D> boxes = new ArrayList<Body2D>();
	final List<Body2D> boundary = new ArrayList<Body2D>();
	Box2DProcessing box2D;
	
	public void setup() {
		size(800,800);
		box2D = new Box2DProcessing(this);
		box2D.createWorld();
		boundary.add(new Boundary(this, box2D, new PVector(200, 700), 300, 30));
		boundary.add(new Boundary(this, box2D, new PVector(400, 500), 300, 30));
		
	}

	public void draw() {
		background(255);
		box2D.step();
		boxes.forEach(box -> box.display());
		boundary.forEach(b -> b.display());
		if(mousePressed){
			if(random(10)>5){
				boxes.add(new Body2D(this, box2D, new PVector(mouseX, mouseY)));
			}else{
				boxes.add(new Cell(this, box2D, new PVector(mouseX, mouseY)));
			}
		}
	}
	
	@Override
	public void keyPressed() {
		 Iterator<Body2D> it = boxes.iterator();
		  while (it.hasNext()) {
			  Body2D body = it.next();
			  body.destroy();
			  it.remove();
		  }
	}
}
