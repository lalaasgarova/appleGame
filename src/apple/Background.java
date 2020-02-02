package apple;

import acm.graphics.GCompound;
import acm.graphics.GImage;

public class Background extends GCompound implements MyConstants{

	/* fields */
	
	private GImage background;
	
	/* THIS class adds background to the game */
	
	public Background() {
		
		background = new GImage("8.png");
		background.scale(BACKGROUND_SCALE);
		add(background);
		}

}
