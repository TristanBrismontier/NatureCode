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
	float rX;
	float rY;
	int side = 800;
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
		p++;
		
		 int count=0;
		 background(255);
		 spotLight(255, 0, 0, width/2, height/8, 400, 0, 0, -1, PI/2, 2);
//		  camera(mouseX*2,mouseY, (height/2) / tan(PI/6), width/2, height/3, 0, 0, 1, 0);
		  translate(width/2, height, -200);
		  rotateY(radians(p++));
			background(255);
			noStroke();
			for(Entry<UUID,List<PVectorWidth>> entry : buleMap.entrySet()) {
			   for (PVectorWidth unit : entry.getValue()) {
				   count++;
					pushMatrix();
					translate(unit.x, unit.y,unit.z);
					sphere(unit.getWid());
					popMatrix();
			}
			}
			System.out.println(count);
	}

	public void mousePressed() { 
		initStick();
	}

	private void initStick(){
		compute = true;
		buleMap = new HashMap<UUID, List<PVectorWidth>>();	
		stick = new Stick(this, new PVector(0, 0), 50,80);
		background(255);
	}
	
    public static void main(String args[])
    {
      PApplet.main(new String[] { Mysktech.class.getName() });
    }

}
