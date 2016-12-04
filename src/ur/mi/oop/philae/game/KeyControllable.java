package ur.mi.oop.philae.game;

/**
 * This interface is used to pass matching key events to components, 
 * which are controllable by using the arrow key (UP and DOWN)
 * 
 * @author Alexander Bazo
 * @version 1.0
 *
 */
public interface KeyControllable {
	
	public void onArrowUpPressed();
	public void onArrowDownPressed();
	
	public void onArrowUpReleased();
	public void onArrowDownReleased();

}
