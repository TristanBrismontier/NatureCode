package tree;
import processing.core.*; 

public class Mysktech extends PApplet {

	Stick stick;
	public void setup() {
		size(800,800);
		initStick();
		smooth();
	}

	public void draw() {
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
