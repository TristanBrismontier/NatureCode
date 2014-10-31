package nc.box2d;

import nc.box2d.shiffman.box2d.Box2DProcessing;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import processing.core.PApplet;
import processing.core.PVector;

public class Body2D {
	protected Body body;
	protected Box2DProcessing box2d;
	protected final PApplet p;
	
	protected float gray;
	protected float size;
	
	public Body2D(PApplet p, Box2DProcessing box2d, PVector location) {
		this.p = p;
		this.box2d = box2d;
		this.gray = 175;
		this.size = 20;
		
		BodyDef bd = new BodyDef();
		bd.type = BodyType.DYNAMIC;
	
		bd.position.set(box2d.coordPixelsToWorld(location.x,location.y));
		body = box2d.createBody(bd);
		
		PolygonShape ps = new PolygonShape();
		float box2dW = box2d.scalarPixelsToWorld(size/2);
		float box2dH = box2d.scalarPixelsToWorld(size/2);
		
		ps.setAsBox(box2dW, box2dH);
		
		FixtureDef fd = new FixtureDef();
		fd.shape = ps;
		fd.density = 1;
		fd.friction = 0.3f;
		fd.restitution = 0.5f;
		
		body.createFixture(fd);
	}
	
	public Body2D(PApplet p, Box2DProcessing box2d, PVector location, boolean otherShape) {
		this.p = p;
		this.box2d = box2d;
		this.gray = 175;
		this.size = 20;
		
		BodyDef bd = new BodyDef();
		bd.type = BodyType.DYNAMIC;
	
		bd.position.set(box2d.coordPixelsToWorld(location.x,location.y));
		body = box2d.createBody(bd);
	}
	
	
	public void display() {
		Vec2 pos = box2d.getBodyPixelCoord(body);
		float a = body.getAngle();
		
		p.pushMatrix();
	
		p.translate(pos.x, pos.y);
		p.rotate(a);
		p.fill(gray);
		p.stroke(0);
		p.rectMode(p.CENTER);
		p.rect(0, 0, size, size);
		p.popMatrix();
		
	}
	
	public void destroy(){
		box2d.destroyBody(body);
	}

}
