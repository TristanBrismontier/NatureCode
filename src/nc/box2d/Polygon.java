package nc.box2d;

import java.util.ArrayList;
import java.util.List;

import nc.box2d.shiffman.box2d.Box2DProcessing;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.FixtureDef;

import processing.core.PApplet;
import processing.core.PVector;

public class Polygon extends Body2D {
	private final List<Vec2> vertices = new ArrayList<Vec2>();
	
	public Polygon(PApplet p, Box2DProcessing box2d, PVector location) {
		super(p, box2d, location,true);
		vertices.add(box2d.vectorPixelsToWorld(new Vec2(-15, 25)));
		vertices.add(box2d.vectorPixelsToWorld(new Vec2(15, 0)));
		vertices.add(box2d.vectorPixelsToWorld(new Vec2(20, -15)));
		vertices.add(box2d.vectorPixelsToWorld(new Vec2(-10, -10)));
		
		Vec2[] verticesTab = new Vec2[vertices.size()];
		verticesTab = vertices.toArray(verticesTab);
		
		PolygonShape ps = new PolygonShape();
		ps.set(verticesTab, verticesTab.length);

		FixtureDef fd = new FixtureDef();
		fd.shape = ps;
		fd.density = 1;
		fd.friction = 0.3f;
		fd.restitution = 0.5f;
		body.createFixture(fd);
	}
	
	@Override
	public void display() {
		  Vec2 pos = box2d.getBodyPixelCoord(body);
		    float a = body.getAngle();

		    // First we get the Fixture attached to the body...
		    Fixture f = body.getFixtureList();
		    // ...then the Shape attached to the Fixture.
		    PolygonShape ps = (PolygonShape) f.getShape();

		    p.rectMode(p.CENTER);
		    p.pushMatrix();
		    p.translate(pos.x,pos.y);
		    p.rotate(-a);
		    p.fill(175);
		    p.stroke(0);
		    p.beginShape();
		    //[offset-up] We can loop through that array and convert each vertex from Box2D space to pixels.
		    for (int i = 0; i < ps.getVertexCount(); i++) {
		      Vec2 v = box2d.vectorWorldToPixels(ps.getVertex(i));
		      p.vertex(v.x,v.y);
		    }
		    p.endShape(p.CLOSE);
		    p.popMatrix();
	}

}


