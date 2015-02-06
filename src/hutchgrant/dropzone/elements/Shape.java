package hutchgrant.dropzone.elements;
import hutchgrant.dropzone.core.settings;

import android.graphics.Color;
import android.graphics.Paint;

public class Shape extends settings {
	
	public Coord loc;
	public Coord size;
	public int color = 0;
	public static Paint paint = new Paint();
	
	public Shape(){
		super();
	}
	public Shape(Coord cord, float size){
		super();
		this.loc = new Coord(cord.x, cord.y);
		this.size = new Coord(size, size);
	}
	public Shape(Coord cord, float width, float height){
		super();
		this.loc = new Coord(cord.x, cord.y);
		this.size = new Coord (width, height);
	}
	
	public void move(Coord cord){
		this.loc = new Coord(cord.x, cord.y);
		
	}
	public void move(float posx, float posy){
		this.loc.x = posx;
		this.loc.y = posy;
		
	}
	
	public void Scale(float multiply){
		this.size.x = this.size.x * multiply;
		this.size.y = this.size.y * multiply;
		
	}
	public void draw(){
		paint.setColor(color);
	}
	
	public void setColor(int a, int r, int g, int b){
		color = Color.argb(a,r,g,b);
	}
	
	public int getColor(){
		return color;
	}
}
