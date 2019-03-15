package canton;

import java.awt.Rectangle;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Vehicle {

	
	private int x, y, vehicleNum, xspeed, yspeed, lastX,lastY, laps;
	private boolean firstQ=false, secondQ=false, thirdQ=false;
	private Rectangle hitbox;
	private Runner instance = null;
	private ArrayList<Obstacles> obstacles = new ArrayList<Obstacles>();
	
	
	public Vehicle(int vehicleNum, int x, int y)
	{
		this.x=x;
		this.y=y;
		this.vehicleNum=vehicleNum;
		xspeed=0;
		yspeed=0;
		laps=0;
		hitbox = new Rectangle(x, y, 10, 10);
	}
	
	public int greaterLaps(Vehicle v)
	{
		if(v.laps>laps)
		return v.laps;
		else return laps;
	}
	
	
	
	public void updateInstance(Runner in) {
		instance = in;
	}
	
	

	public void move()
	{
		obstacles = instance.getObstacleManager().getObstacles();
		hitbox = new Rectangle(x, y, 10, 10);
		
	
		if(outOfBounds())
		{
			
			x=lastX;
			y=lastY;
		}
		else if(intersects())
		{
			
			x=lastX;
			y=lastY;
			
		}
		else{
			
			lastX=x;
			lastY=y;
			
			x+=xspeed;
			y+=yspeed;
			}
	
		if(x==10||y==10)
		{
			laps++;
		}
	}
	
	public void draw(Graphics g)
	{	
		move();
		try {
			BufferedImage img;
			img = ImageIO.read(new File("C:\\Java\\Final\\Images\\Car1r.png"));
			
			g.drawImage(img, x-5, y-5, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("img false");
		}
		
		
			
	}


	public boolean outOfBounds()
	{
		Color c=Color.BLACK;
		try {
			BufferedImage img;
			img = ImageIO.read(new File("C:\\Java\\Final\\Images\\Canton500Trackresize.png"));
			c = new Color(img.getRGB(x, y));
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("img false");
		}
		return x<=0||y<=0||x>=1283||y>=660||((c.getRed()!=255&&c.getGreen()!=235&&c.getBlue()!=59)||(c.getRed()!=255&&c.getGreen()!=255&&c.getBlue()!=255));
		
	}
	
	public boolean intersects()
	{
		for(int i=0; i<obstacles.size(); i++)
		{
			//obstacles.get(i).getHitbox().getBounds().intersects(hitbox.getBounds())
			if(hitbox.intersects(obstacles.get(i).getHitbox()))
			{
				
				obstacles.remove(i);
				
				return true;
			}
			
		}
		return false;
	}
	
	/*public Rectangle getHitbox()
	{
		return hitbox;
	}*/

	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getXspeed() {
		return xspeed;
	}

	public void setXspeed(int xspeed) {
		this.xspeed = xspeed;
	}

	public int getYspeed() {
		return yspeed;
	}
	
	public void setYspeed(int yspeed) {
		this.yspeed = yspeed;
	}
	
	public int getLaps()
	{
		return laps;
	}

}
