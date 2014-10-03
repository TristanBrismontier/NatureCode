package tree;
import java.util.UUID;

import processing.core.PApplet;
import processing.core.PVector;

public class EntityT {

	final PApplet p;
	final ColorT color;
	PVector location;
	float width;
	float height;
	final UUID id;

	public EntityT(PApplet p, ColorT color, PVector location, float width, float height,final UUID id) {
		super();
		this.p = p;
		this.id = id;
		this.color = color;
		this.location = location.get();
		this.width = width;
		this.height = height;
	}

	public ColorT getColor() {
		return color;
	}

}
