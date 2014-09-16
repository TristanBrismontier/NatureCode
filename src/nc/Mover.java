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
	protected final float absCine;

	protected boolean d3;

	public Mover(PApplet p, float m, boolean d3) {
		this.p = p;
		this.d3 = d3;
		location =(d3)? new PVector(p.random(p.width), p.random(p.height),p.random(p.height)):new PVector(p.random(p.width), p.random(p.height));
		velocity = new PVector(0, 0);
		acceleration = new PVector(0, 0);
		this.mass = m;
		G = (float) 0.4;
		absCine = (float)0.4;
	}
	
	public Mover(PApplet p, float m, boolean d3,PVector v){
		this(p,m,d3);
		location = v;
	}


	public void update() {
		velocity.add(acceleration);
		location.add(velocity);
		acceleration.mult(0);
	}

	public void display(){
	if(d3){
		display3D();
	}else{
		display2D();
	}
	
	}
	
	public void display3D() {
//		p.stroke(0);
		p.noStroke();

		p.fill(255, 255, 255);
		p.pushMatrix();
		p.translate(location.x,	location.y,location.z);
		if(mass>=25){
			p.fill(25, 25, 25);
			p.sphere(mass);
		}else{
			p.sphere(mass*5);
		}
		p.popMatrix();
	}
	
	public void display2D(){
		p.stroke(0);
		p.fill(0, 0, 0, 125);
		p.ellipse(location.x, location.y, mass*5, mass*5);
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
		distance = p.constrain(distance, (float) 5, (float) 25);

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
		
		if (location.z > p.height -  mass * 5/2) {
			location.z = p.height -  mass * 5/2;
			velocity.z *= -1;
			velocity.mult(absCine);
		} else if (location.z <  mass * 5/2) {
			location.z =  mass * 5/2;
			velocity.z *= -1;
			velocity.mult(absCine);
		}
	}
}
