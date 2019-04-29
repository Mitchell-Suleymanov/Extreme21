package Aces;
import Extreme21.Extreme21Game;

//This Ace is intended to be for the Opponent ONLY
public class AceDraw2Plus extends Ace {
	
	public AceDraw2Plus(){
		this.name = "Draw 2+";
		this.description = "Draw the 2 card. If it is not in the deck, nothing happens. Also draw one Ace.";
	}
		
	@Override
	public void use(Extreme21Game game){
		if(game.getDeck().contains(2)){//2 Card is in deck
			System.out.println("Added the 2 card.");
			int index = game.getDeck().indexOf(2);
			game.getDeck().remove(index);
			game.getOpponent().getHand().add(2); //Add 2 Card to Opponent's Hand
		}
		else{//Card not in deck
			//This will probably never happen because the Opponent AI will check beforehand.
			System.out.println("The 2 card is not in the deck.");
		}
		game.getOpponent().addAce(); //Add one Ace to Opponent's Aces
	}
	
}
