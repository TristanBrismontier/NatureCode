package nc.oscillation;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class Osc extends PApplet {
	
	List<Oscillator> oscs = new ArrayList<Oscillator>();
	
	public void setup() {
		size(1500,1500);
		for (int i = 0; i <10; i++) {
			oscs.add(new Oscillator(this));
		}
	}
	
	public void draw() {
		background(255);
		for (Oscillator osc : oscs) {
			osc.display();
		}
	}

}
