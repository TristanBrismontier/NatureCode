package nc.box2d;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.common.Vec2;

import nc.box2d.shiffman.box2d.Box2DProcessing;
import processing.core.PApplet;
import processing.core.PVector;

public class MainBoxe2D extends PApplet {

	final List<Box> boxes = new ArrayList<Box>();
	Box2DProcessing box2D;
	
	public void setup() {
		size(800,800);
		box2D = new Box2DProcessing(this);
		box2D.createWorld(new Vec2(0.0f, 0.0f));
	}

	public void draw() {
		background(255);
		box2D.step();
		boxes.forEach(box -> box.display());
		if(mousePressed){
			boxes.add(new Box(this, box2D, new PVector(mouseX, mouseY)));
		}
	}
}
