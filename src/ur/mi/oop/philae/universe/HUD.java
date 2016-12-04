package ur.mi.oop.philae.universe;

import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Label;
import de.ur.mi.graphics.Rect;
import ur.mi.oop.philae.config.GameConfig;
import ur.mi.oop.philae.scenes.Actor;
import ur.mi.oop.philae.scenes.Scene;

public class HUD extends Actor
{
	private Lander lander;
	private Color speedMeterColor;
	private Color fuelMeterColor;
	public static final int SPEEDMETER_BORDERSIZE = 5;
	

	public HUD(Scene scene, Lander lander, double x, double y, double width, double height,
			String backgroundFile) 
	{
		super(scene, x, y, width, height, backgroundFile);
		this.lander = lander;
	}
	
	public void draw() 
	{
		drawFuelIndicator();
		drawSpeedIndicator();
	}
	
	/*
	 * **************************************************************************
	 * The drawFuelIndicator method implements all elements of the fuel-indicator
	 * **************************************************************************
	 */
	
	private void drawFuelIndicator() 
	{
		setFuelMeterColor();
		drawFuelMeter();
		drawFuelLabel();
	}

	/*
	 ******************************************************************************* 
	 * setFuelMeterColor checks the relative speed of the lander and switches the
	 * color of the fuelmeter (= the bars on the left side of the screen) from green 
	 * to red if the amount of fuel left is smaller than 
	 * GameConfig.MISSION_CONTROL_FUEL_LIMIT (= 40 %)
	 * *****************************************************************************
	 */
	
	private void setFuelMeterColor() 
	{
		if (lander.getFuelInPercent()<=GameConfig.MISSION_CONTROL_FUEL_LIMIT)
		{
			fuelMeterColor = GameConfig.UI_FUEL_INDICATOR_WARNING_COLOR;
		}
		
		else
		{
			fuelMeterColor = GameConfig.UI_FUEL_INDICATOR_COLOR;
		}
	}
	
	/*
	 * **************************************************************************
	 * drawFuelMeter represents the amount of fuel left by displaying bars on the
	 * left side of the screen. For every 10 % fuel burned, one bar disappears.
	 * First the number of bars is calculated. Then, the bars are displayed using
	 * values stored in GameConfig.
	 * **************************************************************************
	 */
	
	private void drawFuelMeter() 
	{
		int numFuelBars = (int) (lander.getFuelInPercent()/10)+1;
		
		for (int bars = 1; bars < numFuelBars; bars++)
		{
			Rect fuelBar = new Rect(GameConfig.UI_HUD_ELEMENTS_OFFSET, GameConfig.UI_HUD_ELEMENTS_Y_BOTTOM-(bars*10), (int) GameConfig.UI_HUD_BARS_WIDTH, (int) GameConfig.UI_HUD_BARS_HEIGHT, fuelMeterColor);
			fuelBar.draw();
		}
	}
	
	private void drawFuelLabel() 
	{
		Label fuelLabel = new Label (GameConfig.UI_HUD_ELEMENTS_OFFSET+GameConfig.UI_HUD_BARS_WIDTH, GameConfig.UI_HUD_ELEMENTS_Y_BOTTOM, GameConfig.UI_FUEL_LABEL, GameConfig.UI_SPEED_INDICATOR_COLOR, GameConfig.UI_TEXT_SIZE);
		fuelLabel.draw();
	}
	
	/*
	 * *******************************************************************
	 * The following methods implement all elements of the speed-indicator
	 * *******************************************************************
	 */
		
	private void drawSpeedIndicator() 
	{
		setSpeedMeterColor();
		drawSpeedLabel();
		drawSpeedBox();
		drawSpeedMeter();
	}
	
	private void setSpeedMeterColor() 
	{
		if (lander.getSpeedInPercent()>GameConfig.MISSION_CONTROL_SPEED_LIMIT)
		{
			speedMeterColor = GameConfig.UI_SPEED_INDICATOR_WARNING_COLOR;
		}
		
		else
		{
			speedMeterColor = GameConfig.UI_SPEED_INDICATOR_SAFETY_COLOR;
		}
	}
	
	private void drawSpeedLabel() 
	{
		Label speedLabel = new Label(GameConfig.UI_SPEEDLABEL_X, GameConfig.UI_HUD_ELEMENTS_Y_BOTTOM, GameConfig.UI_SPEED_LABEL, GameConfig.UI_SPEED_INDICATOR_COLOR, GameConfig.UI_TEXT_SIZE);
		speedLabel.draw();
	}

	private void drawSpeedBox() 
	{
		Rect speedBox = new Rect (GameConfig.UI_SPEEDMETER_X, GameConfig.UI_HUD_ELEMENTS_Y_BOTTOM-GameConfig.MISSION_CONTROL_MAX_SPEED, (int) GameConfig.UI_HUD_BARS_WIDTH, (int) GameConfig.UI_HUD_HEIGHT,Color.TRANSPARENT);
		speedBox.setBorder(GameConfig.UI_SPEED_INDICATOR_COLOR, SPEEDMETER_BORDERSIZE);
		speedBox.draw();		
	}
	
	private void drawSpeedMeter() 
	{
		Rect speedMeter = new Rect (GameConfig.UI_SPEEDMETER_X+(SPEEDMETER_BORDERSIZE/2), GameConfig.UI_HUD_ELEMENTS_Y_BOTTOM-Math.abs(lander.getSpeedInPercent()), (int) GameConfig.UI_HUD_BARS_WIDTH-(SPEEDMETER_BORDERSIZE-1), (int) Math.abs(lander.getSpeedInPercent()), speedMeterColor);
		speedMeter.draw();
	}
}
