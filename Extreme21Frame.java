package Extreme21;

import Aces.*;
import Opponents.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.NumberFormatter;

import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.Timer;




public class Extreme21Frame {

	private JFrame frame = new JFrame();
	//private JComboBox comboBox = new JComboBox();
	private JComboBox<Ace> cb = new JComboBox<Ace>();
	private Timer timer;
    
	private final JLabel labelPlayerLife = new JLabel("Your Life:");
	private final JLabel labelOpponentLife = new JLabel("Opponent's Life:");
	private final JLabel labelPlayerBet = new JLabel("Your Bet:");
	private final JLabel labelOpponentBet = new JLabel("Opponent's Bet:");
	private final JLabel labelPlayerHand = new JLabel("");
	private final JLabel labelOpponentHand = new JLabel("");
	private final JButton btnDraw = new JButton("Draw");
	private final JButton btnStay = new JButton("Stay");
	private final JButton btnUseAce = new JButton("Use Ace");
	private final JButton btnAceInfo = new JButton("Ace Info");
	private final JButton button_0 = new JButton("1");
	private final JButton button_1 = new JButton("2");
	private final JButton button_2 = new JButton("3");
	private final JButton button_3 = new JButton("-1");
	private final JButton button_4 = new JButton("-2");
	private final JButton button_5 = new JButton("-3");
	private final JButton[] playerActiveAces = new JButton[6]; 
	private final JButton[] opponentActiveAces = new JButton[6]; 

	int count =1;
    

	private Extreme21Game game;
	private Player player;
	private Opponent opponent;
	
