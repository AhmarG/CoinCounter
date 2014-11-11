import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import org.imgscalr.Scalr;

/**
 * CoinCounter.java
 * 
 * The CoinCounter class creates a GUI mini-game for 
 * users to play. The goal is to reach a randomly generated
 * coin value using the least amount of coins possible.
 * 
 * @author Ahmar Gordon
 * @version 1.2
 */
public class CoinCounter 
{
	private JFrame frame = new JFrame("Coin Counter");
	private int p, n, d, q, t = 0;
	private int x = (int) Math.ceil(Math.random()*100);
	private JLabel label1 = new JLabel(Integer.toString(p), SwingConstants.CENTER);
	private JLabel label2 = new JLabel("" + n, SwingConstants.CENTER);
	private JLabel label3 = new JLabel("" + d, SwingConstants.CENTER);
	private JLabel label4 = new JLabel("" + q, SwingConstants.CENTER);
	private JLabel total = new JLabel("" + t);
	private JLabel rand = new JLabel("" + x);
	
	/**
	 * Constructor for the CoinCounter class.
	 * 
	 * Initializes the keyboard listener for user interaction
	 * and adds it to the frame.
	 * Creates frame elements and makes it visible.
	 */
	public CoinCounter()
	{
		//Start keyboard listener
		KeyListener listener = new MyKeyListener();
		frame.addKeyListener(listener);		
		//Display the window.
		frame.setSize(800, 800);
		frame.setVisible(true);
		frame.setFocusable(true);
		//Initializes components
		createAndShowGUI();
	}
	
