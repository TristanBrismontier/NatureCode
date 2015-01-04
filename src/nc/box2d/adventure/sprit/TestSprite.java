package nc.box2d.adventure.sprit;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PImage;

public class TestSprite extends PApplet {

	int size = 1000;
	PImage finn;
	int x1 = 25;
	int x2 = 75;
	int y1 = 0;
	int y2 = 100;
	boolean shift = true;
	int indexSprite =0;
	int index = 0;
	List<Sprite> sprite = new ArrayList<Sprite>();
	
	public void setup(){
		size((int)(size*1.5f),size);
		finn = loadImage("finnsprit.png");
		sprite.add(new Sprite(21,71,0,100));
		sprite.add(new Sprite(73,155,0,100));
		sprite.add(new Sprite(158,217,0,100));
		sprite.add(new Sprite(223,298,0,100));
		sprite.add(new Sprite(302,361,0,100));
	}
	
	public void draw(){
		background(0);
		fill(100,100,255);
		stroke(255);
		rect(width/2, height/2, 50, 100);
		if(frameCount%8 ==0){
			increIndex();
		}
		Sprite sp = sprite.get(index);
		if(shift){
			image(finn, width/2, height/2, 50, 100, sp.a, sp.c,sp.b, sp.d);
		}else{
			image(finn, width/2, height/2, 50, 100, sp.b, sp.c, sp.a, sp.d);
		}
		
		
	}
	 
	private void increIndex() {
		if(index >= sprite.size()-1){
			index=0;
		}else{
			index++;
		}
			
	}

	@Override
	public void keyPressed() {
		if (keyCode == RIGHT) {
			if(shift){
				x1++;
			}else{
				x2++;
			}
		}
		if (keyCode == LEFT) {
			if(shift){
				x1--;
			}else{
				x2--;
			}
		}
		
		if (keyCode == UP) {
			if(shift){
				y1++;
			}else{
				y2++;
			}
		}
		if (keyCode == DOWN) {
			if(shift){
				y1--;
			}else{
				y2--;
			}
		}
		
		if( key == ' '){
			shift = !shift;
		}
		
		if( key == 'a'){
			System.out.println("new Sprite("+x1+","+x2+","+y1+","+y2+")");
		}
	}
}


