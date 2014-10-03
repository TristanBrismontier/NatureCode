package tree.cube;

import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;

import processing.core.*;
import tree.PVectorWidth;

public class CubeTest extends PApplet {

	int p = 0;

	public void setup() {
		size(800, 800, P3D);
		noStroke();
		fill(204);
		
	}

	public void draw() {

		p++;
		int count = 0;
		background(255);
		spotLight(255, 0, 0, width / 2, height / 8, 400, 0, 0, -1, PI / 2, 2);
		// camera(mouseX*2,mouseY, (height/2) / tan(PI/6), width/2, height/3, 0,
		// 0, 1, 0);
		translate(width / 2, height, -200);
		rotateY(radians(p++));
		background(255);
		noStroke();
		pushMatrix();
		drawCylinder(8, new PVectorWidth(new PVector(0, 0,0), 30, 1, 1, UUID.randomUUID()), new PVectorWidth(new PVector(30, -50,6), 10, 1, 1, UUID.randomUUID()));
		
		popMatrix();

	}
	
	void drawCylinder(int sides,PVectorWidth last,PVectorWidth current)
	{
	    float angle = 360 / sides;
	    float r1 = last.getWid();
	    float r2 = current.getWid();
	    
	    // draw top shape
	    beginShape();
	    for (int i = 0; i < sides; i++) {
	        float x = cos( radians( i * angle ) ) * r1;
	        float z = sin( radians( i * angle ) ) * r1;
	        vertex( last.x + x, last.y, last.z+z );    
	    }
	    endShape(CLOSE);
	    // draw bottom shape
	    beginShape();
	    for (int i = 0; i < sides; i++) {
	        float x = cos( radians( i * angle ) ) * r2;
	        float z = sin( radians( i * angle ) ) * r2;
	        vertex( current.x + x, current.y, current.z+z );  
	    }
	    endShape(CLOSE);
	 // draw body
	    beginShape(TRIANGLE_STRIP);
	    for (int i = 0; i < sides + 1; i++) {
	        float x1 = cos( radians( i * angle ) ) * r1;
	        float z1 = sin( radians( i * angle ) ) * r1;
	        float x2 = cos( radians( i * angle ) ) * r2;
	        float z2 = sin( radians( i * angle ) ) * r2;
	        vertex( last.x + x1, last.y, last.z+z1 );
	        vertex( current.x + x2, current.y, current.z+z2 );    
	    }
	    endShape(CLOSE);
	}
}
