package tree;


import java.util.Random;


public class ColorT {

	float r;
	float g;
	float b;
	
	public ColorT() {
		Random generator = new Random();
		this.r =  generator.nextFloat()*255;
		this.g = generator.nextFloat()*255;
		this.b = generator.nextFloat()*255;
	}

	public float getR() {
		return r;
	}

	public float getG() {
		return g;
	}

	public float getB() {
		return b;
	}
}
