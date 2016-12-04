package ur.mi.oop.philae.scenes;

import de.ur.mi.graphics.GraphicsObject;
import de.ur.mi.graphics.Image;

/**
 * The actor class represents a in game object that is updated and drawn by a
 * scene. Each actor consists of a background image which is drawn each time the
 * draw() method is called. The actors position is given by the coordinates of
 * its center.
 * 
 * @author Alexander Bazo
 * @version 1.0
 *
 */
public abstract class Actor extends GraphicsObject 
{

	protected Scene scene;
	private Image backgroundImage;
	private boolean visible;

	public Actor(Scene scene, double x, double y, double width, double height, String backgroundFile) 
	{
		super(x - width / 2, y - height / 2);
		this.scene = scene;
		this.visible = true;
		if (backgroundFile != null) 
		{
			backgroundImage = new Image(this.x, this.y, (int) width,
					(int) height, backgroundFile);
		}

	}

	protected void setBackgroundImage(Image backgroundImage) 
	{
		this.backgroundImage = backgroundImage;
	}

	public void draw() 
	{
		if (!visible) 
		{
			return;
		}
		if (backgroundImage != null) 
		{
			backgroundImage.draw();
		}
	}

	public void update() 
	{
	
	}

	@Override
	public void move(double dx, double dy) 
	{
		super.move(dx, dy);
		if (backgroundImage != null) 
		{
			backgroundImage.setPosition(x, y);
		}
	}

	@Override
	public void setPosition(double x, double y) 
	{
		super.setPosition(x - width / 2, y - height / 2);
		if (backgroundImage != null) 
		{
			backgroundImage.setPosition(this.x, this.y);
		}
	}

	public void hide() 
	{
		this.visible = false;
	}

	public void show() 
	{
		this.visible = true;
	}

}
