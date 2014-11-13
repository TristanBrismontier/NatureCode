package nc.box2d;

import nc.box2d.shiffman.box2d.Box2DProcessing;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.joints.DistanceJoint;
import org.jbox2d.dynamics.joints.DistanceJointDef;

import processing.core.PApplet;
import processing.core.PVector;

public class LinkParticule {
	PApplet p;
	Cell c1;
	Cell c2;
	Box2DProcessing box2d;
	
	public LinkParticule(PApplet p, PVector location, Box2DProcessing box2d) {
		this.p = p;
		this.box2d = box2d;
		
		c1 = new Cell(p,box2d,new PVector(location.x-40,location.y),  p.random(50), BodyType.DYNAMIC, true);
		c2 = new Cell(p,box2d,new PVector(location.x+40,location.y), p.random(50), BodyType.DYNAMIC, true);
		DistanceJointDef djd = new DistanceJointDef();
		
		djd.bodyA = c1.body;
		djd.bodyB = c2.body;
		djd.length = box2d.scalarPixelsToWorld(80);
		
		djd.frequencyHz  = 1f;
		djd.dampingRatio = 1f;
		DistanceJoint dj = (DistanceJoint) box2d.world.createJoint(djd);
		
	}
	
	void display(){
		Vec2 pos1 = box2d.getBodyPixelCoord(c1.body);
	    Vec2 pos2 = box2d.getBodyPixelCoord(c2.body);
	    p.stroke(0);
	    p.line(pos1.x,pos1.y,pos2.x,pos2.y);
	 
	    c1.display();
	    c2.display();
	}
	
	void destroy(){
		c1.destroy();
		c2.destroy();
	}
	
}
