package nc.toxiclibs.cluster;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletPhysics2D;
import toxi.physics2d.VerletSpring2D;

public class Cluster {
	PApplet p; 
	List<Node> nodes;
	
	static final float elas = 0.01f;
	
	public Cluster(PApplet p, VerletPhysics2D physics,Vec2D center,float num,float diameter) {
		this.p = p;
		nodes = new ArrayList<Node>();
		for (int i = 0; i < num; i++) {
			Node node = new Node(p, center.add(Vec2D.randomVector()));
			nodes.add(node);
			physics.addParticle(node);
		}
		
		List<Node> notalreadyLink = new ArrayList<Node>(nodes);
		for (Node node : nodes) {
			notalreadyLink.remove(node);
			if(!notalreadyLink.isEmpty()){
				for (Node nodeToLink : notalreadyLink) {
					VerletSpring2D spring = new VerletSpring2D(node, nodeToLink, diameter,elas);
					physics.addSpring(spring);
				}
			}
		}		
	}
	
	public void display(){
		p.fill(205,147,249);
		p.beginShape();
		nodes.forEach(n -> p.vertex(n.x,n.y));
		p.endShape();
		List<Node> notalreadyLink = new ArrayList<Node>(nodes);
		for (Node node : nodes) {
			notalreadyLink.remove(node);
			if(!notalreadyLink.isEmpty()){
				for (Node nodeToLink : notalreadyLink) {
					p.stroke(255);
					p.line(node.x, node.y, nodeToLink.x, nodeToLink.y);
				}
			}
		}
	}

	public void setPos(int mouseX, int mouseY) {
		Node node = nodes.get(0);
		node.lock();
		node.x = mouseX;
		node.y = mouseY;
		node.unlock();
	}
		
}
