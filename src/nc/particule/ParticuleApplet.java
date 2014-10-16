package nc.particule;

import processing.core.PApplet;
import processing.core.PVector;

public class ParticuleApplet extends PApplet {

	private static final PVector gravity = new PVector(0, 0.1f);
	final int side=800;
	Particule particule;
	
	public void setup(){
		size(side,side);
		particule = new Particule(this, new PVector(side/2,side/10));
	}

	public void draw(){
		background(255);
		if(particule.isDead()){
			particule = new Particule(this, new PVector(side/2,side/10));
		}
		particule.applyForce(gravity);
		particule.run();
	}
}
