package program.customComponent;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class CustomRadioButton extends JRadioButton
{
	static String metal= "Metal";
	static String metalClassName = "javax.swing.plaf.metal.MetalLookAndFeel";
	private static final long serialVersionUID = 1L;
	public static ButtonGroup group = new ButtonGroup();
	public CustomRadioButton(String title)
	{
		super(title);
		setFocusPainted(false);
		setFont(new Font("Courier", Font.PLAIN, 11));
		setForeground(new Color(11, 11, 11));
		setContentAreaFilled(false);
		setBorderPainted(false);
		setRolloverEnabled(false);
		setFocusable(false);
		group.add(this);
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		try {UIManager.setLookAndFeel(metalClassName);}
		catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {e.printStackTrace();}
	}
}
