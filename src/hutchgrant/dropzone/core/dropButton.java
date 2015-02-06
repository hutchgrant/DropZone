package hutchgrant.dropzone.core;

import hutchgrant.dropzone.elements.Coord;
import hutchgrant.dropzone.elements.element;

public class dropButton extends element{
	
	public boolean Selected = false;
	int direction = 0;
	public dropButton(int imageID, Coord pos, int framecount, Coord size){
		super(imageID, pos, framecount, size);
	}
	
	public void update(){
		if(direction == 0){
			this.animRect.left = 0;
			this.animRect.top = currentFrame/sheetWidth * spriteHeight;
		}
		else if(direction == 1){
			this.animRect.left = spriteWidth;
		this.animRect.top = currentFrame/sheetWidth * spriteHeight;
		}
			else{
				this.animRect.left = 0;
				this.animRect.top = currentFrame/sheetWidth * spriteHeight;
			}

		this.animRect.right = animRect.left + spriteWidth;
		this.animRect.bottom = animRect.top + spriteHeight;
	}
	
	public boolean isSelected(Coord pos){
		if(pos.x >= this.loc.x  && pos.x <= this.loc.x + this.size.x &&
			pos.y >= this.loc.y && pos.y <=  this.loc.y + this.size.y*1.5){
			
			Selected = true;
			direction = 1;
		}
		else{
			direction = 0;
			Selected = false;
		}
		update();
		return Selected;
	}
	
	
	public void draw(){
		
		super.draw();
	}
}
