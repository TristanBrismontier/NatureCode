import processing.core.PApplet;

public class NoiseP extends PApplet {
 
	float a = 0;
	float b = 0;
	
	public void setup() {
	    size(1000,300);
	    background(255);
	}
  public void draw() {
	  loadPixels();
	  float xoff = 0+a;
	  for (int i = 0; i <width; i++) {		
		  float yoff= 0+b;
		  for (int j = 0; j < height; j++) {
			pixels[i+j*width] = color(map(noise(xoff,yoff),0,1,0,255));
			yoff+=0.01;
		 }
		  xoff+=0.01;
	  }
	 updatePixels(); 
	 a+=random(-1, 1)/10;
	 b+=random(-1, 1)/10;
  } 
}

