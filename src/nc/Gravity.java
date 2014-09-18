package nc;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import processing.core.*;



public class Gravity extends PApplet {

	List<Mover> bmE = new ArrayList<Mover>();
	float rX;
	float rY;
	float side;
	
	public void setup() {
		side = 800;
		size(800, 800,OPENGL);
		background(255);
		for (int i = 0; i < 25; i++) {
			bmE.add(new Mover(this, random(1, 8),true));
		}
		bmE.add(new Mover(this, 50,true,new PVector(side/2,side/2,side/2)));
		
	}

	public void draw() {
		background(255);
		spotLight(255, 0, 0, width/2, height/8, 400, 0, 0, -1, PI/2, 2);
		translate(150, 0, -side*(float)1.5);
		if(mousePressed){
			rX=mouseX;
			rY=mouseY;
		}
		rotateY((float)rX/100);
		rotateX((float)rY/100);
		
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
			if(bm.mass <25){
				bm.update();
//				bm.checkEdge();
			}
			bm.display();
		}
		translate(150, 0, -side*(float)1.75);
	}
}
