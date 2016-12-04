package ur.mi.oop.philae.config;

import de.ur.mi.graphics.Color;

/**
 * This class stores all relevant values used in the game. Use these values
 * to initializes and control the game. Each value can be reference by using 
 * the class name as a static Reference: GameConfig.STATIC_CONSTANT_NAME
 * 
 * @author Alexander Bazo
 * @version 1.0
 *
 */
public class GameConfig {
	
	// Window size
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 800;
	
	// Window center
	public static final double CENTER_X = GAME_WIDTH/2;
	public static final double CENTER_Y = GAME_HEIGHT/2;
	
	
	// Background images and sounds for scenes and actors
	public static final String ASSETS_COMET_BACKGROUND = "../assets/planet.png";
	public static final String ASSETS_ORBITER_BACKGROUND = "../assets/rosetta.png";
	public static final String ASSETS_LANDER_BACKGROUND_ENGINE_OFF = "../assets/philae_engine_off.png";
	public static final String ASSETS_LANDER_BACKGROUND_ENGINE_UP = "../assets/philae_moving_up.png";
	public static final String ASSETS_LANDER_BACKGROUND_ENGINE_DOWN = "../assets/philae_moving_down.png";
	public static final String ASSETS_SPACE_BACKGROUND = "../assets/stars.png";
	public static final String ASSETS_WON_SCREEN_BACKGROUND = "../assets/game_won.png";
	public static final String ASSETS_LOST_SCREEN_BACKGROUND = "../assets/game_lost.png";
	public static final String SOUNDS_LANDER_ENGINE = "../assets/engines.wav";
	public static final String SOUNDS_BACKGROUND_MUSIC = "../assets/background.wav";
	
	// Relative dimensions of comet, orbiter and lander
	public static final double OBJECTS_COMET_RADIUS = GAME_WIDTH/4;
	public static final double OBJECTS_COMET_Y_POSITION = GAME_WIDTH*0.6;
	public static final double OBJECTS_ORBITER_WIDTH = GAME_WIDTH/4;
	public static final double OBJECTS_ORBITER_HEIGHT = OBJECTS_ORBITER_WIDTH/3;
	public static final double OBJECTS_LANDER_WIDTH = OBJECTS_ORBITER_HEIGHT;
	public static final double OBJECT_LANDER_HEIGHT = OBJECTS_ORBITER_HEIGHT;
	
	// Important values for mission simulation
	public static final int MAX_ANGLE=360;
	public static final double MISSION_CONTROL_SPEED_LIMIT = 30;
	public static final double MISSION_CONTROL_FUEL_LIMIT = 40;
	public static final double MISSION_CONTROL_LANDING_ZONE_DISTANCE = OBJECTS_COMET_RADIUS / 2.5;
	public static final double MISSION_CONTROL_ORBIT_GRAVITATION = 0.7;
	public static final double MISSION_CONTROL_MAX_SPEED = 100;
	public static final double MISSION_CONTROL_MAX_FUEL = 300;
	public static final double MISSION_CONTROL_MAX_ABSOLUTE_SPEED = 5;
	public static final double MISSION_CONTROL_FUEL_BURN_PER_FRAME = 1;
	public static final double MISSION_CONTROL_INITIAL_ORBITER_POSITION = 315;
	
	// Relative dimensions and text labels for UI components
	public static final double UI_HUD_HEIGHT = GAME_HEIGHT/8;
	public static final int UI_TEXT_SIZE = 30;
	public static final double UI_HUD_BARS_WIDTH = GAME_WIDTH/20;
	public static final double UI_HUD_BARS_HEIGHT = 9;
	public static final double UI_HUD_ELEMENTS_OFFSET = GAME_WIDTH/100;
	public static final double UI_HUD_ELEMENTS_Y_BOTTOM = GAME_HEIGHT*0.8;
	public static final double UI_SPEEDMETER_X = GAME_WIDTH-UI_HUD_BARS_WIDTH-UI_HUD_ELEMENTS_OFFSET;
	public static final double UI_SPEEDLABEL_X = UI_SPEEDMETER_X-(GAME_WIDTH/8.4);
	
	public static final Color UI_FUEL_INDICATOR_COLOR = Color.WHITE;
	public static final Color UI_FUEL_INDICATOR_WARNING_COLOR = Color.RED;
	public static final Color UI_SPEED_INDICATOR_COLOR = Color.WHITE;
	public static final Color UI_SPEED_INDICATOR_SAFETY_COLOR = Color.GREEN;
	public static final Color UI_SPEED_INDICATOR_WARNING_COLOR = Color.RED;
	public static final String UI_SPEED_LABEL = "Speed";
	public static final String UI_FUEL_LABEL = "Fuel";
	public static final Color UI_GAME_MESSAGE_COLOR = Color.BLACK;
	public static final String UI_GAME_WON_TEXT = "Congratulations! Philae landed safely";
	public static final String UI_GAME_LOST_TEXT = "Game Over! Philae crashed";

}
