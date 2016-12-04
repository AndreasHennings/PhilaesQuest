package ur.mi.oop.philae.scenes;

import java.util.ArrayList;

import ur.mi.oop.philae.game.GameStateListener;
import ur.mi.oop.philae.game.KeyControllable;
import de.ur.mi.geom.Point;

/**
 * A scene is a collection of several Actors, representing a certain state or
 * mode of the game. Each scene has a position and size within the games window.
 * New actors can be added and are automatically updated and drawn when the
 * scenes update respectively draw method are called. Visibility (draw) and
 * status can be furthermore controlled by show() (hide()) and activate()
 * (deactivate()).
 * 
 * @author Alexander Bazo
 * @version 1.0
 *
 */
public abstract class Scene implements KeyControllable 
{

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected boolean active = false;
	protected boolean visible = true;
	protected GameStateListener gameStateListener;

	private ArrayList<Actor> actors;

	public Scene(GameStateListener gameStateListener, int x, int y, int width,
			int height) 
	{
		this.gameStateListener = gameStateListener;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.actors = new ArrayList<Actor>();
	}

	protected void addActor(Actor actor) 
	{
		actors.add(actor);
	}

	public void update() 
	{
		if (!active) 
		{
			return;
		}
		for (Actor actor : actors) 
		{
			actor.update();
		}
	}

	public void draw() 
	{
		if (!visible) 
		{
			return;
		}
		for (Actor actor : actors) 
		{
			actor.draw();
		}
	}

	public Point getPosition() 
	{
		return new Point(x, y);
	}

	public void activate() 
	{
		active = true;
	}

	public void deactivate() 
	{
		active = false;
	}

	public void hide() 
	{
		visible = false;
	}

	public void show() 
	{
		visible = true;
	}

	public int getWidth() 
	{
		return width;
	}

	public int getHeight() 
	{
		return height;
	}

}
