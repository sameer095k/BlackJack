//GUI for BlackJack Class

import javax.swing.*;		//used for the swing components
import java.text.*;			//used for the DecimalFormat class
import java.util.Arrays;
import java.awt.*;			//used for the Font class 
import java.awt.event.*;
import java.awt.image.*;
public class GUI extends JFrame implements ActionListener
{
	//Instantiating Object
	JPanel home = new JPanel();
	JPanel play = new JPanel();
	JLabel homeLabel = new JLabel ("Welcome to BlackJack Application");
	JButton playButton = new JButton("Play");
	JButton exitButton = new JButton("Exit");
	JButton stopButton = new JButton("Stop");
	JButton hitButton = new JButton("Hit");
	JButton replayButton = new JButton("Play Again");
	JButton back = new JButton("Back");
	private final int WIDTH_INTEGER = 245;
	private final int LENGTH_INTEGER = 245;
	BlackJack b; //= new BlackJack();
	//ImageIcon i = new ImageIcon("Ace of Clubs.png", null);
	ImageIcon start = new ImageIcon(new ImageIcon("BlackJackStart.jpg").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
	JLabel homeLabel1 = new JLabel();
	JLabel current = new JLabel("												Your Current Hand is:"
								+ " 											");
	JLabel dcurrent = new JLabel("\n												Dealer Current Hand is:"
			+ " 											");
	JLabel val = new JLabel();
	JLabel dealerVal = new JLabel();
	JLabel result = new JLabel();
	JLabel[] playerHand = new JLabel[7];
	JLabel[] dealerHand = new JLabel[7];
	String hand = new String();	//used for both Dealer and Player 
	String[] individual;	//slice strings and one unit holds one card only
	JPanel handPanel = new JPanel();
	JPanel dealerPanel = new JPanel();
	
	
	public GUI()
	{
		setHome();
		//result.setText("Your Current Hand is: \n"); //+ b.displayHand());
		add(home);
		b = new BlackJack();
		b.startGame();
		setLabels();
		//printCards(true);
		setVisible(true);
		setSize(WIDTH_INTEGER - 20,LENGTH_INTEGER);
		setListeners();

	}
	
	//set components on the home screen
	public void setHome()
	{
		home.add(homeLabel);
		homeLabel1.setIcon(start);
		home.add(homeLabel1);
		home.add(playButton);
		home.add(exitButton);
	}
	
	//set components for play panel
	public void setPlay()
	{
		play.add(current);
		setPlayerHand();
		play.add(handPanel);
		play.add(val);
		if(b.getPlayerHandValue() >= 21)
		{
			hitButton.setEnabled(false);
			stopButton.setEnabled(false);
		}
		if(!(hitButton.isEnabled()))
		{
			setDealerHand();
			printResults();
			play.add(dcurrent);
			play.add(dealerPanel);
			play.add(dealerVal);
			play.add(result);
		}
		play.add(hitButton);
		play.add(stopButton);
		play.add(replayButton);
		play.add(back);
	    add(play);
	   	repaint();
	   	revalidate();
		setSize(WIDTH_INTEGER + 200,LENGTH_INTEGER + 250);
	}
	
	public void setLabels()
	{
		for(int i = 0; i < playerHand.length; i++)
		{
			playerHand[i] = new JLabel();
		}
		
		for(int i = 0; i < dealerHand.length; i++)
		{
			dealerHand[i] = new JLabel();
		}
	}
	
	public void setPlayerHand()
	{
		hand = b.displayHand();
		individual = hand.split(", ");
		handPanel.removeAll();
		for(int i = 0; i < individual.length; i++)
		{
			playerHand[i].removeAll();
			individual[i] += ".png";
			playerHand[i].setIcon(new ImageIcon(new ImageIcon(individual[i]).getImage()
						 .getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
			handPanel.add(playerHand[i]);
		}
		val.setText("																					"
			+ "	Your Current Hand Value is:" + b.getPlayerHandValue()
			+ "																		 							");
		
	}
	
	public void setDealerHand()
	{
		hand = b.displayDealerHand();
		individual = hand.split(", ");
		dealerPanel.removeAll();
		for(int i = 0; i < individual.length; i++)
		{
			dealerHand[i].removeAll();
			individual[i] += ".png";
			dealerHand[i].setIcon(new ImageIcon(new ImageIcon(individual[i]).getImage()
						 .getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
			dealerPanel.add(dealerHand[i]);
		}
		dealerVal.setText("																					"
			+ "	Dealer Hand Value is:" + b.getDealerHandValue()
			+ "																		 							");
		
	}
	
	//add action listeners to buttons 
	public void setListeners()
	{
		playButton.addActionListener(this);
		exitButton.addActionListener(this);
		hitButton.addActionListener(this);
		stopButton.addActionListener(this);
		replayButton.addActionListener(this);
		back.addActionListener(this);
	}
	
	public static void main(String[] args) 
	{
		GUI myObject = new GUI();
		myObject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}


	public void actionPerformed(ActionEvent e) 
	{
  	    Object sourceObject = e.getSource();
  	    
  	    if(sourceObject == playButton)
  	    {
  	    	remove(home);
  	    	setPlay();
  	    }
  	    
  	    else if(sourceObject == exitButton)
  	    {
  	    	System.exit(0);
  	    }
  	    
  	    else if(sourceObject == hitButton)
  	    {
  	    	if(b.checkPlayer())
  	    	{
  	    		b.playerHit();
  	  		//result.setText("Your Current Hand is: \n"); //+ b.displayHand());
  	    	//	printCards(true);
  	    		setPlay();
  	    		repaint();
  	    		revalidate();
  	    		
  	    	}
  	    	
  	    	if(b.checkDealer())
  	    		b.dealerHit();
  	    	
  	    	if(b.getPlayerHandValue() >= 21)
  	    	{
  	    		hitButton.setEnabled(false);
  	    		stopButton.setEnabled(false);
  	  	    	while(b.checkDealer())
  	  	    		b.dealerHit();
  	    		setPlay();
  	    	}
  	    }
  	    
  	    else if(sourceObject == stopButton)
  	    {
  	    	while(b.checkDealer())
  	    	{
  	    		b.dealerHit();
  	    	}
  	    	stopButton.setEnabled(false);
  	    	hitButton.setEnabled(false);
  	    	setPlay();
  	    	
  	    }
  	    
  	    else if(sourceObject == replayButton)
  	    {
  	    	b.reset();
  	    	b.startGame();
  			play.removeAll();
  			handPanel.removeAll();
  	    	setPlay();
  	    	if(b.getPlayerHandValue() < 21)
  	    	{
  	    		play.remove(dealerVal);
  	    		play.remove(dealerPanel);
  	    		play.remove(dcurrent);
  	    		play.remove(result);
  	    		result.setText("");
  	    		dealerVal.setText("");
  	    		dealerPanel.removeAll();
  	    		repaint();
  	    		revalidate();
  	    	}
  	  		stopButton.setEnabled(true);
  	  		hitButton.setEnabled(true);
  	    }
  	    
  	    else if(sourceObject == back)
  	    {
  	    	remove(play);
  	    	add(home);
  	    	repaint();
  	    	revalidate();
  			setSize(WIDTH_INTEGER,LENGTH_INTEGER);
  	    }
		
	}
	
	
	public void printResults()
	{
		int player = b.getPlayerHandValue();
		int dealer = b.getDealerHandValue();
		result.setText("																					"
				+ b.decideWinner(player, dealer)
				+ "																							");
	}


}
