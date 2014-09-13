import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;

public class Gravity extends PApplet {

	List<Mover> bmE = new ArrayList<Mover>();

	public void setup() {
		size(1000, 600);
		background(255);
		smooth();
		for (int i = 0; i < 25; i++) {
			bmE.add(new Mover(this, random(1, 8)));
		}
		bmE.add(new Mover(this, 25));
	}

	public void draw() {
		background(255);
		for (Mover bm : bmE) {
			for (Mover bmA : bmE) {
				bm.applyForce(bmA.attract(bm));
			}
			bm.update();
			bm.checkEdge();
			bm.display();
		}
	}
}