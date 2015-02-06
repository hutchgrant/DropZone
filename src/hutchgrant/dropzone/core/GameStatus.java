package hutchgrant.dropzone.core;

public class GameStatus extends settings {

	public static float gameScore; 	
	public static long timeStart;
	public static long timeLimit;
	public static long timePassed;
	public static boolean gameStart;
	public static boolean gameOver;
	public static boolean gameWon;	
	public static int gameLevel;	

	
	public static void init(int _candyGoal) {
		timeStart = System.currentTimeMillis();
		timeLimit = 60000;
		timePassed = timeLimit;
		gameStart = true;
		gameOver = false;
		gameWon = false;
		gameLevel = 1;
	}

	public static void reset() {
		timeStart = System.currentTimeMillis();
		gameScore = 0;
		timeStart = 0;
		timeLimit = 60000;
		timePassed = timeLimit;
		gameStart = false;
		gameOver = false;
		gameWon = false;
		gameLevel = 0;
	}

	public static int calculateScore() {
	///	gameScore = health * (timePassed/1000f) * candyCurrent;
		return (int)(gameScore);
	}

	public static void updateTimer() {
//		if (gameStart == true && gameOver == false) {
			timePassed = settings.time_now - timeStart;
			if (timePassed >= timeLimit) {
		//		gameOver = true;
				gameWon = false;
			}
//		}
	}
	
}
