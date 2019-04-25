package Opponents;

import java.util.ArrayList;
import java.util.Random;
import Aces.*;
import Extreme21.Extreme21Game;

public class OpponentPawn extends Opponent {
	
	public OpponentPawn(){
		this.life = 10;
		this.bet  = 1;
		this.luck = 10;
		this.willStay = false;
		this.canUseAces = true;
		this.hand = new ArrayList<Integer>();
		this.acesInPlay = new ArrayList<Ace>();
		
		/*
		 * Opponents have a limited variety of Aces.
		 * Pawn only has One Up, Two Up, and Shield 
		 * Ace[0] = Shield (55%)
		 * Ace[1] = One Up (35%)
		 * Ace[2] = Two Up (10%)L
		 * L = uses Luck variable
		 */
		this.aces = new int[3];

	}
	
	@Override
	public void addAce(){
		//(max - min + 1) + min is your range, range is between 2 and 7
		//Random random = new Random();
		//int value = random.nextInt(6) + 1;

		Random random = new Random();
		int value = random.nextInt(100) + 1;
		
		if(value<=luck) {aces[2]++;}
		else if(value<=35) {aces[1]++;}
		else {aces[0]++;}
	}
	
	@Override
	public void nextAction(Extreme21Game game){
		//Variables needed for figuring out the next action
		ArrayList<Integer> playerHand = game.getPlayer().getHand();
		ArrayList<Integer> deck = game.getDeck();
		double playerVictory = 0.0;
		double mySafety = 0.0;
		int playerSum = (game.getPlayer().getSum())-(game.getPlayer().getHand().get(0));
		int mySum = this.getSum();
		int limit = game.getLimit();
		int count =0;
		
		System.out.println("S: "+ aces[0] + " | O: " + aces[1] + " | T: " + aces[2]);
		
		if(life<7 && acesInPlay.isEmpty() && aces[1]>0){
			aces[1]--;
			new AceOneUp().use(game);
			game.getPlayer().setWillStay(false);
		}
		
		//If the player's sum is greater than the limit or the opponent
		//has a total equaling the limit, the opponent will know to stay.
		if(playerSum>=limit || mySum==limit) {
			/*
			 * Pawn will use a TwoUp if:
			 * 1. Their Life < 7
			 * 2. They have at least one TwoUp
			 * 3. They have space on their board for an Ace in Play (size<6)
			 * 4. They know they will win.
			 */
			if(life<7 && aces[2]>0 &&  acesInPlay.size()<6){
				aces[2]--;
				new AceTwoUp().use(game);
				game.getPlayer().setWillStay(false);
			} 
			willStay = true;
			return;
		}
		
		/*
		 * Pawn will use Shield if:
		 * 1. Their Life - Bet < 1
		 * 2. They have at least one Shield
		 * 3. They have space on their board for an Ace in Play (size<6)
		 * Pawn isn't intelligent enough to know when it can defend against a high enough Bet.
		 */
		while((life-bet<2) && aces[0]>0 && acesInPlay.size()<6) {
			aces[0]--;
			new AceShield().use(game);
			game.getPlayer().setWillStay(false);
		}
			
		//If the opponent is over the limit, prepare for defeat...
		if(mySum>limit){
			willStay = true;
			//Play defense ace if life is low and bet is high
			return;
		}
		
		for(int i=0;i<deck.size();i++){
			if(mySum+deck.get(i)<=limit){count++;}
		}
		if(mySum+playerHand.get(0)<=limit){count++;}
		count--; //Pawn is pessimistic, thinking the Player has a possible good card.
		mySafety = (double)count / (double)deck.size();
		System.out.println(hand.get(0));
		//System.out.println("Probability of safe draw: " + mySafety);
		
		//If the chance of drawing a non lethal card is low, then draw.
		if(mySafety>=0.6)
		{
			this.drawCard(game.getDeck());
			willStay = false;
			return;
		}
		
		//Find Player's probability of winning. If it's high, risk it all.
		count =0;
		for(int i=0;i<deck.size();i++){
			if(playerSum+deck.get(i)>=mySum && playerSum+deck.get(i)<=limit){count++;}
		}
		if(playerSum+playerHand.get(0)>=mySum && playerSum+playerHand.get(0)<=limit){count++;}
		playerVictory = (double)count / (double)(deck.size()+1);
		//System.out.println(hand.get(0) + " | " + count);
		//System.out.println("Probability of player winning now: " +playerVictory);
		
		if(playerVictory>=0.65 && mySum<limit){
			this.drawCard(game.getDeck());
			//game.getOpponent().drawCard(game.getDeck());
			willStay = false;
			return;
		}
		willStay=true;
	}
}
