package ur.mi.oop.philae.universe;


import de.ur.mi.geom.Point;
import ur.mi.oop.philae.config.GameConfig;
import ur.mi.oop.philae.scenes.Actor;
import ur.mi.oop.philae.scenes.Scene;

/*
 * **********************************************************************
 * This class is used for implementing orbiter. It receives all necessary
 * parameters from gameScene. Please look there for parameter details.
 * **********************************************************************
 */

public class Orbiter extends Actor
{
	private static final int MAX_ANGLE=360;
	private Point[] orbiterPositions;  //Declaring array for storing orbiter positions
	private int angleCounter;  //Declaring counter for angle increment

	public Orbiter(Scene scene, double x, double y, double width, double height, String backgroundFile) 
	{
		super(scene, x, y, width, height, backgroundFile);
		orbiterPositions = new Point [MAX_ANGLE];  //Initializing array to store all orbiter positions
		angleCounter=0;  //starting angle is zero
		precalculateOrbiterPosition();  //please see corresponding method for details
	}
	
	public void update()
	{
		setPosition(orbiterPositions[angleCounter].getX(),orbiterPositions[angleCounter].getY());
		updateAngleCounter();
	}
	
	/*
	 * *****************************************************************************
	 * The updateAngleCounter method increases the value of angleCounter and resets
	 * it to zero if it reaches MAX_ANGLE (= 360 degrees)
	 * ****************************************************************************
	 */
	
	private void updateAngleCounter() 
	{
		angleCounter++;
		if (angleCounter==MAX_ANGLE)
		{
			angleCounter=0;
		}
	}
	
	/*
	 * *******************************************************************************
	 * The precalculateOrbiterPosition method pre-calculates the orbiters position and 
	 * stores them in the orbiterPositions array. As the values need not be calculated 
	 * during runtime, the application runs a little faster. 
	 * A little trick commonly used in n-body simulations.
	 * ******************************************************************************
	 */

	private void precalculateOrbiterPosition() 
	{
		for (int i = 0; i<MAX_ANGLE; i++)
		{
			orbiterPositions[i] = new Point ( (scene.getWidth()/2) + ( (scene.getWidth()*0.5) * (Math.sin(Math.toRadians(i)))), 
			(scene.getHeight()/2) + (scene.getHeight()*0.5) * (Math.cos(Math.toRadians(i))));
		}
	}	
}
