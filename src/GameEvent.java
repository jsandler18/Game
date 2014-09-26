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
			main.velx=-400;
		}
		if(keyPressed==KeyEvent.VK_RIGHT){
			main.velx=400;
		}
		if(keyPressed==KeyEvent.VK_UP){
			if(main.vely==0){
				main.vely=-1200;
			}
		}
		if(keyPressed==KeyEvent.VK_DOWN){
			main.vely=200;
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
		/*if(keyPressed==KeyEvent.VK_UP){
			main.vely=0;
		}*/
		if(keyPressed==KeyEvent.VK_DOWN){
			main.vely=0;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}