import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;




public class Game extends JPanel implements Runnable {
 
	GameEvent event;
	
	public Player player;
	
	public double velx=0;
	public double vely=0;
	public double posx=400;
	public double posy=400;
	
	public double respawnPosx=400;
	public double respawnPosy=400;
	
	public double screenEnterX;
	public double screenEnterY;
	public double lastValidPosx=400;
	public double lastValidPosy=400;
	
	int screenX=0;
	int screenY=0;
	
	public boolean landed = false;
	
	public double accy=2500;
	
	final private double frameRate = 1/60.0; //seconds per frame
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
		this.requestFocusInWindow();
		window.setVisible(true);
		this.addKeyListener(event);
		player = new Player(posx,posy,25,25,this);
		renderObjects = new ArrayList<RenderObject>();
		loadScreen();
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

		
		for(int x = 0; x < renderObjects.size(); x++){
			if(renderObjects.get(x).getType()=='w' || renderObjects.get(x).getType()=='f'){
				g2d.setColor(Color.blue);
				g2d.fill(renderObjects.get(x));
			}
			else if(renderObjects.get(x).getType()=='d'){
				g2d.setColor(Color.red);
				g2d.fill(renderObjects.get(x));
			}
		}
		
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
			

			posx+=velx*frameTime;
			posy+=vely*frameTime;
			
			

			if(renderTime>frameRate){

				repaint();
				renderTime=0;
			}
			renderTime+=frameTime;
			try{
				Thread.sleep(4);
			}catch (Exception e){
				System.out.println("thread stop error");
			}
			loadScreen();
		}

	}
	
	/**
	 * This method is called whenever the player hits the edge of the screen.  
	 * It loads the RenderObjects for the next screen and changes the player's
	 * position to the opposite side that he exited the previous screen.
	 */
	private void loadScreen(){
		File screen;
		if(posx+25>800){
			screenX++;
			posx=0;
			screenEnterX=posx;
			screenEnterY=posy;
		}
		else if(posx<0){
			screenX--;
			posx=775;
			screenEnterX=posx;
			screenEnterY=posy;
		}
		else if(posy+25>800){
			screenY--;
			posy=0;
			screenEnterX=posx;
			screenEnterY=posy;
		}
		else if(posy<0){
			screenY++;
			posy=775;
			screenEnterX=posx;
			screenEnterY=posy;
		}


		renderObjects.clear();
		screen = new File("C:\\Users\\Jake\\Desktop\\programming\\Personal projects\\Game\\src\\"+screenX+","+screenY+".scrn");

		try{
			BufferedReader read = new BufferedReader(new FileReader(screen));
			String object;
			object=read.readLine();
			do{
				if(object.charAt(0)=='#'){
					continue;
				}
				renderObjects.add(createRenderObject(object));
				object=read.readLine();
			}while(object!=null);
			read.close();
		}catch(FileNotFoundException e){
			//System.out.println("error in loading");
		}catch (IOException e) {
			//System.out.println("error in reading");
		}
	}
	
	/**
	 * this method takes a string with the 5 parameters of a RenderObject
	 * delimited by commas and creates a RenderObject with those arguments
	 * @param parameters
	 * @return
	 */
	private RenderObject createRenderObject(String parameters){
		double[] rectangleParams = new double[4];
		char type='f';
		String field="";
		int lastComma=0;
		int param=0;
		for(int x = 0; x < parameters.length();x++){
			if(x!=parameters.indexOf(",",lastComma)){
				field=field+parameters.charAt(x);
			}
			else{
				lastComma=parameters.indexOf(",",lastComma)+1;
				rectangleParams[param]=Double.parseDouble(field);
				param++;
				field="";
			}
		}
		type=parameters.charAt(parameters.length()-2);
		
		return new RenderObject(rectangleParams[0],rectangleParams[1],rectangleParams[2],rectangleParams[3],type);
	}

	public static void main(String[] args) {
		Game start = new Game();

	}

}
