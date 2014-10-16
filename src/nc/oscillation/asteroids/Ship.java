package nc.oscillation.asteroids;

import nc.Mover;
import nc.oscillation.asteroids.BurstParticle.BurstParticleFactory;
import nc.particle.ParticleSystem;
import processing.core.PApplet;
import processing.core.PVector;

public class Ship extends Mover {

	float oDeg;
	float sheild;
	boolean boost;
	ParticleSystem<BurstParticle> rightBurst;
	ParticleSystem<BurstParticle> leftBurst;
	
	public Ship(PApplet p, float m) {
		super(p, m);
		location = new PVector(p.width/2, p.height/2);
		sheild = 0;
		oDeg=0;
		boost = false;
		rightBurst = new ParticleSystem<BurstParticle>(p, new PVector(-4, 27),new BurstParticleFactory() );
		leftBurst = new ParticleSystem<BurstParticle>(p, new PVector(5, 27),new BurstParticleFactory() );
	}
	
	public void display(){
		
		p.pushMatrix();
		p.translate(location.x, location.y);
		drawship();	
		

		//draw Velocity Direction;
		p.stroke(0);
		p.rotate(velocity.heading());
		p.line(0,0,10,0);		
		p.popMatrix();
	}

	private void drawship() {
		p.pushMatrix();
		p.rotate(p.radians(oDeg));
		rightBurst.run(boost);
		leftBurst.run(boost);
		p.stroke(0);
		p.fill(0,125);
		p.triangle(0, -8, -10, 20, 10, 20);
		p.fill(0,125);
		p.rect(-7, 20, 5, 5);
		p.rect(2, 20, 5, 5);
		drawSheild();
		p.popMatrix();
	}

	private void drawSheild() {
		p.noStroke();
		p.fill(0,153,153,sheild);
		p.ellipse(0, 6, 40, 40);
	}
	
	@Override
	public void update() {
		if (boost) {
			boost();
		}
		
		super.update();
		sheild--;
		sheild = p.constrain(sheild, 0, 125);
	}
	
	@Override
	public boolean checkEdge() {
		boolean contact = super.checkEdge();
		if(contact){
			sheild=125;
		}
		return contact;
	}
	
	public void startBoost(){
		boost=true;
	}

	public void stopBoost(){
		boost=false;
	}
	
	private void boost(){
		float x = p.sin(p.radians(oDeg));
		float y = -p.cos(p.radians(oDeg));
		PVector boost = new PVector(x, y);
		boost.normalize();
		boost.mult(0.1f);
		
		this.applyForce(boost);
	}
	
	public void addrotate(){
		oDeg+=3;
	}
	
	public void subrotate(){
		oDeg-=3;
	}

}
