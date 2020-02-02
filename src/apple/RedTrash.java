package apple;

import acm.graphics.GCompound;

public class RedTrash extends GCompound{

	/* fields */
	
	private Trash redTrash;
	
	public RedTrash() {
		redTrash = new Trash("redtrash.png");
		add(redTrash);
		}

}
