package nc.particle;

import processing.core.PApplet;
import processing.core.PVector;
import nc.particle.Particle;

public interface ParticuleBuilder {
	Particle build(PApplet p, PVector location);
}
