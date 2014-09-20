package nc.predator.food;

import processing.core.PApplet;
import processing.core.PVector;
import nc.Mover;

/**
 * Le Mover Predateur rÃ©pulse la food 
 *
 * @author Tristan Brismontier
 * 
 * Based on book: Nature of Code by DANIEL SHIFFMAN 
 */
 

public class Predator extends Mover {

	public Predator(PApplet p, float m, boolean d3) {
		super(p, m);
	}

	public void interac(Mover other) {
		//interact devrais definir un vector vitess pluste qu'une accÃ©lÃ©ration
		// ou bien definir une acceleration non limite par le mass, avec une limite de vitess.
		if (other instanceof Food) {
			PVector force = other.attract(this);
			PVector f = PVector.div(force, mass);
			acceleration.add(f);
		}
	}
	
	public PVector attract(Mover m) {
		PVector force = (m instanceof Food)?PVector.sub(m.location, location):PVector.sub(location, m.location);
		float distance = force.mag();
		distance = p.constrain(distance, 5f, 25f);

		force.normalize();
		float strenght = (G * mass * m.mass) / (distance * distance);
		force.mult(strenght);
		return force;
	}

}
