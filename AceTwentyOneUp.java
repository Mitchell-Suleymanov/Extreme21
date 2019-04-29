package Aces;
import Extreme21.Extreme21Game;

public class AceTwentyOneUp extends Ace {
	
	//Target will help in remove function. True = Player, False = Opponent
	boolean targetPlayer;

	public AceTwentyOneUp(){
		this.name = "Twenty-One-Up";
		this.description = "This can only be used if your card total is 21. Raise your opponent's bet by 21 while this Ace is in play.";
		this.isActive=true;
	}
	
	@Override
	public void use(Extreme21Game game){
		game.setUpdateAcesInPlay(true);//flag as true so it is updated in the update() method for Extreme11Frame
		
		//Checks if it is currently the Player's turn.
		if(game.getPlayerIsNext() && game.getPlayer().getSum()==21){
			targetPlayer= false;
			int bet = game.getOpponent().getBet();
			game.getOpponent().setBet(bet+21); //Player used TwentyOneUp, so increment Opponent's Bet by 21 
			AceTwentyOneUp ace = new AceTwentyOneUp(); 
			game.getPlayer().getAcesInPlay().add(ace); //Add TwentyOneUp to Player's list of acesInPlay
			game.getPlayer().addAce();
		}
		else if(!game.getPlayerIsNext()){
			//We do not need to check if the sum of the Opponent's Hand is 21. The Opponent AI will already take that into account.
			targetPlayer= true;
			int bet = game.getPlayer().getBet();
			game.getPlayer().setBet(bet+21); //Opponent used TwentyOneUp, so increment Player's Bet by 21 
			AceTwentyOneUp ace = new AceTwentyOneUp();
			game.getOpponent().getAcesInPlay().add(ace); //Add TwentyOneUp to Opponent's list of acesInPlay
			game.getOpponent().addAce();
		}
		else{
			System.out.println("Your card total is not 21. Ace effects will not be used.");
		}
		
		
	}
	
	//When TwentyOneUp is removed from play, undo it's effects.
	//target variable will determine who to adjust. True = Player, False = Opponent
	//Removal Effect: Reduce opponent's bet by 21
	@Override
	public void removeFromPlay(Extreme21Game game){
		game.setUpdateAcesInPlay(true);//flag as true so it is updated in the update() method for Extreme11Frame
		
		//Checks target of the Ace's effects, then does the opposite since it is no longer in play. 
		if(!targetPlayer){
			int bet = game.getPlayer().getBet();
			game.getPlayer().setBet(bet-21);
		}
		else{
			int bet = game.getOpponent().getBet();
			game.getOpponent().setBet(bet-21);
		}
	}
}
