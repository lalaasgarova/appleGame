package apple;

import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;

public class Score extends GCompound{
	
	/* fields */
	
	private GRect rect;
	private GLabel score;

	public Score(String str, double x, double y) {
		rect = new GRect(0, 632, 800, 160);
		score = new GLabel(str, 30, 650);
		rect.setFilled(true);
		rect.setFillColor(Color.green);
		rect.setColor(Color.green);
		score.setColor(Color.WHITE);
		score.setFont("ARIAL-20");
		add(rect);
		add(score);
	}
	
	/* method for changes in the score */
	
	public void setLabel(String string) {
		score.setLabel(string);
	}
}
