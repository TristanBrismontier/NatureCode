package nc.toxiclibs;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletPhysics2D;

public class Cloth {
	PApplet p;
	List<StringParticle> clothParticles;
	public Cloth(PApplet p,VerletPhysics2D physics,Vec2D pos,float nbPY,float nbPX,float apart) {
		this.p = p;
		clothParticles = new ArrayList<StringParticle>();
		Vec2D position = pos.copy();
		for (int i = 0; i < nbPY; i++) {
			StringParticle string = new StringParticle(p, physics, position, nbPX, apart);
			clothParticles.add(string);
			position.y += apart;			
		}
		
		List<StringParticle> tempString = new ArrayList<StringParticle>();
		for (int i = 0; i <= nbPX; i++) {
			StringParticle string = new StringParticle(p);	
			for (StringParticle stringParticle : clothParticles) {
				 string.addParticle(physics, stringParticle.getStringElments().get(i), apart);
			}
			tempString.add(string);
		}
		tempString.forEach(t -> clothParticles.add(t));
	}
	
	public void attachStartEnd(VerletPhysics2D physics, Particle pStart, Particle pEnd) {
		clothParticles.get(0).attachStartEnd(physics, pStart, pEnd);
	}
	
	public void display(){
		clothParticles.forEach(cp -> cp.display());
	}
}
