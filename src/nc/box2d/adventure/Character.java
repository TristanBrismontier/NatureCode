package nc.box2d.adventure;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import processing.core.PApplet;
import processing.core.PVector;
import nc.box2d.Body2D;
import nc.box2d.shiffman.box2d.Box2DProcessing;

public class Character extends Body2D {
	
	int jumpCount = 0;
	int invuCount = 0;
	int coldDown = 0;
	boolean invu = false;
	final static int limitinvu = 22;
	final static int limitcoldDown = 6;
	final static int limitJumpCount = 20;

	public Character(PApplet p, Box2DProcessing box2d, PVector location,
			float whith, float height) {
		super(p, box2d, location, whith, height, BodyType.DYNAMIC);
		body.setFixedRotation(true);
		
	}
	
	public void monitor(boolean rig, boolean lef, boolean jump, boolean attack){
		Vec2 pos = this.body.getPosition();
		this.fd.setFriction(100.0f);
		if(rig){
			this.fd.setFriction(0.2f);
			this.body.applyLinearImpulse(new Vec2(100,0), pos, true);
		}
		if(lef){
			this.fd.setFriction(0.2f);
			this.body.applyLinearImpulse(new Vec2(-100,0), pos, true);		
		}
		if(jump &&  jumpCount <= limitJumpCount){
			this.fd.setFriction(0.2f);
			System.out.println("jump " + jumpCount++ );
			this.body.applyLinearImpulse(new Vec2(0,200), pos, true);
		} 
		if((coldDown == limitcoldDown)&& attack ){
			coldDown = 0;
			invuCount = limitinvu;
		}
	}
	
	public void update(){
		if(invuCount>0){
			invuCount--;
			invu = true;
		}else if(coldDown<limitcoldDown){
			coldDown++;
			invu=false;
		}
		float speedLimit = 40;
		Vec2 velo = this.body.getLinearVelocity();
		if(velo.y == 0) jumpCount =0;
		Vec2 newVelo = new Vec2(p.constrain(velo.x, -speedLimit, speedLimit),p.constrain(velo.y, -speedLimit*10, speedLimit));
		this.body.setLinearVelocity(newVelo);
		
	}
	
	public void display() {
		Vec2 pos = box2d.getBodyPixelCoord(body);
		float a = body.getAngle();
		
		p.pushMatrix();
	
		p.translate(pos.x, pos.y);
		p.rotate(-a);
		if(invu){
			p.fill(255,0,0);
		}else{
			p.fill(175);
		}
		p.stroke(255);
		p.rectMode(p.CENTER);
		p.rect(0, 0, whith, height);
		p.popMatrix();
		
	}
}
