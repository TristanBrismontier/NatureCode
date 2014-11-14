package nc.box2d;

import java.util.ArrayList;
import java.util.List;

import nc.box2d.shiffman.box2d.Box2DProcessing;

import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.joints.DistanceJoint;
import org.jbox2d.dynamics.joints.DistanceJointDef;

import processing.core.PApplet;
import processing.core.PVector;

public class ChainCell {
	PApplet p;
	List<Cell> cells;
	Box2DProcessing box2d;
	float height;

	public ChainCell(PApplet p, Box2DProcessing box2d, float height) {
		this.p = p;
		this.box2d = box2d;
		this.height = height;
		int r = 10;
		cells = new ArrayList<Cell>();

		Cell lastCell = new Cell(p, box2d, new PVector(0, height), r,
				BodyType.STATIC, true);
		for (int i = 0; i < p.width; i = i + r) {
			Cell c = new Cell(p, box2d, new PVector(i, height), r,
					BodyType.DYNAMIC, false);
			if (lastCell != null) {
				DistanceJointDef djd = new DistanceJointDef();

				djd.bodyA = c.body;
				djd.bodyB = lastCell.body;
				djd.length = box2d.scalarPixelsToWorld(1);

				djd.frequencyHz =0f;
				djd.dampingRatio = 1f;
				DistanceJoint dj = (DistanceJoint) box2d.world.createJoint(djd);
			}
			lastCell = c;
			cells.add(c);
		}
		Cell c = new Cell(p, box2d, new PVector(p.width, height), r,
				BodyType.STATIC, true);
		DistanceJointDef djd = new DistanceJointDef();

		djd.bodyA = c.body;
		djd.bodyB = lastCell.body;
		djd.length = box2d.scalarPixelsToWorld(1);

		djd.frequencyHz = 0f;
		djd.dampingRatio = 1f;
		DistanceJoint dj = (DistanceJoint) box2d.world.createJoint(djd);
		cells.add(c);

	}

	public void display() {
		for (Cell cell : cells) {
			cell.display();
		}
	}

	public void destroy() {
		for (Cell cell : cells) {
			cell.destroy();
		}
	}

}
