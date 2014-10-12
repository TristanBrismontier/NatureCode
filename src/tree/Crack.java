package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import processing.core.PApplet;
import processing.core.PVector;

import com.google.common.collect.Iterables;

public class Crack extends PApplet {

	Stick stick;
	float p = 0;
	final int side = 800;
	boolean compute = true;
	ArrayList<MapPVector> buleMap = new ArrayList<MapPVector>();

	public void setup() {
		size(side, side, P3D);
		initStick();
	}

	public void draw() {
		computeStickPosition();
		displaySticks();
	}

	private void displaySticks() {
		background(0);
		noStroke();
		pointLight(51, 102, 126, 35, 40, 36);
		translate(width / 2, height, -200);
		rotateY(radians(p++));
		for (Entry<UUID, List<PVectorWidth>> entry : buleMap.entrySet()) {
			for (PVectorWidth unit : entry.getValue()) {
				pushMatrix();
				drawCylinder(unit);
				popMatrix();
			}
		}
	}

	private void computeStickPosition() {
		if (compute) {
			List<PVectorWidth> buleListComputed = stick.display();
			for (PVectorWidth pVectorWidth : buleListComputed) {
				List<PVectorWidth> listbule = buleMap.get(pVectorWidth.getId());
				if (listbule == null) {
					listbule = new ArrayList<PVectorWidth>();
					listbule.add(pVectorWidth);
					buleMap.put(pVectorWidth.getId(), listbule);
				} else {
					PVectorWidth lastVector = Iterables.getLast(listbule);
					if (abs(lastVector.dist(pVectorWidth)) >= pVectorWidth
							.getWid()) {
						listbule.add(pVectorWidth);
					}
				}
			}
		}
	}

	public void mousePressed() {
		initStick();
		for (int i = 0; i < platformNames.length; i++) {
			for (int j = 0; j < platformNames.length; j++) {
				
			}
			
		}
	}

	void drawCylinder(final PVectorWidth current) {
		float r1 = current.getWid();
		translate(current.x, current.y, current.z);
		box(r1);
	}

	private void initStick() {
		;
		compute = true;
		buleMap = new HashMap<UUID, List<PVectorWidth>>();
		stick = new Stick(this, new PVector(0, 0), 50, 180);
		background(255);
	}

	public static void main(String args[]) {
		PApplet.main(new String[] { Crack.class.getName() });
	}

}
