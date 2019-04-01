package Aces;
import Extreme21.Extreme21Game;

public class AceTwoUp extends Ace {
	
	//Target will help in remove function. True = Player, False = Opponent
	boolean targetPlayer;

	public AceTwoUp(){
		this.name = "Two Up";
		this.description = "Your opponent's bet is increased by 2 while this Ace is in play.";
		this.isActive=true;
	}
	
	@Override
	public void use(Extreme21Game game){
		game.setUpdateAcesInPlay();//flag as true so it is updated in the update() method for Extreme21Frame
		
		if(game.getPlayerIsNext()){
			int bet = game.getOpponent().getBet();
			game.getOpponent().setBet(bet+2);
			AceTwoUp ace = new AceTwoUp();
			game.getPlayer().getAcesInPlay().add(ace);
			
		}
		else{
			int bet = game.getPlayer().getBet();
			game.getPlayer().setBet(bet+2);
			AceTwoUp ace = new AceTwoUp();
			game.getOpponent().getAcesInPlay().add(ace);
		}
		
		
	}
	
	//When TwoUp is removed from play, undo it's effects.
	//target variable will determine who to adjust. True = Player, False = Opponent
	//Effect undone: Reduce opponent's bet by 2
	@Override
	public void removeFromPlay(Extreme21Game game, boolean target){
		game.setUpdateAcesInPlay();//flag as true so it is updated in the update() method for Extreme21Frame
		if(target){
			int bet = game.getPlayer().getBet();
			game.getPlayer().setBet(bet-2);
		}
		else{
			int bet = game.getOpponent().getBet();
			game.getOpponent().setBet(bet-2);
		}
	}
}
