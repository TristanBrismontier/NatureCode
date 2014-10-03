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
		
		PVector p = new PVector(5,5);
		PVector p2 = new PVector(6,5);
		
		System.out.println(p.dist(p2));
		  
	}
}
