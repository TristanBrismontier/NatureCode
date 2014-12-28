package tree;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import processing.core.PApplet;
import processing.core.PVector;



public class Mysktech extends PApplet {

	Stick stick;
	int p=0;
	float ratio = 5;
	float rX;
	float rY;
	final int side = 1500;
	
	boolean compute = true;
	Map<Float,List<PVectorWidth>> buleMap = new HashMap<Float, List<PVectorWidth>>();	
	
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
					PVectorWidth lastVector = listbule.get(listbule.size()-1);
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
		background(255);
		noStroke();
		spotLight(255, 0, 0, width/2, height/8, 400, 0, 0, -1, PI/2, 2);
		translate(width/2, height+20, -200);
		rotateY(radians(p++));
		for(Entry<Float,List<PVectorWidth>> entry : buleMap.entrySet()) {
			PVectorWidth last =null;
			Iterator<PVectorWidth> uniterator = entry.getValue().iterator();
			while (uniterator.hasNext()) {
				 PVectorWidth unit = uniterator.next();
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
		buleMap = new HashMap<Float, List<PVectorWidth>>();	
		stick = new Stick(this, new PVector(0, 0), 50,180);
		background(255);
	}
}