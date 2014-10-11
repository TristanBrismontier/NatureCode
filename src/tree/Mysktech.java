package tree;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import processing.core.PApplet;
import processing.core.PVector;

import com.google.common.collect.Iterables;


public class Mysktech extends PApplet {

	Stick stick;
	int p=0;
	float ratio = 5;
	float rX;
	float rY;
	final int side = 800;
	boolean compute = true;
	Map<UUID,List<PVectorWidth>> buleMap = new HashMap<UUID, List<PVectorWidth>>();	
	
	public void setup() {
		size(side,side, P3D);
		initStick();
		smooth();
	}

	public void draw() {
		if(compute){
			List<PVectorWidth> buleListComputed = stick.display();
			for (PVectorWidth pVectorWidth : buleListComputed) {
				List<PVectorWidth> listbule = buleMap.get(pVectorWidth.getId());
				if(listbule == null){
					listbule = new ArrayList<PVectorWidth>();
					listbule.add(pVectorWidth);
					buleMap.put(pVectorWidth.getId(), listbule);
				}else{
					PVectorWidth lastVector = Iterables.getLast(listbule);
					if(abs(lastVector.dist(pVectorWidth))>=pVectorWidth.getWid()){
						listbule.add(pVectorWidth);
					}
				}
			}
					if (buleListComputed.isEmpty()) {
				System.out.println("finish");
				compute = false;
				
			}
		}
		
		ratio = (ratio>1)?ratio-0.005f:1;
		p++;
		background(0);
		noStroke();
		spotLight(255, 0, 0, width/2, height/8, 400, 0, 0, -1, PI/2, 2);
//		camera(mouseX*2,mouseY, (height/2) / tan(PI/6), width/2, height/3, 0, 0, 1, 0);
		translate(width/2, height+20, -200);
		rotateY(radians(p++));
		for(Entry<UUID,List<PVectorWidth>> entry : buleMap.entrySet()) {
			PVectorWidth last =null;
			 for (PVectorWidth unit : entry.getValue()) {
				if(last != null){
					pushMatrix();
					drawCylinder(9, last, unit);
					popMatrix();
				}
				last=unit;
			}
		}
	}

	public void mousePressed() { 
		initStick();
	}

	void drawCylinder(int sides,PVectorWidth last,PVectorWidth current)
	{
	    float angle = 360 / sides;
	    float r1 = last.getWid()/ratio;
	    float r2 = current.getWid()/ratio;
    
	 // draw body
	    beginShape(TRIANGLE_STRIP);
	    for (int i = 0; i < sides + 1; i++) {
	        float x1 = cos( radians( i * angle ) ) * r1;
	        float z1 = sin( radians( i * angle ) ) * r1;
	        float x2 = cos( radians( i * angle ) ) * r2;
	        float z2 = sin( radians( i * angle ) ) * r2;
	        vertex( last.x + x1, last.y, last.z+z1 );
	        vertex( current.x + x2, current.y, current.z+z2 );    
	    }
	    endShape(CLOSE);
	}
	private void initStick(){
		ratio = 5;
		compute = true;
		buleMap = new HashMap<UUID, List<PVectorWidth>>();	
		stick = new Stick(this, new PVector(0, 0), 50,180);
		background(255);
	}
	
    public static void main(String args[])
    {
      PApplet.main(new String[] { Mysktech.class.getName() });
    }

}
