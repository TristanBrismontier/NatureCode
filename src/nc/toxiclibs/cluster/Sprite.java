package nc.toxiclibs.cluster;

import processing.core.PApplet;
import processing.core.PImage;
import toxi.geom.Vec2D;

public class Sprite extends Node {
	
	final PImage sprite;

	public Sprite(final PApplet p,final Vec2D arg0,final String spriteName) {
		super(p, arg0);
		partOfShape = false;
		this.sprite = p.loadImage(spriteName);
	}

	public PImage getSprite() {
		return sprite;
	}

}
