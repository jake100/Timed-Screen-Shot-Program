package program.customComponent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicSliderUI;

import program.Window;

public class CustomSlider extends BasicSliderUI
{
	private Color nubcol;
	public static final int SLIDER_WIDTH = (int)(Window.WIDTH*.875), SLIDER_HEIGHT = 34;
	public CustomSlider(JSlider s, Color col, Color nubcol) 
	{
		super(s);
		s.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		s.setPaintTicks(true);
        s.setMajorTickSpacing(10);
        s.setBackground(col);
        s.setBorder(BorderFactory.createLineBorder(Color.black));
		s.setFocusable(false);
		this.nubcol = nubcol;
	}
	 @Override
	 public void paint(Graphics g, JComponent c) 
	 {
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
	                			 RenderingHints.VALUE_ANTIALIAS_ON);
	        super.paint(g, c);
	 }
	 @Override
	    protected Dimension getThumbSize() {return new Dimension(13, 25);}
	 @Override
	    public void paintTrack(Graphics g)
	 	{
	        Graphics2D g2d = (Graphics2D) g;
	        Stroke old = g2d.getStroke();
	        g2d.setStroke(new BasicStroke(3));
	        g2d.setPaint(Color.BLACK);
	        if (slider.getOrientation() == SwingConstants.HORIZONTAL) 
	        {
	            g2d.drawLine(trackRect.x, trackRect.y + trackRect.height / 2, 
	                    	 trackRect.x + trackRect.width, trackRect.y + trackRect.height / 2);
	        }
	        else 
	        {
	            g2d.drawLine(trackRect.x + trackRect.width / 2, trackRect.y, 
	                    	 trackRect.x + trackRect.width / 2, trackRect.y + trackRect.height);
	        }
	        g2d.setStroke(old);
	    }
	 @Override
	    public void paintThumb(Graphics g) 
	 	{
	        Graphics2D g2d = (Graphics2D) g;
	        int x1 = thumbRect.x + 2;
	        int x2 = thumbRect.x + thumbRect.width - 2;
	        int width = thumbRect.width - 4;
	        int topY = thumbRect.y + thumbRect.height / 2 - thumbRect.width / 3;
	        GeneralPath shape = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
	        shape.moveTo(x1, topY);
	        shape.lineTo(x2, topY);
	        shape.lineTo((x1 + x2) / 2, topY + width);
	        shape.closePath();
	        g2d.setPaint(nubcol);
	        g2d.fill(shape);
	        Stroke old = g2d.getStroke();
	        g2d.setStroke(new BasicStroke(2.25f));
	        g2d.setPaint(new Color(0, 0, 0));
	        g2d.draw(shape);
	        g2d.setStroke(old);
	    }
}

