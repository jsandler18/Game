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
			if(this.intersects(temp) && temp.getType()=='f'){
				main.posx=main.lastValidPosx;
				main.posy=main.lastValidPosy;
				if(this.directionY(temp)==2 && main.vely<0){
					main.vely=0;
				}
				else if(this.directionY(temp)==0 && main.vely>0){
					main.vely=0;
					main.accy=0;
				}
			}
			else if(this.intersects(temp) && temp.getType()=='w'){
				main.posx=main.lastValidPosx;
				main.posy=main.lastValidPosy;
				if(this.directionX(temp)==1 && main.velx<0){
					main.velx=0;
				}
				else if(this.directionX(temp)==3 && main.velx>0){
					main.velx=0;
				}
			}else{
				main.lastValidPosx=main.posx;
				main.lastValidPosy=main.posy;
				main.accy=2500;
			}
			
		}
	}
	
	/**
	 * This method tests if there is any 
	 * intersection between the player and 
	 * the passed Rectangle2D.Double
	 * @param test
	 * @return boolean
	 */
	public boolean intersects(Rectangle2D.Double test){
		if(test.contains(main.posx,main.posy)){
			return true;
		}
		else if(test.contains(main.posx+this.getWidth(), main.posy)){
			return true;
		}
		else if(test.contains(main.posx,main.posy+this.getHeight())){
			return true;
		}
		else if(test.contains(main.posx+this.getWidth(), main.posy+this.getHeight())){
			return true;
		}
		return false;
	}
	
	/**
	 * This method tests which side of the passed Rectangle2D.Double 
	 * the player is colliding with. It assumes that a collision has already been detected.
	 * 
	 * @param test
	 * @return an integer representing one of 4 directions.  0 is up, 1 is right, 2 is down and 3 is left
	 */
	public int directionX(Rectangle2D.Double test){
		if(main.posx<test.getX()){
			return 3;
		}
		else if(main.posx>test.getX()){
			return 1;
		}
		return 3;
	}
	public int directionY(Rectangle2D.Double test){
		if(main.posy>test.getY()){
			return 2;
		}
		else if(main.posy< test.getY()){
			return 0;
		}
		return 0;
	}
}
