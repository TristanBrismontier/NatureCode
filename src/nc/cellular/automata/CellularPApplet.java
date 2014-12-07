package nc.cellular.automata;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import processing.core.PApplet;

public class CellularPApplet extends PApplet {

	
	List<List<Boolean>> cells;
	int size;
	
	public void setup() {
		size(810, 800);
		size = 20;
//		frameRate(2);
		initCells();
	}

	private void initCells() {
		cells = new ArrayList<>();
		for (int i = 0; i < height / size; i++) {
			initCollumn();
		}
	}

	private void initCollumn() {
		List<Boolean> first = new ArrayList<>();
		Random ran = new Random();
		for (int i = 0; i < width / size; i++) {
			first.add(ran.nextBoolean());
		}
		cells.add(first);
	}

	public void draw() {
		if(cells.size()*size>height){
			return;
		}
		System.out.println(frameCount);
		for (int i = 0; i < cells.size(); i++) {
			List<Boolean> currentCells = cells.get(i);
			for (int j = 0; j < currentCells.size(); j++) {
				Boolean current = currentCells.get(j);
				int liveNeightboor = computeLive(i,j);
				if (current) {
					fill(255);
				} else {
					fill(0);
				}
				noStroke();
				rect(j * size, i * size, size, size);
				currentCells.set(j, computeCell(current,liveNeightboor));
			}
		}
	}
	

	private Boolean computeCell(Boolean current, int liveNeightboor) {
		if(current){
			if(liveNeightboor >=4 || liveNeightboor <= 1){
				return false;
			} else {
				return true;
			}
		}else{
			if(liveNeightboor == 3){
				return true;
			}else{
				return false;
			}
		}
		
	}

	private int computeLive(int i, int j) {
		int liveNeightboor = 0;
		if (i - 1 > 0) {
			liveNeightboor += computeLiveCell(j, cells.get(i-1), true);
		}else{
			liveNeightboor +=5;
		}
		if (i + 1 < cells.size()) {
			liveNeightboor += computeLiveCell(j, cells.get(i+1), true);
		}else{
			liveNeightboor +=5;
		}
		liveNeightboor += computeLiveCell(j, cells.get(i), false);
		
		return liveNeightboor;
	}

	private int computeLiveCell(int j, List<Boolean> currentCells, boolean computeCurrent) {
		int liveNeightboor = 0;
		if (j - 1 > 0) {
			liveNeightboor += (currentCells.get(j - 1))?1:0;
		}else{
			liveNeightboor +=5;
		}
		if (j + 1 < currentCells.size()) {
			liveNeightboor += (currentCells.get(j + 1))?1:0;
		}else{
			liveNeightboor +=5;
		}
		
		if(computeCurrent){
			liveNeightboor +=currentCells.get(j)?1:0;
		}
		return liveNeightboor;
	}

	private Boolean computeRule(boolean previous, boolean current, boolean next) {
		return false;
	}
	
	@Override
	public void keyPressed() {
		initCells();
	}
	
	
}