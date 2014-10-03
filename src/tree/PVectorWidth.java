package tree;

import java.util.UUID;

import processing.core.PVector;

public class PVectorWidth extends PVector {
	private final float wid;
	private final float grey;
	private final float alpha;
	private final UUID id;

	public PVectorWidth(final PVector vector, final float wid,final float grey,final float alpha, UUID id) {
		super(vector.x,vector.y,vector.z);
		this.wid = wid;
		this.grey = grey;
		this.alpha = alpha;
		this.id = id;
	}
	
	public UUID getId() {
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
