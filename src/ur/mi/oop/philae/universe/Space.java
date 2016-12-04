package ur.mi.oop.philae.universe;

import ur.mi.oop.philae.scenes.Actor;
import ur.mi.oop.philae.scenes.Scene;

/*
 * ***********************************************************************************
 * This class is used for implementing the space background. It receives all necessary
 * parameters from gameScene. Please look there for parameter details.
 * ***********************************************************************************
 */

public class Space extends Actor 
{

	public Space(Scene scene, double x, double y, double width, double height, String backgroundFile) 
	{
		super(scene, x, y, width, height, backgroundFile);
	}

}
