import processing.core.*;

public class BoostMove {

	PApplet p;
	PVector location;
	PVector velocity;
	PVector acceleration;
	float mass;
	float xDeformation;
	float yDeformation;
	
	public BoostMove(PApplet p, float mass) {
		this.p = p;
		location = new PVector(30, 30);
		velocity = new PVector(0, 0);
		acceleration = new PVector(0, 0);
		System.out.println("x "+location.x + " y"+ location.y );
		this.mass = mass;
		float xDeformation = 1;
		float yDeformation = 1;
	}
	
	void update(){
		velocity.add(acceleration);
		location.add(velocity);
		acceleration.mult(0);
	}
	
	void display(){
		p.stroke(0);
		p.fill(58,125);
		p.ellipse(location.x, location.y, mass*xDeformation, mass*yDeformation);
	}
	
	void checkEdge(){
		 if(location.x > p.width){
			 location.x = p.width;
			 velocity.x *= -1; 
		 }else if (location.x < 0){
			 location.x = 0;
			 velocity.x *=-1;
		 }

		 if(location.y > p.height){
			 location.y = p.height;
			 velocity.y *= -1;
		 } else if(location.y <0){
			 location.y = 0;
			 velocity.y *= -1;
		 }
	}
	
	void applyForce(PVector force){
		PVector f = PVector.div(force, mass);
		acceleration.add(f);
	}
}
