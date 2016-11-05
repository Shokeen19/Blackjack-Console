
/*
 * This is the main class which implements the logic of the game and contains main method
 * The basic logic of the game is as follows:
 * player and dealer are provided with 2 cards initially with player's cards are faced up and only first card of dealer in faced up.
 * if the player or the dealer has a total value of cards as 21 it is a blackjack and game ends here.
 * the player who has the blackjack wins.
 * if there is no blackjack then the user will have the choice of hit and stand. 
 * if user hits then a card is taken out from deck and added to user hand and getting the total value of user hand
 * if the user hand value is greater than 21, user is busted and dealer wins.
 * if the total value is 21 then the user has blackjack and he wins.
 * if the value is less than 21 then user will have the choice of hit and stand again
 * it continues till the user in not busted.
 * if user choose to stand at any time then the dealer will show up his second card 
 * if the total of dealer's cards is less than 17, dealer has to take a card from the deck.
 * the same rules applies to the dealer now. 
 */

import java.util.Scanner;

public class BlackJack {
	
	Deck deck = new Deck(3); // three decks are used in this game
	Hand playerHand = new Hand();
	Hand dealerHand = new Hand();
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		System.out.println("Welcome to BlackJack Game");
		
		int cash = 1000; // initial cash provided to player 
		int bet;
		while(true)
		{
			System.out.println();
			System.out.println("Total ammount in wallet : "+cash);
			do // ask the player to bet an amount
			{
				System.out.print("Enter the amount in the range 1-"+cash+" to bet, 0 to exit the game : ");
				bet = sc.nextInt();
				if(bet < 0 || bet > cash)
				{
					System.out.println("Invalid Amount, Please enter again");
				}
			}while(bet < 0 || bet > cash);
			if(bet==0) // if bet amount is 0 terminate the game
				break;
			BlackJack bj = new BlackJack();
			String result = bj.playGame();
			if(result.equals("Win"))
			{
				cash += bet; //if user wins then add the bet amount to the user wallet
			}
			else if(result.equals("Lose"))
			{
				cash -= bet; // if user loses then subtract the bet amount from user wallet
			}
			if(cash == 0) // terminate the game if the user runs out of cash
			{
				System.out.println("Your wallet is empty. Game Over.");
				System.out.println("Play Again.");
				break;
			}
				
		}
	}
	
	public String playGame()
	{
		deck.shuffleDeck();
		
		//deck.printDeck();
		
		playerHand.addCard(deck.takeCard()); // initially adding 2 cards for each player
		playerHand.addCard(deck.takeCard());
		
		dealerHand.addCard(deck.takeCard());
		dealerHand.addCard(deck.takeCard());
		
		System.out.println();
		printInitialCards(); // showing the cards on console
		printPartialScore(); // along with the score
		System.out.println();
		
		if(dealerHand.getHandValue() == 21) // dealer's blackjack detected, dealer wins
		{
			printCards();
			printScore();
			System.out.println("Dealer have BlackJack --- You Lose.");
			return "Lose";
		}
		if(playerHand.getHandValue() == 21) // player's blackjack detected, player wins
		{
			printCards();
			printScore();
			System.out.println("You have BlackJack --- You Win.");
			return "Win";
		}
		
		while(true) // repeatedly ask the user to hit or stand
		{
			
			System.out.print("Enter H for Hit or S for Stand : ");
			
			{
				String in = sc.next();
				if(in.toUpperCase().equals("S")) // if stand terminate the loop and allow dealer to play
				{
					break;
				}
				else // other wise take card from deck and add to user's hand
				{
					playerHand.addCard(deck.takeCard());
					if(playerHand.getHandValue() > 21) // if user's total is over 21, user busted and dealer wins
					{
						printCards();
						printScore();
						System.out.println("Your score is greater than 21 -- You are Busted --- You Lose.");
						return "Lose";
					}
					else if(playerHand.getHandValue() == 21) // if user has blackjack, user wins
					{
						printCards();
						printScore();
						System.out.println();
		                System.out.println("You have BlackJack --- You Win.");
						return "Win";
					}
				}
			}
			printInitialCards();
			printPartialScore();
		}
		
		while(dealerHand.getHandValue()<=16) // take out cards from deck until total becomes 17 or more
		{
			
			dealerHand.addCard(deck.takeCard());
			if(dealerHand.getHandValue() > 21) // if dealer's total is over 21, dealer busted, user wins
			{
				printCards();
				printScore();
                System.out.println("Dealer's score is greater than 21 -- Dealer is Busted --- You Win.");
				return "Win";
			}
		}
		if(dealerHand.getHandValue()==playerHand.getHandValue()) // if user's total and dealer's total are equal, game ties
		{
			printCards();
			printScore();
            System.out.println("Scores are equal --- Game Tied.");
            return "Tied";
		}
		else if(dealerHand.getHandValue()>playerHand.getHandValue()) // if dealer's total is greater than user's total, dealer wins
		{
			printCards();
			printScore();
            System.out.println("Dealer Win this Hand.");
            return "Lose";
		}
		else
		{
			printCards();
			printScore();
            System.out.println("You Win this hand.");
            return "Win";
		}
	}
	
	public void printCards() // method to print the cards on console for dealer and player
	{
		
		System.out.println();
		System.out.print("Dealer's cards : ");
		for(int i=0;i<dealerHand.numberOfCards();i++)
		{
			System.out.print(dealerHand.getCard(i).getFace()+" of "+dealerHand.getCard(i).getSuit()+", ");
		}
		
		System.out.println();
		System.out.print("Player's cards : ");
		for(int i=0;i<playerHand.numberOfCards();i++)
		{
			System.out.print(playerHand.getCard(i).getFace()+" of "+playerHand.getCard(i).getSuit()+", ");
		}
		System.out.println();
	}
	
	public void printScore() // method to print the score of each player
	{
		System.out.println("Dealer's final Score : "+dealerHand.getHandValue());
		System.out.println("Player's final score : "+playerHand.getHandValue());
	}
	
	public void printPartialScore() // method to print the score of dealer's one card until player stands
	{
		int dealerScore = dealerHand.getCard(0).getValue();
		if(dealerScore > 10)
			System.out.println("Dealer's Score for now : "+10);
		else
			System.out.println("Dealer's Score for now : "+dealerScore);
		System.out.println("Player's Score for now : "+playerHand.getHandValue());
		System.out.println();
	}
	public void printInitialCards() // method to show only the first card for dealer
	{
		
		System.out.println("Dealer's Face up Card : "+dealerHand.getCard(0).getFace()+" of "+dealerHand.getCard(0).getSuit()+", ");
		
		System.out.print("Player's cards : ");
		for(int i=0;i<playerHand.numberOfCards();i++)
		{
			System.out.print(playerHand.getCard(i).getFace()+" of "+playerHand.getCard(i).getSuit()+", ");
		}
		System.out.println();
	}
}
