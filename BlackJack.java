import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

//This is the class for BlackJack, it will hand out two cards to begin with
//Then if the user wants it will let them take another card if they are able to
//Then it will print out the winner
public class BlackJack {
	private Card[] cards = new Card[52];	//52 Unique Card Objects
	private int frontCard = 0; //top of the deck
	private Vector<Card> playerHand = new Vector<Card>(2,2);
	private Vector<Card> dealerHand = new Vector<Card>(2,2);
	Random rand = new Random();
	Scanner scan = new Scanner(System.in);
	
	//Constructor simply puts the cards into place and then shuffles them around
	public BlackJack()
	{
		cards = setDeck(cards);
		shuffle();
	}
	
	
	//This method will set the deck in order to start with
	private Card[] setDeck(Card[] x)
	{
		int cardCounter = 0;	//position of array
		String suitCard = "";
		//this loop will set the deck to 4 sets of 1-13
		for(int suit = 0; suit <= 3; suit++)
		{
			if(suit==0)
				suitCard = "Spades";
			else if (suit == 1)
				suitCard = "Clubs";
			else if (suit == 2)
				suitCard = "Diamonds";
			else if (suit == 3)
				suitCard = "Hearts";
			for(int val = 1; val<=13; val++)
			{
				x[cardCounter] = new Card(val, suitCard);
				cardCounter++;
			}
		}
		return x;
	}

	
	//This method will shuffle the cards randomly 
	private void shuffle()
	{
		for(int counter = 0; counter<52; counter++)
		{
			int randomInt = rand.nextInt(52);
			//performing swap
			Card swap = cards[randomInt];
			cards[randomInt] = cards[counter];
			cards[counter] = swap;			
		}
	}
	
	//This method will start the game off by dealing the first 4 cards out
	public void startGame()
	{
		playerHand.addElement(cards[frontCard]);
		frontCard++;
		playerHand.addElement(cards[frontCard]);
		frontCard++;
		
		dealerHand.addElement(cards[frontCard]);
		frontCard++;
		dealerHand.addElement(cards[frontCard]);
		frontCard++;
	}
	

	//This method will calculate the value of players hand
	public int getPlayerHandValue()
	{
		int handSize = playerHand.size();
		int sum = 0;
		for(int i = 0; i < handSize; i++)
			sum += (playerHand.elementAt(i).val);
		if(aceFound(playerHand) && sum + 10 <= 21)
			sum+=10;
		return sum;
	}
	
	//This method will calculate the value of dealer's hand
	public int getDealerHandValue()
	{
		int handSize = dealerHand.size();
		int sum = 0;
		for(int i = 0; i < handSize; i++)
			sum += (dealerHand.elementAt(i).val);
		if(aceFound(dealerHand) && sum + 10 <= 21)
			sum+=10;
		
		return sum;
	}
	
	//Check if dealer should take another card, only if under 18 
	public boolean checkDealer()
	{
		if(getDealerHandValue() >= 18 )
			return false;
		else 
			return true;
	}
	
	//Check if player wants to take hit, decide by input a char
	public boolean checkPlayer()
	{
		if(getPlayerHandValue()>=21)
			return false;
		return true;
	/*	System.out.print("Please enter y if you want to take a hit, n if you want to stay: ");
		char z = scan.next().charAt(0);
		return z == 'y' ? true: false;*/
		
	}
	
	//adds a random card to the dealers hand
	public void dealerHit()
	{
		dealerHand.addElement(cards[frontCard]);	
		frontCard++;
	}
	
	//adds a random card to the player's hand
	public void playerHit()
	{
		playerHand.addElement(cards[frontCard]);
		frontCard++;
	}
	
	//Check if an ace is found
	private boolean aceFound(Vector<Card> dealerHand2)
	{
		boolean ace = false;
		int size = dealerHand2.size();
		for(int i = 0; i<size; i++)
		{
			if(dealerHand2.elementAt(i).num == "Ace")
				ace = true;
		}
		return ace;	
	}
	
	
	//This method will display your current hand 
	public String displayHand()
	{
		int handSize = playerHand.size();
		String currentHand = "";
		System.out.println();
		for(int i = 0; i < handSize; i++)
		{
			String x = playerHand.elementAt(i).toString();
			if(i==handSize-1)
				currentHand += (x + "");
			else
				currentHand += (x + ", ");
			//currentHand += (x + "\n");
		}
		return currentHand;
	}
	
	//This method will display Dealer's current hand 
	public String displayDealerHand()
	{
		int handSize = dealerHand.size();
		String currentHand = "";
		System.out.println();
		for(int i = 0; i < handSize; i++)
		{
			String x = dealerHand.elementAt(i).toString();
			if(i==handSize-1)
				currentHand += ("" + x);
			else
				currentHand += (x + ", ");
			//currentHand += (x + "\n");
		}
		return currentHand;
	}
	
	//Check who the winner is
	public String decideWinner(int x, int y)
	{
		String result = null;
		if(y > 21 && x <= 21 )
			result = ("\nCongratulations! You Won!, dealer went over 21");
		else if(x > 21 && y <= 21)
			result =("\nSorry, the Dealer Won, you went over 21");
		else if(y<=21 && y > x)
			result =("\nSorry, the Dealer Won");
		else if(x<= 21 && x > y)
			result =("\nCongratulations! You Won!");
		else if(y == x)
			result =("\nGame Was a Tie");
		else if(y>21 && x>21)
			result = ("\nBoth went over 21, its a Tie");
		return result;
	}
	
	public void reset()
	{
		playerHand.clear();
		dealerHand.clear();
		frontCard = 0;
		shuffle();
	}
	
	//This method will print out the Deck 13 in a line
	/*private void displayDeck()
	{
		for(int i = 0; i < 52; i++)
		{
			if(i%13 == 0)
				System.out.println();
			System.out.print(cards[i] + ", ");
		}
	}*/
}
