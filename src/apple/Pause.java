package apple;

import acm.graphics.GCompound;

public class Pause extends GCompound {

	public Pause() {

	}

	public static void pauseIt() {
		if (test.isPaused == true) {
			test.isCreated = true;
			test.pause.setVisible(true);

		}
		while (test.isPaused == true) {
			System.out.println("SS");
		}

		if (test.isCreated == true) {
			test.pause.setVisible(false);
			test.isCreated = false;

		}
	}
}