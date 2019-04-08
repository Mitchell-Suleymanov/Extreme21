package Aces;
import Extreme21.Extreme21Game;

public class AceTwoUpPlus extends Ace {
	
	//Target will help in remove function. True = Player, False = Opponent
	boolean targetPlayer;

	public AceTwoUpPlus(){
		this.name = "Two Up+";
		this.description = "Return your opponent's last face-up card to the deck. "
				+ "Your opponent's bet is increased by 2 while this Ace is in play.";
		this.isActive=true;
	}
	
	@Override
	public void use(Extreme21Game game){
		game.setUpdateAcesInPlay(true);//flag as true so it is updated in the update() method for Extreme21Frame
		
		//Checks if it is currently the Player's turn.
		if(game.getPlayerIsNext()){
			
			//Check if the Opponent has at least 1 face-up card. If not, the Ace wont work.
			int index = game.getOpponent().getHand().size();
			if(index>1){
				int card = game.getOpponent().getHand().remove(index-1);
				game.getDeck().add(card);
			}
			else{
				//Ace failed to work. Add it back to your list of Aces.
				System.out.println("This cannot be used. Your opponent does not have a face-up card.");
				game.getPlayer().addAce(); //id for AceReturn
				return;
			}
			
			targetPlayer= false;
			int bet = game.getOpponent().getBet();
			game.getOpponent().setBet(bet+2); //Player used TwoUp+, so increment Opponent's Bet by 2 
			AceTwoUpPlus ace = new AceTwoUpPlus(); 
			game.getPlayer().getAcesInPlay().add(ace); //Add TwoUp+ to Player's list of acesInPlay
			
		}
		else{
			//We do not need to check size of the Player's Hand. The Opponent AI will already take that into account.
			targetPlayer= true;
			int bet = game.getPlayer().getBet();
			game.getPlayer().setBet(bet+2); //Opponent used TwoUp+, so increment Player's Bet by 2 
			AceTwoUpPlus ace = new AceTwoUpPlus();
			game.getOpponent().getAcesInPlay().add(ace); //Add TwoUp+ to Opponent's list of acesInPlay
		}
		
		
	}
	
	//When TwoUp+ is removed from play, undo it's effects.
	//target variable will determine who to adjust. True = Player, False = Opponent
	//Effect undone: Reduce opponent's bet by 2
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
