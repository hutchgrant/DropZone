package hutchgrant.dropzone.elements;

import android.content.Context;
import android.util.Log;
import android.view.SurfaceView;

import hutchgrant.dropzone.core.settings;

import java.util.ArrayList;
import java.util.List;

import com.example.dropzone.R;

import android.graphics.Color;

public class elementMap extends element {
	private List<element> mazeStructure = new ArrayList<element>();  
	private List<element> element = new ArrayList<element>();  
	public int elementSize;
	public int direction = 9;							// holds whether maze is moving horizontally or vertically
	public float speed;
	public int[] colorArray;
	public float screenWidth;
	public float screenHeight;
	public float speedPerSecond;

	public float moveSpeed = 0;
	public float distance = 0;
	public float travel = 0;
	
	public int POINTS = 0;
	
	static final int MOVE_DOWN = 0;
	static final int MOVE_RIGHT = 1;
	static final int MOVE_LEFT = 2;
	static final int MOVE_UP = 3;
	
	public boolean MOVING= false;
	public elementMap(int elementSize, float screenWidth, float screenHeight, int locx, int locy, int newspeed) {
		super(new Coord(0,0),(int)screenWidth, (int)screenHeight);
		this.elementSize = elementSize; 
		this.initMaze();
		this.direction = 0;
		this.speed = newspeed;
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.speedPerSecond = 160f;
	//	this.loc.y = (float) (this.screenHeight/2) - (this.elementSize*2 + this.elementSize/2);
	//	this.loc.x = (float) (this.screenWidth/2) - (this.elementSize*2 + this.elementSize/2);
		this.loc.x = locx;
		this.loc.y = locy;

	}

	
	// **********************************************************************************
	// INITIALIZATION METHODS
	

	// read the maze structure from a text file and create an array that holds all necessary objects.
	public void initMaze() {
		mazeStructure = new ArrayList<element>();
		element = new ArrayList<element>();
		
		String[] mazeLines = context.getResources().getStringArray(R.array.maze_three);

		// figure out the size of the maze by checking the size of the array
		this.size.y = mazeLines.length * this.elementSize;
		String mazeCols[] = mazeLines[0].split(",");
		this.size.x = mazeCols.length * this.elementSize;
		
		// loop through each line on the array to read it in
		for (int i=0; i < mazeLines.length; i++) {
			int moveAmt = 0;
			String elements[] = mazeLines[i].split(",");
			element MapElement;			

			// loop through each element in a line to read it in
			for (int e=0; e < elements.length; e++) {
				if (Integer.parseInt(elements[e]) > 0) {					
					if (Integer.parseInt(elements[e]) == 1) {
						MapElement = new landing(R.drawable.landing, new Coord(e*this.elementSize, i*elementSize), 1, new Coord(this.elementSize, this.elementSize));
				//		MapElement = new Wall(R.drawable.game_wall, new Coord(e*this.elementSize, i*this.elementSize),6, new Coord(this.elementSize, this.elementSize));
						mazeStructure.add(MapElement);
					}
				}					
			}
		}	
		
	///	GameStatus.init(elements.size());

	}


	
	// ************************************************************************************
	// MOVE METHODS

	// Move the maze based on current direction and current speed
	public void moveAuto(int mainDirecion) {
		//if (GameStatus.gameOver == true) return;
		//this.speed = settings.speedTimeFactor * this.speedPerSecond;
		//if(MOVING){
		this.direction = mainDirecion;
		if(travel == ((this.size.x/-1))){   
			travel = 0;   /// reset elementmap
		}else{
			
			if (this.direction == MOVE_RIGHT){
					travel=travel-speed;
					super.move(new Coord(travel, -this.elementSize));
					Log.v("mapmove","map moved "+travel);
			}
			else if(this.direction == MOVE_LEFT){
				travel=travel+speed;
					super.move(new Coord(travel, 0));
					Log.v("mapmove","map moved "+travel);
			}
			else if(this.direction == MOVE_UP){
				travel=travel+100;
					super.move(new Coord(0,travel));
					Log.v("mapmove","map moved "+travel);
			}
			else if(this.direction == MOVE_DOWN){
				travel=travel-100;
					super.move(new Coord(0,travel));
					Log.v("mapmove","map moved "+travel);
			}
		}
	}
	
