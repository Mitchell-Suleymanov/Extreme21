package Aces;
import Extreme21.Extreme21Game;

public class AceMine extends Ace {
	
	//Target will help in remove function. True = Player, False = Opponent
	boolean targetPlayer;

	public AceMine(){
		this.name = "Mine";
		this.description = "If this Ace leaves the field, draw 3 Aces.";
		this.isActive=true;
	}
	
	@Override
	public void use(Extreme21Game game){
		game.setUpdateAcesInPlay(true);//flag as true so it is updated in the update() method for Extreme21Frame
		
		//Checks if it is currently the Player's turn.
		if(game.getPlayerIsNext()){
			targetPlayer= true; //true = target is Player
			AceMine ace = new AceMine(); 
			game.getPlayer().getAcesInPlay().add(ace); //Add Mine to Player's list of acesInPlay
		}
		else{
			targetPlayer= false; //false = target is Opponent
			AceMine ace = new AceMine();
			game.getOpponent().getAcesInPlay().add(ace); //Add Mine to Opponent's list of acesInPlay
		}
		
		
	}
	
	//When Mine is removed from play, activate it's Trigger effect.
	//target variable will determine who to adjust. True = Player, False = Opponent
	//Removal Effect: The person who used Mine draws 3 Aces.
	@Override
	public void removeFromPlay(Extreme21Game game){
		game.setUpdateAcesInPlay(true);//flag as true so it is updated in the update() method for Extreme21Frame
		
		//Checks target of the Ace's effects, then does the opposite since it is no longer in play. 
		if(targetPlayer){//Player is effected
			game.getPlayer().addAce();
			game.getPlayer().addAce();
			game.getPlayer().addAce();
		}
		else{
			game.getOpponent().addAce();
			game.getOpponent().addAce();
			game.getOpponent().addAce();
		}
	}
}
