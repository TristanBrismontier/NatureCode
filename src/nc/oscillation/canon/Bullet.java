package nc.oscillation.canon;

import processing.core.PApplet;
import processing.core.PVector;
import nc.Mover;

public class Bullet extends Mover {
	
	float angle;
	float aVelo;

	public Bullet(final PApplet p, final float m, final PVector pos, final PVector initialVelo) {
		super(p, m, pos);
		this.velocity = initialVelo;
		this.absCine = 0.8f;
		angle = 0;
		aVelo = 0;
	}
	
	@Override
	public void update() {
		aVelo = p.constrain((velocity.x) / 10, -1f, 1f);
		angle += aVelo;
		super.update();
	}

	
	@Override
	public void display() {
		p.pushMatrix();
		p.translate(location.x, location.y);
		
		p.stroke(0);
		p.fill(0, 0, 0, 125);
		p.ellipse(0, 0, mass*5, mass*5);
		p.rotate(angle);
		p.arc(0, 0,  mass*5, mass*5, 0 ,p.HALF_PI);
			
		p.popMatrix();
	}
	
	

}
