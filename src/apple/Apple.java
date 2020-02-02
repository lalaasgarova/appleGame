package apple;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.util.RandomGenerator;

public  class Apple extends GCompound implements Runnable, MyConstants {

	private int collisionCounter = 0;
	private GImage apple;
	private int random_x;
	private int PAUSE_TIME = 10;
	public static int applesCaught = 0;
	public boolean isGreen;


	public void run() {
		chooseAnApple();
		for (int i = 0; i < ((650 - apple.getHeight()) / MyConstants.dy); i++) {
			Pause.pauseIt();
			pause(PAUSE_TIME);
			apple.move(0, MyConstants.dy);
			if (CheckForCollisions(test.getTrash()) == true && collisionCounter == 1 && !isGreen) {
				applesCaught++;
				test.getTrash().sendToFront();
			}
				if (CheckForCollisions(test.getTrash()) == true && collisionCounter == 1 && isGreen) {
					applesCaught --;
				test.getTrash().sendToFront();
		}
			speedUp();
		}

		remove(apple);
	}

	/* speeds Apple's falling up, by reducing pause time */

	protected void speedUp() {
		
		if (PAUSE_TIME > 3)
			PAUSE_TIME = PAUSE_TIME - 2;
	}
	
	public void chooseAnApple() {
		RandomGenerator rgen = new RandomGenerator();
		 isGreen = rgen.nextBoolean(MyConstants.GREEN_PROBABILITY);
		 if(isGreen) addAppleRandom("green.gif");
		 else addAppleRandom("apple.gif");
	}

	public void addAppleRandom(String image) {
			if (!test.isPaused) {
			RandomGenerator rgen = new RandomGenerator();
			random_x = rgen.nextInt(0, MyConstants.SCREEN_WIDTH);
			apple = new GImage(image);
			if(!isGreen) apple.scale(MyConstants.RED_APPLE_SCALE);
			else apple.scale(MyConstants.GREEN_APPLE_SCALE);
			add(apple, random_x, 0);

		}
	}

	public GImage getApple() {
		return apple;
	}

	public int getApplesCaught() {
		return applesCaught;
	}

	public boolean CheckForCollisions(BrownTrash brownTrash) {
		if (apple.contains((brownTrash.getX() + brownTrash.getWidth() / 2), brownTrash.getY()) && !test.isPaused) {
			collisionCounter++;
			return true;
		}
		return false;
	}

	public void setApplesCaught(int i) {
		
		applesCaught = i;
	}
}
