package tree;
import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;
import processing.core.PVector;

public class Stick extends EntityT {
	PVector velocity;
	List<Stick> sticks;
	boolean parentStick;
	float vampireLife ;
	float life;

	public Stick(final PApplet parent, PVector location, float width, float life, PVector velocity){
		super(parent, null, location, width, width);
		sticks = new ArrayList<Stick>();
		this.life = life;	
		this.velocity = velocity;
		parentStick = false;
	}

	public Stick(final PApplet parent, PVector location, int width, float life){
		super(parent, null, location, width, width);
		sticks = new ArrayList<Stick>();
		this.life = life;	
		this.velocity = new PVector(0,(float)0.05,0);
		parentStick = true;
		vampireLife = 100;
	}

	public void display(){
		if(life > 0){
			parent.noStroke();
			parent.fill((parentStick?150:75)-life,life);
			float wid = width *(life/255);
			parent.ellipse(location.x, location.y, wid, wid);
			computeNewData();
		}
		for (Stick stick : sticks) {
			stick.display();
		}
	}
	
	private void computeNewData() {
		location.add(velocity);		
		life -=(float)0.15;
		//add map instead
		velocity.y -= ( parent.random(-(200/((parentStick)?2:1)),200) /50000);
		float xrand = (parentStick)?200:350;
		velocity.x += ((velocity.x >1)||(velocity.x <-1))? 0 :( parent.random(-xrand,xrand) /10000);
		if(parent.random(parentStick?300:115)> life && parent.random(10000) < (parentStick?255:150)  ) addstick();
	}

	private void addstick() {
		float ratiolife = (float) ((parentStick)?0.50:0.80);
		float ratioWidth = (float) ((parentStick)?0.75:0.9);
		PVector newLocation =  new PVector();
		newLocation= location.get();
		//add map instead
		PVector newVelocity = new PVector(velocity.x+(parent.random(-1,1)*(float)0.3), velocity.y+(parent.random(-1,1)*(float)0.1),0);
		sticks.add(new Stick(parent, newLocation, width*(float)ratioWidth, life*(float)ratiolife, newVelocity));
		vampireLife -= 1.2;
		float vamprisedLife = (float) life*(vampireLife/100);
		life =(parentStick)? vamprisedLife :life *(float)0.97;
	}

}
