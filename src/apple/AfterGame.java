package apple;

import java.awt.Color;
import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GLabel;

public class AfterGame extends GCompound implements MyConstants{

		/* fields */
	
		private GImage afterGameScreen;
		private GLabel highScore;
		private GLabel currentScore;
	
		public AfterGame(int currentScore, int highScore) {
			
			/* add BACKGROUND to the screen in the end o the Game */
			
			afterGameScreen = new GImage("afterGame.png"); 
			afterGameScreen.scale(MyConstants.AFTER_GAME_BACKGROUND_SCALE);
			add(afterGameScreen);
			
			/* adding the CURRENT score to the screen */
			
			
			this.currentScore = new GLabel(Integer.toString(currentScore));
			this.currentScore.setColor(Color.BLACK);
			this.currentScore.setFont("ARIAL-30");
			add(this.currentScore, 400, 480);
			
			/* adding the HIGH score to the screen */
			
			this.highScore = new GLabel(Integer.toString(highScore));
			this.highScore.setColor(Color.black);
			this.highScore.setFont("ARIAL-30");
			add(this.highScore, 400, 520);
		
			
	}

}
