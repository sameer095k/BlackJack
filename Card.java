
public class Card 
{
	int val = 0;
	String num = "";
	String suit = "";
	
	public Card(int v, String s)
	{
		val = v;
		suit = s;
		
		//Manually change the number value to corresponding card string
		if(val == 1)
			num = "Ace";
		else if(val == 2)
			num = "Two";
		else if(val == 3)
			num = "Three";
		else if(val == 4)
			num = "Four";
		else if(val == 5)
			num = "Five";
		else if(val == 6)
			num = "Six";
		else if(val == 7)
			num = "Seven";
		else if(val == 8)
			num = "Eight";
		else if(val == 9)
			num = "Nine";
		else if(val == 10)
			num = "Ten";
		else if(val == 11)
		{
			num = "Jack";
			val = 10;
		}
		else if(val == 12)
		{
			num = "Queen";
			val = 10;
		}
		else if(val == 13)
		{
			num = "King";
			val = 10;
		}
		if(val > 10)
			val = 10;
		
	}
	
	public String toString()
	{
		return (num + " of " + suit);
	}
	
	
}
