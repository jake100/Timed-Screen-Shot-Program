package program;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener
{
	public boolean pressed = false;
	private int lastKey;
	 public boolean[] key = new boolean[65536];
		public void keyPressed(KeyEvent e)
		{
			int keyCode = e.getKeyCode();
			key[keyCode] = true;
			lastKey = e.getKeyCode();
			pressed = true;
		}
		public int getLastKey() {
			return lastKey;
		}
		public void setLastKey(int lastKey) {
			this.lastKey = lastKey;
		}
		public void keyReleased(KeyEvent e)
		{
			int keyCode = e.getKeyCode();
			key[keyCode] = false;
			pressed = false;
		}
		public void keyTyped(KeyEvent e)
		{
			int keyCode = e.getKeyCode();
			key[keyCode] = true;
		}
	}
