package main;


import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

public class Fox {
	
	int dx;
	int width,height;
	Image fox;
	URL url;
	int x = 30;
	int y = 550;
	
	public Fox(int x, int y){
		this.x = x;
		this.y = y;
		width = 120;
		height = 60;
		dx = 1;
		fox = PicturesFox.fox;
	}
	
	public Fox(){
		fox = PicturesFox.fox;
	}
	
	public void update(StartingPoint sp, Ball b) throws CannotFindFoxException{
		int ballX = b.getX();
		int ballY = b.getY();
		int radius = b.getRadius();
		if((ballX < 0 || ballX > sp.getWidth())){
			throw new CannotFindFoxException();                                                     			
		}else{
			if(ballY >= sp.getHeight() - radius - 1){
				dx = 5;
				x = ballX;
		
			}
		}
		
	}
	
	public void paint(Graphics g){
		g.drawImage(fox,x , y, PicturesFox.sp);
	}
}
