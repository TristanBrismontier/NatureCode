import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;

public class Vector extends PApplet{

	List<BoostMove> bm = new ArrayList<BoostMove>();
	PVector wind = new PVector((float)0.05, 0);
	float c = (float) 0.01;
	
	
	public void setup() {
		size(1000,300);
	    background(255);
	    smooth();
	    for (int i = 0; i < 1; i++) {
	    	bm.add(new BoostMove(this,50));//random(5,50)));
		}
	}
	
	
	public void draw() {
		background(255);
		for (BoostMove bmE : bm) {
			bmE.applyForce(wind);
			
			PVector gravity = new PVector(0,(float)0.1*bmE.mass);
			bmE.applyForce(gravity);
			
			PVector friction = bmE.velocity.get();
			friction.mult(-1);
			friction.normalize();
			friction.mult(c);
			bmE.applyForce(friction);
			
			bmE.update();
			bmE.checkEdge();
			bmE.display();
		}
	}
}
