package nc.box2d;

import nc.box2d.shiffman.box2d.Box2DProcessing;

import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.joints.RevoluteJoint;
import org.jbox2d.dynamics.joints.RevoluteJointDef;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Car {
	PApplet p;
	Box2DProcessing box2d;
	Cell wheel1;
	Cell wheel2;
	Body2D box;
	RevoluteJoint joint;
		
	public Car(PApplet p, Box2DProcessing box2d, float x, float y) {
		this.p = p;
		this.box2d = box2d;
		box = new Body2D(p, box2d, new PVector(x, y), 120, 25 , BodyType.KINEMATIC);
		wheel1 = new Cell(p, box2d, new PVector(x-50, y+25), 20, BodyType.DYNAMIC,true);
		wheel2= new Cell(p, box2d, new PVector(x+50, y+25), 20, BodyType.DYNAMIC,true);
		
		RevoluteJointDef rjd = new RevoluteJointDef();
		
		rjd.initialize(box.body, wheel1.body, wheel1.body.getWorldCenter());		
		rjd.motorSpeed  = -p.PI *4;
		rjd.maxMotorTorque = 1000000.0f;
		rjd.enableMotor = true;
		
		joint = (RevoluteJoint) box2d.world.createJoint(rjd);
	
		RevoluteJointDef rjd2 = new RevoluteJointDef();
		
		rjd2.initialize(box.body, wheel2.body, wheel2.body.getWorldCenter());		
		rjd2.motorSpeed  = -p.PI *4;
		rjd2.maxMotorTorque = 1000000.0f;
		rjd2.enableMotor = true;
		
		joint = (RevoluteJoint) box2d.world.createJoint(rjd2);
		
	}
	
	void toggleMotor(){
		boolean motorStatus = joint.isMotorEnabled();
		joint.enableMotor(!motorStatus);
	}
	
	Body2D getBodyCar(){
		return box; 
	}
	
	void display(){
		wheel1.display();
		wheel2.display();
		box.display();
	}
}
