package program.customComponent;

import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class CustomButton extends JButton
{
	private static final long serialVersionUID = 1L;
	public static final int BUTTON_WIDTH = 43, BUTTON_HEIGHT = 30, LITTLE_BUTTON_SIZE = 10;
	public CustomButton(ImageIcon icon)
	{
		super(icon);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setRolloverEnabled(false);
		setFocusPainted(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
}

