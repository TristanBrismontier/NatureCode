package tree;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import processing.core.PApplet;
import processing.core.PVector;

public class Stick extends EntityT {
	final PVector velocity;
	final List<Stick> sticks;
	final boolean pStick;
	float vampireLife ;
	float life;

	public Stick(final PApplet p, PVector location, float width, float life, PVector velocity){
		super(p, location, width, width, UUID.randomUUID());
		sticks = new ArrayList<Stick>();
		this.life = life;	
		this.velocity = velocity.get();
		pStick = false;
	}

	public Stick(final PApplet p, PVector location, int width, float life){
		super(p, location, width, width, UUID.randomUUID());
		sticks = new ArrayList<Stick>();
		this.life = life;	
		this.velocity = new PVector(0,-0.3f,0);
		pStick = true;
		vampireLife = 100;
	}

	public List<PVectorWidth>  display(){
		final List<PVectorWidth> buleList = new ArrayList<PVectorWidth>();
		if(life > 0){
			final PVectorWidth self = new PVectorWidth(location, width *(life/255), 75-life, life, id);
			if(width *(life/255) >=1f){
				buleList.add(self);
			}
			computeNewData();
		}
		final List<Stick> stickToRemove = new ArrayList<Stick>();
		for (Stick stick : sticks) {
			List<PVectorWidth> childList = stick.display();
			if(childList.isEmpty()){
				stickToRemove.add(stick);
			}else{
				buleList.addAll(childList);
			}
		}
		sticks.removeAll(stickToRemove);
		return buleList;
	}
	
	private void computeNewData() {
		location.add(velocity);		
		life -=p.random(0.11f,0.05f);
		if(pStick){
			computeParentStick();
		}else{
			computeChildStick();
		}
	}

	private void computeChildStick() {
		velocity.y -= p.random(-2,2)/500;
		velocity.x += p.random(-3.5f,3.5f) /100;
		velocity.z += p.random(-3.5f,3.5f) /100;
		p.constrain(velocity.x, -0.95f, 0.95f);
		p.constrain(velocity.z, -0.95f, 0.95f);
		p.constrain(velocity.y, -9f, -0.05f);
		if(p.random(115)> life && percent(1.5f)) addstick();
	}

	private void computeParentStick() {
		velocity.y -=  p.random(-1,2) /500;
		velocity.x +=  p.random(-2,2) /100;
		velocity.z +=  p.random(-2,2) /100;
		p.constrain(velocity.x, -0.95f, 0.95f);
		p.constrain(velocity.z, -0.95f, 0.95f);
		System.out.println(life);
		if(p.random(255)> life && percent(3.5f) || life<25 && percent(50f)) addstick();
	}
	
	private boolean percent(final float chance){
		return  p.random(100) < chance;
	}

	private void addstick() {
		float ratiolife = (pStick)?0.65f:0.80f;
		float ratioWidth = (pStick)?0.75f:0.9f;
		//add map instead
		PVector newVelocity = new PVector(velocity.x+(p.random(-1,1)*0.3f), velocity.y+(p.random(-0.5f,3)*0.1f),velocity.z+(p.random(-1,1)*0.3f));
		sticks.add(new Stick(p, location, width*ratioWidth, life*ratiolife, newVelocity));
		vampireLife -= 1.2f;
		life =life *0.97f;
	}

}
