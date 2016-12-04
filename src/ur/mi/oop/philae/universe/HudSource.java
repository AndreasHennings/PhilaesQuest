package ur.mi.oop.philae.universe;

/**
 * This interface is used to define an information source for the HUD-Actor. 
 * A HudSource provides public methods to get perceptual values for the current speed
 * and fuel status.
 * 
 * @author Alexander Bazo
 * @version 1.0
 *
 */
public interface HudSource {

		public double getFuelInPercent();
		public double getSpeedInPercent();
}
