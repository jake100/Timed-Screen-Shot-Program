package program.customComponent;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class CustomLabel extends JLabel
{
	private static final long serialVersionUID = 1L;

	public CustomLabel(String s, int layOut)
	{
		super(s, layOut);
		setForeground(new Color(235, 205, 185));
		setFont(new Font("Courier", Font.ITALIC, 24));
		setBorder(BorderFactory.createSoftBevelBorder(0, Color.red, new Color(85, 25, 25)));
	}

}
