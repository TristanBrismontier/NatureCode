package nc.box2d.adventure;

import nc.box2d.Border;
import nc.box2d.Cell;
import nc.box2d.shiffman.box2d.Box2DProcessing;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.contacts.Contact;

import processing.core.PApplet;
import processing.core.PVector;

public class AdventureGameApplet extends PApplet {

	int size = 1100;
	Box2DProcessing box2D;
	Border border;
	Character charac;
	boolean rig=false;
	boolean lef=false;
	boolean jump=false; 
	boolean attack = false;
	
	public void setup(){
		size((int)(size*1.5f),size);
		box2D = new Box2DProcessing(this);
		box2D.createWorld(new Vec2(0.0f, -50.0f));
		box2D.listenForCollisions();
		border = new Border(this, box2D);
		charac = new Character(this, box2D, new PVector (width/2, height/2), 50, 100);
	}
	
	public void draw(){
		background(0);
		charac.monitor(rig, lef, jump, attack);
		if(attack)attack=false;
		box2D.step();
		charac.update();
		border.display();
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
		if (key == 'z' || key == 'Z')  {
			jump = true;
		}
		if( key == ' '){
			attack = true;
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
		if (key == 'z' || key == 'Z') {
			jump = false;
		}
		if( key == ' '){
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
		Cell cell1 = (Cell) b1.getUserData();
		Cell cell2 = (Cell) b2.getUserData();
		if (cell1 != null && cell2 != null) {
			cell1.contact(cell2);
			cell2.contact(cell1);
		}
	}
}
