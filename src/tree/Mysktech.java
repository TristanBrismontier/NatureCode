package tree;
import processing.core.*; 

public class Mysktech extends PApplet {

	Stick stick;
	float rX;
	float rY;
	int side = 800;
	
	public void setup() {
		size(side,side,OPENGL);
		initStick();
		smooth();
	}

	public void draw() {
		translate(150, 0, -side*(float)1.5);
		
		if(mousePressed){
			rX=mouseX;
			rY=mouseY;
			System.out.println(mouseX+ " " +mouseY);
		}
		rotateY((float)rX/100);
		rotateX((float)rY/100);
		
		pushMatrix();
//			translate(height/2, height/2, height/2);
			stroke(0);
			noFill();
			box(height);
		popMatrix();
		stick.display();
	}

	public void mousePressed() { 
		initStick();
	}

	private void initStick(){
		
		stick = new Stick(this, new PVector(width/2, height), 50, 255);
		background(255);
	}
	
    public static void main(String args[])
    {
      PApplet.main(new String[] { Mysktech.class.getName() });
    }

}
