package nc.particle;

import processing.core.PApplet;
import processing.core.PVector;

public interface ParticleFactoryI {
	public Particle buildParticle(PApplet p,PVector location);
}
