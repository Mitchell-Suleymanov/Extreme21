package Aces;
import java.util.ArrayList;
import java.util.Collections;

import Extreme21.Extreme21Game;

public class AcePerfectDrawPlus extends Ace {
	//Target will help in remove function. True = Player, False = Opponent
	boolean targetPlayer;
	
	public AcePerfectDrawPlus(){
		this.name = "Perfect Draw+";
		this.description = "Draw the best possible card from the deck."
				+ "Your opponent's bet is increased by 5 while this Ace is in play.";
		this.isActive=true;
	}
	
	@Override
	public void use(Extreme21Game game){
		game.setUpdateAcesInPlay(true);//flag as true so it is updated in the update() method for Extreme21Frame
		ArrayList<Integer> deck = game.getDeck();
		int sumOfHand = 0; //sum of values in Hand
		int bestCard = 0;//best possible card
		int gameLimit = game.getLimit(); //current Limit of game.
		int position = -1; //position in deck of the best card.
		int currentBest=0; //current best card we could find
		
		if(game.getPlayerIsNext() && !deck.isEmpty()){
			//This section will increment the Opponent's Bet by 5
			targetPlayer= false;
			int bet = game.getOpponent().getBet();
			game.getOpponent().setBet(bet+5); //Player used TwoUp, so increment Opponent's Bet by 2 
			AcePerfectDrawPlus ace = new AcePerfectDrawPlus(); 
			game.getPlayer().getAcesInPlay().add(ace); //Add TwoUp to Player's list of acesInPlay
			System.out.println("No cards in Deck.");
			
			//This section will give the Player the best Card.
			ArrayList<Integer> playerHand = game.getPlayer().getHand();
			for(int i=0;i<playerHand.size();i++){
				sumOfHand+= playerHand.get(i);
			}
			if(gameLimit-sumOfHand>0){//If the game's Limit minus the Hand's sum is greater than 0, that means we could have a possible best card
				bestCard = gameLimit-sumOfHand;
				for(int i=0;i<deck.size();i++){
					if(deck.get(i)<=bestCard && deck.get(i)>currentBest){
						position = i;
						currentBest= deck.get(i);
					}
				}
			}
			if(position==-1){//the Player is using this Ace when they are over the Limit, have exactly the Limit, or no best card exists.
				Collections.sort(deck);
				playerHand.add(deck.get(0));
				Collections.shuffle(deck);
				return;
			}
			playerHand.add(deck.remove(position));
			
		}
		else if(!game.getPlayerIsNext() && !deck.isEmpty()){
			targetPlayer= true;
			int bet = game.getPlayer().getBet();
			game.getPlayer().setBet(bet+5); //Opponent used PerfectDraw+, so increment Opponent's Bet by 5 
			AcePerfectDrawPlus ace = new AcePerfectDrawPlus(); 
			game.getOpponent().getAcesInPlay().add(ace); //Add PerfectDraw+ to Opponent's list of acesInPlay

			//This section will give the Opponent the best Card.
			ArrayList<Integer> opponentHand = game.getOpponent().getHand();
			//Get sum of the Opponent's Hand
			for(int i=0;i<opponentHand.size();i++){
				sumOfHand+= opponentHand.get(i);
			}
			if(gameLimit-sumOfHand>0){//If the game's Limit minus the Hand's sum is greater than 0, that means we could have a possible best card
				bestCard = gameLimit-sumOfHand;
				for(int i=0;i<deck.size();i++){
					if(deck.get(i)<=bestCard && deck.get(i)>currentBest){
						position = i;
						currentBest= deck.get(i);
					}
				}
			}
			if(position==-1){//the Opponent is using this Ace when they are over the Limit, have exactly the Limit, or no best card exists.
				Collections.sort(deck);
				opponentHand.add(deck.get(0));
				Collections.shuffle(deck);
				return;
			}
			opponentHand.add(deck.remove(position));
		}
		else{
			//The Opponent AI will know in advance if the deck is empty or not, so they will never reach here.
			//That means this section will only be reached by the Player.
			targetPlayer= false;
			int bet = game.getOpponent().getBet();
			game.getOpponent().setBet(bet+5); //Player used PerfectDraw+, so increment Opponent's Bet by 5 
			AcePerfectDrawPlus ace = new AcePerfectDrawPlus(); 
			game.getPlayer().getAcesInPlay().add(ace); //Add PerfectDraw+ to Player's list of acesInPlay
			System.out.println("No cards in Deck.");
		}
		
		
	}
	//When PerfectDraw+ is removed from play, undo it's effects.
	//target variable will determine who to adjust. True = Player, False = Opponent
	//Removal Effect: Reduce opponent's bet by 5
	@Override
	public void removeFromPlay(Extreme21Game game){
		game.setUpdateAcesInPlay(true);//flag as true so it is updated in the update() method for Extreme21Frame
		
		//Checks target of the Ace's effects, then does the opposite since it is no longer in play. 
		if(!targetPlayer){
			int bet = game.getPlayer().getBet();
			game.getPlayer().setBet(bet-5);
		}
		else{
			int bet = game.getOpponent().getBet();
			game.getOpponent().setBet(bet-5);
		}
	}
}
