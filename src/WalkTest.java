import processing.core.*;

public class WalkTest extends PApplet {

	WhiteWalker w;
	
	public void setup() {
	    size(1000,300);
	    background(255);
	    w = new WhiteWalker(this);
	}

	  public void draw() {
	   w.walk();
	   w.display();
	  } 
}
