package hutchgrant.dropzone.core;

import com.example.dropzone.R;

import hutchgrant.dropzone.elements.Coord;
import hutchgrant.dropzone.elements.Score;
import hutchgrant.dropzone.elements.background;
import hutchgrant.dropzone.elements.carepackage;
import hutchgrant.dropzone.elements.elementMap;
import hutchgrant.dropzone.elements.plane;
import android.R.color;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.GestureDetector.OnGestureListener;

public class game extends Activity implements OnGestureListener {

	private GestureDetector gestureScanner;
	public GameView gameView;
	public GameThread gameThread;
	public elementMap map;			
	public plane dropPlane;
	public steering Steer;
	public background bkGrnd;
	public dropButton dropCntrl;
	public carepackage CarePack;
	public Score score;
	
	public int gameClosed;				// variable to ensure game only launches one win or lost activity
	public boolean drop = false;
	Paint paint = new Paint();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// creates a gestureDetector object with using listener interface from this object
		gestureScanner = new GestureDetector(this);		
		// sets the content view by referencing the gamescreen.xml layout file
		setContentView(R.layout.gamescreen);					
		// Initialise gameView object using the attributes outlined in the gamescreen.xml layout file
		gameView = (GameView) findViewById(R.id.gameView);	
		
		// initialize gameThread and declare an anonymous version of this class to redefine the draw, update and setup methods
		gameThread = new GameThread(this, new Handler()) 		
		{
			public int MainSpriteSize, CntrlSize, MainSpeed;
			
			
			public void setup() {
				// window display settings
				Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay(); 
				int width = display.getWidth(); 
				int height = display.getHeight();
				// standard size of elements
				MainSpriteSize = width/12;
				CntrlSize = MainSpriteSize *3;
				/// set main speed
				MainSpeed = 4;
				
				/// INIT game objects
				dropPlane = new plane(R.drawable.plane_vehicle_small, new Coord(CntrlSize,50), 1, new Coord (CntrlSize,MainSpriteSize));
				bkGrnd = new background(R.drawable.artic_drop, new Coord(-width/10,-0), 10, new Coord(width, height), MainSpeed);
				Steer = new steering(R.drawable.steeringcntrl, new Coord(-CntrlSize/8, height-((CntrlSize*2)- MainSpriteSize)), 3, new Coord(CntrlSize, CntrlSize));
				dropCntrl = new dropButton(R.drawable.dropbutton, new Coord((width-CntrlSize), height-((CntrlSize*2)-MainSpriteSize)), 2, new Coord((CntrlSize/4)*3, (CntrlSize/4)*3));
				map = new elementMap(this.CntrlSize, width*2, height*2, -CntrlSize,-CntrlSize, MainSpeed);
				CarePack = new carepackage(R.drawable.carepackage, new Coord(CntrlSize,50), 1, new Coord(CntrlSize, CntrlSize), MainSpeed/2, MainSpeed/2, 100, 500);
				
				score = new Score(R.drawable.dropbutton,new Coord(width-CntrlSize,50), 1, new Coord(300,50));
				gameClosed = 0;  	
					
			}	
			
			// draw the maze and bumper to screen
			public void draw(Canvas c) {
				c.drawARGB(255, 0, 0, 0);	
				// Draw Background
				bkGrnd.move();
				bkGrnd.draw();

				score.draw();
				// Draw Elements
				map.moveAuto(1);
				map.draw();
			//	map.checkColAndDraw(CarePack);
			//	score.Score = map.POINTS; 

				
				// Draw Plane
				dropPlane.draw();
				// Draw steering
				Steer.draw();
				// Draw Drop Button
				dropCntrl.draw();
				// draw score
				
				// Drop Button Pressed and Care Package Move/Draw
				if(drop){ //Drop button pressed
					CarePack.move();  // drop Care package
					CarePack.draw();   // Draw it
					if(CarePack.Stop == true){
						drop = false;   // reset the drop button
					}
				}
			}				
			
			
		};

		// link the gameThread to the gameView using the setThread method from surfaceView class
		// this method enables the gameView to call a method form the gameThread that links a handle 
		gameView.setThread(gameThread);
}
	public void onResume(Bundle savedInstanceState) {
	
	}
	
// ************************************************
// CALLBACK METHODS FOR ON-GESTURE-LISTENER INTERFACE
// ************************************************

public boolean onTouchEvent(MotionEvent me) {
	boolean result = false;
	
		result = gestureScanner.onTouchEvent(me);

	try {
		Thread.sleep(30);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}
@Override
public boolean onDown(MotionEvent e) {
		int check = 9;
		boolean dropCheck = false;
		check = Steer.isSelected(e.getX(), e.getY());
		Log.v("contrl", "control check direction ="+ check);
		if(!drop){
		dropCheck = dropCntrl.isSelected(new Coord(e.getX(), e.getY()));
		if(dropCheck){
			Log.v("dropped","drop button pressed");
			drop = true;
			CarePack.restart();
		}
		else{
			drop = false;
		}
		}
		if(check == 1){    /// steering pulled up
			// move plane down
			dropPlane.moveDown();
		}
		else if(check == 2){ // steering pulled down
			// move plane up
			dropPlane.moveUp();
		}
		else{   			//steering idle
			// move plane idle
			dropPlane.moveIdle();
		}   
	return true;
}
@Override
public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
		float velocityY) {
	// TODO Auto-generated method stub
	return false;
}
@Override
public void onLongPress(MotionEvent e) {
	Steer.direction = 0;
	dropPlane.moveIdle();
}
@Override
public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
		float distanceY) {
	// TODO Auto-generated method stub
	return false;
}
@Override
public void onShowPress(MotionEvent e) {
	Steer.direction = 0;
	dropPlane.moveIdle();
}
@Override
public boolean onSingleTapUp(MotionEvent e) {
	Steer.direction = 0;
	dropPlane.moveIdle();
	return false;
}

}
