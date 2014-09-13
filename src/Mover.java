import processing.core.PApplet;
import processing.core.PVector;

public class Mover {

	PApplet p;
	PVector location;
	PVector velocity;
	PVector acceleration;
	float mass;
	final float G;

	public Mover(PApplet p, float m) {
		this.p = p;
		location = new PVector(p.random(p.width), p.random(p.height));
		velocity = new PVector(0, 0);
		acceleration = new PVector(0, 0);
		this.mass = m;
		G = (float) 0.4;
	}

	void update() {
		velocity.add(acceleration);
		location.add(velocity);
		acceleration.mult(0);
	}

	void display() {
		p.stroke(0);

		p.fill(0, 0, 0, 125);
		p.ellipse(location.x, location.y, mass * 5, mass * 5);
	}

	void applyForce(PVector force) {
		PVector f = PVector.div(force, mass);
		acceleration.add(f);
	}

	PVector attract(Mover m) {
		PVector force = PVector.sub(location, m.location);
		float distance = force.mag();
		distance = p.constrain(distance, (float) 5, (float) 25);

		force.normalize();
		float strenght = (G * mass * m.mass) / (distance * distance);
		force.mult(strenght);
		return force;
	}

	void checkEdge() {

		if (location.x >= p.width -  mass * 5/2) {
			location.x = p.width -  mass * 5/2;
			velocity.x *= -1;
			velocity.mult((float)0.4);
		} else if (location.x <  mass * 5/2) {
			location.x =  mass * 5/2;
			velocity.x *= -1;
			velocity.mult((float)0.4);
		}

		if (location.y >= p.height -  mass * 5/2) {
			location.y = p.height -  mass * 5/2;
			velocity.y *= -1;
			velocity.mult((float)0.4);
		} else if (location.y <=  mass * 5/2) {
			location.y =  mass * 5/2;
			velocity.y *= -1;
			velocity.mult((float)0.4);
		}
	}
}
