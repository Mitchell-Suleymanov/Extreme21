package Aces;

import Extreme21.Extreme21Game;

public class AceDestroyPlusPlus extends Ace {

	//Target will help in remove function. True = Player, False = Opponent
	boolean targetPlayer;
	
	public AceDestroyPlusPlus(){
		this.name = "Destroy++";
		this.description = "Remove all Aces in play by your opponent."
				+ "They can no longer use Aces while this Ace is in play.";
		this.isActive=true;
	}
	
	@Override
	public void use(Extreme21Game game){
		game.setUpdateAcesInPlay(true);//flag as true so it is updated in the update() method for Extreme21Frame
		
		//Checks if it is currently the Player's turn.
		if(game.getPlayerIsNext()){
			if(game.getOpponent().getAcesInPlay().isEmpty()){
				game.getOpponent().setCanUseAces(false); //Opponent can no longer use Aces while this is in play
				return;
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
			game.getPlayer().setCanUseAces(false); //Player can no longer use Aces while this is in play
		}
	}
	
	//When Destroy++ is removed from play, undo it's effects.
	//target variable will determine who to adjust. True = Player, False = Opponent
	//Removal Effect: Let opponent use Aces again.
	@Override
	public void removeFromPlay(Extreme21Game game){
		game.setUpdateAcesInPlay(true);//flag as true so it is updated in the update() method for Extreme11Frame
		
		//Checks target of the Ace's effects, then does the opposite since it is no longer in play. 
		if(!targetPlayer){
			game.getPlayer().setCanUseAces(true);
		}
		else{
			game.getOpponent().setCanUseAces(true);
		}
	}
}
