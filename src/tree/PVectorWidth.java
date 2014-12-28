package tree;

import processing.core.PVector;

public class PVectorWidth extends PVector {
	private final float wid;
	private final float grey;
	private final float alpha;
	private final float id;

	public PVectorWidth(final PVector vector, final float wid,final float grey,final float alpha, float id) {
		super(vector.x,vector.y,vector.z);
		this.wid = wid;
		this.grey = grey;
		this.alpha = alpha;
		this.id = id;
	}
	
	public float getId() {
		return id;
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
