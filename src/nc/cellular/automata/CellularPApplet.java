package nc.cellular.automata;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class CellularPApplet extends PApplet {
	
	List<Boolean> cells;
	int size;
	
	public void setup() {
		size(810, 800);
		size = 10;
		cells = new ArrayList<Boolean>();
		for (int i = 0; i < width/size; i++) {
			if(i == (int)(width/size/2)){
				cells.add(true);
			}else{
				cells.add(false);
			}
		}
		
	}
	
	public void draw() {
		background(0);

		for (int j = 0; j < cells.size(); j++) {
			if(cells.get(j)){
				fill(255);
			}else{
				fill(0);
			}
			stroke(255);
			rect(j*10,height/2,10,10);
		}
		
		
	}
}