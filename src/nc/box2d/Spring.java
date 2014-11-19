package nc.box2d;

import nc.box2d.shiffman.box2d.Box2DProcessing;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.joints.MouseJoint;
import org.jbox2d.dynamics.joints.MouseJointDef;

import processing.core.PApplet;

public class Spring {

	MouseJoint mouseJoint;
	PApplet p;
	Box2DProcessing box2d;

	public Spring(PApplet p, Box2DProcessing box2d) {
		this.mouseJoint = null;
		this.p = p;
		this.box2d = box2d;
	}

	void update(float x, float y) {
		if (mouseJoint != null) {
			Vec2 mouseWorld = box2d.coordPixelsToWorld(x, y);
			mouseJoint.setTarget(mouseWorld);
		}
	}

	void bind(float x, float y, Body2D box) {
		MouseJointDef md = new MouseJointDef();
		md.bodyA = box2d.getGroundBody();
		md.bodyB = box.body;
		Vec2 mp = box2d.coordPixelsToWorld(x, y);
		md.target.set(mp);
		md.maxForce = 1000.0f * box.body.m_mass;
		md.frequencyHz = 5.0f;
		md.dampingRatio = 0.9f;
		mouseJoint = (MouseJoint) box2d.world.createJoint(md);
	}
	
	void display(){
		if(mouseJoint == null) return;
		
		Vec2 v1 = new Vec2(0, 0);
		mouseJoint.getAnchorA(v1);
		Vec2 v2 = new Vec2(0, 0);
		mouseJoint.getAnchorB(v2);
		
		v1 = box2d.coordPixelsToWorld(v1);
		v2 = box2d.coordPixelsToWorld(v2);
		
		p.stroke(255);
		p.strokeWeight(1);
		p.line(v1.x, v1.y, v2.x, v2.y);
	}
	
	void destroy(){
		if(mouseJoint == null) return;
		box2d.world.destroyJoint(mouseJoint);
		mouseJoint = null;
	}
}
