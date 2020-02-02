package apple;

import acm.graphics.GCompound;
import acm.graphics.GImage;

/*superclass*/

public class Trash extends GCompound implements MyConstants{

	/* instance variables */
	
	private GImage trash;
	
	public Trash(String string) {
		trash = new GImage(string);
		trash.scale(MyConstants.TRASH_SCALE);
		add(trash);
	}
}
