package nc.toxiclibs.cluster;

import processing.core.PApplet;
import processing.core.PImage;
import toxi.geom.Vec2D;

public class Sprite extends Node {
	
	PImage sprite;

	public Sprite(PApplet p, Vec2D arg0, String spriteName) {
		super(p, arg0);
		partOfShape = false;
		sprite = p.loadImage(spriteName);
	}

	public PImage getSprite() {
		return sprite;
	}

}
