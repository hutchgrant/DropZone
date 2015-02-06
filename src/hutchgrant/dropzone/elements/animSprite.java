package hutchgrant.dropzone.elements;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class animSprite extends sprite {
	
	protected Bitmap spriteSheet;
	protected Rect animRect;
	
	public int fps;
	public int numFrames;
	public int currentFrame;
	public long frameTimer;
	
	public int spriteHeight;
	public int spriteWidth;
	public int sheetWidth;
	public int sheetHeight;
	
	public animSprite(){
		
	}
	public animSprite(int spriteID, Coord pos, int framecount, Coord size){
		
		this.frameTimer = 0;
		this.currentFrame = 0;
		this.numFrames = framecount;
		
		this.loc = new Coord(pos.x, pos.y);
		this.size = new Coord(size.x, size.y);
		
		spriteSheet = BitmapFactory.decodeResource(context.getResources(), spriteID);
		this.imgAvail = true;

		this.spriteHeight = spriteSheet.getHeight();
		this.spriteWidth = spriteSheet.getWidth()/framecount;

		this.sheetWidth = framecount;
		this.sheetHeight = 1;
		
		animRect = new Rect(0,0,0,0);
		animRect.top = 0;
		animRect.bottom = spriteHeight;
		animRect.left = 0;
		animRect.right = spriteWidth;
		
		this.fps = 1000 / 25;
		
	}
	
	/// single line animation
	public animSprite(int imgID, Coord cord, int fps, int framecount, Coord newsize){
		//super(imgID, cord, newsize.x, newsize.y);
		animRect = new Rect(0, 0, 0, 0);
		this.frameTimer = 0;
		this.currentFrame = 0;
		spriteSheet = BitmapFactory.decodeResource(context.getResources(), imgID);
		this.spriteWidth = spriteSheet.getWidth()/framecount;
		this.spriteHeight = spriteSheet.getHeight();
		this.sheetHeight = 1;
		this.sheetWidth = framecount;
		this.numFrames = framecount;
		this.fps = 1000/fps;
		this.loc = new Coord(cord.x, cord.y);
		this.size = new Coord(newsize.x, newsize.y);
		this.imgAvail = true;
		animRect.left = 0;
		animRect.top = 0;
		animRect.bottom = spriteHeight;
		animRect.right = spriteWidth;
	}
	/// multiline animation
	public animSprite(int imgID, Coord cord, int sheetWidth, int sheetHeight, int fps, int framecount){
	//	super(imgID, cord, sheetWidth, sheetHeight);
		animRect = new Rect(0, 0, 0, 0);
		this.frameTimer = 0;
		this.currentFrame = 0;
		spriteSheet = BitmapFactory.decodeResource(context.getResources(), imgID);
		this.spriteWidth = spriteSheet.getWidth()/sheetWidth;
		this.spriteHeight = spriteSheet.getHeight()/sheetHeight;
		this.sheetHeight = sheetHeight;
		this.sheetWidth = sheetWidth;
		this.numFrames = framecount;
		this.fps = 1000/fps;
		this.loc = new Coord(cord.x, cord.y);
		this.size = new Coord(sheetWidth, sheetHeight);
		
		animRect.left = 0;
		animRect.top = 0;
		animRect.bottom = spriteHeight;
		animRect.right = spriteWidth;
	}
	public void update(long GameTime){
		if (GameTime > frameTimer + fps) {
			frameTimer = GameTime;
			currentFrame += 1;

			if (currentFrame >= numFrames) {
				currentFrame = 0;
			}
		}
		animRect.left = currentFrame%sheetWidth * spriteWidth;
		animRect.right = animRect.left + spriteWidth;

		animRect.top = currentFrame/sheetWidth * spriteHeight;
		animRect.bottom = animRect.top + spriteHeight;
		
	}
	public void change(int imgID, Coord cord, int fps, int framecount, Coord newsize ){
		
		animRect = new Rect(0, 0, 0, 0);
		this.frameTimer = 0;
		this.currentFrame = 0;
		spriteSheet = BitmapFactory.decodeResource(context.getResources(), imgID);
		this.spriteWidth = spriteSheet.getWidth()/framecount;
		this.spriteHeight = spriteSheet.getHeight();
		this.sheetHeight = 1;
		this.sheetWidth = framecount;
		this.numFrames = framecount;
		this.fps = 1000/fps;
		this.loc = new Coord(cord.x, cord.y);
		this.size = new Coord(newsize.x, newsize.y);
		
		animRect.left = 0;
		animRect.top = 0;
		animRect.bottom = spriteHeight;
		animRect.right = spriteWidth;

	}
	public void draw() {
		if (imgAvail) {
			Rect dest = new Rect((int)loc.x, (int)loc.y, (int)loc.x + (int)size.x, (int)loc.y + (int)size.y);
			canvas.drawBitmap(spriteSheet, animRect, dest, null);
		}
	}

	public void draw(float offSetX, float offSetY) {
		offSetX = offSetX + this.loc.x;
		offSetY = offSetY + this.loc.y;
		if (imgAvail) {
			Rect dest = new Rect((int)offSetX, (int)offSetY, (int)offSetX + (int)size.x, (int)offSetY + (int)size.y);
			canvas.drawBitmap(spriteSheet, animRect, dest, null);
		}
	}
	
}