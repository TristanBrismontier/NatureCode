package nc.particle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;

public class ParticleApplet extends PApplet {

	ParticleSystem system;
	final int side = 800;

	public void setup() {
		size(side, side);
		system = new ParticleSystem(this);
	}

	public void draw() {
		background(255);
		system.run();
	}
}
