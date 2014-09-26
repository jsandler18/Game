import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;



/*
 * left off trying to think of a way to make collidable objects that are not preset bounds
 */
public class Game extends JPanel implements Runnable {
 
	GameEvent event;
	
	public double velx=0;
	public double vely=0;
	public double posx=400;
	public double posy=400;
	
	final private double accy=2000;
	
	private long frameStart;
	private long frameEnd;
	private double frameTime;
	
	public Game() {
		event=new GameEvent(this);
		
		JFrame window = new JFrame("Game");
		window.setSize(818,847);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(this);
		this.setFocusable(true);
		window.setVisible(true);
		this.addKeyListener(event);
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
		
		
		
		Rectangle2D.Double player = new Rectangle2D.Double(posx,posy,50,50);
		g2d.setColor(Color.white);
		g2d.fill(player);
		
	}
	
	
	@Override
	public void run() {
		frameStart = System.currentTimeMillis();
		while(true){
			frameEnd = System.currentTimeMillis();
			frameTime = (frameEnd-frameStart)/1000.0;
			frameStart = System.currentTimeMillis();
			vely+=(accy*frameTime);
			posx+=velx*frameTime;
			posy+=vely*frameTime;
			
			if(posy>725){
				posy=725;
				vely=0;
			}
			
			repaint();
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
