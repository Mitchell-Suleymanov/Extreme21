package Aces;
import Extreme21.Extreme21Game;

public class AceReturn extends Ace {
	
	public AceReturn(){
		this.name = "Return";
		this.description = "Return your last face-up card to the deck.";
	}
	
	@Override
	public void use(Extreme21Game game){		
		int playerHandSize = game.getPlayer().getHand().size();

		//Checks if it is currently the Player's turn.
		if(game.getPlayerIsNext()&&playerHandSize>1){
			int card = game.getPlayer().getHand().remove(playerHandSize-1);
			game.getDeck().add(card);
		}
		else if(!game.getPlayerIsNext()){
			//We do not need to check size of the Opponent's Hand. The Opponent AI will already take that into account.
			int index = game.getOpponent().getHand().size();
			int card = game.getOpponent().getHand().remove(index-1);
			game.getDeck().add(card);
		}
		else{
			System.out.println("This cannot be used. Your opponent does not have a face-up card.");
			game.getPlayer().addAce(); //id for AceReturn
		}
		

	}
	
}
