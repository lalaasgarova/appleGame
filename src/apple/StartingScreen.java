package apple;

import acm.graphics.GCompound;
import acm.graphics.GImage;

public class StartingScreen extends GCompound implements MyConstants{

	/* fields */
	
	public static GImage screen;
	
	public StartingScreen() {
		screen = new GImage("screen1.png");
		screen.scale(MyConstants.STARTING_SCREEN_SCALE);
		add(screen);
	}
	
}
