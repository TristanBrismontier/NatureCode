package tree;




import processing.core.PApplet;
import processing.core.PVector;


public class EntityT {

	final PApplet parent;
	final ColorT color;
	PVector location;
	float width;
	float height;


	public EntityT(PApplet parent, ColorT color, PVector location, float width,
			float height) {
		super();
		this.parent = parent;
		this.color = color;
		this.location = location;
		this.width = width;
		this.height = height;
	}

	public PApplet getParent() {
		return parent;
	}
	public ColorT getColor() {
		return color;
	}

}
