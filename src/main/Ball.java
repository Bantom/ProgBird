package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

public class Ball {
	private double gravity = 15;
	private double energyloss = 1;
	private double dt = .2;
	private double xFriction = .9;
	private int x = 400;
	private int y = 25;
	private double dx = 0;
	private double dy = 0;
	private double gameDy = -75;
	private int radius = 20;
	private int agility = 3;
	private int maxSpeed = 20;
	private boolean game_over = false;
	URL url;
	Image bird;
	
	public Ball(int i, int j) {
		x = i;
		y = j;
		bird = PicturesBird.bird;
	}
	
	public void setAgility(int agility) {
		this.agility = agility;
	}
	
	public int getAgility() {
		return agility;
	}
	
	public int getMaxSpeed() {
		return maxSpeed;
	}
	
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
	public double getGameDy() {
		return gameDy;
	}
	
	public void setGameDy(double gameDy) {
		this.gameDy = gameDy;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public Ball(){
		bird = PicturesBird.bird;
	}
	
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
	
	public double getDx() {
		return dx;
	}
	
	public double getDy() {
		return dy;
	}
	
	public void setDy(double dy) {
		this.dy = dy;
	}
	
	public void setDx(double dx) {
		this.dx = dx;
	}
	
	public void setGravity(double gravity) {
		this.gravity = gravity;
	}
	
	public double getGravity() {
		return gravity;
	}
	
	public void moveRight(){
		if(dx+ agility< maxSpeed){
			dx += agility;
		}
	}
	
	public void moveLeft(){
		if(dx-agility > -maxSpeed){
			dx -= agility;
		}
	}
	
	public void update(StartingPoint sp){
		if (x + dx > sp.getWidth()- radius-1){
			x = sp.getWidth() - radius - 1;
			dx = -dx; 
		}else if(x +dx < 0+radius){
			x= 0+radius;
			dx=-dx;
		}
		else{
			x += dx;
		}
		
		if(y == sp.getHeight()- radius -1){
			dx *= xFriction;
			if(Math.abs(dx)< .8){
				dx = 0;
			}
		}

		if( y-200 > sp.getHeight() - radius -1){
			game_over = true;
		}else{
			dy += gravity *dt;
			y += dy*dt + .5*gravity*dt*dt;
		}
	}
	
	public void paint(Graphics g){
		g.drawImage(bird,x , y, PicturesBird.sp);
		
	}

	public boolean getGameOver() {
		return game_over;
	}
}
