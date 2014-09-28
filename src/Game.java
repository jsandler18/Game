import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;



/*
 * creating the collision method for the Player class
 */
public class Game extends JPanel implements Runnable {
 
	GameEvent event;
	
	public Player player;
	
	public double velx=0;
	public double vely=0;
	public double posx=400;
	public double posy=400;
	
	public double respawnPosx=400;
	public double respawnPosy=400;
	
	public double lastValidPosx=400;
	public double lastValidPosy=400;
	
	final private double accy=2500;
	
	final private double frameRate = 1/48.0; //seconds per frame
	private long frameStart;
	private long frameEnd;
	private double frameTime;
	private double renderTime=0;
	
	private ArrayList<RenderObject> renderObjects;
	
	public Game() {
		event=new GameEvent(this);
		
		JFrame window = new JFrame("Game");
		window.setSize(818,847);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(this);
		this.setFocusable(true);
		window.setVisible(true);
		this.addKeyListener(event);
		player = new Player(posx,posy,25,25,this);
		renderObjects = new ArrayList<RenderObject>();
		repaint();
		
		Thread go = new Thread(this);
		go.start();
	}


	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		Rectangle2D.Double background = new Rectangle2D.Double(0,0,800,800);
		g2d.setColor(Color.black);
		g2d.fill(background);
		
		
		
		player.setRect(posx,posy,25,25);
		g2d.setColor(Color.white);
		g2d.fill(player);
		
		renderObjects.add(new RenderObject(0,700,800,100,'w'));
		g2d.setColor(Color.blue);
		g2d.fill(renderObjects.get(0));
		
	}
	
	
	@Override
	public void run() {
		frameStart = System.currentTimeMillis();
		while(true){
			frameEnd = System.currentTimeMillis();
			frameTime = (frameEnd-frameStart)/1000.0;
			frameStart = System.currentTimeMillis();

			vely+=(accy*frameTime);
			
			player.collide(renderObjects);
			
			lastValidPosx=posx;
			lastValidPosy=posy;

			posx+=velx*frameTime;
			posy+=vely*frameTime;

			
			if(renderTime>frameRate){

				repaint();
				renderTime=0;
			}
			renderTime+=frameTime;
			try{
				Thread.sleep(1);
			}catch (Exception e){
				System.out.println("thread stop error");
			}
		}

	}

	public static void main(String[] args) {
		Game start = new Game();

	}

}
