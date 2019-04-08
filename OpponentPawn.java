package Opponents;

import java.util.ArrayList;

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
		this.aces = new ArrayList<Ace>();
		this.acesInPlay = new ArrayList<Ace>();
	}
	
	@Override
	public void addAce(){
		//(max - min + 1) + min is your range, range is between 2 and 7
		//Random random = new Random();
		//int value = random.nextInt(6) + 1;

		AceDrawX ace = new AceDrawX();
		aces.add(ace);
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
		
		//If the player's sum is greater than the limit or the opponent
		//has a total equaling the limit, the opponent will know to stay.
		if(playerSum>=limit || mySum==limit) {
			willStay = true;
			return;
		}
		
		//If the opponent is over the limit, prepare for defeat...
		if(mySum>limit){
			willStay = true;
			//Play defense ace if life is low and bet is high
			return;
		}
		
		
		if(game.getPlayer().getLife()>5 && acesInPlay.isEmpty() || acesInPlay.size()<2){
			AceTwoUp ace = new AceTwoUp();
			ace.use(game);
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
			//game.getOpponent().drawCard(game.getDeck());
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
		
		
		
//		while(life - bet <= 2 && bet<12){
//			//play defense ace
//		}
		
		willStay=true;
	}
}
