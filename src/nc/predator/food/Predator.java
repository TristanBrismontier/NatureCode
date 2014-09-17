package nc.predator.food;

import processing.core.PApplet;
import processing.core.PVector;
import nc.Mover;

public class Predator extends Mover {

	public Predator(PApplet p, float m, boolean d3) {
		super(p, m, d3);
	}

	public void interac(Mover other) {
		//interact devrais définir un vector vitess plustôt qu'une accélération
		// ou bien définir une accélération non limité par le mass, avec une limite de vitess.
		if (other instanceof Food) {
			PVector force = other.attract(this);
			PVector f = PVector.div(force, mass);
			acceleration.add(f);
		}
	}
	
	public PVector attract(Mover m) {
		PVector force = (m instanceof Food)?PVector.sub(m.location, location):PVector.sub(location, m.location);
		float distance = force.mag();
		distance = p.constrain(distance, (float) 5, (float) 25);

		force.normalize();
		float strenght = (G * mass * m.mass) / (distance * distance);
		force.mult(strenght);
		return force;
	}

}
