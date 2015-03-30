package main;

import java.awt.Color;
import java.util.Random;

public class ScorePlus extends Item {
	private StartingPoint appletInfo;
	
	public ScorePlus(int x, StartingPoint appletInfo) {
		super(x);
		this.appletInfo = appletInfo;
	}
	
	public void performAction(Ball b){
		Random r =  new Random();
		appletInfo.setScore(appletInfo.getScore()+ r.nextInt(2000)+500);
		}
	public void paint(Graaphics g){
		g.setColor(Color.BLUE);
		super.paint(g);
	}
}
