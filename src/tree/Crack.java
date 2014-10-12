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
	ArrayList<MapPVector> pVectorMap = new ArrayList<MapPVector>();

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
		 for (int i = 0; i < pVectorMap.size(); i++){
		       for (int j = 0; j < pVectorMap.get(i).pVectorWidths.size(); j++) {
		        pushMatrix();
		        drawCylinder(pVectorMap.get(i).pVectorWidths.get(j));
		        popMatrix();
		      }
		    }
	}

	private void computeStickPosition() {
		if (compute) {
			ArrayList<PVectorWidth> buleListComputed = stick.display();
			
			
			for (int i = 0; i < buleListComputed.size(); i++) {
				ArrayList<PVectorWidth> listbule = null;
				for (int j = 0; j < pVectorMap.size(); j++) {
					if(pVectorMap.get(j).id == buleListComputed.get(i).getId()){
						listbule = pVectorMap.get(j).pVectorWidths;
					}
				}
				
				if (listbule == null) {
					listbule = new ArrayList<PVectorWidth>();
					listbule.add(buleListComputed.get(i));
					pVectorMap.add(new MapPVector(buleListComputed.get(i).getId(), listbule));
				} else {
					PVectorWidth lastVector = listbule.get(listbule.size()-1);
					if (abs(lastVector.dist(buleListComputed.get(i))) >= buleListComputed.get(i)
							.getWid()) {
						listbule.add(buleListComputed.get(i));
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
		compute = true;
		pVectorMap =  new ArrayList<MapPVector>();
		stick = new Stick(this, new PVector(0, 0), 50, 180);
		background(255);
	}

	public static void main(String args[]) {
		PApplet.main(new String[] { Crack.class.getName() });
	}

}
