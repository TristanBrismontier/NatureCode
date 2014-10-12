package tree;
import java.util.UUID;

import processing.core.PApplet;
import processing.core.PVector;

public class EntityT {

	final PApplet p;
	PVector location;
	float width;
	float height;
	final float id;

	public EntityT(PApplet p, PVector location, float width, float height,final float id) {
		super();
		this.p = p;
		this.id = id;
		this.location = location.get();
		this.width = width;
		this.height = height;
	}


}
