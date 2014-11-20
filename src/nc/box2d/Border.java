package nc.box2d;

import java.util.ArrayList;
import java.util.List;

import nc.box2d.shiffman.box2d.Box2DProcessing;

import org.jbox2d.collision.shapes.ChainShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;

import processing.core.PApplet;

public class Border {
	private PApplet p;
	private Box2DProcessing box2d;
	private final List<Vec2> surface = new ArrayList<Vec2>();
	
	public Border(PApplet p, Box2DProcessing box2d) {
		this.p = p; 
		this.box2d = box2d;
		
		surface.add(new Vec2(10,10)); 
	 	surface.add(new Vec2(p.width-10,10)); 
	 	surface.add(new Vec2(p.width-10,p.height-10)); 
	 	surface.add(new Vec2(10,p.height-10)); 
	 	surface.add(new Vec2(10,10));

		
		ChainShape chain = new ChainShape();
		List<Vec2> vertices = new ArrayList<Vec2>();
		surface.forEach(v -> vertices.add(box2d.coordPixelsToWorld(v)));
		
		Vec2[] verticesTab = new Vec2[vertices.size()];
		verticesTab = vertices.toArray(verticesTab);
		chain.createChain(verticesTab, verticesTab.length);
		
		BodyDef bd = new BodyDef();
		Body body = box2d.world.createBody(bd);
		body.createFixture(chain, 1);
	}
	
	public void display() {
		p.strokeWeight(1);
		p.stroke(255);
		p.noFill();
		p.beginShape();
		surface.forEach(v -> p.vertex(v.x, v.y));
		p.endShape();
	}
}
