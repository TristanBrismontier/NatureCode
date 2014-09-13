import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;

public class Vector extends PApplet{

	List<BoostMove> bm = new ArrayList<BoostMove>();
	PVector wind = new PVector((float)0.05, 0);
	PVector gravity = new PVector(0, (float)0.5);
	
	public void setup() {
		size(1000,300);
	    background(255);
	    smooth();
	    for (int i = 0; i < 20; i++) {
	    	bm.add(new BoostMove(this,random(5,20)));
		}
	}
	
	
	public void draw() {
		background(255);
		for (BoostMove bmE : bm) {
			bmE.applyForce(wind);
			bmE.applyForce(gravity);
			bmE.update();
			bmE.checkEdge();
			bmE.display();
		}
	}
}
