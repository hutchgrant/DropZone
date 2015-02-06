package hutchgrant.dropzone.core;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.view.SurfaceHolder;

public class GameThread extends Thread {

	private long mLastTime;

	private boolean mRun = false;

	private SurfaceHolder mSurfaceHolder;
	public void setup(){};
	public void update(){};
	public void draw(Canvas c){};

	public GameThread(Context context, Handler handler) {
		settings.context = context;
	}

	public void doStart() {
		synchronized (mSurfaceHolder) {
			mLastTime = System.currentTimeMillis() + 100;
		}
	}

	public void pause() {
		synchronized (mSurfaceHolder) {
//			if (mMode == STATE_RUNNING) 
//				setState(STATE_PAUSE);
		}
	}

	@Override
	public void run() {
		setup();
		
		while (mRun) {
			Canvas c = null;				 
			try {
				c = mSurfaceHolder.lockCanvas(null);

				synchronized (mSurfaceHolder) {
					settings.canvas = c;
					updateThread();
					if(c != null)
						draw(c);

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (c != null) {
					mSurfaceHolder.unlockCanvasAndPost(c);
				}
			}
		}
	}


	private void updateThread() {
		long now = System.currentTimeMillis();
		settings.time_now = System.currentTimeMillis();
		GameStatus.updateTimer();

		if((mLastTime > now) || settings.isPause)
			return;
		
		settings.time_elapsed_sec_percent = (now - mLastTime) / 1000.0f;
		settings.millis_elapsed = ((int)(now - mLastTime));
		mLastTime = now;

		// set-speed setting variable here
		if (settings.time_elapsed_sec_percent < 0.2f) settings.speedTimeFactor = (float)(settings.time_elapsed_sec_percent);
		else settings.speedTimeFactor = 0.2f;

		update();		
	}
	
	public void unpause() {
		synchronized (mSurfaceHolder) {
			mLastTime = System.currentTimeMillis() + 100;
		}	
	}
	
	
	public SurfaceHolder getMSurfaceHolder() {
		return mSurfaceHolder;
	}

	public void setMSurfaceHolder(SurfaceHolder surfaceHolder) {
		mSurfaceHolder = surfaceHolder;
	}

	public void begin(){
		mRun = true;
	}
	
	public void end(){
		mRun = false;
	}

}
