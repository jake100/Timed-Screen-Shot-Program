package program;

import static java.awt.GraphicsDevice.WindowTranslucency.TRANSLUCENT;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import program.customComponent.CustomButton;
import program.customComponent.CustomLabel;
import program.customComponent.CustomRadioButton;
import program.customComponent.CustomSlider;
import program.customComponent.CustomTextField;

public class Window extends JFrame implements Runnable
{
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 240, HEIGHT = 253, UPS = 30;
	public static final float OPACITY = 0.96f;
	private int posX=0,posY=0, time = 0;
	private static Thread thread;
	private JPanel panel = new JPanel();
	private CustomLabel label = new CustomLabel(time+" seconds", JLabel.CENTER);
	private CustomLabel extlabel = new CustomLabel(FileNamer.ext.toString(), JLabel.CENTER);
	private JSlider slider = new JSlider(SwingConstants.HORIZONTAL, 0, 60, time);
	private JSlider extslider = new JSlider(SwingConstants.HORIZONTAL, 0, 2, 0);
	private CustomTextField text = new CustomTextField();
	private CustomButton button = new CustomButton(new ImageIcon("res/start.png"));
	private CustomButton closeButton = new CustomButton(new ImageIcon("res/exit.png"));
	private CustomButton minimizeButton = new CustomButton(new ImageIcon("res/min.png"));
	private CustomRadioButton timeCapButton = new CustomRadioButton("time cap");
	private CustomRadioButton buttonTriggerButton = new CustomRadioButton("hotkey");
	
	private String textText = "f12";
	private TimedCapture capture = new TimedCapture();
	private KeyCapture keyCap = new KeyCapture();
	private InputHandler input = new InputHandler();
	private String currentKey = textText;
	public Window()
	{
		this.setUndecorated(true);

		timeCapButton.setSelected(true);
		timeCapButton.setHorizontalTextPosition(JRadioButton.LEFT);
		slider.setUI(new CustomSlider(slider, new Color(50, 55, 50), new Color(225, 55, 55)));
		extslider.setUI(new CustomSlider(extslider, new Color(110, 15, 15), new Color(55, 55, 55)));
        extslider.setMajorTickSpacing(1);
        
		panel.setBackground(new Color(150, 10, 10));
		panel.setLayout(null);
		panel.setSize(new Dimension(WIDTH, HEIGHT));
		panel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		addComponents();
		componentBounds();
		actions();//actionListerners, mouseListeners
		repaint();
	}
	public static void main(String[] args)
	{
		 GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		 GraphicsDevice gd = ge.getDefaultScreenDevice();
		 if (!gd.isWindowTranslucencySupported(TRANSLUCENT))
		 {
	          System.err.println("Translucency is not supported");
	          System.exit(0);
	     }
	    Window w = new Window();
	    w.setIconImage(Toolkit.getDefaultToolkit().getImage("res/icon.png"));
	    w.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
	    w.setDefaultCloseOperation(3);
	    w.setLocationRelativeTo(null);
		w.setSize(new Dimension(WIDTH, HEIGHT));
		w.setVisible(true);
		w.setResizable(false);
		w.start();
		
		w.setOpacity(OPACITY);
	}
	
