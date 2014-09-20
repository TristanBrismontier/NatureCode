package nc;
import processing.core.*;

public class Attractor {
	
	final private PApplet p;
	final PVector location;
	final float mass;
	final float G;
	
	
	public Attractor(final PApplet p) {
	this.p = p;
	location = new PVector(p.width/2, p.height/2);
	mass = 20;
	G = 0.4f;
	}
	
	void display(){
		p.stroke(0);
		p.fill(175,200);
		p.ellipse(location.x, location.y, mass*2, mass*2);
	}
	
	PVector attract(Mover m){
		PVector force = PVector.sub(location, m.location);
		float distance = force.mag();
		distance = p.constrain(distance,5f,25f);
		
		force.normalize();
		float strenght = (G * mass * m.mass)/(distance*distance);
		force.mult(strenght);
		return force;
	}
}
