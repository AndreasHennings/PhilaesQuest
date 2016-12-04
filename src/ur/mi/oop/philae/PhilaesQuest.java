package ur.mi.oop.philae;

import java.awt.event.KeyEvent;

import ur.mi.oop.philae.config.GameConfig;
import ur.mi.oop.philae.game.GameStateListener;
import ur.mi.oop.philae.scenes.EndScene;
import ur.mi.oop.philae.scenes.GameScene;
import de.ur.mi.graphicsapp.GraphicsApp;
import de.ur.mi.sound.Sound;

/**
 * PhilaesQuest is a LunarLander (http://en.wikipedia.org/wiki/Lunar_Lander_(video_game) clone,
 * simulating the landing of ESAs Philae on 67P/Churyumov-Gerasimenko.
 * 
 * The main class of PhilaesQuest. A GraphicsApp, creating and controlling two scenes to
 * display the game screen and the end screen, updated and drawn within the draw loop. 
 * Relevant KeyEvents are pushed into the scenes, using the KeyControllable interface which 
 * the scene class implements. PhilaeQuest implements the GameStateListener interface to be 
 * informed about relevant in-game events.
 * 
 * @author Andreas Hennings
 * @version 1.0
 *
 * 
 * [Note: engine sounds were implemented but later removed due to a lagging performance caused
 * by linux sound driver issues]
 * 
 */
@SuppressWarnings("serial")
public class PhilaesQuest extends GraphicsApp implements GameStateListener 
{
	private Sound gameMusic;
	
	private GameScene gameScene;
	private EndScene endScene;
	
	private boolean gameActive;  //Variable to determine if the game is running (=true) or not (=false). Used by draw() method

	public void setup() 
	{
		//Setup dimensions of game screen, initialize gameScene and set the game's status to 'running'
		size(GameConfig.GAME_WIDTH, GameConfig.GAME_HEIGHT);
		initGameScene();
		gameActive=true;
		
		// Initialize gameMusic and start loop-playing it:
		gameMusic = new Sound(GameConfig.SOUNDS_BACKGROUND_MUSIC);
		gameMusic.loop();
	}
	
	/*
	 * ******************************************************************************
	 * Method to initialize, activate and show the gameScene, called from setup above
	 * ******************************************************************************
	 */

	private void initGameScene() 
	{
		gameScene= new GameScene(this, 0, 0, GameConfig.GAME_WIDTH, GameConfig.GAME_HEIGHT);
		gameScene.activate();
		gameScene.show();		
	}
	
	/*
	 * ***********************************************************************************
	 * Method to initialize, activate and show the endScene, called from setEndState below
	 * ***********************************************************************************
	 */
	
	private void initEndScene() 
	{
		endScene= new EndScene(this, 0, 0, GameConfig.GAME_WIDTH, GameConfig.GAME_HEIGHT);
		endScene.activate();
		endScene.show();		
	}

	/*
	 * ******************************************************************************
	 * If gameActive is true, gameScene is updated and drawn. Else, endScene is drawn
	 * ******************************************************************************
	 */
	
	public void draw() 
	{
		if (gameActive)
		{
			gameScene.update();
			gameScene.draw();
		}
		
		else
		{
			endScene.draw();
		}		
	}
	
	/*
	 * *****************************************************************************
	 * setEndState is called from the methods below it, it initializes the endScene,
	 * informs it about the gameResult, switches the gameActive boolean variable,
	 * and deactivates the gameScene
	 * *****************************************************************************
	 */
	
	private void setEndState(boolean landedSafely) 
	{
		initEndScene();
		endScene.setGameResult(landedSafely);
		
		gameActive=false;
		gameScene.hide();
		gameScene.deactivate();
	}

	/*
	 * *********************************************************************
	 * The following methods are called from gameScene and send a boolean to
	 * setEndState: true if the game is won, false if the game is lost.
	 * *********************************************************************
	 */
	
	@Override
	public void gameLost() 
	{
		setEndState(false);
	}

	@Override
	public void gameWon() 
	{
		setEndState(true);
	}
	
	/*
	 * ***********************************************************************
	 * The following methods catch arrow key inputs and send them to gameScene
	 * ***********************************************************************
	 */

	@Override
	public void keyPressed(KeyEvent e) 
	{
		switch (e.getKeyCode())
		{
			case UP: 
			gameScene.onArrowUpPressed();
			break;
			
			case DOWN: 
			gameScene.onArrowDownPressed();
			break;
		
			default:
			break;
		}
	}
		
	@Override
	public void keyReleased(KeyEvent e) 
	{
		switch (e.getKeyCode())
		{
			case UP: 
			gameScene.onArrowUpReleased();
			break;
			
			case DOWN: 
			gameScene.onArrowDownReleased();
			break;
		
			default:
			break;
		}
	}
}
