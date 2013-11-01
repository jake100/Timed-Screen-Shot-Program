package program;

import java.awt.event.KeyEvent;

public class KeyCapture
{
	public static final int F1 = KeyEvent.VK_F1;
	public static final int F2 = KeyEvent.VK_F2;
	public static final int F3 = KeyEvent.VK_F3;
	public static final int F4 = KeyEvent.VK_F4;
	public static final int F5 = KeyEvent.VK_F5;
	public static final int F6 = KeyEvent.VK_F6;
	public static final int F7 = KeyEvent.VK_F7;
	public static final int F8 = KeyEvent.VK_F8;
	public static final int F9 = KeyEvent.VK_F9;
	public static final int F10 = KeyEvent.VK_F10;
	public static final int F11 = KeyEvent.VK_F11;
	public static final int F12 = KeyEvent.VK_F12;
	 
	 public String decodeKey(int currentKey)
	 {
		 if(currentKey==F1)return "F1";
		 if(currentKey==F2)return "F2";
		 if(currentKey==F3)return "F3";
		 if(currentKey==F4)return "F4";
		 if(currentKey==F5)return "F5";
		 if(currentKey==F6)return "F6";
		 if(currentKey==F7)return "F7";
		 if(currentKey==F8)return "F8";
		 if(currentKey==F9)return "F9";
		 if(currentKey==F10)return "F10";
		 if(currentKey==F11)return "F11";
		 if(currentKey==F12)return "F12";
		 return "invalid";
	 }
}
