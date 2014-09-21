package nc.oscillation.asteroids;

import processing.core.PApplet;

public class Asteroids extends PApplet {

	Ship ship;
	boolean radd;
	boolean rsub;

	public void setup() {
		size(800, 800);
		frameRate(100);
		radd = false;
		rsub = false;
		ship = new Ship(this, 10);
	}

	public void draw() {
		background(255);

		if (radd) {
			ship.addrotate();
		}
		if (rsub) {
			ship.subrotate();
		}

		ship.update();
		ship.checkEdge();
		ship.display();

	}

	@Override
	public void keyPressed() {
		if (keyCode == RIGHT) {
			radd = true;
		}
		if (keyCode == LEFT) {
			rsub = true;
		}
		if (key == 'z' || key == 'Z') {
			ship.startBoost();
		}
	}

	@Override
	public void keyReleased() {
		if (keyCode == RIGHT) {
			radd = false;
		}
		if (keyCode == LEFT) {
			rsub = false;
		}
		if (key == 'z' || key == 'Z') {
			ship.stopBoost();
		}
	}

}
