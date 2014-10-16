package nc;
import processing.core.*;

public class WhiteWalker {

	final PApplet p;
	float x,y;
	
	float tx,ty;
	
	WhiteWalker(final PApplet parent){
		this.p = parent;
		tx=0;
		ty=1000; 
	}
	
	void display(){
		p.stroke(0);
		p.point(x, y);	
		
	
	}
	
	void walk(){
		x = p.map(p.noise(tx), 0, 1, 0, p.width); 
		y = p.map(p.noise(ty), 0, 1, 0, p.height); 
	
		tx+=0.01;
		ty+=0.01;
		
	}
	
	PVector getVectorPosition(){
		return new PVector(x, y);
	}
}
