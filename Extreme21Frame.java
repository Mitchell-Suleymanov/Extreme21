package Extreme21;

import Aces.*;
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

	int count =1;
    

	private Extreme21Game game;
	
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
	        	   game.getPlayer().setWillStay(true);
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
        		   game.getPlayer().useAce(i, game);
        		   cb.removeItemAt(cb.getSelectedIndex());
        	   }
        	   update();
           }
    }); 
		
		btnAceInfo.addActionListener(new ActionListener(){
	           @Override
	           public void actionPerformed(ActionEvent evt){
	                //then you know that is attached to this button
        		   int i = cb.getSelectedIndex();
        		   //game.getPlayer()
        		   
	        	   if(i>=0){
	        		   System.out.println("Item #" + (i+1) + " is " + cb.getItemAt(i).getName());
	        	   }
	           }
	    }); 
	    
		game = new Extreme21Game();
		game.newRound();
		run();
		
		//to hide a button, do .setVisible(false);
	}
	
	private void run(){
		//update();
		if(game.getPlayer().getWillStay() && game.getOpponent().getWillStay()){

			
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
		game.getOpponent().nextAction(game);
		update(); 
		//game.pause(5000);
		game.setPlayerIsNext();
		run();
	}
	
	private void update(){
		labelPlayerHand.setText(game.getPlayerHand());
		labelOpponentHand.setText(game.getOpponentHand());
		labelPlayerBet.setText("Your Bet: " + game.getPlayer().getBet());
		labelPlayerLife.setText("Your Life: " + game.getPlayer().getLife());
		labelOpponentBet.setText("Opponent's Bet: " + game.getOpponent().getBet());
		labelOpponentLife.setText("Opponent's Life: " + game.getOpponent().getLife());

		cb.removeAllItems();
		for(int i=0;i<game.getPlayer().getAces().size();i++)
			{cb.addItem(game.getPlayer().getAces().get(i));}
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
					run();
			  }
			} );
			timer.setRepeats( false );
			timer.start();

	}
}
