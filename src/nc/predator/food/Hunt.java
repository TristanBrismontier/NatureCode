package nc.predator.food;

import java.util.ArrayList;
import java.util.List;

import nc.Mover;
import processing.core.PApplet;

public class Hunt extends PApplet {
	
	List<Mover> movers = new ArrayList<Mover>();

	public void setup() {
		size(800, 800);
		background(255);
		
		movers.add(new Predator(this, random(1, 8),false));
		movers.add(new Food(this, random(1, 8),false));
		movers.add(new Predator(this, random(1, 8),false));
		movers.add(new Food(this, random(1, 8),false));
		movers.add(new Predator(this, random(1, 8),false));
		movers.add(new Food(this, random(1, 8),false));
		movers.add(new Predator(this, random(1, 8),false));
		movers.add(new Food(this, random(1, 8),false));
		movers.add(new Predator(this, random(1, 8),false));
		movers.add(new Food(this, random(1, 8),false));
		movers.add(new Predator(this, random(1, 8),false));
		movers.add(new Food(this, random(1, 8),false));
	
		
		
	}

	public void draw() {
		background(255);
		for (Mover bm : movers) {
			for (Mover bmA : movers) {
				bm.interac(bmA);;
			}
		
			bm.update();
			bm.checkEdge();
			bm.display();
		}

	}

}
