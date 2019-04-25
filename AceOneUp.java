package Aces;
import Extreme21.Extreme21Game;

public class AceOneUp extends Ace {
	
	//Target will help in remove function. True = Player, False = Opponent
	boolean targetPlayer;

	public AceOneUp(){
		this.name = "One Up";
		this.description = "Your opponent's bet is increased by 1 while this Ace is in play. Also draw one Ace";
		this.isActive=true;
	}
	
	@Override
	public void use(Extreme21Game game){
		game.setUpdateAcesInPlay(true);//flag as true so it is updated in the update() method for Extreme11Frame
		
		//Checks if it is currently the Player's turn.
		if(game.getPlayerIsNext()){
			targetPlayer= false;
			int bet = game.getOpponent().getBet();
			game.getOpponent().setBet(bet+1); //Player used OneUp, so increment Opponent's Bet by 1 
			AceOneUp ace = new AceOneUp(); 
			game.getPlayer().getAcesInPlay().add(ace); //Add OneUp to Player's list of acesInPlay
			game.getPlayer().addAce();
		}
		else{
			targetPlayer= true;
			int bet = game.getPlayer().getBet();
			game.getPlayer().setBet(bet+1); //Opponent used OneUp, so increment Player's Bet by 1 
			AceOneUp ace = new AceOneUp();
			game.getOpponent().getAcesInPlay().add(ace); //Add OneUp to Opponent's list of acesInPlay
			game.getOpponent().addAce();
		}
		
		
	}
	
	//When OneUp is removed from play, undo it's effects.
	//target variable will determine who to adjust. True = Player, False = Opponent
	//Removal Effect: Reduce opponent's bet by 1
	@Override
	public void removeFromPlay(Extreme21Game game){
		game.setUpdateAcesInPlay(true);//flag as true so it is updated in the update() method for Extreme11Frame
		
		//Checks target of the Ace's effects, then does the opposite since it is no longer in play. 
		if(!targetPlayer){
			int bet = game.getPlayer().getBet();
			game.getPlayer().setBet(bet-1);
		}
		else{
			int bet = game.getOpponent().getBet();
			game.getOpponent().setBet(bet-1);
		}
	}
}
