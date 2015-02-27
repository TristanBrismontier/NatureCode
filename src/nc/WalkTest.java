package nc;
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
//	   
//		float size = 5*5;
//		fill(255,0,0);
//		ellipse(200, 200, size, size);
//		rotate(0);
//		fill(0,255,0,175);
//		arc(200, 200,   size,  size, 0 ,HALF_PI);
//		fill(0,0,255,175);
//		arc(200, 200,   size,  size,PI ,PI+HALF_PI);
	  } 
}
