package nc.particle.shatter;

import nc.Mover;
import nc.particle.Particle;
import nc.particle.ParticleSystem;
import processing.core.PApplet;
import processing.core.PVector;

public class ShatterShape extends PApplet {

	ShapeParticleSystem system;
	final int side = 800;

	public void setup() {
		size(side, side);
		system = new ShapeParticleSystem(this, new PVector(width / 2, height/ 2),(p,location) ->new SquareParticle(p, location), 100);
	}

	public void draw() {
		background(255);
		system.update();
		system.run();
	}
	
	@Override
	public void mouseClicked() {
		system.shatter(new PVector(mouseX, mouseY));
	}
	
	@Override
	public void keyPressed() {
		system = new ShapeParticleSystem(this, new PVector(width / 2, height/ 2),(p,location) ->new SquareParticle(p, location), 100);
	}
}
