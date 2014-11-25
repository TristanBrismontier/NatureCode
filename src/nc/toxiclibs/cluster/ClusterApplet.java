package nc.toxiclibs.cluster;

import processing.core.PApplet;
import processing.core.PImage;
import toxi.geom.Rect;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletPhysics2D;
import toxi.physics2d.behaviors.GravityBehavior;

public class ClusterApplet  extends PApplet {

	VerletPhysics2D physics;
	Cluster cluster;
	boolean build;

	
	public void setup() {
		size(800,800);
	
		physics = new VerletPhysics2D();
		physics.setWorldBounds(new Rect(200,200,width/2,height/2));
		physics.addBehavior(new GravityBehavior(new Vec2D(0,0.5f)));
		cluster = new Cluster(this, physics, new Vec2D(width/2,height/2));
		createPrincess();
//		cluster.linkAll(physics);
		build = false;
	}
	
	public void draw() {
		background(0);
		
		if(build){
			
			physics.update();
		}
		cluster.display();
		if(mousePressed && build){
			cluster.setPos(mouseX,mouseY);
		}
	}
	

	@Override
	public void keyPressed() {
		build = true;
		cluster.linkAll(physics);
	}
	
	public void createPrincess(){
		cluster.addNewNode(446,471, physics);
		cluster.addNewNode(446,478, physics);
		cluster.addNewNode(444,485, physics);
		cluster.addNewNode(440,486, physics);
		cluster.addNewNode(432,480, physics);
		cluster.addNewNode(431,475, physics);
		cluster.addNewNode(428,479, physics);
		cluster.addNewNode(427,484, physics);
		cluster.addNewNode(424,488, physics);
		cluster.addNewNode(417,490, physics);
		cluster.addNewNode(412,484, physics);
		cluster.addNewNode(408,477, physics);
		cluster.addNewNode(401,489, physics);
		cluster.addNewNode(397,493, physics);
		cluster.addNewNode(393,491, physics);
		cluster.addNewNode(388,487, physics);
		cluster.addNewNode(385,476, physics);
		cluster.addNewNode(385,472, physics);
		cluster.addNewNode(379,476, physics);
		cluster.addNewNode(376,480, physics);
		cluster.addNewNode(370,481, physics);
		cluster.addNewNode(363,481, physics);
		cluster.addNewNode(360,478, physics);
		cluster.addNewNode(355,472, physics);
		cluster.addNewNode(354,464, physics);
		cluster.addNewNode(355,458, physics);
		cluster.addNewNode(345,455, physics);
		cluster.addNewNode(337,453, physics);
		cluster.addNewNode(328,448, physics);
		cluster.addNewNode(323,442, physics);
		cluster.addNewNode(321,433, physics);
		cluster.addNewNode(320,426, physics);
		cluster.addNewNode(322,417, physics);
		cluster.addNewNode(325,408, physics);
		cluster.addNewNode(331,400, physics);
		cluster.addNewNode(336,394, physics);
		cluster.addNewNode(339,392, physics);
		cluster.addNewNode(336,382, physics);
		cluster.addNewNode(332,377, physics);
		cluster.addNewNode(332,368, physics);
		cluster.addNewNode(332,360, physics);
		cluster.addNewNode(337,352, physics);
		cluster.addNewNode(347,344, physics);
		cluster.addNewNode(359,340, physics);
		cluster.addNewNode(359,329, physics);
		cluster.addNewNode(364,318, physics);
		cluster.addNewNode(372,310, physics);
		cluster.addNewNode(382,303, physics);
		cluster.addNewNode(397,301, physics);
		cluster.addNewNode(407,301, physics);
		cluster.addNewNode(414,306, physics);
		cluster.addNewNode(424,316, physics);
		cluster.addNewNode(428,326, physics);
		cluster.addNewNode(432,334, physics);
		cluster.addNewNode(448,334, physics);
		cluster.addNewNode(455,339, physics);
		cluster.addNewNode(463,345, physics);
		cluster.addNewNode(467,353, physics);
		cluster.addNewNode(469,364, physics);
		cluster.addNewNode(458,380, physics);
		cluster.addNewNode(464,382, physics);
		cluster.addNewNode(470,385, physics);
		cluster.addNewNode(478,393, physics);
		cluster.addNewNode(484,399, physics);
		cluster.addNewNode(488,419, physics);
		cluster.addNewNode(486,429, physics);
		cluster.addNewNode(483,435, physics);
		cluster.addNewNode(479,440, physics);
		cluster.addNewNode(473,445, physics);
		cluster.addNewNode(467,449, physics);
		cluster.addNewNode(465,451, physics);
		cluster.addNewNode(469,455, physics);
		cluster.addNewNode(469,461, physics);
		cluster.addNewNode(466,466, physics);
		cluster.addNewNode(462,470, physics);
		cluster.addNewNode(454,473, physics);
		cluster.addNewNode(450,473, physics);
		cluster.addNewNode(446,469, physics);	
		
		cluster.addSprite(393,326,"star.png", physics);
		cluster.addSprite(423,357,"rY.png", physics);
		cluster.addSprite(376,361,"lY.png", physics);
		cluster.addSprite(407,372,"mouth.png", physics);
	}
}
