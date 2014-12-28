package nc.box2d;

import nc.box2d.shiffman.box2d.Box2DProcessing;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Body2D {
	protected Body body;
	protected Box2DProcessing box2d;
	protected final PApplet p;
	protected FixtureDef fd;
	
	protected float gray;
	protected float whith;
	protected float height;
	
	public Body2D(PApplet p, Box2DProcessing box2d, PVector location) {
		this(p,box2d,location,p.random(50),p.random(50),BodyType.DYNAMIC);
	}
	
	public Body2D(PApplet p, Box2DProcessing box2d, PVector location, float whith, float height, BodyType type) {
		this.p = p;
		this.box2d = box2d;
		this.gray = 175;
		this.whith = whith;
		this.height = height;
		BodyDef bd = new BodyDef();
		bd.type = type;
		
		bd.position.set(box2d.coordPixelsToWorld(location.x,location.y));
		body = box2d.createBody(bd);
		PolygonShape ps = new PolygonShape();
		float box2dW = box2d.scalarPixelsToWorld(whith/2);
		float box2dH = box2d.scalarPixelsToWorld(height/2);
		
		ps.setAsBox(box2dW, box2dH);
		
		fd = new FixtureDef();
		fd.shape = ps;
		fd.density = 1;
		fd.friction = 1f;
		fd.restitution = 0f;
		
		body.createFixture(fd);
	}
	
	public Body2D(PApplet p, Box2DProcessing box2d, PVector location, boolean otherShape){
		this(p, box2d, location, p.random(50), otherShape, BodyType.DYNAMIC);
	}
	
	public void applyForce(Vec2 force){
		Vec2 pos = body.getWorldCenter();
		body.applyForce(force, pos);
	}
	
	public Vec2 attract(Body2D b2D){
		Vec2 pos = body.getWorldCenter();
		Vec2 moverPos = b2D.body.getWorldCenter();
		Vec2 force = pos.sub(moverPos);
		float distance = force.length();
		distance = p.constrain(distance, 1, 5);
		force.normalize();
		float strenght = ( 0.81f*1*b2D.body.m_mass);
		force.mulLocal(strenght);
		return force;
	}
	
	public Body2D(PApplet p, Box2DProcessing box2d, PVector location,float width, boolean otherShape, BodyType type) {
		this.p = p;
		this.box2d = box2d;
		this.gray = 175;
		this.whith = width;
		
		BodyDef bd = new BodyDef();
		bd.type = type;
	
		bd.position.set(box2d.coordPixelsToWorld(location.x,location.y));
		body = box2d.createBody(bd);
	}
	
	
	public void display() {
		Vec2 pos = box2d.getBodyPixelCoord(body);
		float a = body.getAngle();
		
		p.pushMatrix();
	
		p.translate(pos.x, pos.y);
		p.rotate(-a);
		p.fill(175);
		p.stroke(255);
		p.rectMode(p.CENTER);
		p.rect(0, 0, whith, height);
		p.popMatrix();
		
	}
	
	public void destroy(){
		box2d.destroyBody(body);
	}

}
