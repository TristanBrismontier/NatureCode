package nc.box2d.adventure;

import nc.box2d.Border;
import nc.box2d.shiffman.box2d.Box2DProcessing;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.contacts.Contact;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class AdventureGameApplet extends PApplet {

	int size = 1000;
	Box2DProcessing box2D;
	Border border;
	Character charac;
	boolean rig = false;
	boolean lef = false;
	boolean jump = false;
	boolean attack = false;
	PImage field;

	public void setup() {
		size((int) (size * 1.6f), size);
		box2D = new Box2DProcessing(this);
		box2D.createWorld(new Vec2(0.0f, -50.0f));
		box2D.listenForCollisions();
		border = new Border(this, box2D);
		charac = new Character(this, box2D, new PVector(width / 2,
				height / 2), 50, 100, true);
		field = loadImage("field.png");
	}

	public void draw() {
		image(field, 0, 0);
		charac.monitor(rig, lef, jump, attack);
		if (attack)
			attack = false;
		box2D.step();
		charac.update();
//		border.display();
		charac.display();
	}

	@Override
	public void keyPressed() {
		if (keyCode == RIGHT) {
			rig = true;
		}
		if (keyCode == LEFT) {
			lef = true;
		}
		if (key == 'z') {
			jump = true;
		}
		if (key == ' ') {
			attack = true;
		}
		if (key == 'e') {
			speak();
		}
		
	}

	private void speak() {
		float x = charac.getX();
		if(x<250){
			System.out.println("Sage");
		}
		if(x>900 && x<1200){
			System.out.println("Bernard");
		}
		if(x> 1450){
			System.out.println("La Porte");
		}
		
	}

	@Override
	public void keyReleased() {
		if (keyCode == RIGHT) {
			rig = false;
		}
		if (keyCode == LEFT) {
			lef = false;
		}
		if (key == 'z') {
			jump = false;
		}
		if (key == ' ') {
			attack = false;
		}
	}

	public void beginContact(Contact cp) {
		Fixture f1 = cp.getFixtureA();
		Fixture f2 = cp.getFixtureB();

		Body b1 = f1.getBody();
		Body b2 = f2.getBody();
		if (b1 == null || b2 == null) {
			return;
		}
		Character char1 = (Character) b1.getUserData();
		Character char2 = (Character) b2.getUserData();
		if (char1 != null && char2 != null) {
			if (char1.isInvu() && char1.isPlayer()) {

				char2.destroy();
//				charac.remove(char2);
			}
			if (char2.isInvu() && char2.isPlayer()) {
				char1.destroy();
//				charac.remove(char1);
			}
		}
	}
}
