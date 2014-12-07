package nc.cellular.automata;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import processing.core.PApplet;

public class CellularPApplet extends PApplet {

	List<List<Boolean>> cells;
	int size;
	boolean[] ruleset = {false,true,false,true,true,false,true,false};
	
	public void setup() {
		size(810, 800);
		size = 2;
		initCells(false);
	}

	private void initCells(boolean randomRule) {
		if(randomRule){
			Random random = new Random();
			for (int i = 0; i < ruleset.length; i++) {
				ruleset[i] = random.nextBoolean();
			}
		}
		cells = new ArrayList<>();
		List<Boolean> first = new ArrayList<>();
		for (int i = 0; i < width / size; i++) {
			if (i == (int) (width / size / 2)) {
				first.add(true);
			} else {
				first.add(false);
			}
		}
		cells.add(first);
	}

	public void draw() {
		if(cells.size()*size>height){
			return;
		}
		System.out.println(frameCount);
		background(0);
		for (int i = 0; i < cells.size(); i++) {
			List<Boolean> currentCells = cells.get(i);
			for (int j = 0; j < currentCells.size(); j++) {
				Boolean current = currentCells.get(j);
				if (current) {
					fill(255);
				} else {
					fill(0);
				}
				noStroke();
				rect(j * size, i * size, size, size);
			}
		}

		List<Boolean> currentCells = cells.get(cells.size() - 1);
		List<Boolean> nextCells = new ArrayList<>();
		for (int j = 0; j < currentCells.size(); j++) {
			nextCells.add(applyRules(j, currentCells));
		}
		cells.add(nextCells);
	}

	private Boolean applyRules(int j, List<Boolean> currentCells) {
		Boolean next = false;
		Boolean previous = false;
		if (j - 1 > 0) {
			previous = currentCells.get(j - 1);
		}
		if (j + 1 < currentCells.size()) {
			next = currentCells.get(j + 1);
		}
		return computeRule(previous, currentCells.get(j), next);
	}

	private Boolean computeRule(boolean previous, boolean current, boolean next) {
		if      (previous && current && next) return ruleset[0];
	    else if (previous && current && !next) return ruleset[1];
	    else if (previous && !current && next) return ruleset[2];
	    else if (previous && !current && !next) return ruleset[3];
	    else if (!previous && current && next) return ruleset[4];
	    else if (!previous && current && !next) return ruleset[5];
	    else if (!previous && !current && next) return ruleset[6];
	    else if (!previous && !current && !next) return ruleset[7];
	    return false;
	}
	
	@Override
	public void keyPressed() {
		initCells(true);
	}
	
	
}