package nc;

import processing.core.PApplet;
import processing.core.PVector;

public class Mover3D extends Mover {

	public Mover3D(PApplet p, float m) {
		super(p, m);
		location = new PVector(p.random(p.width), p.random(p.height),p.random(p.height));
	}
	
	public Mover3D(PApplet p, float m,PVector v){
		this(p,m);
		location = v;
	}
	
	@Override
	public void display() {
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
	
	@Override
	public boolean checkEdge() {

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
		
		return false;
	}
	

}
