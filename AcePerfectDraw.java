package Aces;
import java.util.ArrayList;
import java.util.Collections;

import Extreme21.Extreme21Game;

public class AcePerfectDraw extends Ace {
	
	public AcePerfectDraw(){
		this.name = "Perfect Draw";
		this.description = "Draw the best possible card from the deck.";
	}
	
	@Override
	public void use(Extreme21Game game){
		ArrayList<Integer> deck = game.getDeck();
		int sumOfHand = 0; //sum of values in Hand
		int bestCard = 0;//best possible card
		int gameLimit = game.getLimit(); //current Limit of game.
		int position = -1; //position in deck of the best card.
		int currentBest=0; //current best card we could find
		
		if(game.getPlayerIsNext() && !deck.isEmpty()){
			ArrayList<Integer> playerHand = game.getPlayer().getHand();
			//Get sum of the Player's Hand
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
			System.out.println("No cards in Deck.");
		}
		
		
	}

}
