package Extreme21;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import Opponents.*;

public class Extreme21Game {
	
	private ArrayList<Integer> deck;
	private Player player;
	private Opponent opponent;
	private int round;
	private int limit;
	private boolean playerIsNext;
	private boolean updateAcesInPlay;
	
	public Extreme21Game(){
		this.deck = new ArrayList<Integer>();
		this.player = new Player();
		this.round = 0;
		this.limit = 21;
		this.playerIsNext = true;
		this.updateAcesInPlay = false;
	}
	
	private Opponent generateOpponent(){
		//1:   Pawn (100)
		//2~4: Pawn (30) || Knight (40) || Bishop (30)
		//5:   Queen(100)
		//6~9: Pawn (10) || Knight(20) || Bishop (30) || Rook (40)
		//10:  King (100)
		if(this.round ==1){
			return new OpponentPawn();
		}
		else
			return new OpponentPawn();
	}
	
	public void pause(long milliseconds){
		try{
			Thread.sleep(milliseconds);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public Opponent getOpponent(){
		return opponent;
	}
	
	public void playerDrawsAce(){
		
		player.addAce();
	}
	
	public void newRound(){
		round++;
		opponent = generateOpponent();
//		
//		deck.clear();
//		for(int i=1;i<12;i++){this.deck.add(i);}
//		Collections.shuffle(deck);
//		player.getHand().clear();
//		
//		
		if(round ==1){
			for(int i=0;i<4;i++)
			{
				player.addAce();
				opponent.addAce();
			}
		}
		else{
			opponent.addAce();
		}
//		player.drawCard(deck);
//		opponent.drawCard(deck);
//		player.drawCard(deck);
//		opponent.drawCard(deck);
		this.newMatch();
	}
	
	public void newMatch(){
		deck.clear();
		for(int i=1;i<12;i++){this.deck.add(i);}
		Collections.shuffle(deck);
		player.getHand().clear();
		opponent.getHand().clear();
		
		if(!player.getAcesInPlay().isEmpty()){ 
			player.getAcesInPlay().clear();
			updateAcesInPlay=true;
		}
				
		player.addAce();
		opponent.addAce();
		
		player.drawCard(deck);
		opponent.drawCard(deck);
		player.drawCard(deck);
		opponent.drawCard(deck);

		player.setBet(1);
		opponent.setBet(1);
	}
	
	public void endMatch(){
		int playerSum = player.getSum();
		int opponentSum = opponent.getSum();
		
		//this.pause(1500);
		if(playerSum<=limit && (playerSum>opponentSum || opponentSum>limit))
		{
			//player wins
			System.out.println("Player Wins!");
			if(opponent.getBet()>0)
				opponent.setLife(opponent.getLife() - opponent.getBet());
			System.out.println(opponent.getLife());
		}
		else if(opponentSum<=limit && (opponentSum>playerSum || playerSum>limit))
		{
			System.out.println("Opponent Wins!");
			if(player.getBet()>0)
				player.setLife(player.getLife() - player.getBet());
			System.out.println(player.getLife());
		}
		else
		{
			//draw
			System.out.println("It's a... Draw!");
		}
		
		player.setWillStay(false);
		opponent.setWillStay(false);

		//this.pause(1500);
		if(opponent.getLife()<=0)
			this.newRound();
		else
			this.newMatch();
	}
	
	public void playerDrawsCard(){
		player.setWillStay(false);
		player.drawCard(deck);
	}

	public void opponentNextAction(){
		opponent.nextAction(this);
	}
	
	public boolean getPlayerIsNext(){
		return playerIsNext;
	}
	
	public void setPlayerIsNext(){
		playerIsNext = !(playerIsNext);
	}
	
	public boolean getUpdateAcesInPlay(){
		return updateAcesInPlay;
	}
	
	public void setUpdateAcesInPlay(){
		updateAcesInPlay = !(updateAcesInPlay);
	}
	
	public String getPlayerHand(){
		return player.handToString() + "/" + limit;
	}

	public String getOpponentHand(){
		return opponent.handToString() + "/" + limit;
	}
	
	public String revealOpponentHand(){
		return opponent.revealHand() + "/" + limit;
	}
	
	public ArrayList<Integer> getDeck(){
		return deck;
	}
	
	public int getLimit(){
		return limit;
	}
	
	public void setLimit(int newLimit){
		limit = newLimit;
	}
	
}
