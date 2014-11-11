package nc.box2d;

import nc.box2d.shiffman.box2d.Box2DProcessing;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;

import processing.core.PApplet;
import processing.core.PVector;

public class Icky extends Body2D {

	public Icky(PApplet p, Box2DProcessing box2d, PVector location) {
		super(p, box2d, location,true);
		
		PolygonShape ps = new PolygonShape();
		float box2dW = box2d.scalarPixelsToWorld(10);
		float box2dH = box2d.scalarPixelsToWorld(20);
		ps.setAsBox(box2dW, box2dH);
		
		CircleShape cs = new CircleShape();
		cs.m_radius = box2d.scalarPixelsToWorld(20);
		
		Vec2 offset = new Vec2(0,-20);
		offset = box2d.vectorPixelsToWorld(offset);
		cs.m_p.set(offset);
		
		body.createFixture(ps, 1.0f);
		body.createFixture(cs, 1.0f);
	}
	
	@Override
	public void display() {
		Vec2 pos = box2d.getBodyPixelCoord(body);
		float a  =  -body.getAngle();
		
		p.rectMode(p.CENTER);
		p.pushMatrix();
		p.translate(pos.x, pos.y);
		p.rotate(a);
		p.fill(175);
		p.rect(0, 0, 20, 40);
		p.ellipse(0, -20 , 40, 40);
		p.popMatrix();	
	}
	


}
