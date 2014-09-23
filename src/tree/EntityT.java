package tree;
import processing.core.PApplet;
import processing.core.PVector;

public class EntityT {

	final PApplet p;
	final ColorT color;
	PVector location;
	float width;
	float height;

	public EntityT(PApplet p, ColorT color, PVector location, float width, float height) {
		super();
		this.p = p;
		this.color = color;
		this.location = location.get();
		this.width = width;
		this.height = height;
	}

	public ColorT getColor() {
		return color;
	}

}
