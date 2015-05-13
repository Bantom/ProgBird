package main;
import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.Random;

public class StartingPoint extends Applet implements Runnable, KeyListener, MouseMotionListener, MouseListener{


	private Image i;
	private Graphics doubleG;
	Ball b;
	Platform p[] = new Platform[7];
	Item item[] = new Item[3];
	Fox fox;
	private int score;
	double cityX = 0;
	double cityDx = .3;
	URL url;
	Image city;
	int levelcheck = 0;
	boolean gameOver = false;
	boolean mouseIn = false;
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void init(){
		setSize(800,600);
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		try{
			url = getDocumentBase();
		}catch(Exception e){
			
		}
		city = getImage(url, "main/images/Background.png");
		Pictures p = new Pictures(this);
		PicturesBird a = new PicturesBird(this);
		PicturesFox k = new PicturesFox(this);
	}
	
	public void start(){
		b = new Ball();
		score = 0;
		for (int i =0; i < p.length; i++){
			p[i] = new Platform(i*120, 300);
		}
		
		for (int i =0; i < item.length; i++){
			Random r = new Random();
			switch (r.nextInt(4)){
			case 0:
				item[i] = new GravUp(getWidth() + 2000*i);
				break;
			case 1:
				item[i] = new GravDown(getWidth() + 2000*i);
				break;
			case 2:
				item[i] = new AgilUp(getWidth() + 2000*i);
				break;
			case 3:
				item[i] = new AgilDown(getWidth() + 2000*i);
				break;
			case 4:
				item[i] = new ScorePlus(getWidth() + 2000*i, this);
				break;
			default:
				break;
			}
		}
		fox = new Fox();
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
		g.drawImage(city, (int) cityX, 0, this);
		g.drawImage(city, (int) cityX + getWidth(), 0, this);
		
		b.paint(g);
		fox.paint(g);
		for(int i=0; i<p.length; i++){
			p[i].paint(g);
		}
			for(int w=0; w<item.length; w++){
				item[w].paint(g);
				}

		
		String s = Integer.toString(score);
		Font font = new Font("Serif", Font.BOLD, 32);
		g.setFont(font);
		g.setColor(Color.BLACK);;
		g.drawString(s, getWidth() -150+2, 50+2);
		g.setColor(new Color(198,226,255));;
		g.drawString(s, getWidth() -150, 50);
		if(gameOver){
			g.setColor(Color.RED);
			g.drawString("GAME OVER",300,300);
			g.drawRect(280, 320, 180, 40);
			if(mouseIn){
				g.setColor(Color.RED);
				g.drawString("Play again?",280,340);
			}else{
				g.setColor(Color.GREEN);
				g.drawString("Play again?",280,340);
			}
		}
	}
	
	


		
	public void run(){
		while(true){
			
			for(int i =0; i < p.length; i++){
				int testx = p[i].getX();
				if(testx < 0 - p[i].getWidth()){
					Random r = new Random();
					int fakei = i;
					if( i == 0){
						fakei = p.length;
					}
					p[i].setX(p[fakei-1].getX() + p[i].getWidth() + Pictures.level * r.nextInt(25));
				}
			}
			gameOver = b.getGameOver();
			
			if(levelcheck>100){
				Pictures.level++;
				levelcheck = 0;
			}
			
			levelcheck++;
			b.update(this);
			
			if(cityX > getWidth() * -1){
				cityX -= cityDx;
			}else{
				cityX = 0;
			}
			
			if(!gameOver){
				score ++;
			}

			try {
				fox.update(this, b);
			} catch (CannotFindFoxException e) {
				e.printStackTrace();
			}

				Random r = new Random();
			
			for( int i = 0 ; i < item.length; i++){
				if(item[i].isCreateNew()){
					//item[i] = null;
					switch (r.nextInt(4)){
					case 0:
						item[i] = new GravUp(getWidth() + 10*r.nextInt(500));
						break;
					case 1:
						item[i] = new GravDown(getWidth()+ 10*r.nextInt(500));
						break;
					case 2:
						item[i] = new AgilUp(getWidth()+ 10*r.nextInt(500));
						break;
					case 3:
						item[i] = new AgilDown(getWidth()+ 10*r.nextInt(500));
						break;
					case 4:
						item[i] = new ScorePlus(getWidth()+ 10*r.nextInt(500), this);
						break;
					default:
						break;
					}
					item[i].setCreateNew(false);
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

	@Override
	public void mouseDragged(MouseEvent e) {
				
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(gameOver){
		if(e.getX() > 280 && e.getX() < 460){
			if(e.getY()> 320 && e.getY() < 360){
				mouseIn = true;
			}
		}
		if(e.getX() < 280 || e.getX() > 460){
				mouseIn = false;
		}
		if(e.getY() < 320 || e.getY() > 360){
			mouseIn = false;
		}
	}
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(mouseIn){
			b = null;
			b = new Ball();
			fox = null;
			fox = new Fox();
			score = 0;
			Pictures.level = 1;
			for (int i =0; i < p.length; i++){
				p[i] = new Platform(i*120, 300);
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
			mouseIn = false;
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