	public synchronized void start()
	{
		thread = new Thread(this, "Timer");
		thread.start();
	}
	public void run() 
	{
		final long SECOND = 1000000000;
		long lastLoopTime = System.nanoTime();
		while(true)
		{
			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / ((double) SECOND / UPS);
			capture.update(delta, this);
			if(buttonTriggerButton.isSelected())
			{
				button.setIcon(new ImageIcon("res/start-key.png"));
				label.setText(textText);
				//if(input.pressed)keyCap.setCurrentKey(input.getLastKey());
				//textText = keyCap.decodeKey();
			}
			else if(time==0)button.setIcon(new ImageIcon("res/start.png"));
			else button.setIcon(new ImageIcon("res/start-clock.png"));
			if(capture.isInvisibleTime())setOpacity(0.0f);
			else setOpacity(OPACITY);
			try {Thread.sleep((lastLoopTime - System.nanoTime()) / (SECOND / 1000) + 10);} 
			catch (Exception ex){}
		}
	}
	public void addComponents()
	{
		panel.add(closeButton);
		panel.add(minimizeButton);
		panel.add(label);
		panel.add(extlabel);
		panel.add(slider);
		panel.add(extslider);
		panel.add(button);
		panel.add(timeCapButton);
		panel.add(buttonTriggerButton);
		panel.add(text);
		add(panel);
	}
	public void componentBounds()
	{
		closeButton.setBounds((int)(WIDTH-10*1.5), 5, CustomButton.LITTLE_BUTTON_SIZE, CustomButton.LITTLE_BUTTON_SIZE);
		minimizeButton.setBounds((int)((WIDTH-10*1.5)-CustomButton.LITTLE_BUTTON_SIZE*1.65), 5, CustomButton.LITTLE_BUTTON_SIZE, CustomButton.LITTLE_BUTTON_SIZE);
		
		button.setBounds((int)((WIDTH/2.0)-(CustomButton.BUTTON_WIDTH/2.0)), (int)((HEIGHT)-CustomButton.BUTTON_HEIGHT*1.5), CustomButton.BUTTON_WIDTH, CustomButton.BUTTON_HEIGHT);
		label.setBounds((int)((WIDTH/2.0)-(CustomSlider.SLIDER_WIDTH/2.0)), (int)((HEIGHT*.7)-CustomSlider.SLIDER_HEIGHT*4.75), CustomSlider.SLIDER_WIDTH-1, CustomSlider.SLIDER_HEIGHT);
		
		extlabel.setBounds((int)((WIDTH/2.0)-(CustomSlider.SLIDER_WIDTH/2.0)), (int)((HEIGHT*.7)-CustomSlider.SLIDER_HEIGHT*0.90), CustomSlider.SLIDER_WIDTH-1, CustomSlider.SLIDER_HEIGHT);
		extslider.setBounds((int)((WIDTH/2.0)-(CustomSlider.SLIDER_WIDTH/2.0)), (int)((HEIGHT*.7)-CustomSlider.SLIDER_HEIGHT*2.18), CustomSlider.SLIDER_WIDTH-1, CustomSlider.SLIDER_HEIGHT);
		slider.setBounds((int)((WIDTH/2.0)-(CustomSlider.SLIDER_WIDTH/2.0)), (int)((HEIGHT*.7)-CustomSlider.SLIDER_HEIGHT*3.50), CustomSlider.SLIDER_WIDTH-1, CustomSlider.SLIDER_HEIGHT);
		text.setBounds((int)((WIDTH/2.0)-(CustomSlider.SLIDER_WIDTH/2.0)), (int)((HEIGHT*.7)-CustomSlider.SLIDER_HEIGHT*3.50), CustomSlider.SLIDER_WIDTH-1, CustomSlider.SLIDER_HEIGHT);
		
		timeCapButton.setBounds(10, (int)((HEIGHT)-CustomButton.BUTTON_HEIGHT*1.65), (int) (CustomButton.BUTTON_WIDTH*1.9), (int) (CustomButton.BUTTON_HEIGHT));
		buttonTriggerButton.setBounds(149, (int)((HEIGHT)-CustomButton.BUTTON_HEIGHT*1.65), (int) (CustomButton.BUTTON_WIDTH*1.9), (int) (CustomButton.BUTTON_HEIGHT));
	}
	public void actions()
	{
		addKeyListener(input);
		addMouseListener(new MouseAdapter()
		{
		   public void mousePressed(MouseEvent e)
		   {
		      posX=e.getX();
		      posY=e.getY();
		   }
		});
		addMouseMotionListener(new MouseAdapter()
		{
		     public void mouseDragged(MouseEvent evt)
		     {
				setLocation (evt.getXOnScreen()-posX,evt.getYOnScreen()-posY);
		     }
		});
		slider.addChangeListener
		(
			new ChangeListener()
			{
				public void stateChanged(ChangeEvent e)
				{
					time = slider.getValue();
					label.setText(time+" seconds");
					if(timeCapButton.isSelected())
					{
						if(time==0)button.setIcon(new ImageIcon("res/start.png"));
						else button.setIcon(new ImageIcon("res/start-clock.png"));
					}
				}
			}
		);
		button.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					capture.startTimer(time);
				}
			}
		);
		closeButton.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.exit(0);
				}
			}
		);
		minimizeButton.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						setState(JFrame.ICONIFIED);
					}
				}
			);
		timeCapButton.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						slider.setVisible(true);
						text.setVisible(false);
					}
				}
		);
		buttonTriggerButton.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						slider.setVisible(false);
						text.setVisible(true);
					}
				}
		);
	}
}
