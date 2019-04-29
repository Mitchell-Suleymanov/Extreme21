package Opponents;

import java.util.ArrayList;
import java.util.Random;
import Aces.*;
import Extreme21.Extreme21Game;

public class OpponentBishop extends Opponent {
	
	public OpponentBishop(){
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
		 * Ace[0] = Draw 2+       (20%)
		 * Ace[1] = Draw 4+       (20%)
		 * Ace[2] = Draw 6+       (20%)
		 * Ace[3] = One Up        (30%)         
		 * Ace[4] = Twenty-One-Up (10%)L
		 * L = uses Luck variable
		 */
		this.aces = new int[5];

	}
	
	@Override
	public void addAce(){
		//(max - min + 1) + min is your range, range is between 2 and 7
		//Random random = new Random();
		//int value = random.nextInt(6) + 1;

		Random random = new Random();
		int value = random.nextInt(100) + 1;
		
		if(value<=luck) {aces[4]++;}
		else if(value<=30) {aces[0]++;}
		else if(value<=50) {aces[1]++;}
		else if(value<=70) {aces[2]++;}
		else {aces[3]++;}
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
		
		System.out.println("2: "+ aces[0] + " | 4: " + aces[1] + " | 6: " + aces[2] + " | O: " + aces[3] + " | 21: " + aces[4]);

		//If the player's sum is greater than the limit or the opponent has a total equaling the limit, the opponent will know to stay.
		//Additionally, Bishop will use two OneUp Aces if there is less than 2 spots on their Aces In Play
		//If Bishop has a sum of 21 and the limit is 21, go for the instant kill and use TwentyOneUp
		if(playerSum>=limit || mySum==limit) {
			//
			if(mySum==21 && limit==21 && aces[4]>0){
				new AceTwentyOneUp().use(game);
				aces[4]--;
				game.getPlayer().setWillStay(false); //Because an Ace was used, the Player must decide to stay or not again.
			}
			else{
				//Use up to 2 OneUp Aces, assuming Bishop has them and there are 2 empty spots.
				while(acesInPlay.size()<2&&aces[3]>0){
					new AceOneUp().use(game);
					aces[3]--;
					game.getPlayer().setWillStay(false); //Because an Ace was used, the Player must decide to stay or not again.
				}
			}
			willStay = true;
			return;
		}
			
		//If Bishop is over the limit, prepare for defeat...
		if(mySum>limit){
			willStay = true;
			return;
		}
		
		/*
		 * Bishop needs a constant supply of Aces. Therefore, Bishop will use useless Aces to draw more.
		 */		
		while((!deck.contains(2)&&aces[0]>1)||(!deck.contains(4)&&aces[1]>1)||(!deck.contains(6)&&aces[2]>1)){
			if(!deck.contains(2)&&aces[0]>1){new AceDraw2Plus().use(game); aces[0]--;}
			if(!deck.contains(4)&&aces[1]>1){new AceDraw4Plus().use(game); aces[1]--;}
			if(!deck.contains(6)&&aces[2]>1){new AceDraw6Plus().use(game); aces[2]--;}			
		}
		

		/*
		 * Logic for using Draw cards:
		 *  2 = deck has 2
		 *	4 = deck has 4
		 *	6 = deck has 6
		 *	aces[0] = # of Draw2
		 *	aces[1] = # of Draw4
		 *	aces[2] = # of Draw6
		 *  limit = game limit (usually 21)
		 *	sum = sum of cards hand
		 *	
		 *	if (     limit-sum is 12 && 2,4,6 && aces[0,1,2]>0) : use aces[0,1,2]
		 *	else if (limit-sum is 10 && 4,6   && aces[1,2]  >0) : use aces[1,2]
		 *  else if (limit-sum is 8  && 2,6   && aces[0,2]  >0) : use aces[0,2]
		 *	else if (limit-sum is 6  && 2,4   && aces[0,1]  >0) : use aces[0,1]
		 *	else if (limit-sum is 6  && 6     && aces[2]    >0) : use aces[2]
		 *	else if (limit-sum is 4  && 4     && aces[1]    >0) : use aces[1]
		 *	else if (limit-sum is 2  && 2     && aces[0]    >0) : use aces[0]
		 *	else (normal calculations for draw)
		 */
		if(limit-mySum==12 && deck.contains(2) && deck.contains(4) && deck.contains(6) && aces[0]>1 && aces[1]>0 && aces[2]>0){
			new AceDraw2Plus().use(game);
			new AceDraw4Plus().use(game);
			new AceDraw6Plus().use(game);
			aces[0]--;
			aces[1]--;
			aces[2]--;
			game.getPlayer().setWillStay(false); //Because an Ace was used, the Player must decide to stay or not again.
		}
		else if(limit-mySum==10 && deck.contains(4) && deck.contains(6) && aces[1]>0 && aces[2]>0){
			new AceDraw4Plus().use(game);
			new AceDraw6Plus().use(game);
			aces[1]--;
			aces[2]--;
			game.getPlayer().setWillStay(false); //Because an Ace was used, the Player must decide to stay or not again.
		}
		else if(limit-mySum==8 && deck.contains(2) && deck.contains(6) && aces[0]>0 && aces[2]>0){
			new AceDraw2Plus().use(game);
			new AceDraw6Plus().use(game);
			aces[0]--;
			aces[2]--;
			game.getPlayer().setWillStay(false); //Because an Ace was used, the Player must decide to stay or not again.
		}
		else if(limit-mySum==6 && deck.contains(2) && deck.contains(4) && aces[0]>1 && aces[1]>0){
			new AceDraw2Plus().use(game);
			new AceDraw4Plus().use(game);
			aces[0]--;
			aces[1]--;
			game.getPlayer().setWillStay(false); //Because an Ace was used, the Player must decide to stay or not again.
		}
		else if(limit-mySum==6 && deck.contains(6) && aces[2]>0){
			new AceDraw6Plus().use(game);
			aces[2]--;
			game.getPlayer().setWillStay(false); //Because an Ace was used, the Player must decide to stay or not again.
		}
		else if(limit-mySum==4 && deck.contains(4) && aces[1]>0){
			new AceDraw4Plus().use(game);
			aces[1]--;
			game.getPlayer().setWillStay(false); //Because an Ace was used, the Player must decide to stay or not again.
		}
		else if(limit-mySum==2 && deck.contains(2) && aces[0]>1){
			new AceDraw2Plus().use(game);
			aces[0]--;
			game.getPlayer().setWillStay(false); //Because an Ace was used, the Player must decide to stay or not again.
		}
		else{ //Normal Draw Procedure
			for(int i=0;i<deck.size();i++){
				if(mySum+deck.get(i)<=limit){count++;}
			}
			if(mySum+playerHand.get(0)<=limit){count++;}
			//count--; //Bishop is optimistic, thinking the Player wont have a possible good card.
			mySafety = (double)count / (double)deck.size();
			System.out.println(hand.get(0));
			//System.out.println("Probability of safe draw: " + mySafety);
			
			//If the chance of drawing a non lethal card is low, then draw.
			if(mySafety>=0.5)
			{
				//Use up to 2 OneUp Aces, assuming Bishop has them and there are 2 empty spots.
				while(acesInPlay.size()<2&&aces[3]>0){
					new AceOneUp().use(game);
					aces[3]--;
					game.getPlayer().setWillStay(false); //Because an Ace was used, the Player must decide to stay or not again.
				}
				this.drawCard(game.getDeck());
				willStay = false;
				return;
			}
		}
	
		/*
		 * Bishop needs a constant supply of Aces. Therefore, Bishop will use useless Aces to draw more.
		 */		
		while((!deck.contains(2)&&aces[0]>1)||(!deck.contains(4)&&aces[1]>1)||(!deck.contains(6)&&aces[2]>1)){
			if(!deck.contains(2)&&aces[0]>1){new AceDraw2Plus().use(game); aces[0]--;}
			if(!deck.contains(4)&&aces[1]>1){new AceDraw4Plus().use(game); aces[1]--;}
			if(!deck.contains(6)&&aces[2]>1){new AceDraw6Plus().use(game); aces[2]--;}			
		}
		//Recheck value of mySum because we may have added cards to the hand.
		mySum = this.getSum();
		
		if(mySum==21 && limit==21 && aces[4]>0){
			new AceTwentyOneUp().use(game);
			aces[4]--;
			game.getPlayer().setWillStay(false); //Because an Ace was used, the Player must decide to stay or not again.
		}
		else{
			//Use up to 2 OneUp Aces, assuming Bishop has them and there are 2 empty spots.
			while(acesInPlay.size()<2&&aces[3]>0){
				new AceOneUp().use(game);
				aces[3]--;
				game.getPlayer().setWillStay(false); //Because an Ace was used, the Player must decide to stay or not again.
			}
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
		
		if(playerVictory>=0.75 && mySum<limit){
			this.drawCard(game.getDeck());
			//game.getOpponent().drawCard(game.getDeck());
			willStay = false;
			return;
		}
		willStay=true;
	}
}
