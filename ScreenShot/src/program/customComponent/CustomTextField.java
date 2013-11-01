package program.customComponent;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class CustomTextField extends JTextField
{
	private static final long serialVersionUID = 1L;
	private boolean selected = false;
	public boolean isSelected() {return selected;}
	public CustomTextField()
	{
		setVisible(false);
		setBackground(new Color(50, 55, 50));
		setBorder(BorderFactory.createLineBorder(Color.black));
		setForeground(new Color(205, 25, 25));
		addFocusListener(new FocusListener() 
		{
	        public void focusGained(FocusEvent e) 
	        {
	            selected = true;
	        }
	        public void focusLost(FocusEvent e) 
	        {
	        	selected = false;
	        }
	    });
	}
}
