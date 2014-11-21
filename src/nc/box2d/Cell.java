package nc.box2d;

import java.awt.Color;

import nc.box2d.shiffman.box2d.Box2DProcessing;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import processing.core.PApplet;
import processing.core.PVector;

public class Cell extends Body2D {
	Color color;

	public Cell(PApplet p, Box2DProcessing box2d, PVector location, float r, BodyType type, boolean density) {
			super(p, box2d, location,r,true, type);
		
			color = new Color((int)p.random(255), (int)p.random(255),(int)p.random(255));
			CircleShape ps = new CircleShape();
			float box2dW = box2d.scalarPixelsToWorld(whith/2);
			
			ps.setRadius(box2dW);
			
			FixtureDef fd = new FixtureDef();
			fd.shape = ps;
			fd.density = (density)?1:0;
			fd.friction = 0.3f;
			fd.restitution = 0.5f;
			body.createFixture(fd);
			body.setUserData(this);
	}

	@Override
	public void display() {
		Vec2 pos = box2d.getBodyPixelCoord(body);
		float a = body.getAngle();
		
		p.pushMatrix();
	
		p.translate(pos.x, pos.y);
		p.rotate(-a);
		p.fill(color.getRed(),color.getGreen(),color.getBlue());
		p.strokeWeight(2);
		p.stroke(255);
		p.ellipse(0, 0, whith, whith);
		p.line(0, 0, whith/2, 0);
		p.popMatrix();
	}
	
	public void setColor(Color c) {
		this.color= new Color(c.getRed(), c.getGreen(), c.getBlue());
	}
	public void contact(Cell c){
		int r = (color.getRGB() >> 16) & 0x000000ff;
		int g = (color.getRGB() >> 8) & 0x000000ff;
		int b = color.getRGB() & 0x000000ff;

		r += (c.color.getRGB() >> 16) & 0x000000ff;
		g += (c.color.getRGB() >> 8) & 0x000000ff;
		b += c.color.getRGB() & 0x000000ff;

		int average = ((r/2)<<16)+((g/2)<<8)+(b/2);

		int rn = (average >> 16) & 0x000000ff;
		int gn = (average >> 8) & 0x000000ff;
		int bn = average & 0x000000ff;
		color = new Color(rn, gn, bn);
		c.setColor(color);
	}
	
}
