package program;

import java.awt.AWTException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class TimedCapture 
{
	private final double DELAY = .5;
	private int screenWidth = 0, screenHeight = 0;
	private int x = 0, y = 0, width = 0, height = 0;
	private boolean invisibleTime = false, takePic = false;
	private double time = 0;
	private FileNamer fileNamer;
	public TimedCapture()
	{
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		screenWidth = gd.getDisplayMode().getWidth();
		screenHeight = gd.getDisplayMode().getHeight();
		fileNamer = new FileNamer();
		setFullScreen();
	}
	public void update(double delta, final JFrame frame)
	{
		if(invisibleTime)
		{
			if(time-DELAY <= 0 && takePic)
			{
				takePic = false;
				CaptureScreen();
			}
			if(time > 0) time -= delta;
			else invisibleTime = false;
		}
		else
		{
			if(!frame.isFocused())
			{ 
			      frame.setAlwaysOnTop(true);
			      frame.toFront();
			      frame.requestFocus();
			      frame.setAlwaysOnTop(false);
			}
	        
		}
	}
	public void CaptureScreen()
	{
		try
		{
			Robot robot = new Robot();
			BufferedImage img = robot.createScreenCapture(new Rectangle(x, y, width, height));
			ImageIO.write(img, fileNamer.ext.toString(), new File(fileNamer.newIntName()));
		}
		catch(AWTException ex){}
		catch(IOException ex){}
	}
	public void startTimer(double time)
	{
		invisibleTime = true;
		takePic = true;
		this.time = (time+DELAY)*Window.UPS;
	}
	public void setFullScreen()
	{
		x = 0;
		y = 0;
		width = screenWidth;
		height = screenHeight;
	}
	public boolean isInvisibleTime(){return invisibleTime;}
}
