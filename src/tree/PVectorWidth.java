package tree;

import processing.core.PVector;

public class PVectorWidth extends PVector {
	private final float wid;
	private final float grey;
	private final float alpha;
	
	public PVectorWidth(final PVector vector, final float wid,final float grey,final float alpha) {
		super(vector.x,vector.y,vector.z);
		this.wid = wid;
		this.grey = grey;
		this.alpha = alpha;
	}
	
	public float getGrey() {
		return grey;
	}

	public float getAlpha() {
		return alpha;
	}

	public float getWid(){
		return wid;
	}
	
}
