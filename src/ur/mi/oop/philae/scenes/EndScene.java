package ur.mi.oop.philae.scenes;

import ur.mi.oop.philae.config.GameConfig;
import ur.mi.oop.philae.game.GameStateListener;
import ur.mi.oop.philae.universe.GameLostImage;
import ur.mi.oop.philae.universe.GameWonImage;

/*
 * *************************************************************************
 * This class is responsible for showing the final gameLost or gameWon image
 * It is initialized by and called from the PhilaeQuest main method
 * *************************************************************************
 */

public class EndScene extends Scene
{
	/*
	 * *********************************************************************
	 * Following, both actors are declared. 
	 * Please see the initActors for further details on their implementation
	 * *********************************************************************
	 */
	
	GameWonImage gameWonImage;
	GameLostImage gameLostImage;
	
	public EndScene(GameStateListener gameStateListener, int x, int y,int width, int height) 
	{
		super(gameStateListener, x, y, width, height);
		initActors();
	}
	
	/*
	 * *******************************************************************
	 * initActors initializes the gameWonImage and the gameLostImage. Both
	 * receive necessary information about their:
	 * - scene (parameter 1)
	 * - position (x and y, parameters 2 and 3)
	 * - dimensions (i. e. with and height, parameters 4 and 5) 
	 * - visual representation (background image, parameter 6)
	 * *******************************************************************
	 */
	
	private void initActors() 
	{
		gameWonImage = new GameWonImage(this, GameConfig.CENTER_X, GameConfig.CENTER_Y, GameConfig.GAME_WIDTH, GameConfig.GAME_HEIGHT, GameConfig.ASSETS_WON_SCREEN_BACKGROUND);
		gameLostImage = new GameLostImage(this, GameConfig.CENTER_X, GameConfig.CENTER_Y, GameConfig.GAME_WIDTH, GameConfig.GAME_HEIGHT, GameConfig.ASSETS_LOST_SCREEN_BACKGROUND);
	}
	
	/*
	 * ********************************************************************************
	 * setGameResult is called from the PhilaesQuest main method. It receives a boolean
	 * variable that determines if the game is lost or won. Based on that, either the
	 * gameWonImage or the gameLostImage are added as actors to endScene.
	 * ********************************************************************************
	 */
	
	public void setGameResult(boolean gameResult)
	{
		if (gameResult)
		{
			this.addActor(gameWonImage);
		}
		else
		{
			this.addActor(gameLostImage);
		}
	}
	
	/*
	 * ***************************************************************************
	 * The following methods are auto-implemented by scene() and have no function.
	 * They can be ignored.
	 * ***************************************************************************
	 */
	
	@Override
	public void onArrowDownPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onArrowUpReleased() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onArrowDownReleased() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onArrowUpPressed() {
		// TODO Auto-generated method stub
		
	}

	

}
