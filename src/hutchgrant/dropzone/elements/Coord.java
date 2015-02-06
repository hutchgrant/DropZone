package hutchgrant.dropzone.elements;


public class Coord {

	public float x = 0;
	public float y = 0;
	
	public Coord(){
		super();
	}
	
	public Coord(Coord pos){
		if(pos != null){
			this.x = pos.x;
			this.y = pos.y;
		}
	}
	
	public Coord(float posx, float posy){
			super();
			this.x = posx;
			this.y = posy;
	}
	public void add(Coord pos){
		this.x += pos.x;
		this.y += pos.y;
	}
	
	public void sub(Coord pos){
		this.x -= pos.x;
		this.y -= pos.y; 
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Coord other = (Coord) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	public int distance(Coord coord){
		return (int)(Math.sqrt(Math.pow(coord.x - x, 2) + Math.pow(coord.y - y, 2)));
	}
	
	
}
