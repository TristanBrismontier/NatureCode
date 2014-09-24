package tree;
import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;

public class Mysktech extends PApplet {

	Stick stick;
	float rX;
	float rY;
	int side = 800;
	List<PVectorWidth> buleList = new ArrayList<PVectorWidth>();	
	
	public void setup() {
		size(side,side);
		initStick();
		smooth();
	}

	public void draw() {
		
		buleList.addAll(stick.display());
		if(frameCount%10 == 0){
			background(255);
			pushMatrix();
			translate(width/2, height);
			for (PVectorWidth unit : buleList) {
				noStroke();
				fill(unit.getGrey(),unit.getAlpha());
				ellipse(unit.x, unit.y, unit.getWid(), unit.getWid());
			}
			popMatrix();
		}
		
	}

	public void mousePressed() { 
		initStick();
	}

	private void initStick(){
		buleList = new ArrayList<PVectorWidth>();	
		stick = new Stick(this, new PVector(0, 0), 50, 255);
		background(255);
	}
	
    public static void main(String args[])
    {
      PApplet.main(new String[] { Mysktech.class.getName() });
    }

}
