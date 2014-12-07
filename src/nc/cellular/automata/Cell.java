package nc.cellular.automata;

import processing.core.PApplet;

public class Cell {

	boolean live;
	int liveNeigbour;
	
	public Cell(boolean live, int liveNeigbour) {
		super();
		this.live = live;
		this.liveNeigbour = liveNeigbour;
	}
	public boolean isALive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public int getLiveNeigbour() {
		return liveNeigbour;
	}
	public void setLiveNeigbour(int liveNeigbour) {
		this.liveNeigbour = liveNeigbour;
	}
	
	
}
