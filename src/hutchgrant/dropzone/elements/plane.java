package hutchgrant.dropzone.elements;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Log;

import com.example.dropzone.R;

public class plane extends element{
	Coord Projection; 
	boolean Ascending = false, Decending = false;
	public int direction = 0; /// 1 == up ,  2 == down
	public int travel = 0;
	public int speed = 10;
	public int degree = 0;
	Matrix matrix;
	
	public plane(Coord pos, int width, int height){
		super(pos, width, height);
	}
	public plane(int imgID, Coord pos, int fps, Coord size){
		super(imgID, pos, fps, size);
	}
	
	public void update(){
		if(this.direction == 0){
			// draw idle
			super.change(R.drawable.plane_vehicle, new Coord(this.loc.x, this.loc.y), 1, 1, new Coord(this.size.x, this.size.y));
		}
		else if(this.direction == 1){
			// draw angled up
		}
		else if(this.direction == 2){
			// draw angled down
		}
		else if(this.direction == 3){
			// begin descending from idle
		}
		else if(this.direction == 4){
			// beging ascending from idle
		}
	}
	public void changeDirection(int newDirection){
		direction = newDirection;
	}
	public void moveAuto(int direction){
		if(direction == 0){
			moveIdle();    /// plane is moving idle
		}
		else if(direction == 1){  //plane is angled up
			moveUp();
		}
		else if(direction == 2){   /// plane is angled down
			moveDown();
		}
	}
	public void moveIdle(){
		travel = 0;
		degree = 0;
	}
	public void moveUp(){
		travel = travel+speed;
		degree = degree - 5;
		//rotate(degree);
		super.move(new Coord(this.loc.x,this.loc.y-travel));
	}
	public void moveDown(){
		travel = travel+speed;
		degree = degree + 5;
	//	rotate(degree);
		super.move(new Coord(this.loc.x,this.loc.y+travel));
		
	}	
	public void rotate(int degree){
		matrix = new Matrix();
		matrix.setRotate(degree,spriteSheet.getWidth()/2,spriteSheet.getHeight()/2);
		Bitmap test = spriteSheet;
	    spriteSheet = Bitmap.createBitmap(test, 0, 0, spriteSheet.getWidth()/2, spriteSheet.getHeight()/2, matrix, true);

	}
	public void setDirection(int newdirection){
		this.direction = newdirection;
		Log.v("carDirection","car direction = "+direction);
	//	update();
	}
	public void changeDirection(Coord projectionVector) {
		if (projectionVector.y > 0) {setDirection(2); }
		else if (projectionVector.y < 0) { setDirection(1); }
	}
	public boolean hasIntersected(element e) {
//		super.hasIntersected(rectangle);
		boolean intersected = false;

		if (((this.loc.x + this.size.x >= e.loc.x) && (this.loc.x - this.size.x <= (e.loc.x + e.size.x))) && 
			((this.loc.y + this.size.y >= e.loc.y) && (this.loc.y - this.size.y <= (e.loc.y + e.size.y)))) {
				// figure out the half-width and half-height size of the rectangle (create a coord with this data)
				Coord rectCenter = new Coord((e.loc.x + (e.size.x/2)),(e.loc.y + (e.size.y/2)));
				// figure out the distance from the center of the rectangle to one of its edges (create a coord with this data)
				float rectTangent = rectCenter.distance(new Coord(e.loc.x + e.size.x, e.loc.y + e.size.y));
				// figure out the distance between the center of the circle and rectangle
				float centerDistance = this.loc.distance(rectCenter);
				// if distance between the center of the circle and rectangle is smaller than the circle radius plus the distance from rectangle center of rect to its edge 
				if (centerDistance < rectTangent + this.size.x) {

					// if the circle is overlapping with the maze element less on the horizontal axis
					if ( Math.abs(rectCenter.x - this.loc.x) > Math.abs(rectCenter.y - this.loc.y)) {
						// set the projection vector to be horizontal
						if (this.loc.x - rectCenter.x > 0 ){ 
							Projection = new Coord(1, 0);		
							Log.v("collision", "collision right");
						}
						if (this.loc.x - rectCenter.x < 0){
							Projection = new Coord(-1, 0);						
							Log.v("collision", "collision left");

						}
					// otherwise, if it overlaps more on the vertical axis
					} else {
						if (this.loc.y - rectCenter.y > 0){
							Projection = new Coord(0, 1);						
							Log.v("collision", "collision top");

						}
						if (this.loc.y - rectCenter.y < 0){
							Projection = new Coord(0, -1);						
							Log.v("collision", "collision bottom");

						}
					}

					intersected = true;
					return intersected;
				}
		} 
		return intersected;
	}

		
	public Coord intersectProjection() {
		return Projection;
	}

	public boolean hasIntersected(plane c) {
		boolean intersected = false;
		if(this.loc.distance(new Coord(c.loc.x, c.loc.y)) < (this.size.x + c.size.x)) 
			intersected = true;
		return intersected;
	}
	
	public boolean pointHasIntersected(float x, float y) {
		boolean intersected = false;
		if(this.loc.distance(new Coord(x, y)) < this.size.x) intersected = true;
		return intersected;
	}
	public void draw(){
	/*	int drawX = (int) (this.loc.x - (this.size.x));
		int drawY = (int) (this.loc.y - (this.size.y));
		Rect dest = new Rect(drawX, drawY, drawX + spriteWidth, drawY + spriteHeight);
		canvas.drawBitmap(spriteSheet, animRect, dest, null);
	*/	//update();
		super.draw();
	}
}
