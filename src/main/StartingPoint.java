package main;
import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class StartingPoint extends Applet implements Runnable, KeyListener{


	private Image i;
	private Graphics doubleG;
	Ball b;
	Platform p[] = new Platform[7];
	Item item[] = new Item[3];
	Fox fox;
	private int score;
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void init(){
		setSize(800,600);
		addKeyListener(this);
	}
	
	public void start(){
		b = new Ball();
		score = 0;
		for (int i =0; i < p.length; i++){
			Random r = new Random();
			p[i] = new Platform(getWidth() + 200*i, getHeight() - 40 - r.nextInt(400));
		}
		for (int i =0; i < item.length; i++){
			Random r = new Random();
			switch (r.nextInt(5)){
			case 0:
				item[i] = new GravUp(getWidth() + 2000*i);
				break;
			case 1:
				item[i] = new GravDown(getWidth() + 2000*i);
				break;
			case 3:
				item[i] = new AgilUp(getWidth() + 2000*i);
				break;
			case 4:
				item[i] = new AgilDown(getWidth() + 2000*i);
				break;
			case 5:
				item[i] = new ScorePlus(getWidth() + 2000*i, this);
				break;
			}
		}
		fox = new Fox(30,550);
		Thread thread =  new Thread(this);
		thread.start();

	}
	
	
	public void stop(){
		
	}
	
	public void destroy(){
		
	}
	
	public void update(Graphics g){
		 if( i == null){
			 i = createImage(this.getSize().width, this.getSize().height);			 
			 doubleG = i.getGraphics();
		 }
		 doubleG.setColor(getBackground());
		 doubleG.fillRect(0, 0, this.getSize().width, this.getSize().height);
		 
		 doubleG.setColor(getForeground());
		 paint(doubleG);
		 
		 g.drawImage(i, 0, 0, this);
	}
	
	public void paint(Graphics g){
		b.paint(g);
			fox.paint(g);
		for(int i=0; i<p.length; i++){
			p[i].paint(g);
			
			for(int i1=0; i1<item.length; i1++){
				item[i1].paint(g);
				}
		}
		
		String s = Integer.toString(score);
		Font font = new Font("Serif", Font.BOLD, 32);
		g.setFont(font);
		g.setColor(Color.BLACK);;
		g.drawString(s, getWidth() -150+2, 50+2);
		g.setColor(new Color(198,226,255));;
		g.drawString(s, getWidth() -150, 50);
	}
	
	public void run(){
		while(true){
			b.update(this);
			
			score ++;
			
			try{
				fox.update(this, b);
			}catch( Exception e){
				System.out.println("We can not find fox." + "Error:" + e.getMessage());
			}
			
			Random r = new Random();
			
			for( int i = 0 ; i < item.length; i++){
				if(item[i].getY() == this.getHeight() + 100){
					item[i] = null;
					switch (r.nextInt(5)){
					case 0:
						item[i] = new GravUp(getWidth() + 10*r.nextInt(500));
						break;
					case 1:
						item[i] = new GravDown(getWidth()+ 10*r.nextInt(500));
						break;
					case 3:
						item[i] = new AgilUp(getWidth()+ 10*r.nextInt(500));
						break;
					case 4:
						item[i] = new AgilDown(getWidth()+ 10*r.nextInt(500));
						break;
					case 5:
						item[i] = new ScorePlus(getWidth()+ 10*r.nextInt(500), this);
						break;
					}
					
				}
			}
			
			for(int i=0; i<p.length; i++){
			p[i].update(this,b);
			}
			
			for(int i=0; i<item.length; i++){
			item[i].update(this,b);
			}
			
			repaint();
			try{
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT:
			b.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			b.moveRight();
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
}
