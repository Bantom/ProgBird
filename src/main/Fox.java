package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Fox {
	
	int dx;
	int x,y,width,height;
	
	public Fox(int x, int y){
		this.x = x;
		this.y = y;
		width = 100;
		height = 45;
		dx = 1;
	}
	
	public void update(StartingPoint sp, Ball b) throws CastomException{
		int ballX = b.getX();
		int ballY = b.getY();
		int radius = b.getRadius();
		if((ballX < 0 || ballX > 800)){
			throw new CastomException();                                                     			
		}else{
			if(ballY == 600 - radius - 1){
				dx = 5;
				x = ballX;
		
			}
		}
		
	}
	
	public void paint(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
		g.drawRect(x ,y, width, height);
	}
}
