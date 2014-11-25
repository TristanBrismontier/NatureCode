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
	Node firstNode;
	Node star;
	Node rY;
	Node lY;
	Node mouth;
	
	
	static final float elas = 0.0001f;
	
	public Cluster(PApplet p, VerletPhysics2D physics,Vec2D center) {
		this.p = p;
		nodes = new ArrayList<Node>();
		firstNode = new Node(p, center);
		firstNode.lock();
		nodes.add(firstNode);
		physics.addParticle(firstNode);
				
	}

	public void linkAll(VerletPhysics2D physics) {
		for (Node node : nodes) {
			System.out.println(node.x+","+node.y);
			node.unlock();
		}
		List<Node> notalreadyLink = new ArrayList<Node>(nodes);
		for (Node node : nodes) {
			notalreadyLink.remove(node);
			float elass = (node.equals(firstNode))?0.02f:elas;
			if(!notalreadyLink.isEmpty()){
				for (Node nodeToLink : notalreadyLink) {
					float diameter = node.distanceTo(nodeToLink);
					VerletSpring2D spring = new VerletSpring2D(node, nodeToLink, diameter,elass);
					physics.addSpring(spring);
				}
			}
		}		
	}
	
	
	public void addNewNode(int mouseX, int mouseY, VerletPhysics2D physics) {
		Node node = new Node(p, new Vec2D(mouseX,mouseY));
		node.lock();
		physics.addParticle(node);
		nodes.add(node);
	}
	
	
	
	public void display(){
		p.noStroke();
		p.fill(205,147,249,100);
		p.beginShape();
	
		for (Node n : nodes) {
			if(n.isPartOfShape()){
				p.vertex(n.x,n.y);
			}
		}
		p.endShape();
//		List<Node> notalreadyLink = new ArrayList<Node>(nodes);
//		for (Node node : nodes) {
//			notalreadyLink.remove(node);
//			if(!notalreadyLink.isEmpty()){
//				for (Node nodeToLink : notalreadyLink) {
//					p.stroke(255);
//					p.line(node.x, node.y, nodeToLink.x, nodeToLink.y);
//				}
//			}
//		}
	}

	public void setPos(int mouseX, int mouseY) {
		firstNode.lock();
		firstNode.x = mouseX;
		firstNode.y = mouseY;
		firstNode.unlock();
	}
		
}
