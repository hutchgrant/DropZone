package hutchgrant.dropzone.elements;

import android.util.Log;


public class carepackage extends element{

	int speedx = 10, speedy = 10;
	int distancex = 20, distancey = 320;
	int travelx = 0, travely = 0;
	boolean reverse = false;
	public boolean Stop = false;
	Coord Projection; 
	
	public carepackage(int imageID, Coord location, int framecount, Coord size, int speedx, int speedy, int distancex, int distancey){
		super(imageID, location, framecount, size);
		this.distancex = distancex;
		this.distancey = distancey;
		this.speedx = speedx;
		this.speedy = speedy;
	}
	
	public carepackage(Coord location, float sizeX, float sizeY){
		super(location, (int)sizeX, (int)sizeY);
	}
	
	public void move(){
		if(!Stop){
			travely = travely + speedy;
			if(travely >= distancey){
				Stop = true;
				travely=0;
			}
			else{
					super.move(new Coord(this.loc.x, travely));
				}
			}
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

	public boolean hasIntersected(carepackage c) {
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
	public void restart()
	{
		Stop = false;
	}
	public void draw(){
		super.draw();
	}
}
