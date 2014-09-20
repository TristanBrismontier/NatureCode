package tree;
import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;
import processing.core.PVector;

public class Stick extends EntityT {
	PVector velocity;
	List<Stick> sticks;
	boolean pStick;
	float vampireLife ;
	float life;

	public Stick(final PApplet p, PVector location, float width, float life, PVector velocity){
		super(p, null, location, width, width);
		sticks = new ArrayList<Stick>();
		this.life = life;	
		this.velocity = velocity;
		pStick = false;
	}

	public Stick(final PApplet p, PVector location, int width, float life){
		super(p, null, location, width, width);
		sticks = new ArrayList<Stick>();
		this.life = life;	
		this.velocity = new PVector(0,(float)0.05,0);
		pStick = true;
		vampireLife = 100;
	}

	public void display(){
		if(life > 0){
			p.noStroke();
			p.fill((pStick?150:75)-life,life);
			float wid = width *(life/255);
			p.pushMatrix();
			p.translate(location.x,	location.y,location.z);
			
				p.sphere(wid);
			
			p.popMatrix();
			
			
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
		velocity.y -= ( p.random(-(200/((pStick)?2:1)),200) /50000);
		float xrand = (pStick)?200:350;
		velocity.x += ((velocity.x >1)||(velocity.x <-1))? 0 :( p.random(-xrand,xrand) /10000);
		if(p.random(pStick?300:115)> life && p.random(10000) < (pStick?255:150)  ) addstick();
	}

	private void addstick() {
		float ratiolife = (float) ((pStick)?0.50:0.80);
		float ratioWidth = (float) ((pStick)?0.75:0.9);
		PVector newLocation =  new PVector();
		newLocation= location.get();
		//add map instead
		PVector newVelocity = new PVector(velocity.x+(p.random(-1,1)*(float)0.3), velocity.y+(p.random(-1,1)*(float)0.1),0);
		sticks.add(new Stick(p, newLocation, width*(float)ratioWidth, life*(float)ratiolife, newVelocity));
		vampireLife -= 1.2;
		float vamprisedLife = (float) life*(vampireLife/100);
		life =(pStick)? vamprisedLife :life *(float)0.97;
	}

}
