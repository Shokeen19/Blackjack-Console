
/*
 * This class represent a card, card have two attributes one is value and one is suit.
 * The card value can be from 1 - 13, where 1, 11,12 and 13 are face cards and named as Ace, Jack, Queen and King respectively.
 * There are a number of methods provided to access the value and suit of the card.
 */

public class Card {
		
	private final static String[] suits= {"Heart", "Club", "Diamond", "Spade"}; // there are four suits in a deck
	private final static String[] faces = {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"}; // there are 13 cards in each suit.	
	private int value, suit;
	
	public Card( int value, int suit)
	{
		this.value = value;
		this.suit = suit;
	}
	
	public int getValue()
	{
		return value; // getting the value of card in integer from 1-13.
	}
	
	public String getSuit()
	{
		return suits[suit]; // getting the suit of the card.
	}
	
	public String getFace()
	{
		return faces[value-1]; // getting the face of card according to the value.
	}
}
