package tree;
import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;

public class Mysktech extends PApplet {

	Stick stick;
	int p=0;
	float rX;
	float rY;
	int side = 800;
	boolean compute = true;
	List<PVectorWidth> buleList = new ArrayList<PVectorWidth>();	
	
	public void setup() {
		size(side,side, P3D);
		initStick();
		smooth();
	}

	public void draw() {
		if(compute){
			List<PVectorWidth> buleListComputed = stick.display();
			if(frameCount%4 == 0){
				buleList.addAll(buleListComputed);
			}
			if (buleListComputed.isEmpty()) {
				System.out.println("finish");
				compute = false;
				
			}
		}
		p++;
		
		 background(255);
		 
//		  camera(mouseX*2,mouseY, (height/2) / tan(PI/6), width/2, height/3, 0, 0, 1, 0);
		  translate(width/2, height, -200);
		  rotateY(radians(p++));
			background(255);
			for (PVectorWidth unit : buleList) {
				noStroke();
				fill(unit.getGrey());
				pushMatrix();
				translate(unit.x, unit.y,unit.z);
				sphere(unit.getWid());
				popMatrix();
			}
		
	}

	public void mousePressed() { 
		initStick();
	}

	private void initStick(){
		compute = true;
		buleList = new ArrayList<PVectorWidth>();	
		stick = new Stick(this, new PVector(0, 0), 50,100);
		background(255);
	}
	
    public static void main(String args[])
    {
      PApplet.main(new String[] { Mysktech.class.getName() });
    }

}
