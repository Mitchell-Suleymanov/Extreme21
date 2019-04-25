package Aces;
import java.util.ArrayList;
import java.util.Collections;

import Extreme21.Extreme21Game;

//This Ace is intended to be for the Player ONLY
public class AceInspector extends Ace {

	public AceInspector(){
		this.name = "Inspector";
		this.description = "Reveals the top 2 card of the deck (or however many are left). Shuffle the deck.";
	}
	
	@Override
	public void use(Extreme21Game game){		
		ArrayList<Integer> deck = game.getDeck();
		if(deck.isEmpty()) {return;}
		for(int i=0;i<2 && i<deck.size();i++){
			System.out.println(deck.get(i));
		}
		if(deck.size()>1){Collections.shuffle(deck);}
	}
}
