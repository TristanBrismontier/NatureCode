import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import processing.core.*;



public class Gravity extends PApplet {

	List<Mover> bmE = new ArrayList<Mover>();

	public void setup() {
		size(1000, 1000,P3D);
		background(255);
		for (int i = 0; i < 25; i++) {
			bmE.add(new Mover(this, random(1, 8)));
		}
		bmE.add(new Mover(this, 25));
		
	}

	public void draw() {
		background(255);
//		lights();
		spotLight(255, 0, 0, width/2, height/8, 400, 0, 0, -1, PI/2, 2);

		translate(150, 0, -height*(float)1.75);
		
		rotateY((float)mouseY/100);
		rotateX((float)mouseX/100);
	

		
		pushMatrix();
		translate(height/2, height/2, height/2);
		stroke(0);
		noFill();
		box(height);
		
		popMatrix();
		
		
		
		
		
		for (Mover bm : bmE) {
			for (Mover bmA : bmE) {
				bm.applyForce(bmA.attract(bm));
			}
			bm.update();
			bm.checkEdge();
			bm.display();
		}
		translate(150, 0, -height*(float)1.75);
	}
}