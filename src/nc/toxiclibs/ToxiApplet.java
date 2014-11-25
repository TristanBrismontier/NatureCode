package nc.toxiclibs;

import processing.core.PApplet;
import toxi.geom.Rect;
import toxi.geom.Vec2D;
import toxi.physics.VerletSpring;
import toxi.physics2d.VerletPhysics2D;
import toxi.physics2d.VerletSpring2D;
import toxi.physics2d.behaviors.GravityBehavior;


public class ToxiApplet extends PApplet {

	VerletPhysics2D physics;
	Particle p1;
	Particle p2;
	StringParticle string;
	
	public void setup() {
		size(800,800);
		physics = new VerletPhysics2D();
		physics.setWorldBounds(new Rect(0,0,width,height));
		physics.addBehavior(new GravityBehavior(new Vec2D(0,0.5f)));
		
		p1 = new Particle(this, new Vec2D(width/2,100));
		p2 = new Particle(this, new Vec2D(width/2-100,height/2));
		p2.setSize(30);
		physics.addParticle(p1);
		physics.addParticle(p2);
		p1.lock();
		
		string = new StringParticle(this, physics, p1, p2);
	}
	
	public void draw() {
		physics.update();
		background(0);
		p1.display();		
		p2.display();
		string.display();
		if(mousePressed){
			p2.lock();
			p2.x = mouseX;
			p2.y = mouseY;
			p2.unlock();
		}
	}
}