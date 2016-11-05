
/*
 * This class represent a hand of cards for each of the players.
 * There are several methods provided to manage and get data regarding the cards in the hand.
 * 
 */

import java.util.ArrayList;

public class Hand {
	
	private int handValue = 0; // it holds the total value of all the cards in a hand. initially 0
	
	private ArrayList<Card> handCards; // it holds the actual cards in a hand
	private boolean hasAce = false;
	public Hand()
	{
		handCards = new ArrayList<Card>();
	}
	
	public void addCard(Card card)
	{
		handCards.add(card); // adding a card in the hand.
	}
	
	public Card getCard(int pos)
	{
		return (Card)handCards.get(pos); // getting a card from the hand from the specified position.
	}
	
	public int numberOfCards()
	{
		return handCards.size(); // it gives the total number of cards in a hand.
	}
	
	public int getHandValue()
	{
		handValue = 0;
		for(int i=0;i<handCards.size();i++)
		{
			Card card = handCards.get(i);
			int cardValue = card.getValue();
				if(cardValue>10) // if the card value is greater than 10 means the face cards, their value is counted as 10.
					cardValue = 10;
				if(cardValue == 1)
					hasAce = true;
			handValue += cardValue; // adding the card value to the total hand value.
		}
		
		if(hasAce && handValue + 10 <=21) // detecting the Ace and using it's value as 1 or 11 accordingly
			handValue += 10;				
		
		return handValue;
	}

}