	public Extreme21Frame() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	public void initialize() {
		
		frame.setVisible(true);
		frame.setBounds(100, 100, 720, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		cb.setBounds(564, 263, 130, 20);
		frame.getContentPane().add(cb);
		
		labelPlayerLife.setBounds(21, 275, 120, 32);
		frame.getContentPane().add(labelPlayerLife);
		
		labelPlayerBet.setBounds(21, 299, 120, 32);
		frame.getContentPane().add(labelPlayerBet);
		
		labelOpponentLife.setBounds(21, 11, 120, 32);
		frame.getContentPane().add(labelOpponentLife);
		
		labelOpponentBet.setBounds(21, 40, 120, 32);
		frame.getContentPane().add(labelOpponentBet);
		
		labelPlayerHand.setBounds(176, 275, 319, 32);
		frame.getContentPane().add(labelPlayerHand);

		labelOpponentHand.setBounds(176, 40, 319, 32);
		frame.getContentPane().add(labelOpponentHand);
		
		btnDraw.setBounds(176, 331, 89, 23);
		frame.getContentPane().add(btnDraw);		
		btnDraw.setVisible(false);
		
		btnStay.setBounds(291, 331, 89, 23);
		frame.getContentPane().add(btnStay);
		btnStay.setVisible(false);
		
		btnUseAce.setBounds(406, 331, 89, 23);
		frame.getContentPane().add(btnUseAce);
		btnUseAce.setVisible(false);
		
		btnAceInfo.setBounds(564, 235, 130, 23);
		frame.getContentPane().add(btnAceInfo);
		
		button_0.setBounds(123, 235, 76, 23);
		frame.getContentPane().add(button_0);
		
		button_1.setBounds(209, 235, 76, 23);
		frame.getContentPane().add(button_1);
		
		button_2.setBounds(295, 235, 76, 23);
		frame.getContentPane().add(button_2);
		
		button_3.setBounds(123, 83, 76, 23);
		frame.getContentPane().add(button_3);
		
		button_4.setBounds(209, 83, 76, 23);
		frame.getContentPane().add(button_4);
		
		button_5.setBounds(291, 83, 80, 23);
		frame.getContentPane().add(button_5);
		
		button_0.setVisible(false);
		button_1.setVisible(false);
		button_2.setVisible(false);
		button_3.setVisible(false);
		button_4.setVisible(false);
		button_5.setVisible(false);

		playerActiveAces[0]=button_0;
		playerActiveAces[1]=button_1;
		playerActiveAces[2]=button_2;
		opponentActiveAces[0]=button_3;
		opponentActiveAces[1]=button_4;
		opponentActiveAces[2]=button_5;

		//cb.addItem(new Ace());
		
	    //b.setToolTipText( "Click here to accept the option you have selected." ); something for later
		
		btnDraw.addActionListener(new ActionListener(){
	           @Override
	           public void actionPerformed(ActionEvent evt){
	        	   game.playerDrawsCard();
	        	   btnDraw.setVisible(false);
	        	   btnStay.setVisible(false);
	        	   btnUseAce.setVisible(false);
	        	   game.setPlayerIsNext();
	        	   run();
	           }
	    }); 
		
		btnStay.addActionListener(new ActionListener(){
	           @Override
	           public void actionPerformed(ActionEvent evt){
	        	   player.setWillStay(true);
	        	   btnDraw.setVisible(false);
	        	   btnStay.setVisible(false);
	        	   btnUseAce.setVisible(false);
	        	   game.setPlayerIsNext();
	        	   run();
	           }
	    }); 
		

		
		btnUseAce.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent evt){
    		   int i = cb.getSelectedIndex();
        	   if(i>=0){
        		   System.out.println("Item #" + (i+1) + " will do this: " + cb.getItemAt(i).getDescription());
        		   player.useAce(i, game);
        		   cb.removeItemAt(cb.getSelectedIndex());
        	   }
        	   opponent.setWillStay(false);
        	   update();
           }
    }); 
		
		btnAceInfo.addActionListener(new ActionListener(){
	           @Override
	           public void actionPerformed(ActionEvent evt){
	               //then you know that is attached to this button
        		   int i = cb.getSelectedIndex();
        		   //player
        		   
	        	   if(i>=0){
	        		   System.out.println("Item #" + (i+1) + " is " + cb.getItemAt(i).getDescription());
	        	   }
	           }
	    }); 
	    
		game = new Extreme21Game();
		game.newRound();
		player=game.getPlayer();
		opponent= game.getOpponent();
		run();
		
		//to hide a button, do .setVisible(false);
	}
	
	private void run(){
		//update();
		if(player.getWillStay() && opponent.getWillStay()){

			
			System.out.println(game.revealOpponentHand());
			
			delay();

		}
		else if(game.getPlayerIsNext())
			playerGoes();
		else
			opponentGoes();
	}
	
	private void playerGoes(){
 	    btnDraw.setVisible(true);
 	    btnStay.setVisible(true);
 	    btnUseAce.setVisible(true);
		update();
		
	}
	
	private void opponentGoes(){
		update(); 
		opponent.nextAction(game);
		update(); 
		//game.pause(5000);
		game.setPlayerIsNext();
		run();
	}
	
	private void update(){
		if(game.getUpdateAcesInPlay()){
			int i=0;
			ArrayList<Ace> playerAcesInPlay = player.getAcesInPlay();
			for(i=0;i<playerAcesInPlay.size();i++){
				playerActiveAces[i].setVisible(true);
				playerActiveAces[i].setText(playerAcesInPlay.get(i).getName());
				playerActiveAces[i].setToolTipText(playerAcesInPlay.get(i).getDescription());
			}
			for(int x=i;x<3;x++){
				playerActiveAces[x].setVisible(false);
			}
			
			ArrayList<Ace> opponentAcesInPlay = opponent.getAcesInPlay();
			for(i=0;i<opponentAcesInPlay.size();i++){
				opponentActiveAces[i].setVisible(true);
				opponentActiveAces[i].setText(opponentAcesInPlay.get(i).getName());
				opponentActiveAces[i].setToolTipText(opponentAcesInPlay.get(i).getDescription());
			}
			for(int x=i;x<3;x++){
				opponentActiveAces[x].setVisible(false);
			}
			
			
		    game.setUpdateAcesInPlay(false);//Flag as false so this if statement wont be called again if no updates occur
		}
		
		
		labelPlayerHand.setText(game.getPlayerHand());
		labelOpponentHand.setText(game.getOpponentHand());
		labelPlayerBet.setText("Your Bet: " + player.getBet());
		labelPlayerLife.setText("Your Life: " + player.getLife());
		labelOpponentBet.setText("Opponent's Bet: " + opponent.getBet());
		labelOpponentLife.setText("Opponent's Life: " + opponent.getLife());

		
		
		cb.removeAllItems();
		for(int i=0;i<player.getAces().size();i++)
			{cb.addItem(player.getAces().get(i));}
	}
	
	private void delay(){
		timer = new Timer(3000, new ActionListener(){
			  @Override
			  public void actionPerformed( ActionEvent e ){
					labelOpponentHand.setText(game.revealOpponentHand());
			  }
			} );
			timer.setRepeats( false );
			timer.start();		
		timer = new Timer(6000, new ActionListener(){
			  @Override
			  public void actionPerformed( ActionEvent e ){
					game.endMatch();
					update();
					run();
			  }
			} );
			timer.setRepeats( false );
			timer.start();
	}
}
