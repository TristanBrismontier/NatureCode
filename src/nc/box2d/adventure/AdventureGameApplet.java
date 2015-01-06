package nc.box2d.adventure;

import java.util.ArrayList;
import java.util.List;

import nc.box2d.Border;
import nc.box2d.shiffman.box2d.Box2DProcessing;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.contacts.Contact;

import processing.core.PApplet;
import processing.core.PVector;

public class AdventureGameApplet extends PApplet {

	int size = 800;
	Box2DProcessing box2D;
	Border border;
	List<Character> characs;
	boolean rig=false;
	boolean lef=false;
	boolean jump=false; 
	boolean attack = false;
	
	public void setup(){
		characs = new ArrayList<Character>();
		size((int)(size*1.5f),size);
		box2D = new Box2DProcessing(this);
		box2D.createWorld(new Vec2(0.0f, -50.0f));
		box2D.listenForCollisions();
		border = new Border(this, box2D);
		characs.add(new Character(this, box2D, new PVector (width/2, height/2), 50, 100, true));
		characs.add(new Character(this, box2D, new PVector (width/2, height/2), 50, 100, false));
		
	}
	
	public void draw(){
		background(0);
		characs.get(0).monitor(rig, lef, jump, attack);
		if(attack)attack=false;
		box2D.step();
		characs.forEach(c -> c.update());
		border.display();
		characs.forEach(c -> c.display());
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
		if (key == 'v' || key == 'V') {
			characs.add(new Character(this, box2D, new PVector (width/2, height/2), 50, 100, false));
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
		Character char1 = (Character) b1.getUserData();
		Character char2 = (Character) b2.getUserData();
		if (char1 != null && char2 != null) {
			if(char1.isInvu() && char1.isPlayer()){
				
				char2.destroy();
				characs.remove(char2);
			}
			if(char2.isInvu() && char2.isPlayer()){
				char1.destroy();
				characs.remove(char1);
			}
		}
	}
}
