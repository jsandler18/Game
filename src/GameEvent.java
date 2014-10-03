import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GameEvent implements KeyListener {
	Game main;
		
	public GameEvent(Game in) {
		main=in;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyPressed = e.getExtendedKeyCode();
		if(keyPressed==KeyEvent.VK_LEFT){
			main.velx=-500;
		}
		if(keyPressed==KeyEvent.VK_RIGHT){
			main.velx=500;
		}
		if(keyPressed==KeyEvent.VK_UP){
			if(main.landed==true){
				main.vely=-1200;
				main.landed=false;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyPressed = e.getExtendedKeyCode();
		if(keyPressed==KeyEvent.VK_LEFT){
			main.velx=0;
		}
		if(keyPressed==KeyEvent.VK_RIGHT){
			main.velx=0;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
