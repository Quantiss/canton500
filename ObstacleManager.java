package canton;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Rectangle;

public class ObstacleManager{

	private Runner instance = null;
	ArrayList <Obstacles> obstacles=new ArrayList<Obstacles>();
	public static int count=0;
	int[] x = new int[50];
	int[] y = new int[50];
	
	public void updateInstance(Runner in) {
		
		fillList();
		instance = in;
	}
	
	public void fillArr(){
		
		for (int i = 0; i < x.length; i++) {
			int c=(int) (Math.random() * 1283);
			int b=(int) (Math.random() * 660);
			while(!(((c>672&&c<890)&&(b>81&&b<267))||((c>615&&c<1125)&&(b>428&&b<495))))
			{
				/*(c>672||c<890)
				
				(b>428||b<495)
				(b>81||b<267)
				*/
				c=(int) (Math.random() * 1283);
				b=(int) (Math.random() * 660);
				
			}
			x[i] = c;
			y[i] = b;
		}
	}
	
	public void fillList()
	{
		while(obstacles.size()<15)
		{
			
			obstacles.add(new Obstacles(x[count],y[count]));
			count++;
			if(count==49) count=0;
		}
	}
	
	public ObstacleManager() 
	{
		fillArr();
		
		while(obstacles.size()<5)
		{
			
			obstacles.add(new Obstacles(x[count],y[count]));
			count++;
			if(count==49) count=0;
		}
		
	}
	
	public ArrayList<Obstacles> getObstacles() {
		return obstacles;
	}
	
	
	public void draw(Graphics g)
	{
		g.setColor(Color.CYAN);
		for(Obstacles ob : obstacles)
		{
			ob.draw(g);
			
		}
	}
	
	
}
