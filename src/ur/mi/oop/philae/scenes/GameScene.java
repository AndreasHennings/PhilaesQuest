package ur.mi.oop.philae.scenes;

import de.ur.mi.graphics.Image;
import ur.mi.oop.philae.config.GameConfig;
import ur.mi.oop.philae.game.GameStateListener;
import ur.mi.oop.philae.universe.Comet;
import ur.mi.oop.philae.universe.HUD;
import ur.mi.oop.philae.universe.Lander;
import ur.mi.oop.philae.universe.LanderMode;
import ur.mi.oop.philae.universe.Orbiter;
import ur.mi.oop.philae.universe.Space;

/*
 * *************************************************************************************************************
 * This class handles all actors and actor interaction during gameplay and determines if the game is lost or won
 * It is initialized by and called from the PhilaeQuest main method
 * *************************************************************************************************************
 */

public class GameScene extends Scene
{	
	/*
	 * *********************************************************************
	 * Following, all actors and images are declared. 
	 * Please see the initActors for further details on their implementation
	 * *********************************************************************
	 */
	
	private Space space;
	private Comet comet;
	private Orbiter orbiter;
	private Lander lander;
	private HUD hud;
	
	private Image landerEngineOff;
	private Image landerEngineUp;
	private Image landerEngineDown;

	public GameScene(GameStateListener gameStateListener, int x, int y, int width, int height) 
	{
		super(gameStateListener, x, y, width, height);
		initActors();
		initLanderImages();
	}
	
	public void update()
	{
		super.update();
		checkGameState();  //See the method below for further information
	}
	
	/*
	 * **********************************************************************
	 * The following method checks if the lander has run out of fuel while
	 * still not on the comets surface or has crashed (relative speed greater
	 * than GameConfig.MISSION_CONTROL_SPEED_LIMIT), in which case the game
	 * is lost. If the lander landed safely, the game is won. In both cases
	 * the PhilaeQuest main class is informed via gameStateListener
	 * **********************************************************************
	 */
	
	private void checkGameState() 
	{
		if ((getDistance()>0) && (lander.getFuelInPercent()<=0)
		|| ((getDistance()<=0) && (lander.getSpeedInPercent()>GameConfig.MISSION_CONTROL_SPEED_LIMIT)))
		{
			gameStateListener.gameLost();
		}
		
		if ((getDistance()<=0) && (lander.getSpeedInPercent()<GameConfig.MISSION_CONTROL_SPEED_LIMIT))
		{
			gameStateListener.gameWon();
		}
	}
	
	/*
	 * *********************************************************************
	 * The initLanderImages method loads the three different representations 
	 * of the lander and initialize them to the current y-position
	 * *********************************************************************
	 */
	
	private void initLanderImages() 
	{
		landerEngineOff = new Image(GameConfig.CENTER_X, lander.getY(), (int) GameConfig.OBJECTS_LANDER_WIDTH, (int) GameConfig.OBJECT_LANDER_HEIGHT, "../assets/philae_engine_off.png");
		landerEngineUp = new Image(GameConfig.CENTER_X, lander.getY(), (int) GameConfig.OBJECTS_LANDER_WIDTH, (int) GameConfig.OBJECT_LANDER_HEIGHT, "../assets/philae_moving_up.png");
		landerEngineDown = new Image(GameConfig.CENTER_X, lander.getY(), (int) GameConfig.OBJECTS_LANDER_WIDTH, (int) GameConfig.OBJECT_LANDER_HEIGHT, "../assets/philae_moving_down.png");		
	}
	
	/*
	 * **********************************************************************
	 * The following methods initialize the single actors used in this scene: 
	 * They receive necessary information about their 
	 * - scene (parameter 1)
	 * - position (x and y, parameters 2 and 3)
	 * - dimensions (i. e. with and height, parameters 4 and 5) 
	 * - visual representation (background image, parameter 6)
	 * (see the GameConfig for specific values)
	 * Then they are added to the scene's actors arrayList
	 * **********************************************************************
	 */

	private void initActors()
	{
		space = new Space(this, GameConfig.CENTER_X, GameConfig.CENTER_Y, GameConfig.GAME_WIDTH, GameConfig.GAME_HEIGHT, GameConfig.ASSETS_SPACE_BACKGROUND);
		this.addActor(space);
		
		comet = new Comet(this, GameConfig.CENTER_X, GameConfig.OBJECTS_COMET_Y_POSITION, GameConfig.OBJECTS_COMET_RADIUS, GameConfig.OBJECTS_COMET_RADIUS, GameConfig.ASSETS_COMET_BACKGROUND);
		this.addActor(comet);
		
		orbiter = new Orbiter(this, GameConfig.MISSION_CONTROL_INITIAL_ORBITER_POSITION, GameConfig.MISSION_CONTROL_INITIAL_ORBITER_POSITION, GameConfig.OBJECTS_ORBITER_WIDTH, GameConfig.OBJECTS_ORBITER_HEIGHT, GameConfig.ASSETS_ORBITER_BACKGROUND);
		this.addActor(orbiter);
		
		lander = new Lander(this, GameConfig.CENTER_X, 0, GameConfig.OBJECTS_LANDER_WIDTH, GameConfig.OBJECT_LANDER_HEIGHT, GameConfig.ASSETS_LANDER_BACKGROUND_ENGINE_OFF);
		this.addActor(lander);
		
		hud = new HUD(this, lander, GameConfig.CENTER_X, GameConfig.CENTER_Y, GameConfig.GAME_WIDTH, GameConfig.GAME_HEIGHT, null);
		this.addActor(hud);
	}
	
	/*
	 * *****************************************************************
	 * The following methods are called from the main PhilaesQuest-class
	 * and implement user interaction (= pressing arrow keys on keyboard)
	 * by causing the lander to change
	 * - its visual representation
	 * - its engine's mode
	 * - its fuel status
	 * *****************************************************************
	 */

	@Override
	public void onArrowUpPressed() 
	{
		lander.setBackgroundImage(landerEngineUp);
		lander.setLanderEngine(LanderMode.ENGINE_FIRING_UPWARDS);
		lander.setFuelConsumption();
	}

	@Override
	public void onArrowDownPressed() 
	{
		lander.setBackgroundImage(landerEngineDown);
		lander.setLanderEngine(LanderMode.ENGINE_FIRING_DOWNWARDS);	
		lander.setFuelConsumption();
	}

	@Override
	public void onArrowUpReleased() 
	{
		lander.setBackgroundImage(landerEngineOff);
		lander.setLanderEngine(LanderMode.ENGINE_OFF);
	}

	@Override
	public void onArrowDownReleased() 
	{
		lander.setBackgroundImage(landerEngineOff);	
		lander.setLanderEngine(LanderMode.ENGINE_OFF);
	}
	
	/*
	 * ************************************************************************
	 * The following methods are used to calculate the exact distance between
	 * the lander and the comets surface. The getDistance method is used by the
	 * checkGameState method above.
	 * ************************************************************************
	 */
	
	public double getDistance()
	{
		return (getCometTop() - getLanderBottom());
	}
	
	private double getCometTop() 
	
	{
		return GameConfig.OBJECTS_COMET_Y_POSITION-GameConfig.MISSION_CONTROL_LANDING_ZONE_DISTANCE;
	}
	
	private double getLanderBottom()
	{
		return lander.getY()+GameConfig.OBJECT_LANDER_HEIGHT;
	}
	
	
}
