import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.security.acl.LastOwnerException;
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
	public void collide(ArrayList<RenderObject> renderedObjects){
		RenderObject temp;
		for(int x = 0; x < renderedObjects.size(); x++){
			temp = renderedObjects.get(x);
			if(this.intersects(temp) && temp.getType()=='w'){
				main.posx=main.lastValidPosx;
				main.posy=main.lastValidPosy;
				main.velx=0;
				main.vely=0;

				try{
					Thread.sleep(10000);
				}catch(Exception e){}
			}else{
			}
			
		}
	}
	
	public boolean intersects(Rectangle2D.Double test){
		if(test.contains(this.getX(),this.getY())){
			return true;
		}
		else if(test.contains(this.getX()+this.getWidth(), this.getY())){
			return true;
		}
		else if(test.contains(this.getX(),this.getY()+this.getHeight())){
			return true;
		}
		else if(test.contains(this.getX()+this.getWidth(), this.getY()+this.getHeight())){
			return true;
		}
		return false;
	}

}
