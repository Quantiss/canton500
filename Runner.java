package canton;

import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.invoke.SwitchPoint;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;



import javax.swing.JButton;
import javax.sound.sampled.*;

public class Runner extends JPanel implements KeyListener
{
	Vehicle p1=new Vehicle(1, 160, 418);
	Vehicle p2=new Vehicle(2, 135, 418);
	ObstacleManager o1=new ObstacleManager();
	boolean gameOver=false;
	private static AudioInputStream music;
	private static String path = "C:\\Java\\Final\\Audio\\Audio\\StartMenuMusic.wav";
	private static String pathMP = "C:\\Java\\Final\\Images\\mainpageNC.png";
	private boolean start=true;
	private boolean instructions;
	
	public Runner()
	{
		setSize(new Dimension(1300, 700));
		setPreferredSize(new Dimension(1300, 700));
		setFocusable(true);
		addKeyListener(this);
		
	}
	
	public void paint(Graphics g) {
		
		if(start)
		{
			BufferedImage img;
			try {
				img = ImageIO.read(new File("C:\\Java\\Final\\Images\\mainpageNC.png"));
				g.drawImage(img, 0, 0, null);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("img false");
			}
			
			g.dispose();
			repaint();
			
			
			
		}
		
		
			
		
		else
		{	
		if(gameOver==true)
			{try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.exit(0);
			}
		
		try {
			BufferedImage img;
			img = ImageIO.read(new File("C:\\Java\\Final\\Images\\Canton500Trackresize.png"));
			g.drawImage(img, 0, 0, null);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("img false");
		}
		

		
		
		if(p1.intersects())
		{
			System.out.println("i");
			long start=System.currentTimeMillis();
			long end=start+500;
			while(System.currentTimeMillis()<end)
			{
			p1.setXspeed(0);
			p1.setYspeed(0);
			
			}
		}
		else if(p2.intersects())
		{
			System.out.println("i");
			long start=System.currentTimeMillis();
			long end=start+500;
			while(System.currentTimeMillis()<end)
			{
			p2.setXspeed(0);
			p2.setYspeed(0);
			
			}
		}
		
		else{o1.updateInstance(this);
		o1.draw(g);
		p1.updateInstance(this);
		p1.draw(g);	
		p2.updateInstance(this);
		p2.draw(g);}
		
		g.setColor(Color.white);
		g.setFont(new Font("Century Gothic",Font.BOLD, 12));
		g.drawString("laps:"+p1.greaterLaps(p2)+"/5", 1200, 20);
		if(p1.greaterLaps(p2)==5)
		{
			g.setColor(Color.black);
			g.setFont(new Font("Century Gothic",Font.BOLD, 50));
			g.drawString("GAME OVER!", 500, 250);
			gameOver=true;
			if(p1.getLaps()>p2.getLaps())
			{
				g.setFont(new Font("Century Gothic",Font.BOLD, 30));
				g.drawString("Player one wins", 550, 400);
			}
			else {
				g.setFont(new Font("Century Gothic",Font.BOLD, 30));
				g.drawString("Player two wins", 550, 400);
			}
		}
		
		g.dispose();
		repaint();
		}
		
	}
	
	
	public ObstacleManager getObstacleManager() {
		return o1;
	}
	
	public static void main(String[] args) 
	{
		Runner game = new Runner();
	
		JFrame frame = new JFrame();
		
		frame.setTitle("Canton 500");
		frame.add(game);
		frame.pack();
		frame.setSize(new Dimension(1300, 700));
		frame.setResizable(false);
		frame.setIconImage(new ImageIcon("C:\\Java\\Final\\Images\\Car1.png").getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		try {
	    	Sound s = new Sound();
	    	while(frame.isVisible()==true) {
			s.playSound("C:\\Java\\Final\\Audio\\Audio\\StartMenuMusic.wav");
			}
		}
		catch (Exception e) {
			System.out.println("Audio File loaded: False");
		}
		}

	
	

	@Override
	public void keyReleased(KeyEvent e) {
		int c=e.getKeyCode();
		if (c== KeyEvent.VK_W)
		{
			
			p1.setYspeed(0);
		}
		if (c == KeyEvent.VK_S)
		{
			p1.setYspeed(0);
		}
		if (c== KeyEvent.VK_D)
		{
			p1.setXspeed(0);
		}
		if (c == KeyEvent.VK_A)
		{
			p1.setXspeed(0);
		}
		if (c== KeyEvent.VK_UP)
		{
			p2.setYspeed(0);
		}
		if (c == KeyEvent.VK_DOWN)
		{
			p2.setYspeed(0);
		}
		if (c== KeyEvent.VK_RIGHT)
		{
			p2.setXspeed(0);
		}
		if (c == KeyEvent.VK_LEFT)
		{
			p2.setXspeed(0);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		if(c==KeyEvent.VK_SPACE) start=false;
		if (c== KeyEvent.VK_W)
		{
			p1.setYspeed(-1);
		}
		if (c == KeyEvent.VK_S)
		{
			p1.setYspeed(1);
		}
		if (c== KeyEvent.VK_D)
		{
			p1.setXspeed(1);
		}
		if (c == KeyEvent.VK_A)
		{
			p1.setXspeed(-1);
		}
		if (c== KeyEvent.VK_UP)
		{
			p2.setYspeed(-1);
		}
		if (c == KeyEvent.VK_DOWN)
		{
			p2.setYspeed(1);
		}
		if (c== KeyEvent.VK_RIGHT)
		{
			p2.setXspeed(1);
		}
		if (c == KeyEvent.VK_LEFT)
		{
			p2.setXspeed(-1);
		}
		
	}
}
