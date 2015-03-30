package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Item {
	
	private int x, y, dx, radius;
	private StartingPoint sp;
	
	public Item(int x) {
		this.x = x;
		Random r = new Random();
		y = r.nextInt(400)+ radius;
		radius = 10;
		dx= -2;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return x;
	}
	
	
	public void update(StartingPoint sp, Ball b){
		x += dx;
		this.sp = sp;
		checkForCollision(b);
		if (x < 0 - radius){
			Random r = new Random();
			x = sp.getWidth() + 2000 + r.nextInt(300);
		}
	}
	
	private void checkForCollision(Ball b) {
		int ballX = b.getX();
		int ballY = b.getY();
		int ballR = b.getRadius();
		
		int a = x - ballX;
		int bb = y - ballY;
		int collide = radius + ballR;
		//distance between objects
		double c = Math.sqrt((double) (a*a) + (double) (bb*bb));
		
		if( c < collide ){
			performAction(b);
			x = 0;
			y = sp.getHeight() + 100;
		}
	}

	protected void performAction(Ball b) {

		
	} 

	public void paint(Graphics g){
		//g.setColor(Color.GREEN);
		g.fillOval(x-radius ,y-radius , radius*2, radius*2);
	}
}
