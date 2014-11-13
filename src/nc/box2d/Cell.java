package nc.box2d;

import nc.box2d.shiffman.box2d.Box2DProcessing;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import processing.core.PApplet;
import processing.core.PVector;

public class Cell extends Body2D {

	public Cell(PApplet p, Box2DProcessing box2d, PVector location, float r, BodyType type, boolean density) {
			super(p, box2d, location,r,true, type);
		
			CircleShape ps = new CircleShape();
			float box2dW = box2d.scalarPixelsToWorld(whith/2);
			
			ps.setRadius(box2dW);
			
			FixtureDef fd = new FixtureDef();
			fd.shape = ps;
			fd.density = 0;
			fd.friction = 0.3f;
			fd.restitution = 0.5f;
			body.createFixture(fd);
	}

	@Override
	public void display() {
		Vec2 pos = box2d.getBodyPixelCoord(body);
		float a = body.getAngle();
		
		p.pushMatrix();
	
		p.translate(pos.x, pos.y);
		p.rotate(-a);
		p.fill(gray);
		p.strokeWeight(2);
		p.stroke(0);
		p.ellipse(0, 0, whith, whith);
		p.line(0, 0, whith/2, 0);
		p.popMatrix();
	}
	
}
