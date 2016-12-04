package ur.mi.oop.philae.game;

/**
 * This interface is used to inform components about relevant game events,
 * in particular when the player din win or lose the game.
 * 
 * @author Alexander Bazo
 * @version 1.0
 *
 */
public interface GameStateListener {
	
	public void gameLost();
	public void gameWon();

}
