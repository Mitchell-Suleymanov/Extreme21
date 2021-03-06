package Aces;
import Extreme21.Extreme21Game;

public class AceShield extends Ace {
	
	//Target will help in remove function. True = Player, False = Opponent
	boolean targetPlayer;

	public AceShield(){
		this.name = "Shield";
		this.description = "Your bet is decreased by 1 while this Ace is in play.";
		this.isActive=true;
	}
	
	@Override
	public void use(Extreme21Game game){
		game.setUpdateAcesInPlay(true);//flag as true so it is updated in the update() method for Extreme11Frame
		
		//Checks if it is currently the Player's turn.
		if(game.getPlayerIsNext()){
			targetPlayer= true;
			int bet = game.getPlayer().getBet();
			game.getPlayer().setBet(bet-1); //Player used Shield, so decrement Player's Bet by 1 
			AceShield ace = new AceShield();
			game.getPlayer().getAcesInPlay().add(ace); //Add Shield to Player's list of acesInPlay
		}
		else{
			targetPlayer= false;			
			int bet = game.getOpponent().getBet();
			game.getOpponent().setBet(bet-1); //Opponent used Shield, so decrement Opponent's Bet by 1 
			AceShield ace = new AceShield(); 
			game.getOpponent().getAcesInPlay().add(ace); //Add Shield to Opponent's list of acesInPlay
		}
		
		
	}
	
	//When Shield is removed from play, undo it's effects.
	//target variable will determine who to adjust. True = Player, False = Opponent
	//Removal Effect: Increase your bet by 1
	@Override
	public void removeFromPlay(Extreme21Game game){
		game.setUpdateAcesInPlay(true);//flag as true so it is updated in the update() method for Extreme11Frame
		
		//Checks target of the Ace's effects, then does the opposite since it is no longer in play. 
		if(!targetPlayer){ //if Opponent is target
			int bet = game.getOpponent().getBet();
			game.getOpponent().setBet(bet+1);
		}
		else{ //if Player is target
			int bet = game.getPlayer().getBet();
			game.getPlayer().setBet(bet+1);
		}
	}
}
