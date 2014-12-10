package nc.cellular.automata;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import processing.core.PApplet;

public class CellularPApplet extends PApplet {

	List<List<Cell>> cells;
	int size;
	boolean simulate = false;

	public void setup() {
		size(500, 500);
		size = 10;
		frameRate(5);
		initCells();
	}

	private void initCells() {
		cells = new ArrayList<>();
		for (int i = 0; i < height / size; i++) {
			initCollumn();
		}
	}

	private void initCollumn() {
		List<Cell> first = new ArrayList<>();
		Random ran = new Random();
		for (int i = 0; i < width / size; i++) {
			first.add(new Cell(false, 0));
		}
		cells.add(first);
	}

	public void draw() {

		for (int i = 0; i < cells.size(); i++) {
			List<Cell> currentCells = cells.get(i);
			for (int j = 0; j < currentCells.size(); j++) {
				Cell current = currentCells.get(j);
				current.setLiveNeigbour(computeLive(i, j));
				if (current.isALive()) {
					fill(255);
				} else {
					fill(0);
				}
				noStroke();
				if(!simulate){
					stroke(255);
				}
				rect(j * size, i * size, size, size);
			}
		}
		if (simulate) {
			for (List<Cell> cellss : cells) {
				for (Cell cell : cellss) {
					cell.setLive(computeCell(cell.isALive(), cell.liveNeigbour));
				}
			}
		}

	}

	@Override
	public void mouseClicked() {
		super.mouseClicked();
		Cell cell = cells.get((int)(mouseY/size)).get((int)(mouseX/size));
		cell.setLive(!cell.isALive());

	}

	private Boolean computeCell(Boolean current, int liveNeightboor) {
		if (current) {
			if (liveNeightboor >= 4 || liveNeightboor <= 1) {
				return false;
			} else {
				return true;
			}
		} else {
			if (liveNeightboor == 3) {
				return true;
			} 
		}
		return false;
	}

	private int computeLive(int i, int j) {
		int liveNeightboor = 0;
		if (i - 1 > 0) {
			liveNeightboor += computeLiveCell(j, cells.get(i - 1), true);
		} else {
			liveNeightboor += 5;
		}
		if (i + 1 < cells.size()) {
			liveNeightboor += computeLiveCell(j, cells.get(i + 1), true);
		} else {
			liveNeightboor += 5;
		}
		liveNeightboor += computeLiveCell(j, cells.get(i), false);

		return liveNeightboor;
	}

	private int computeLiveCell(int j, List<Cell> currentCells,
			boolean computeCurrent) {
		int liveNeightboor = 0;
		if (j - 1 > 0) {
			liveNeightboor += (currentCells.get(j - 1).isALive()) ? 1 : 0;
		} else {
			liveNeightboor += 5;
		}
		if (j + 1 < currentCells.size()) {
			liveNeightboor += (currentCells.get(j + 1).isALive()) ? 1 : 0;
		} else {
			liveNeightboor += 5;
		}

		if (computeCurrent) {
			liveNeightboor += currentCells.get(j).isALive() ? 1 : 0;
		}
		return liveNeightboor;
	}

	private Boolean computeRule(boolean previous, boolean current, boolean next) {
		return false;
	}

	@Override
	public void keyPressed() {
		simulate = !simulate;
	}

}