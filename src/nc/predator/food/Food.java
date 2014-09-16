package nc.predator.food;

import processing.core.PApplet;
import processing.core.PVector;
import nc.Mover;
import nc.NoiseP;

public class Food extends Mover {

	float tx=0;
	float ty=10000;
	
	public Food(PApplet p, float m, boolean d3) {
		super(p, m, d3);

	}

	public void display2D(){
		p.stroke(0);
		p.fill(5, 0, 200, 125);
		
		p.ellipse(location.x, location.y, mass*5, mass*5);
	}
	
	public void interac(Mover other) {
		if (other instanceof Predator) {
			PVector force = other.attract(this);
			PVector f = PVector.div(force, mass);
			acceleration.add(f);
		}
			
	}
	
	@Override
	public void update() {
		tx ++;
		ty ++;
		PVector randomPVector = PVector.random2D();
		randomPVector.mult(p.random(1));
		acceleration.add(randomPVector);
		System.out.println(acceleration.x + " | " + acceleration.y);
		super.update();
	}
	
	public PVector attract(Mover m) {
		PVector force = PVector.sub(location, m.location);
		float distance = force.mag();
		distance = p.constrain(distance, (float) 5, (float) 25);

		force.normalize();
		float strenght = (G * mass * m.mass) / (distance * distance);
		force.mult(strenght);
		return force;
	}
	
}