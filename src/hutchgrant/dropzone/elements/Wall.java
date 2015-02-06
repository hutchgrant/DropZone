package hutchgrant.dropzone.elements;

public class Wall extends element{

	public Wall(Coord newloc, int width, int height){
		super(newloc, width, height);
		this.elementType = 1;
		this.loc = new Coord(newloc.x, newloc.y);
		this.size = new Coord(width, height);
	}
	public Wall(int imgID, Coord cord, int framecount, Coord newsize) {
		super(imgID, cord, framecount, newsize);
		this.elementType = 1;
	}
	public void draw(){
		super.draw();
	}
	public void draw(float offsetX, float offsetY){
		super.draw(offsetX, offsetY);
	}
}
