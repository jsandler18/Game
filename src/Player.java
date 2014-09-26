import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;

/**
 * This class represents the player.  There should only be one instance of this class at any given time.
 * It is constructed like a Rectangle2D.Double, but also takes the Game class as its final argument so it
 *  can manipulate position and velocity. It contains the methods for collision detection and reaction.
 * @author Jake
 *
 */
public class Player extends Double {
	
	Game main;
	
	public Player(double posx, double posy, double width, double height, Game in){
		super(posx,posy,width,height);
		this.main=in;
	}
	
	/**
	 * This method takes the game's ArrayList of RenderObjects and tests them for an intersection.
	 * it then reacts based on the type of RenderObject it is colliding with.  
	 * @param renderedObjects
	 */
	public void collide(ArrayList renderedObjects){
		
	}

}
