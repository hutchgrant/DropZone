package hutchgrant.dropzone.elements;

import android.graphics.Color;
import android.graphics.*;
import android.util.Log;

public class Score extends element{

	public int Score = 0;
	
	public Score(int imageID, Coord loc, int framecount, Coord size){
		super(imageID, loc, framecount, size);
		paint.setColor(Color.GREEN);
		Log.v("Score", "Score created");
	}
	
	public void draw(){	
		//super.draw();	
		paint.setColor(Color.WHITE);

		// draw some text using FILL style
		paint.setStyle(Paint.Style.FILL);
		//turn antialiasing on
		paint.setAntiAlias(true);
		paint.setTextSize(30);
		canvas.drawText("SCORE:"+Integer.toString(Score), this.loc.x, this.loc.y, paint);
		Log.v("Score","Score drawn");
	}
}
