package hutchgrant.dropzone.elements;

public class landing extends element{
	public landing(int imageID, Coord location, int framecount, Coord size){
		super(imageID, location, framecount, size);
		this.elementType = 1;
	}
	
	public void move(){
		
	}
	
	public void draw(){
		super.draw();
	}
}
