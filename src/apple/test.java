package apple;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

public class test extends GraphicsProgram  {

	private GObject gobj;
	private GPoint last;
	private static final long serialVersionUID = 1L;
	private int previousScore;
	private int applesCaught;
	public GOval invisible;
	public static boolean isClicked = false;
	public static BrownTrash brownTrash;
	public static RedTrash redTrash;
	public static Background bcg;
	protected Score score;
	private int THREAD_PAUSE_TIME = 1000, THREAD_PAUSE_2 = 200;
	private static int highScore = 0;
	private Apple apple;
	static boolean isPaused = false;
	public static boolean isCreated = false;
	static Pause pause;
	private GOval oval;
	boolean gameOver = false;
	private boolean isClicked2 = false;

	public void run() {

		addStartingScreen();
		addActionListeners();
		addMouseListeners();
		setSize(650, 750);
		addFileExeption();
		
		while (isClicked == false) {
			System.out.print("");
		}
		if (isClicked == true) {
			remove(oval);
			isClicked = false;
			addBackground();
			addKeyListeners();
			addScore();
			brownTrash.setVisible(true);
			brownTrash.sendToFront();
			pause = new Pause();
			add(pause);

			for (int i = 0; i < 100; i++) {
				while(isPaused) {
					System.out.print("");
				}
				changeColor();
				addApple();
				speedUp();
				refreshCaughtApples();
				changeScore(i);
				if(apple.isGreen) i--;
			}
			checkHighScore();
			removeAll();
			gameOver = true;
			addAfterGameScreen();
			saveHighScore();
			removeAll();
		}
	}


	private void addAppleClickOval() {
		oval = new GOval(80, 80);
		oval.setFilled(true);
		oval.setVisible(false);
		add(oval, 280, 580);
		oval.sendForward();
	}

	private void addFileExeption() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("score.txt"));
			highScore = Integer.parseInt(br.readLine());
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void changeColor() {
		
		if (applesCaught != previousScore) {
			brownTrash.setVisible(false);
			Sound.playSound("sounds/sound.wav");
			pause(THREAD_PAUSE_2);
		} else {
			brownTrash.setVisible(true);
		}

	}

	private void changeScore(int i) {
		
		score.setLabel("Apples caught: " + applesCaught + " " + "Apples missed: " + (i + 1 - applesCaught));
	}


	private void refreshCaughtApples() {
		
		applesCaught = apple.getApplesCaught();
	}

	private void speedUp() {
		
		if (THREAD_PAUSE_TIME > THREAD_PAUSE_2) THREAD_PAUSE_TIME -= 10;
	}

	private void addApple() {
		
		previousScore = applesCaught;
		apple = new Apple();
		Thread thread = new Thread(apple);
		add(apple);
		changeColor();
		apple.sendToFront();
		redTrash.sendForward();
		brownTrash.sendForward();
		thread.start();
		pause(THREAD_PAUSE_TIME);

	}

	private void saveHighScore() {
		
		try {
			FileWriter out = new FileWriter("score.txt");
			out.write(Integer.toString(highScore));
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addAfterGameScreen() {
		
		AfterGame bc = new AfterGame(applesCaught, highScore);
		add(bc);
		GOval oval1 = new GOval(42, 42);
		oval1.setColor(Color.black);
		oval1.setFilled(true);
		oval1.setVisible(false);
		add(oval1, 30, 575);
		while(isClicked2 == false) {
			System.out.print("");
		}
		if(isClicked2  == true) {
		test t = new test();
		t.repaint();
		t.start();
		}
	}

	private void checkHighScore() {
		
		if (applesCaught > highScore)
			highScore = applesCaught;
	}

	private void addStartingScreen() {
		
		StartingScreen sc1 = new StartingScreen();
		add(sc1);
		addBrownTrash();
		addRedTrash();
		//highScore = 0;
		applesCaught = 0;
		previousScore = 0;
		addAppleClickOval();
		Apple.applesCaught = 0;
	}

	private void addBackground() {
		
		bcg = new Background();
		bcg.sendToBack();
		add(bcg);
		add(redTrash, 0, 632 - redTrash.getHeight());
		add(brownTrash, 0, 632 - redTrash.getHeight());
	}

	public static BrownTrash getTrash() {
		
		return brownTrash;
	}


	private void addBrownTrash() {
		
		brownTrash = new BrownTrash();
	}

	private void addRedTrash() {
		
		redTrash = new RedTrash();
	}

	private void addScore() {
		
		score = new Score("", MyConstants.SCORE_X, MyConstants.SCORE_Y);
		add(score);
	}

	public void mouseMoved(MouseEvent e) {

		if(e.getX() < getWidth() - brownTrash.getWidth()/2 && e.getX() > brownTrash.getWidth()/2) {
			redTrash.setLocation(e.getX() - redTrash.getWidth() / 2, 632 - redTrash.getHeight());
			brownTrash.setLocation(e.getX() - brownTrash.getWidth() / 2, 632 - brownTrash.getHeight());
		}
		last = new GPoint(e.getPoint());
	}
	
	public void mousePressed(MouseEvent e) {
		
		last = new GPoint(e.getPoint()); 
		gobj = getElementAt(last);
		if (gobj instanceof GOval && gameOver) {
			isClicked2 = true;
		}
		if (gobj instanceof GOval) {
			isClicked = true;
		}


	}

	public void mouseReleased(MouseEvent e) {
		
		if (gobj instanceof GOval) {
			isClicked = true;
		}
	}

	/* if you press esc the game pauses, if enter, if resumes*/	
	public void keyReleased(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			isPaused ^= true;
			System.out.println(isPaused);
			break;

		case KeyEvent.VK_ENTER:
			isPaused = false;
			break;
		}
	}

	public static void main(String[] args) {
		
		if (!isPaused) {
			new test().start();
		}
	}
}