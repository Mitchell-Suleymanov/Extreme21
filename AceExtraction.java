package Aces;
import Extreme21.Extreme21Game;

/*
 * This Ace is intended to be for the Player only.
 * It is not intended to be used by the Opponent.
 */
public class AceExtraction extends Ace {
	
	public AceExtraction(){
		this.name = "Extraction";
		this.description = "Return your last face-up card to the deck. "
				+ "Draw Aces equal to the number of the card you just returned and increase your bet by that amount.";
	}
	
	@Override
	public void use(Extreme21Game game){		
		int playerHandSize = game.getPlayer().getHand().size(); 

		//Checks if it is currently the Player's turn.
		if(game.getPlayerIsNext()&&playerHandSize>1){
			int card = game.getPlayer().getHand().remove(playerHandSize-1);
			int bet = game.getPlayer().getBet(); //Get Player's Bet
			
			game.getDeck().add(card); //Add removed card to Deck
			game.getPlayer().setBet(bet+card); //Increase Player's Bet by Card value
			for(int i=0;i<card;i++){game.getPlayer().addAce();} //Give Player Aces equal to the value of the Card returned
		}
		else if(!game.getPlayerIsNext()){
			//We do not need to check size of the Opponent's Hand. The Opponent AI will already take that into account.
			int index = game.getOpponent().getHand().size()-1; 
			int card = game.getOpponent().getHand().remove(index);
			int bet = game.getOpponent().getBet();
			
			game.getDeck().add(card); //Add removed card to Deck
			game.getOpponent().setBet(bet+card); //Increase Opponent's Bet by Card value
			for(int i=0;i<card;i++){game.getOpponent().addAce();} //Give Opponent Aces equal to the value of the Card returned
		}
		else{
			System.out.println("This cannot be used. Your opponent does not have a face-up card.");
			game.getPlayer().addAce(); //id for AceReturn
		}
		

	}
	
}