	/**
	 * Initializes all default components of the GUI and 
	 * adds them to the pane.
	 * 
	 * @param pane Container for all graphical elements
	 */
	public void addComponentsToPane(Container pane) 
	{
		pane.setLayout(new GridBagLayout());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		BufferedImage image;// storage variable for pictures
		JLabel label;// for all generic labels
		
		GridBagConstraints c = new GridBagConstraints();// controls location of components
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input;
		
		//Fetches the images and adds them to the pane
		try
		{
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = 0;
			input = classLoader.getResourceAsStream("penny.jpg");
			image = ImageIO.read(input);
			//image = ImageIO.read(new File("C:\\Users\\gordona\\Pictures\\penny.jpg"));
			label = new JLabel(new ImageIcon(resize(image, 160)));
			pane.add(label, c);

			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 1;
			c.gridy = 0;
			input = classLoader.getResourceAsStream("nickel.jpg");
			image = ImageIO.read(input);
			//image = ImageIO.read(new File("C:\\Users\\gordona\\Pictures\\nickel.jpg"));
			label = new JLabel(new ImageIcon(resize(image, 190)));
			pane.add(label, c);

			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 2;
			c.gridy = 0;
			input = classLoader.getResourceAsStream("dime.jpg");
			image = ImageIO.read(input);
			//image = ImageIO.read(new File("C:\\Users\\gordona\\Pictures\\dime.jpg"));
			label = new JLabel(new ImageIcon(resize(image, 150)));
			pane.add(label, c);

			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 3;
			c.gridy = 0;
			input = classLoader.getResourceAsStream("quarter.jpg");
			image = ImageIO.read(input);
			//image = ImageIO.read(new File("C:\\Users\\gordona\\Pictures\\quarter.jpg"));
			label = new JLabel(new ImageIcon(resize(image, 210)));
			pane.add(label, c);
		}
		catch(Exception e)
		{
			System.err.println("Failed to load image");
		}
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		label1.setFont(new Font("Serif", 0, 40));
		pane.add(label1, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		label2.setFont(new Font("Serif", 0, 40));
		pane.add(label2, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		label3.setFont(new Font("Serif", 0, 40));
		pane.add(label3, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		label4.setFont(new Font("Serif", 0, 40));
		pane.add(label4, c);

		label = new JLabel("Goal Value:");
		label.setFont(new Font("Serif", 0, 40));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40;      //make this component tall
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 3;
		c.weighty = 1.0;
		pane.add(label, c);

		rand = new JLabel(Integer.toString(x));
		rand.setFont(new Font("Serif", 0, 40));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40;      //make this component tall
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 2;
		c.gridy = 3;
		c.weighty = 1.0;
		pane.add(rand, c);

		label = new JLabel("Your Total:");
		label.setFont(new Font("Serif", 0, 40));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;       //reset to default
		c.weighty = 1.0;   //request any extra vertical space
		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 0;       //aligned with button 2
		c.gridwidth = 2;   //2 columns wide
		c.gridy = 4;       //third row
		pane.add(label, c);

		total.setFont(new Font("Serif", 0, 40));
		c.gridx = 2;
		c.gridy = 4;
		c.weighty = 1.0;
		pane.add(total, c);
	}
	
	/**
	 * Resizes an image to any size
	 * 
	 * @param img image to be resized
	 * @param newSize new size of picture
	 * @return new resized image
	 */
	public static BufferedImage resize(BufferedImage img, int newSize)
	{
		BufferedImage newimage = Scalr.resize(img, newSize);
		return newimage;
	}

	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event-dispatching thread.
	 */
	private void createAndShowGUI() 
	{
		//Set up the content pane.
		addComponentsToPane(frame.getContentPane());
	}

	/**
	 * Sets the rules for the CoinCounter.
	 * Creates the replay option.
	 */
	public void checker()
	{
			if(t == x)
			{
				int selectedOption = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "You win!", 
                        JOptionPane.YES_NO_OPTION);
				
				if (selectedOption == JOptionPane.YES_OPTION) 
				{
					resetGame();
				}
				if (selectedOption == JOptionPane.NO_OPTION) 
				{
				    System.exit(0);
				}
			}
			if(t > x)
			{
				JOptionPane.showMessageDialog(null, "Oops! Try again");
				reset();
			}
	}
	
	/**
	 * Resets the entire game with a new random goal value
	 */
	public void resetGame()
	{
		p = 0;
		n = 0;
		d = 0;
		q = 0;
		t = 0;
		x = (int) Math.ceil(Math.random()*100);
		label1.setText(Integer.toString(p));
		label2.setText(Integer.toString(n));
		label3.setText(Integer.toString(d));
		label4.setText(Integer.toString(q));
		total.setText(Integer.toString(t));
		rand.setText(Integer.toString(x));
	}
	
	/**
	 * Resets the current game with the same goal value
	 */
	public void reset()
	{
		p = 0;
		n = 0;
		d = 0;
		q = 0;
		t = 0;
		label1.setText(Integer.toString(p));
		label2.setText(Integer.toString(n));
		label3.setText(Integer.toString(d));
		label4.setText(Integer.toString(q));
		total.setText(Integer.toString(t));
	}

	/**
	 * Inner class for the keyboard listener
	 *
	 */
	private class MyKeyListener implements KeyListener
	{
		private MyKeyListener() {}

		public void keyTyped(KeyEvent e) {}
		
		/**
		 * Listens for these keys to be pressed while the program
		 * is running. Only keys that correspond to program
		 * functions have actions.
		 */
		public void keyPressed(KeyEvent e)
		{
			if (e.getKeyCode() == 80)//P
			{
				p += 1;
				t += 1;				
				label1.setText(Integer.toString(p));
				total.setText(Integer.toString(t));
				checker();
			}

			if (e.getKeyCode() == 78)//N
			{
				n += 1;
				t += 5;
				label2.setText(Integer.toString(n));
				total.setText(Integer.toString(t));
				checker();
			}

			if (e.getKeyCode() == 68)//D
			{
				d += 1;
				t += 10;
				label3.setText(Integer.toString(d));
				total.setText(Integer.toString(t));
				checker();
			}
			if (e.getKeyCode() == 81)//Q
			{
				q += 1;
				t += 25;
				label4.setText(Integer.toString(q));
				total.setText(Integer.toString(t));
				checker();
			}

		}

		public void keyReleased(KeyEvent e) {}
	}

	/**
	 * Creates a CoinCounter object to run.
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args) 
	{
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				CoinCounter counter = new CoinCounter();
			}
		});
		
	}
}
