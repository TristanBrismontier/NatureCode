package nc;
import processing.core.PApplet;
import processing.core.PVector;

public class Mover {

	protected PApplet p;
	public PVector location;
	protected PVector velocity;
	protected PVector acceleration;
	public float mass;
	protected float G;
	protected float absCine;


	public Mover(PApplet p, float m) {
		this.p = p;
		location = new PVector(p.random(p.width), p.random(p.height));
		velocity = new PVector(0, 0);
		acceleration = new PVector(0, 0);
		this.mass = m;
		G =  0.4f;
		absCine = 0.4f;
	}
	
	public Mover(PApplet p, float m,PVector v){
		this(p,m);
		location = v;
	}

	public void display(){
		p.pushMatrix();
		p.translate(location.x, location.y);
		
		p.stroke(0);
		p.fill(0, 0, 0, 125);
		p.ellipse(0, 0, mass*5, mass*5);
		
		//draw Velocity Direction;

		p.rotate(velocity.heading());
		p.line(0,0,mass*3,0);		
		p.popMatrix();
	}

	public void update() {
		velocity.add(acceleration);
		location.add(velocity);
		acceleration.mult(0);
	}
	
	public void interac(Mover other){
		this.applyForce(other.attract(this));
	}
	
	public void applyForce(PVector force) {
		PVector f = PVector.div(force, mass);
		acceleration.add(f);
	}

	public PVector attract(Mover m) {
		PVector force = PVector.sub(location, m.location);
		float distance = force.mag();
		distance = p.constrain(distance, 5f, 25f);

		force.normalize();
		float strenght = (G * mass * m.mass) / (distance * distance);
		force.mult(strenght);
		return force;
	}

	public void checkEdge() {
			if (location.x > p.width -  mass * 5/2) {
				location.x = p.width -  mass * 5/2;
				velocity.x *= -1;
				velocity.mult(absCine);
			} else if (location.x <  mass * 5/2) {
				location.x =  mass * 5/2;
				velocity.x *= -1;
				velocity.mult(absCine);
			}

			if (location.y > p.height -  mass * 5/2) {
				location.y = p.height -  mass * 5/2;
				velocity.y *= -1;
				velocity.mult(absCine);
			} else if (location.y <  mass * 5/2) {
				location.y =  mass * 5/2;
				velocity.y *= -1;
				velocity.mult(absCine);
			}
	}
}