	public void moveFlick(int travel2) {
		//if (GameStatus.gameOver == true) return;
		//this.speed = settings.speedTimeFactor * this.speedPerSecond;
		
		//if(MOVING){
			if (this.direction == MOVE_RIGHT){
					this.move(new Coord(travel2, 0));
					Log.v("mapmove","map moved "+travel);
			}
			else if(this.direction == MOVE_LEFT){
					this.move(new Coord(travel2, 0));
					Log.v("mapmove","map moved "+travel);
			}
			else if(this.direction == MOVE_UP){
					this.move(new Coord(0,travel2));
			}
			else if(this.direction == MOVE_DOWN){
					this.move(new Coord(0,travel2));
			}
	}
	
	// adjust speed based on how much time has passed since last update
	public void setSpeed(float percentSecPassed) {
		this.speed = percentSecPassed * this.speedPerSecond;
	}
	

	
	// **************************************************************************************
	// DIRECTION METHODS
	
	// reverses the direction of the bumper car
	public void reverseDirection() {
		if (this.direction == MOVE_RIGHT) this.direction = MOVE_LEFT;
		else if (this.direction == MOVE_DOWN) this.direction = MOVE_UP;
		else if (this.direction == MOVE_LEFT) this.direction = MOVE_RIGHT;
		else if (this.direction == MOVE_UP) this.direction = MOVE_DOWN;			
	}

	public void changeDirection(Coord projectionVector) {
		if (projectionVector.x > 0) { this.direction = MOVE_RIGHT; }
		else if (projectionVector.x < 0) { this.direction = MOVE_LEFT; }
		else if (projectionVector.y > 0) { this.direction = MOVE_DOWN; }
		else if (projectionVector.y < 0) { this.direction = MOVE_UP; }
	}
	
	public void shiftDirection() {
		if (this.direction >= 3) this.direction = 0;			
		else this.direction = this.direction + 1;
	}

	// get the current direction of the game play
	public int getDirection() {
		return this.direction;
	}

	
	// ************************************************************************************
	// COLLISION METHODS

	public void checkColAndDraw(carepackage CarePack) {
		// set the already intersected variable to false
	//	if (GameStatus.gameOver == true) return;
		
		boolean alreadyIntersected = false;

		// determine the location of the bumper car relative to the maze and create a bumper car object
		Coord adjustedLoc = new Coord((CarePack.loc.x + (this.loc.x * -1)) , (CarePack.loc.y + (this.loc.y * -1)));
		carepackage bumpCarePack = new carepackage(adjustedLoc, (int)(CarePack.size.x),(int)CarePack.size.y);

		// set the paint color then draw the background of the maze
	//	paint.setColor(Color.argb(255, 255, 255, 255));
	//	paint = null;
	//	canvas.drawRect(this.loc.x, this.loc.y, this.loc.x + this.size.x, this.loc.y + this.size.y, paint);
		// loop through each element in the mazeStructure array to check for collisions with maze WALL and SPIKE elements, and draw these same elements
		for (int i = 0; i < this.mazeStructure.size(); i++) {				

			
			element mazeStructureElement = mazeStructure.get(i);				
			
			// if the collision status is now set to true then add one to the appropriate mazeStatus array element
			if (bumpCarePack.hasIntersected(mazeStructureElement) && alreadyIntersected == false) {  
										// set already intersect variable to true
				if (mazeStructureElement.elementType == 2) {
		//			GameStatus.hitParent
				}
				if(mazeStructureElement.elementType == 1){
					Log.v("POINTS","POINTS ADDED YOU HIT IT!!!");
					alreadyIntersected = true;		
				}
	// ***********************************************
	// ****** ADD CODE TO UPDATE LIFE HERE ***********
	// ***********************************************
			
			} 
			// draw the mazeStructure elements
			mazeStructureElement.update(settings.time_now);
		//	mazeStructureElement.draw(this.loc.x, this.loc.y);
		}


		alreadyIntersected = false;
		
	}
	/*
	public void move(){

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
 //		} 

	
	// ************************************************************************************
	// DRAW METHODS

	// function that draws all maze WALL and SPIKE elements
	public void draw() {
		super.draw();
		for (int i = 0; i < this.mazeStructure.size(); i++) {
			element element = mazeStructure.get(i);
			paint.setColor(element.getColor());
			element.draw(this.loc.x, this.loc.y);
		}
	}
	
}
