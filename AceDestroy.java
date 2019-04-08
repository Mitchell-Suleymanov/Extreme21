package Aces;

import Extreme21.Extreme21Game;

public class AceDestroy extends Ace {

	public AceDestroy(){
		this.name = "Destroy";
		this.description = "Remove the last Ace in play by your opponent.";
	}
	
	@Override
	public void use(Extreme21Game game){
		game.setUpdateAcesInPlay(true);//flag as true so it is updated in the update() method for Extreme21Frame
		
		//Checks if it is currently the Player's turn.
		if(game.getPlayerIsNext()){
			if(game.getOpponent().getAcesInPlay().isEmpty()){
				return;
				//Why would you even use this when there are no Aces In Play for your Opponent?
			}
			int index = game.getOpponent().getAcesInPlay().size()-1; //Get size of Opponent's acesInPlay 
			game.getOpponent().getAcesInPlay().remove(index).removeFromPlay(game); //Call removeFromPlay on the Opponent's LAST Ace.
		}
		else{
			//We do not need to check size of the Player's acesInPlay. The Opponent AI will already take that into account.
			int index = game.getPlayer().getAcesInPlay().size()-1; //Get size of Player's acesInPlay 
			game.getPlayer().getAcesInPlay().remove(index).removeFromPlay(game); //Call removeFromPlay on the Player's LAST Ace.
		}
	}
	
	
}
