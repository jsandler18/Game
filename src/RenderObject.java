import java.awt.geom.Rectangle2D;

/**
 * A RenderObject will be something that can be rendered on screen. 
 * This includes the background, walls, floors, enemies, death pits, etc.
 * The first four arguments to the constructor represent the x position and y position 
 * of the to left corner of the object, and the width and height of the object.
 * The last argument is a single char denoting what thing the object will be rendered as.
 * <br></br>
 * 'b' is for background, 'w' is for walls, 'f' is for floors/ceilings, 'd' is for deadly objects.
 * 
 * @author Jake
 *
 */
@SuppressWarnings("serial")
public class RenderObject extends Rectangle2D.Double {

	private char type;
	
	public RenderObject(double posx, double posy, double width, double height, char type) {
		super(posx, posy, width, height);
		this.type=type;
	}
	
	public char getType(){
		return this.type;
	}

}
