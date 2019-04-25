package Aces;
import java.util.ArrayList;
import java.util.Random;

import Extreme21.Extreme21Game;

public class AceAceSwitchPlus extends Ace {

	public AceAceSwitchPlus(){
		this.name = "Ace Switch+";
		this.description = "Two of your Aces are discard at random. Draw 4 Aces.";
	}
	
	@Override
	public void use(Extreme21Game game){
		Random random = new Random();
		int size=0, value; //size will be the size of the Aces list/array. Value is the random value.
		
		//Checks if it is currently the Player's turn.
		if(game.getPlayerIsNext()){
			ArrayList<Ace> aces = game.getPlayer().getAces();  
			size = aces.size();
			
			if(size<2) {
				//Ace failed to work. Add it back to your list of Aces.
				System.out.println("This cannot be used. You do not have two Aces you can discard.");
				game.getPlayer().addAce(); //id for AceAceSwitch
				return;
			}
			
			value = random.nextInt(size-1) + 0; //random value from 0 to size of ArrayList -1
			aces.remove(value); //remove one Ace from Player's list
			value = random.nextInt(size-2) + 0; //random value from 0 to size of ArrayList -2 because 1 value was removed
			aces.remove(value); //remove another Ace from Player's list
			
			//Player gets 4 new Aces.
			game.getPlayer().addAce();
			game.getPlayer().addAce();
			game.getPlayer().addAce();
			game.getPlayer().addAce();
			}
			else{
				//We do not need to check size of the Opponent's Aces. The Opponent AI will already take that into account.
				//This Ace is also not intended to be used by any Opponent, but just in case, we will write the code.
				int[] aces = game.getOpponent().getAces();
				for(int i=0;i<aces.length;i++){size+=aces[i];}
				
				value = random.nextInt(size-1) + 0; //random value from 0 to size of ArrayList -1
				int count = 0; //count is to give the lower range of the aces to check
				for(int i=0;i<aces.length;i++){
					if(count<value&&value<=aces[i]+count){
						aces[i]--;
						break;
					}
					else{
						count+=aces[i];
					}
				}
				
				//Repeat the method to remove the second Ace.
				value = random.nextInt(size-2) + 0; //random value from 0 to size of ArrayList -2
				count = 0;
				for(int i=0;i<aces.length;i++){
					if(count<value&&value<=aces[i]+count){
						aces[i]--;
						break;
					}
					else{
						count+=aces[i];
					}
				}
				
				//Opponent gets 4 new Aces
				game.getOpponent().addAce();
				game.getOpponent().addAce();
				game.getOpponent().addAce();
				game.getOpponent().addAce();
			}	
		}
}
