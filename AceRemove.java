package Aces;
import Extreme21.Extreme21Game;

public class AceRemove extends Ace {
	
	public AceRemove(){
		this.name = "Remove";
		this.description = "Return your opponent's last face-up card to the deck.";
	}
	
	@Override
	public void use(Extreme21Game game){		
		int opponentHandSize = game.getOpponent().getHand().size();

		//Checks if it is currently the Player's turn.
		if(game.getPlayerIsNext()&&opponentHandSize>1){
			int card = game.getOpponent().getHand().remove(opponentHandSize-1);
			game.getDeck().add(card);
		}
		else if(!game.getPlayerIsNext()){
			//We do not need to check size of the Opponent's Hand. The Opponent AI will already take that into account.
			int index = game.getPlayer().getHand().size()-1;
			int card = game.getPlayer().getHand().remove(index);
			game.getDeck().add(card);
		}
		else{
			System.out.println("This cannot be used. Your opponent does not have a face-up card.");
			game.getPlayer().addAce(); //id for AceRemove
		}
		

	}
	
}
