package Aces;
import Extreme21.Extreme21Game;

public class AceTwoUp extends Ace {
	
	//Target will help in remove function. True = Player, False = Opponent
	boolean targetPlayer;

	public AceTwoUp(){
		this.name = "Two Up";
		this.description = "Your opponent's bet is increased by 2 while this Ace is in play. Also draw one Ace.";
		this.isActive=true;
	}
	
	@Override
	public void use(Extreme21Game game){
		game.setUpdateAcesInPlay(true);//flag as true so it is updated in the update() method for Extreme21Frame
		
		//Checks if it is currently the Player's turn.
		if(game.getPlayerIsNext()){
			targetPlayer= false;
			int bet = game.getOpponent().getBet();
			game.getOpponent().setBet(bet+2); //Player used TwoUp, so increment Opponent's Bet by 2 
			AceTwoUp ace = new AceTwoUp(); 
			game.getPlayer().getAcesInPlay().add(ace); //Add TwoUp to Player's list of acesInPlay
			game.getPlayer().addAce();
		}
		else{
			targetPlayer= true;
			int bet = game.getPlayer().getBet();
			game.getPlayer().setBet(bet+2); //Opponent used TwoUp, so increment Player's Bet by 2 
			AceTwoUp ace = new AceTwoUp();
			game.getOpponent().getAcesInPlay().add(ace); //Add TwoUp to Opponent's list of acesInPlay
			game.getOpponent().addAce();
		}
		
		
	}
	
	//When TwoUp is removed from play, undo it's effects.
	//target variable will determine who to adjust. True = Player, False = Opponent
	//Removal Effect: Reduce opponent's bet by 2
	@Override
	public void removeFromPlay(Extreme21Game game){
		game.setUpdateAcesInPlay(true);//flag as true so it is updated in the update() method for Extreme21Frame
		
		//Checks target of the Ace's effects, then does the opposite since it is no longer in play. 
		if(!targetPlayer){
			int bet = game.getPlayer().getBet();
			game.getPlayer().setBet(bet-2);
		}
		else{
			int bet = game.getOpponent().getBet();
			game.getOpponent().setBet(bet-2);
		}
	}
}
