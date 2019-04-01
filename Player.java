package Extreme21;
import java.util.ArrayList;
import java.util.Random;
import Aces.*;

public class Player {
	
	private int life;
	private int bet;
	private boolean willStay;
	private ArrayList<Integer> hand;
	private ArrayList<Ace> aces;
	private ArrayList<Ace> acesInPlay;	

	public Player(){
		this.life = 10;
		this.bet  = 1;
		this.willStay= false;
		this.hand = new ArrayList<Integer>();
		this.aces = new ArrayList<Ace>();
		this.acesInPlay = new ArrayList<Ace>();
	}
	
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

	public boolean getWillStay() {
		return willStay;
	}
	
	public ArrayList<Integer> getHand() {
		return hand;
	}

	public ArrayList<Ace> getAces() {
		return aces;
	}

	public ArrayList<Ace> getAcesInPlay() {
		return acesInPlay;
	}
	
	public void setLife(int life) {
		this.life = life;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}
	
	public void setWillStay(boolean willStay){
		this.willStay = willStay;
	}

	public void setHand(ArrayList<Integer> hand) {
		this.hand = hand;
	}

	public void setAces(ArrayList<Ace> aces) {
		this.aces = aces;
	}

	public void addAce(){
		//(max - min + 1) + min is your range, range is between 2 and 7
		Random random = new Random();
		int value = random.nextInt(6) + 1;
		Ace ace;
		
		if(value<=4){ace = new AceTwoUp();}
		else{ace = new AceDrawX();}
		aces.add(ace);
	}
	
	public void addAce(int id){

		AceDrawX ace = new AceDrawX();
		aces.add(ace);
	}
	
	public void useAce(int index, Extreme21Game game){
		aces.remove(index).use(game);
		
	}
	
	public void drawCard(ArrayList<Integer> deck){
		if(deck.size()>0) 
			hand.add(deck.remove(0));
	}
	
	public String handToString(){
		String s = "{ " + hand.get(0) + " }";
		
		for(int i=1;i<hand.size();i++){
			s+= " [ " + hand.get(i) + " ]";
		}
		
		
		return s + "          " + this.getSum();
	}
	
}
