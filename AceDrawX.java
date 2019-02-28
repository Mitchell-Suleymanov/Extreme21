package Aces;
import java.util.Random;
import Extreme21.Extreme21Game;

public class AceDrawX extends Ace {

	private int drawX;
	
	public AceDrawX(){
		Random random = new Random();
		//(max - min + 1) + min is your range, range is between 2 and 7
		this.drawX = random.nextInt(6) + 2;
		this.name = "Draw " + this.drawX;
		this.description = "Draw the " + this.drawX + " card. If it is not in the deck, nothing happens.";
	}
	
	public int getDrawX() {
		return drawX;
	}
	
	//ArrayList<Integer> Deck = new ArrayList<Integer>();
	//public ArrayList<Integer> 
	
	public void setDrawX(int x){
		this.drawX = x;
		this.name = "Draw " + this.drawX;
	}
	
	@Override
	public void use(){
		System.out.println("used AceDrawX");
	}
	
}
