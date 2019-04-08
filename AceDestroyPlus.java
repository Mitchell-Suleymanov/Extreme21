package Aces;

import Extreme21.Extreme21Game;

public class AceDestroyPlus extends Ace {

	public AceDestroyPlus(){
		this.name = "Destroy+";
		this.description = "Remove all Aces in play by your opponent.";
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
			int size = game.getOpponent().getAcesInPlay().size(); //Get size of Opponent's acesInPlay
			for(int count=0;count<size;count++){
				game.getOpponent().getAcesInPlay().remove(0).removeFromPlay(game); //Call removeFromPlay on ALL Opponent's Aces In Play.
			}
		}
		else{
			//We do not need to check size of the Player's acesInPlay. The Opponent AI will already take that into account.
			int size = game.getPlayer().getAcesInPlay().size(); //Get size of Player's acesInPlay 
			for(int count=0;count<size;count++){
				game.getOpponent().getAcesInPlay().remove(0).removeFromPlay(game); //Call removeFromPlay on ALL Player's Aces In Play.
			}
		}
	}
	
	
}
