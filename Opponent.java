package Opponents;

import java.util.ArrayList;
import Aces.Ace;
import Aces.AceDrawX;
import Extreme21.Extreme21Game;

public class Opponent {
	
	protected int life;
	protected int bet;
	protected int luck;
	protected boolean willStay;
	protected ArrayList<Integer> hand;
	protected ArrayList<Ace> aces;
	
	public int getSum(){
		int sum=0;
		for(int i=0;i<hand.size();i++){
			sum+=hand.get(i);
		}
		return sum;
	}
	
	public int getLife() {
		return life;
	}

	public int getBet() {
		return bet;
	}
	
	public boolean getWillStay(){
		return willStay;
	}

	public ArrayList<Integer> getHand() {
		return hand;
	}

	public ArrayList<Ace> getAces() {
		return aces;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}
	
	public void setWillStay(boolean stay){
		this.willStay = stay;
	}

	public void setHand(ArrayList<Integer> hand) {
		this.hand = hand;
	}

	public void setAces(ArrayList<Ace> aces) {
		this.aces = aces;
	}

	public void addAce(){
		//(max - min + 1) + min is your range, range is between 2 and 7
		//Random random = new Random();
		//int value = random.nextInt(6) + 1;

		AceDrawX ace = new AceDrawX();
		aces.add(ace);
	}
	
	public void removeAce(Ace ace){
			
	}
	
	public void nextAction(Extreme21Game game){
		
	}
	
	public void drawCard(ArrayList<Integer> deck){
		if(deck.size()>0) 
			hand.add(deck.remove(0));
	}
	
	public String handToString(){
		String s = "{ ? }";
		
		for(int i=1;i<hand.size();i++){
			s+= " [ " + hand.get(i) + " ]";
		}
		
		return s + "          ?+" + (this.getSum()-hand.get(0));
	}
	
	public String revealHand(){
		String s = "{ " + hand.get(0) + " }";
		
		for(int i=1;i<hand.size();i++){
			s+= " [ " + hand.get(i) + " ]";
		}
		
		return s + "          " + this.getSum();
	}
	
}
