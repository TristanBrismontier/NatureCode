package nc.box2d;

import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.joints.RevoluteJoint;
import org.jbox2d.dynamics.joints.RevoluteJointDef;

import nc.box2d.shiffman.box2d.Box2DProcessing;
import processing.core.PApplet;
import processing.core.PVector;

public class Widmill {
	PApplet p;
	Box2DProcessing box2d;
	Body2D box1;
	Body2D box2;
	RevoluteJoint joint;
		
	public Widmill(PApplet p, Box2DProcessing box2d, float x, float y) {
		this.p = p;
		this.box2d = box2d;
		box1 = new Body2D(p, box2d, new PVector(x, y-20), 120, 10 , BodyType.DYNAMIC);
		box2 = new Body2D(p, box2d, new PVector(x, y), 10, 40 , BodyType.STATIC);
		
		RevoluteJointDef rjd = new RevoluteJointDef();
		
		rjd.initialize(box1.body, box2.body, box1.body.getWorldCenter());
		
		rjd.motorSpeed  = p.PI *2;
		rjd.maxMotorTorque = 1000.0f;
		rjd.enableMotor = true;
		
		joint = (RevoluteJoint) box2d.world.createJoint(rjd);
		
	}
	
	void toggleMotor(){
		boolean motorStatus = joint.isMotorEnabled();
		joint.enableMotor(!motorStatus);
	}
	
	void display(){
		box1.display();
		box2.display();
	}
	

}
