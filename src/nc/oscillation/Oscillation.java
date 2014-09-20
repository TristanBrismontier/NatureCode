package nc.oscillation;

import processing.core.PApplet;
import processing.core.PVector;

public class Oscillation extends PApplet {
	
	final float size= 300;
	final float stickLenght = 50;
	final PVector firstLocation = new PVector(-stickLenght/2, 0);
	final PVector secondLocation = new PVector(stickLenght/2, 0);
	float angle = 0;
	float angleSpeed = 1;
	float angleAcc = 0.001f;
	float r;
	float eloigne;
	float s;
	boolean sens = true;
	
	float tr = 0;
	float tg = 1000;
	float tb = 100000;
	
	public void setup(){
		size((int)size, (int)size);
		background(255);
		s = 10;
		r = 0;
		smooth();
		eloigne = 0.00277f *2 *s;
		System.out.println(r+ " " +eloigne );
		frameRate(10000);
	}
	
	public void draw() {
		spiral();
		
	}
	
	private void spiral(){
		translate(size/2, size/2);
		float x = r * cos(radians(angle));
		float y = r * sin(radians(angle));
		
		fill(noisecolor(tr),noisecolor(tg),noisecolor(tb));
		noStroke();
		ellipse(x, y, s, s);
		
		if(r > 220 || r<0){
			sens = !sens;
		}
		
		if(sens){
			r += eloigne;
			angle++;
		}else{
			r -= eloigne;
			angle--;
		}
		tr += 0.001f;
		tg += 0.001f;
		tb += 0.001f;
	}
	
	private float noisecolor(float tr2) {
		return map(noise(tr2), 0, 1, 0, 255);
	}

	private void turnStick(){
		background(255);
		translate(size/2, size/2);
		rotate(radians(angle));
		line(firstLocation, secondLocation);
		ellipse(firstLocation,10);
		ellipse(secondLocation,10);
		angleSpeed += angleAcc;
		angle += angleSpeed;
	}
	private void ellipse(PVector v1, int i) {
		strokeWeight(2);
		fill(125);
		ellipse(v1.x, v1.y, 10, 10);
	}

	private void line(final PVector v1, final PVector v2){
		strokeWeight(2);
		line(v1.x, v1.y, v2.x, v2.y);
	}
}
