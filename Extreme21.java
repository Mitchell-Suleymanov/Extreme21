package Extreme21;
import java.util.ArrayList;
import java.util.Collections;

import Aces.Ace;
import Aces.AceDrawX;

public class Extreme21 {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		//Extreme21Frame window = new Extreme21Frame();
		//window.initialize();
/*
		ArrayList<Integer> Deck = new ArrayList<Integer>();
		ArrayList<Integer> Hand = new ArrayList<Integer>();

		
		for(int i=1;i<6;i++) {Deck.add(i);}
		Hand.add(1);

		AceDrawX drawCard = new AceDrawX();
		Ace drawCard = new AceDrawX();
		
		Player player = new Player();

		
		player.addAce(drawCard);

		player.getAces().get(0).use();
		//System.out.println();
		//System.out.println(player.getAces().get(0).getName());
		//draw(player);
		//((AceDrawX) player.getAces().get(0)).setDrawX(11);

		//System.out.println(player.getAces().get(0).getName());		
		//System.out.println(player.getBet());		

		AceDrawX card1 = new AceDrawX();
		Ace card2 = new AceDrawX();	
		
		ArrayList<Integer> deck = new ArrayList<Integer>();		
		for(int i=1;i<12;i++){deck.add(i);}
		//Collections.shuffle(deck);
		
		Player player = new Player();
		//Opponent opponent = new Opponent();

		Extreme21Game game = new Extreme21Game();
		game.newRound();
		
		//game.getPlayer().drawCard();
		
		System.out.println(game.getDeck().toString());
		System.out.println(game.getPlayerHand());
		
		game.getPlayer().drawCard(game.getDeck());
		System.out.println(game.getDeck().toString());
		System.out.println(game.getPlayerHand());
*/

		
		
		Extreme21Frame window = new Extreme21Frame();
	}
		
	
	public static void draw(Player player){
		int myBet = player.getBet();
		myBet = 10;
		player.setBet(myBet);
	} 

}
