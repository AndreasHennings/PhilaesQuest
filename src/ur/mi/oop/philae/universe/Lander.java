package ur.mi.oop.philae.universe;

import ur.mi.oop.philae.config.GameConfig;
import ur.mi.oop.philae.scenes.Actor;
import ur.mi.oop.philae.scenes.Scene;
import ur.mi.oop.philae.universe.LanderMode;

public class Lander extends Actor implements HudSource
{
	private double fuel;
	private double speedInPercent; // Speed in percentage (0-100 %)
	private double speedInPixels; // Speed in pixels per frame (0-5 pixels)
	
	LanderMode landerMode;
	
	public Lander(Scene scene, double x, double y, double width, double height, String backgroundFile) 
	{
		super(scene, x, y, width, height, backgroundFile);
		speedInPercent = 0.0;
		fuel = 100;
		landerMode= LanderMode.ENGINE_OFF;
	}
	
	/*
	 * ****************************************************************
	 * setLanderEngine is called from gameScene and sets the LanderMode
	 * to the correct values stored in the LanderMode-enum based on
	 * which arrow key is pressed.
	 ****************************************************************** 
	 */
	
	public void setLanderEngine (LanderMode landerMode)
	{
		this.landerMode = landerMode;
	}
	
	/*
	 * ************************************************************************
	 * In the update method, the lander first checks if it has left the top
	 * border of the screen. Then, according to the value stored in landerMode,
	 * the relative speed is altered. Finally, its position is updated.
	 * ************************************************************************
	 */
	
	public void update()
	{
		checkUpperBorderCollision();
		
		switch(this.landerMode)
		{
			case ENGINE_FIRING_UPWARDS:
				
				speedInPercent-=1;
				checkSpeedLimit();
				break;
				
			case ENGINE_FIRING_DOWNWARDS:
				
				speedInPercent+=1;
				checkSpeedLimit();
				break;
				
			case ENGINE_OFF:
				
				speedInPercent+=GameConfig.MISSION_CONTROL_ORBIT_GRAVITATION;
				checkSpeedLimit();
				break;	
		}
		
		updateLanderPosition();		
	}
	
	/*
	 * ***************************************************************
	 * updateLanderPosition calculates the number of pixels the lander
	 * is moved, updates the landers y-value and finally moves it
	 * ***************************************************************
	 */
	
	private void updateLanderPosition() 
	{
		speedInPixels = (GameConfig.MISSION_CONTROL_MAX_ABSOLUTE_SPEED/100)*speedInPercent; 
		y += speedInPixels; // updating y-position of lander
		move(0, speedInPixels); // moving lander
	}
	
	

	
	
	/*
	 * ************************************************************************
	 * checkSpeedLimit makes sure the lander does not move faster than 100 %.
	 * If it does, its speed is reduced to GameConfig.MISSION_CONTROL_MAX_SPEED
	 * ************************************************************************
	 */
	
	private void checkSpeedLimit() 
	{
		if (speedInPercent>GameConfig.MISSION_CONTROL_MAX_SPEED)
		{
			speedInPercent=GameConfig.MISSION_CONTROL_MAX_SPEED;
		}
	}
	
    /* 
     * *********************************************************************************
     * Lander must not leave visible frame, therefore position and speed are set to zero 
     * if the lander's vertical position is <0 in checkUpperBorderCollision
     * *********************************************************************************
     */
	
	private void checkUpperBorderCollision() 
	{
		if (this.y<0)
		{
			this.y = 0;
			speedInPercent = 0;
		}
	}
	
	/*
	 * ******************************************************************************
	 * setFuelConsumption is called from gameScene every time an arrow-key is pressed
	 * and reduces the amount of fuel left in the tank
	 * ******************************************************************************
	 */
	
	public void setFuelConsumption()
	{
		fuel-=1;
	}
	
	/*
	 * ***********************************************************************
	 * getFuelInPercent and getSpeedInPercent return the corresponding values.
	 * They are needed by gameScene to check if the game is lost or won and by
	 * the HUD-class for displaying them
	 * ***********************************************************************
	 */
	
	public double getFuelInPercent()
	{
		return fuel;
	}
	
	public double getSpeedInPercent()
	{
		return speedInPercent;
	}
}
