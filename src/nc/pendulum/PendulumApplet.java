package nc.pendulum;

import processing.core.PApplet;
import processing.core.PVector;

public class PendulumApplet extends PApplet {

	Pendulum p;
	final int side = 500;
	
	public void setup(){
		size(side, side);
		 p = new Pendulum(this,new PVector(width/2,10),300);
	}
	
	public void draw(){
		background(255);
		p.go();
		
	}
}
