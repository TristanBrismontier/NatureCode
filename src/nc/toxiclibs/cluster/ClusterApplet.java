package nc.toxiclibs.cluster;

import nc.toxiclibs.Cloth;
import nc.toxiclibs.Particle;
import processing.core.PApplet;
import toxi.geom.Rect;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletPhysics2D;
import toxi.physics2d.behaviors.GravityBehavior;

public class ClusterApplet  extends PApplet {

	
	VerletPhysics2D physics;
	Cluster cluster;

	
	public void setup() {
		size(800,800);
		physics = new VerletPhysics2D();
		physics.setWorldBounds(new Rect(0,0,width,height));
		physics.addBehavior(new GravityBehavior(new Vec2D(0,0.5f)));
		cluster = new Cluster(this, physics, new Vec2D(width/2,height/2), 4, 200);
		
	}
	
	public void draw() {
		physics.update();
		background(0);
		cluster.display();
		if(mousePressed){
			cluster.setPos(mouseX,mouseY);
			
		}
		
	}
}
