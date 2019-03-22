package Aces;
import Extreme21.Extreme21Game;

public class AceExchange extends Ace {
	
	public AceExchange(){
		this.name = "Exchange";
		this.description = "Swap you and your opponent's last face-up card.";
	}
	
	@Override
	public void use(Extreme21Game game){
		int playerHandSize = game.getPlayer().getHand().size();
		int opponentHandSize = game.getOpponent().getHand().size();

		if(playerHandSize>1 && opponentHandSize>1){
			int temp = game.getPlayer().getHand().get(playerHandSize-1);
			game.getPlayer().getHand().set(playerHandSize-1, game.getOpponent().getHand().get(opponentHandSize-1)); 
			game.getOpponent().getHand().set(opponentHandSize-1, temp); 
		}
		else{//Card not in deck
			System.out.println("This cannot be used. You or your opponent does not have a face-up card.");
			game.getPlayer().addAce();
		}
	}
	
}
