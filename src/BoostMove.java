import processing.core.*;

public class BoostMove extends Mover{


	float xDef;
	float yDef;
	float maxDef;
	
	public BoostMove(PApplet p, float mass) {
		super(p,mass,false);
		this.mass = mass;
		xDef = 1;
		yDef = 1;
		maxDef = (float)0.5; 
	}
	
	@Override
	void update(){
		velocity.add(acceleration);
		location.add(velocity);
		acceleration.mult(0);
	}
	
	@Override
	void display(){
		p.stroke(0);
		float contraint = p.map(Math.min(xDef, yDef), 1, maxDef, 0, 255);
		p.fill(contraint, 0, 0, 125);
		p.ellipse(location.x, location.y, mass*xDef, mass*yDef);
	}
	
void checkEdge(){
		
		checkNearContact();
		
		 if(location.x >= p.width - (mass/2*maxDef)){
			 location.x = p.width - (mass/2*maxDef);
			 velocity.x *= -1; 
		 }else if (location.x < 0){
			 location.x = 0;
			 velocity.x *=-1;
		 }

		 if(location.y >= p.height - (mass*maxDef)/2){
			 location.y = p.height- (mass*maxDef)/2;
			 velocity.y *= -1;
		 } else if(location.y <0){
			 location.y = 0;
			 velocity.y *= -1;
		 }
	}
	
	private void checkNearContact() {
		if(location.y >= p.height - mass/2){
			System.out.println(velocity.mag());
			computeX(location.y +mass/2 - p.height);
		}else if(location.x >= p.width - mass/2 ){
			computeY(location.x +mass/2 - p.width);
		}else if (location.y <= mass/2){
			computeY(location.y - mass/2);
		}else  if (location.x <= mass/2){
			computeX(location.x - mass/2);
		}else{
			yDef = 1;
			xDef = 1;
		}
		
		
	}

	private void computeY(float dep) {
		float res = Math.abs(mass/2 -dep);
		xDef = Math.max((res*2)/mass,maxDef);
		yDef = 1/xDef;
	}

	private void computeX(float dep) {
		float res =Math.abs( mass/2 -dep);
		yDef = Math.max((res*2)/mass,maxDef);
		xDef = 1/yDef;
	}

	@Override
	void applyForce(PVector force){
		PVector f = PVector.div(force, mass);
		acceleration.add(f);
	}
}
