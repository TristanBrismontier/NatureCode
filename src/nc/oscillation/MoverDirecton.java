package nc.oscillation;

import java.util.ArrayList;
import java.util.List;

import nc.Mover;
import processing.core.PApplet;

public class MoverDirecton extends PApplet {
	
	List<Mover> bmE = new ArrayList<Mover>();
	
	public void setup(){
		size(500,500);
		for (int i = 0; i < 10; i++) {
			bmE.add(new Mover(this, random(2, 10)));
		}
	}
	
	public void draw(){
		background(255);
		for (Mover bm : bmE) {
			for (Mover bmA : bmE) {
				bm.interac(bmA);
			}
			if(bm.mass <25){
				bm.update();
				bm.checkEdge();
			}
			bm.display();
		}
	}

}
