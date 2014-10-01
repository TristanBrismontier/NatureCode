package tree.cube;

import processing.core.*;

public class CubeTest extends PApplet{

	int p=0;
	public void setup() {
		 size(800, 800, P3D);
		  noStroke();
		  fill(204);
	}
	
	public void draw() {
		
		  background(255);
		  camera(mouseX, height/2, (height/2) / tan(PI/6), width/2, height/3, 0, 0, 1, 0);
		  translate(width/2, height/2, -100);
		  stroke(0);
		  noFill();
		  box(200);
		  pushMatrix();
	
		  translate(0, height/8, 0);
		  sphere(10);
		  popMatrix();
		  
	}
}
