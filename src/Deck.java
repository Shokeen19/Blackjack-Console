/*
 * This class represent a deck which has 52 cards in total.
 * Here in this application it was asked to take 3 decks to play the game. I have implemented this deck so that any number of decks can be used to play.
 * There are several methods which are used to initialize the deck, shuffle the deck and take out the cards from deck.
 */

public class Deck {
	
	private Card[] deck;
	private int cardsUsed; // holds the index of used cards which can not be used again.
	private int numberofDecks;
	
	public Deck(int numofDecks) // defining the deck with number of decks required to play
	{
		this.numberofDecks = numofDecks;
		deck = new Card[52*numofDecks];
		int k=0;
		for(int n=0;n<numofDecks;n++) // initializing each deck
		{
			for(int i=0;i<4;i++) // each deck has 4 suits
			{
				for(int j=1;j<=13;j++) // each suit has 52 cards
				{
					deck[k]=new Card(j,i);
						
					k++;
				}
			}
		}
		cardsUsed = 0; // initially no card is used
	}
	
	public void shuffleDeck() // method to shuffle the deck
	{
		for(int i=52*numberofDecks-1;i>0;i--)
		{
			int random = (int)(Math.random()*(i+1));
			Card temp = deck[i];
			deck[i] = deck[random];
			deck[random] = temp;
		}
		cardsUsed = 0;
	}
	
	public Card takeCard()
	{
		cardsUsed++; // every time a card is taken out of deck the index of deck in increased.
		return deck[cardsUsed-1]; 
	}
	
	public void printDeck() // method to test if the deck has correct values
	{
		for(int i=0;i<deck.length;i++)
		{
			System.out.print(deck[i].getFace()+" of "+deck[i].getSuit()+", ");
		}
	}
	
}
