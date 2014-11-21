package nc.box2d;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nc.box2d.shiffman.box2d.Box2DProcessing;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.contacts.Contact;

import processing.core.PApplet;
import processing.core.PVector;

public class MainBoxe2D extends PApplet {

	final List<Cell> boxes = new ArrayList<Cell>();

	Box2DProcessing box2D;
	Border border;
	Cell cell;

	public void setup() {
		size(300, 300);
		box2D = new Box2DProcessing(this);
		box2D.createWorld(new Vec2(0.0f, 0.0f));
		box2D.listenForCollisions();
		border = new Border(this, box2D);
		cell = new Cell(this, box2D, new PVector(width / 2, height / 2), 80,
				BodyType.DYNAMIC, true);
		boxes.add(cell);
	}

	public void draw() {
		background(0);
		Vec2 pos = cell.body.getWorldCenter();
		Vec2 target = box2D.coordPixelsToWorld(mouseX, mouseY);
		Vec2 v = target.sub(pos);
		cell.body.setLinearVelocity(v);
		box2D.step();
		border.display();
		for (Cell box : boxes) {
			boxes.forEach(b -> box.applyForce(b.attract(box)));
			box.display();
		}
		if (mousePressed) {
			boxes.add(new Cell(this, box2D, new PVector(mouseX, mouseY),
					random(10,30), BodyType.DYNAMIC, true));
		}
	}

	@Override
	public void mousePressed() {
	}

	public void beginContact(Contact cp) {
		Fixture f1 = cp.getFixtureA();
		Fixture f2 = cp.getFixtureB();

		Body b1 = f1.getBody();
		Body b2 = f2.getBody();
		if (b1 == null || b2 == null) {
			return;
		}
		Cell cell1 = (Cell) b1.getUserData();
		Cell cell2 = (Cell) b2.getUserData();
		if (cell1 != null && cell2 != null) {
			cell1.contact(cell2);
			cell2.contact(cell1);
		}
	}

	public void endContact() {

	}

	@Override
	public void mouseReleased() {
	}

	@Override
	public void keyPressed() {
		cell = new Cell(this, box2D, new PVector(200, 200), 20,
				BodyType.KINEMATIC, true);

		Iterator<Cell> it = boxes.iterator();
		while (it.hasNext()) {
			Cell body = it.next();
			body.destroy();
			it.remove();
		}
	}
}
