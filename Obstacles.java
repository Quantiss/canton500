package canton;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Obstacles
{
	
	private Rectangle hitbox;
	private int ox,oy;
	
	
	public Obstacles(int x, int y)
	{
		ox=x;
		oy=y;
		hitbox = new Rectangle(x, y, 20, 20);
	}
	
	public void draw(Graphics g) {
		
		try {
			BufferedImage img;
			img = ImageIO.read(new File("C:\\Java\\Final\\Images\\BackPackr.png"));
			
			g.drawImage(img, ox, oy, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("img false");
		}
	}
	
	
	public int getX() {
		return ox;
	}
	
	public int getY() {
		return oy;
	}

	public Rectangle getHitbox()
	{
		return hitbox;
	}
}