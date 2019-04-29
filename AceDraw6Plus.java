package Aces;
import Extreme21.Extreme21Game;

//This Ace is intended to be for the Opponent ONLY
public class AceDraw6Plus extends Ace {
	
	public AceDraw6Plus(){
		this.name = "Draw 6+";
		this.description = "Draw the 6 card. If it is not in the deck, nothing happens. Also draw one Ace.";
	}
		
	@Override
	public void use(Extreme21Game game){
		if(game.getDeck().contains(6)){//6 Card is in deck
			System.out.println("Added the 6 card.");
			int index = game.getDeck().indexOf(6);
			game.getDeck().remove(index);
			game.getOpponent().getHand().add(6); //Add 6 Card to Opponent's Hand
		}
		else{//Card not in deck
			//This will probably never happen because the Opponent AI will check beforehand.
			System.out.println("The 6 card is not in the deck.");
		}
		game.getOpponent().addAce(); //Add one Ace to Opponent's Aces
	}
	
}
