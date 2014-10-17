package nc.particle;

import nc.Mover;
import processing.core.PApplet;
import processing.core.PVector;
public class ParticleApplet extends PApplet {

	ParticleSystem system;
	final static float G =  0.4f;
	final int side = 800;

	public void setup() {
		size(side, side);
		system = new ParticleSystem(this, new PVector(width / 2, height/ 10),(p,location) ->new Particle(p, location));
	}

	public void draw() {
		background(255);
		system.update();
		system.applyForce(mouseAttract(system));
		system.checkEdge();
		system.run();
	}

	private PVector mouseAttract(Mover m) {
		PVector force = PVector.sub(new PVector(mouseX, mouseY), m.location);
		float distance = force.mag();
		distance = constrain(distance, 5f, 25f);

		force.normalize();
		float strenght = (G * 500 * m.mass) / (distance * distance);
		force.mult(strenght);
		return force;
	}
}
