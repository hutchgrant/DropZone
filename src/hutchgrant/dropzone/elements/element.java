package hutchgrant.dropzone.elements;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;

public class element extends animSprite {
	
	public int elementType = 0;
	public int bgElementPos = 0;
	public element(){
		
	}
	
	public element(Coord newPos, int width, int height){
		this.loc = new Coord(newPos.x, newPos.y);
		this.size = new Coord(width, height);
		this.imgAvail = false;
	}
	
	public element(int imgID, Coord newPos, int framecount, Coord newSize){
		super(imgID, newPos, framecount, newSize);
	}
	
	public element(int imgID, Coord newPos, int framecount, int framerate, Coord newsize){
		super(imgID, newPos, framecount,framerate, newsize);
	}
	
	public void draw(){
		super.draw();
		if (!imgAvail){
		paint.setColor(this.color);
		canvas.drawRect(this.loc.x, this.loc.y, this.loc.x+size.x, this.loc.y+size.y, paint);
		}
	}
	
	public void draw(float offSetX, float offSetY){
		super.draw(offSetX, offSetY);
		if (!imgAvail) {
			paint.setColor(this.color);
			offSetX = offSetX + this.loc.x;
			offSetY = offSetY + this.loc.y;
			canvas.drawRect(offSetX, offSetY, offSetX+size.x, offSetY+size.y, paint);
		}
	}

	public void setBgElementPos(int i) {
		// TODO Auto-generated method stub
		this.bgElementPos = i;
	}
	
	public void flip()
	{
	    Matrix m = new Matrix();
	    m.preScale(-1, 1);
	    Bitmap src = this.spriteSheet;
	    Bitmap dst = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, false);
	    dst.setDensity(DisplayMetrics.DENSITY_DEFAULT);
	    this.spriteSheet = dst;
	}
	
}
