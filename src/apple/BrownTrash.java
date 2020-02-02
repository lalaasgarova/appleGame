package apple;

import acm.graphics.GCompound;

public class BrownTrash extends GCompound{
	 
	/* fields */
	
	private Trash brownTrash;

	public BrownTrash() {
		
		brownTrash = new Trash("trash.png");
		add(brownTrash);
		}

}
