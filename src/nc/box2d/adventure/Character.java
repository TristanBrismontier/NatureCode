package nc.box2d.adventure;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import nc.box2d.Body2D;
import nc.box2d.adventure.sprit.Sprite;
import nc.box2d.shiffman.box2d.Box2DProcessing;

public class Character extends Body2D {

	int jumpCount = 0;
	int invuCount = 0;
	int coldDown = 0;
	boolean invu = false;
	boolean right = true;
	boolean move = false;
	final static int limitinvu = 22;
	final static int limitcoldDown = 6;
	final static int limitJumpCount = 20;
	int index = 0;

	List<Sprite> sprite = new ArrayList<Sprite>();
	PImage finn;

	public Character(PApplet p, Box2DProcessing box2d, PVector location,
			float whith, float height) {
		super(p, box2d, location, whith, height, BodyType.DYNAMIC);
		body.setFixedRotation(true);
		finn = p.loadImage("finnsprit.png");
		sprite.add(new Sprite(21, 71, 0, 100));
		sprite.add(new Sprite(73, 155, 0, 100));
		sprite.add(new Sprite(158, 217, 0, 100));
		sprite.add(new Sprite(223, 298, 0, 100));
		sprite.add(new Sprite(302, 361, 0, 100));
	}

	public void monitor(boolean rig, boolean lef, boolean jump, boolean attack) {
		Vec2 pos = this.body.getPosition();
		this.fd.setFriction(100.0f);
		if (rig) {
			this.fd.setFriction(0.2f);
			this.body.applyLinearImpulse(new Vec2(100, 0), pos, true);
		}
		if (lef) {
			this.fd.setFriction(0.2f);
			this.body.applyLinearImpulse(new Vec2(-100, 0), pos, true);
		}
		if (jump && jumpCount <= limitJumpCount) {
			this.fd.setFriction(0.2f);
			System.out.println("jump " + jumpCount++);
			this.body.applyLinearImpulse(new Vec2(0, 200), pos, true);
		}
		if ((coldDown == limitcoldDown) && attack) {
			coldDown = 0;
			invuCount = limitinvu;
		}
	}

	public void update() {
		if (invuCount > 0) {
			invuCount--;
			invu = true;
		} else if (coldDown < limitcoldDown) {
			coldDown++;
			invu = false;
		}
		float speedLimit = 40;
		Vec2 velo = this.body.getLinearVelocity();
		if (velo.y == 0)
			jumpCount = 0;
		Vec2 newVelo = new Vec2(p.constrain(velo.x, -speedLimit, speedLimit),
				p.constrain(velo.y, -speedLimit * 10, speedLimit));
		if(newVelo.x !=0){
			right = newVelo.x>0;
			move = true;
		}else{
			move =false;
		}
		this.body.setLinearVelocity(newVelo);

	}

	public void display() {
		Vec2 pos = box2d.getBodyPixelCoord(body);
		float a = body.getAngle();

		p.pushMatrix();

		p.translate(pos.x, pos.y);
		p.rotate(-a);
		if (invu) {
			p.fill(255, 0, 0);
		} else {
			p.fill(175);
		}
		p.stroke(255);
		p.rectMode(p.CENTER);
//		p.rect(0, 0, whith, height);

		Sprite sp = getgretSprite();
		p.imageMode(p.CENTER);
		if (right) {
			p.image(finn, 0, 0, 50, 100, sp.getA(), sp.getC(), sp.getB(), sp.getD());
		} else {
			p.image(finn, 0, 0, 50, 100, sp.getB(), sp.getC(), sp.getA(), sp.getD());
		}
		p.popMatrix();

	}

	private Sprite getgretSprite() {
		if(jumpCount >0){
			return new Sprite(87,11,182,275);
		}
		if(!move){
			return sprite.get(0);
		}
		if (p.frameCount % 8 == 0) {
			if (index >= sprite.size() - 1) {
				index = 0;
			} else {
				index++;
			}

		}
		return sprite.get(index);
	}
}
