package Aces;
import Extreme21.Extreme21Game;

//This Ace is intended to be for the Opponent ONLY
public class AceDraw4Plus extends Ace {
	
	public AceDraw4Plus(){
		this.name = "Draw 4+";
		this.description = "Draw the 4 card. If it is not in the deck, nothing happens. Also draw one Ace.";
	}
		
	@Override
	public void use(Extreme21Game game){
		if(game.getDeck().contains(4)){//2 Card is in deck
			System.out.println("Added the 4 card.");
			int index = game.getDeck().indexOf(4);
			game.getDeck().remove(index);
			game.getOpponent().getHand().add(4); //Add 4 Card to Opponent's Hand
		}
		else{//Card not in deck
			//This will probably never happen because the Opponent AI will check beforehand.
			System.out.println("The 4 card is not in the deck.");
		}
		game.getOpponent().addAce(); //Add one Ace to Opponent's Aces
	}
	
}
