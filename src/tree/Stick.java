package tree;
import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;
import processing.core.PVector;

public class Stick extends EntityT {
	final PVector velocity;
	final List<Stick> sticks;
	final boolean pStick;
	float vampireLife ;
	float life;

	public Stick(final PApplet p, PVector location, float width, float life, PVector velocity){
		super(p, null, location, width, width);
		sticks = new ArrayList<Stick>();
		this.life = life;	
		this.velocity = velocity.get();
		pStick = false;
	}

	public Stick(final PApplet p, PVector location, int width, float life){
		super(p, null, location, width, width);
		sticks = new ArrayList<Stick>();
		this.life = life;	
		this.velocity = new PVector(0,0.1f,0);
		pStick = true;
		vampireLife = 100;
	}

	public float display(){
		if(life > 0){
			p.noStroke();
			p.fill((pStick?150:75)-life,life);
			float wid = width *(life/255);
//			p.pushMatrix();
//			p.translate(location.x,	location.y,location.z);
//			
//				p.sphere(wid);
//			
//			p.popMatrix();
			p.ellipse(location.x, location.y, wid , wid);
			computeNewData();
		}
		final List<Stick> stickToRemove = new ArrayList<Stick>();
		for (Stick stick : sticks) {
			float lifeChild = stick.display();
			if(lifeChild<=0){
				stickToRemove.add(stick);
			}
		}
		sticks.removeAll(stickToRemove);
		return (sticks.isEmpty())?1:life;
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
		p.constrain(velocity.x, -0.95f, 0.95f);
		p.constrain(velocity.y, -9f, -0.05f);
		if(p.random(115)> life && percent(1.5f)) addstick();
	}

	private void computeParentStick() {
		velocity.y -=  p.random(-1,2) /500;
		velocity.x +=  p.random(-2,2) /100;
		p.constrain(velocity.x, -0.7f, 0.7f);
		if(p.random(300)> life && percent(2.5f)) addstick();
	}
	
	private boolean percent(final float chance){
		return  p.random(100) < chance;
	}

	private void addstick() {
		float ratiolife = (pStick)?0.50f:0.80f;
		float ratioWidth = (pStick)?0.75f:0.9f;
		//add map instead
		PVector newVelocity = new PVector(velocity.x+(p.random(-1,1)*0.3f), velocity.y+(p.random(-1,1)*0.1f),0);
		sticks.add(new Stick(p, location, width*ratioWidth, life*ratiolife, newVelocity));
		vampireLife -= 1.2f;
		float vamprisedLife = life*(vampireLife/100f);
		life =(pStick)? vamprisedLife :life *0.97f;
	}

}
