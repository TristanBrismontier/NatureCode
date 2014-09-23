package tree;
import processing.core.*; 

public class Mysktech extends PApplet {

	Stick stick;
	float rX;
	float rY;
	int side = 800;
	
	public void setup() {
		size(side,side);
		initStick();
		smooth();
	}

	public void draw() {
		pushMatrix();
		translate(width/2, height);
		stick.display();
		popMatrix();
	}

	public void mousePressed() { 
		initStick();
	}

	private void initStick(){
		stick = new Stick(this, new PVector(0, 0), 50, 255);
		background(255);
	}
	
    public static void main(String args[])
    {
      PApplet.main(new String[] { Mysktech.class.getName() });
    }

}
