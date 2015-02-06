package hutchgrant.dropzone.core;

import android.util.Log;
import hutchgrant.dropzone.elements.*;

// direction 0 is idle
// direction 1 is up
// direction 2 is down


public class steering extends element{

	boolean Selected = false;
	int direction = 0;
	
	public steering(int imageID, Coord loc, int framecount, Coord size){
		super(imageID, loc, framecount, size);
	}
	
	public void update(){
		if(direction >= 0 && direction < 3){ 
			if(direction == 0){
				animRect.left = direction * spriteWidth;
				animRect.top = currentFrame/sheetWidth * spriteHeight;
			}
			else if(direction == 1){
				animRect.left = direction * spriteWidth;
				animRect.top = currentFrame/sheetWidth * spriteHeight;
			}
			else{
				animRect.left = direction * spriteWidth;
				animRect.top = currentFrame/sheetWidth * spriteHeight;
			}
			animRect.right = animRect.left + spriteWidth;
			animRect.bottom = animRect.top + spriteHeight;
		}
	}
	
	public int isSelected(float selX, float selY){
		if(selX <= this.loc.x +this.size.x && selX >= this.loc.x &&
				selY <= this.loc.y+this.size.y*1.5 && selY >= this.loc.y){
			Selected = true;
			whichDirectionPush(selX, selY);
		}
		else{
			Selected = false;
			direction = 0;
		}

		update();
		return direction;
	}
	
	public void whichDirectionPush(float selX, float selY){
		if(selY <= this.loc.y+((this.size.y/6)*5) && selY >= this.loc.y+((this.size.y/6)*3)){
			direction = 1;   // direction is up
		}
		else if(selY <= this.loc.y+((this.size.y/6)*7) && selY >= this.loc.y+((this.size.y/6)*5)){
			direction = 0;  // direction is idle
		}
		else if(selY <= this.loc.y+((this.size.y/6)*9) && selY >= this.loc.y+((this.size.y/6)*7)){
			direction = 2; // direction is down
		}
		else{
			direction = 0;
		}
		Log.v("pushed","address x: "+selX +" y: "+selY+ " this sizeX: "+this.size.x+" this sizeY: "+this.size.y+" locateX: "+this.loc.x+ "locateY: "+this.loc.y);


	}
	
	public void draw(){
		super.draw();
	}
}
