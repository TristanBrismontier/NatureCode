package nc.oscillation.canon;

import java.util.ArrayList;
import java.util.List;

import nc.Mover;
import processing.core.PApplet;
import processing.core.PVector;

public class Canon extends PApplet {
	private static final PVector gravity = new PVector(0, 0.1f);
	List<Bullet> bmE = new ArrayList<Bullet>();

	public void setup() {
		size(800, 800);
		smooth();
		bmE.add(new Bullet(this, 5, new PVector(0,height),new PVector(5,-5)));

	}

	public void draw() {
		System.out.println(frameCount);
		background(255);
		
		shoot();
		for (Mover bm : bmE) {
			bm.applyForce(gravity);
			bm.update();
			bm.checkEdge();
			bm.display();
		}
	}

	private void shoot() {
		if(!(frameCount%100 == 0)){
			return;
		}
		bmE.add(new Bullet(this, 5, new PVector(0,height),new PVector(random(3, 20),random(-10, -200))));
		
	}

}
