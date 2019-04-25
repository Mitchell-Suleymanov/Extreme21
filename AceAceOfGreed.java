package Aces;
import Extreme21.Extreme21Game;

//This Ace is supposed to be a joke referencing a card from a popular card game.
public class AceAceOfGreed extends Ace {
	public AceAceOfGreed(){
		this.name = "Ace of Greed";
		this.description = "No one knows what this does.";
	}
	
	@Override
	public void use(Extreme21Game game){
		if(game.getPlayerIsNext()){
			game.getPlayer().addAce();
			game.getPlayer().addAce();
		}
		else{
			game.getOpponent().addAce();
			game.getOpponent().addAce();
		}	
	}
}
