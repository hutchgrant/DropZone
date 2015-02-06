package hutchgrant.dropzone.elements;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;

import com.example.dropzone.R;

public class background extends element{

	private List<element> elementMap = new ArrayList<element>();  
	int travel = 0;    //// distance traveled left
	int amount = 0;    /// number of background frames
	int imgID = 0;
	int speed = 0;
	int startPoint = 0;
	
	public background(int imageID, Coord location, int framecount, Coord newsize, int newspeed){
		super(location, ((int)newsize.x*2), (int)newsize.y);
		startPoint = (int)this.loc.x*-1;
		amount = framecount;
		add(framecount);
		imgID = imageID;
		speed = newspeed;
	}
	
	public void move(){
		synchronized(elementMap){

			travel= travel-speed;
			if(travel == ((this.size.x/-1))){   
				for(element c: elementMap){             
					if(c.bgElementPos == 1){
						c.setBgElementPos(2);
					}
					else if(c.bgElementPos == 2){
						c.setBgElementPos(1);
					}
				}
				travel = 0;   // reset travel distance to default
			}
			else{
			for(element c: elementMap){
				if(c.bgElementPos == 1){
					c.move(new Coord(travel, this.loc.y));
				}
				else if(c.bgElementPos == 2){
					c.move(new Coord(travel+this.size.x, this.loc.y));
				}
			}
			}
		
		
		/*
		 * When the map travels the background image distance move the first image to the third image, move the second to first, third image to second. 
		 * recurse infinitely with any image necessary for the level
		 */

		}
	}
	
	public void add(int amount){
		elementMap = new ArrayList<element>();

		element newElement;
			newElement = new element(R.drawable.artic_drop, new Coord(this.loc.x,this.loc.y ), 1, new Coord(this.size.x, this.size.y));
			newElement.bgElementPos = 1;
			elementMap.add(newElement);
			element newElement2;
			newElement2 = new element(R.drawable.artic_drop, new Coord(this.loc.x+this.size.x,this.loc.y ), 1, new Coord(this.size.x, this.size.y));
			newElement2.bgElementPos = 2;
			newElement2.flip();
			
			elementMap.add(newElement2);
	}
	public void draw(){
		super.draw();
		for (int i = 0; i < this.elementMap.size(); i++) {
			element element = elementMap.get(i);
			paint.setColor(element.getColor());
			element.draw(this.loc.x, this.loc.y);
		}
	}
}
