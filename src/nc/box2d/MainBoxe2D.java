package nc.box2d;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;

public class MainBoxe2D extends PApplet {

	final List<Box> boxes = new ArrayList<Box>();
	
	public void setup() {
		size(800, 800);
	}

	public void draw() {
		background(255);
		boxes.forEach(box -> box.display());
		System.out.println(boxes.size());
		if(mousePressed){
			boxes.add(new Box(this, new PVector(mouseX, mouseY)));
		}
	}
}
