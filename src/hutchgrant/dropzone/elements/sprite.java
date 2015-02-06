package hutchgrant.dropzone.elements;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

public class sprite extends Shape{
	
	Bitmap image;
	public boolean imgAvail = false;
	
	public sprite(){
		super();
	}
	
	public sprite(int imageID, Coord cord, float width, float height){
		super(new Coord(cord.x, cord.y), width, height);
		this.image = BitmapFactory.decodeResource(context.getResources(),imageID);
		this.loc = new Coord(cord.x,cord.y);
		imgAvail = true;
	}
	public sprite(Coord cord, float width, float height){
		super(new Coord(cord.x, cord.y), width, height);
		this.loc = new Coord(cord.x, cord.y);
	}
	
	public void draw(){

		super.draw();
		if(imgAvail){
			canvas.drawBitmap(image, loc.x, loc.y, paint);
		}
	}
	
	public void draw(float x, float y){
		x = this.loc.x + x;
		y = this.loc.y + y;
		if(imgAvail){
				canvas.drawBitmap(image, x, y, paint);
		}
	}
	public static Bitmap loadBitMap(int i){
		return BitmapFactory.decodeResource(context.getResources(), i);
	}
}
