// Name: B6-24
// Date: 11/23/19
import java.io.*;
import java.util.*;

public class CardDriver
{
   public static void main(String[] args)
   {
//Make and print new QD   
//       Card c = new Card(1, 12);
//       System.out.println(c);

//Make and print new KC
//       Card c1 = new Card(Card.CLUBS, Card.KING);
//       System.out.println(c1);
      
//Build and print new deck      
//       CardDeck cd = new CardDeck();
//       cd.printDeck(); 	
   
// Test getTopCard   
//       System.out.println(cd.getTopCard());
//       System.out.println();
//       cd.printDeck();
      
// Shuffle and print new deck      
//       CardDeck cd = new CardDeck();
//       cd.shuffle(5);
//       cd.printDeck();
      
      War war = new War();
      war.playGame();

//       Count Shuffled Deck
      
//       int count = 0;
//       ListNode pointer = war.getComputerHand().getHand();
//       while (pointer != null) {
//          count++;
//          pointer = pointer.getNext();
//       }
//       System.out.println("Count is: " + count);
   }
}

class CardDeck 
{
   private ListNode myCards;
   
   public CardDeck() 
   {
      for (int suit = Card.CLUBS; suit <= Card.SPADES; suit++)
         for (int rank = Card.ACE; rank <= Card.KING; rank++) 
         {    
            Card card = new Card(suit, rank);
            putAtEnd(card);
         }
   } 
    
   /*  returns the top card from the deck.
       reassigns a pointer to the new top card.
    */
   public Card getTopCard() 
   {
      if (myCards == null)
         return null;
         
      Card top = (Card)myCards.getValue();
      myCards = myCards.getNext();
      return top;
   }
   
   public Card getTopCard(ListNode head) 
   {
      if (head == null)
         return null;
         
      Card top = (Card)head.getValue();
      head = head.getNext();
      return top;
   }


   
   /*  helper method to put a card at the end of the deck.
    */
   private ListNode putAtEnd(ListNode head, Card c)
   {
      if (head == null) {
         head = new ListNode(c, null);
         return null;
      }
      
      ListNode pointer = head;
      while (pointer.getNext() != null)
         pointer = pointer.getNext();
      
      pointer.setNext(new ListNode(c, null));
      return head;
   }
   
   private void putAtEnd(Card c)
   {
      if (myCards == null) {
         myCards = new ListNode(c, null);
         return;
      }
      
      ListNode pointer = myCards;
      while (pointer.getNext() != null)
         pointer = pointer.getNext();
      
      pointer.setNext(new ListNode(c, null));
   }

   
   public void printDeck()
   {
      ListNode pointer = myCards;
      while (pointer != null) {
         System.out.print(pointer.getValue());
         if (pointer.getNext() != null)
            System.out.print(", ");
         pointer = pointer.getNext();
     }
     System.out.println();
      
   }
   	
	/*  splits this deck into two almost equal halves;
		 chooses the split point randomly at 26 +- 10; 
	    the first half remains in this deck in the same order;
		 the second half is placed into a separate linked list in the same order;
		 @return a reference to the list that refers to the second half of the deck
	 */
   private ListNode split()
   {
      int splitPoint = ((int)(Math.random() * (20)) + 16);
      ListNode pointer = myCards;
      for (int i = 1; i < splitPoint; i++)
         pointer = pointer.getNext();
      
      ListNode lower = pointer.getNext();
      pointer.setNext(null);
      
      return lower;
   }
      
   /*  combines the cards from cards1 and cards2 into one list.
		 takes one card from cards1, the next from cards2, the third
		 from cards1, and so on, alternating decks;  if there are cards
		 left over, all the rest of those cards are copied into the combined list.
		 @return a reference to the first node of the combined list.
	 */
   private ListNode combine(ListNode cards1, ListNode cards2)
   {
      ListNode head = new ListNode(null, null);
      while (cards1 != null && cards2 != null) {
         head = putAtEnd(head, getTopCard(cards1));
         cards1 = cards1.getNext();
         head = putAtEnd(head, getTopCard(cards2));
         cards2 = cards2.getNext();
      }
      
      ListNode pointer = head;
      while (pointer.getNext() != null)
         pointer = pointer.getNext();
      if (cards1 != null)
         pointer.setNext(cards1);
      if (cards2 != null)
         pointer.setNext(cards2);
      
      return head.getNext();
   }
      
      
   /*  splits the deck, then combines the 2 halves;
	    this operation is repeated numTimes number of times.
	 */
   public void shuffle(int numTimes)
   {
      for (int i = 0; i < numTimes; i++) {
         ListNode split = split();
         myCards = combine(myCards, split);
      }
   }
   
   public ListNode getCards() {
      return myCards;
   }
}

/*
using ideas from	http://www.ccs.neu.edu/jpt/fhs-04-05/Cards/CardSampler/
*/
class Card implements Comparable<Card>
{

   public static final int CLUBS  = 0;     //for playing Bridge 
   public static final int DIAMONDS = 1;
   public static final int HEARTS = 2;
   public static final int SPADES = 3;
    
   public static final int ACE = 1;		   //Aces are always low
   public static final int JACK = 11;
   public static final int QUEEN = 12;
   public static final int KING = 13;
      
   private int rank;
   private int suit;
      
   private String[] rankList = {"","Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
   private String[] suitList = {"Clubs","Diamonds","Hearts","Spades"};
   private String[] abbRankList = {"", "A","2","3","4","5","6","7","8","9","10","J","Q","K"};
   private String[] abbSuitList = {"C", "D", "H", "S"};
   
   public Card(int s, int r) {
      if ((ACE <= r) && (r <= KING))
         this.rank = r;
      else
         throw new RuntimeException
                ("Invalid rank in Card constructor: " + rank);
        
      if ((CLUBS <= s) && (s <= SPADES))
         this.suit = s;
      else
         throw new RuntimeException
                ("Invalid suit in Card constructor: " + suit);
   }
   
   public int getRank() 
   { 
      return rank; 
   }
    
   public int getSuit() 
   { 
      return suit; 
   }
    
   public String getRankAsString() 
   { 
      return rankList[rank];
   }
    
   public String getSuitAsString() 
   { 
      return suitList[suit];
   }
   
   public boolean equals(Card other) {
      if (rank == other.getRank())
         return true;
      else
         return false;
   }
   
   public int compareTo(Card other) {
      return rank - other.getRank();
   }
         
   public String toString()
   {
      return ( getRankAsString()+" of "+getSuitAsString() );
      //return ( getRankAsString()+getSuitAsString() );
   }
}

class CardHand{
   private ListNode hand;
   
   public CardHand (ListNode h) {
      hand = h;
   }
   
   public CardHand (CardHand c) {
      hand = c.getHand();
   }
   
   public ListNode getHand() {
      return hand;
   }
   
   public void setHand(ListNode h) {
      hand = h;
   }
   
   public Card getTopCard() 
   {
      if (hand == null)
         return null;
         
      Card top = (Card)hand.getValue();
      hand = hand.getNext();
      return top;
   }
   
   public void putAtEnd(Card c)
   {
      if (hand == null) {
         hand = new ListNode(c, null);
         return;
      }
      
      ListNode pointer = hand;
      while (pointer.getNext() != null)
         pointer = pointer.getNext();
      
      pointer.setNext(new ListNode(c, null));
   }


   public void printHand()
   {
      ListNode pointer = hand;
      while (pointer != null) {
         System.out.print(pointer.getValue());
         if (pointer.getNext() != null)
            System.out.print(", ");
         pointer = pointer.getNext();
     }
     System.out.println();
      
   }
}

class War {

   private Scanner in = new Scanner(System.in);
   private CardHand playerHand, computerHand;
   private int turn = 0, playerCount = 26, computerCount = 26;   
   
   public War() {
      CardDeck deck = new CardDeck();
      //deck.shuffle(10);
      
      computerHand = splitInHalf(deck.getCards());
      playerHand = new CardHand(deck.getCards());
//       computerHand.printHand();
//       playerHand.printHand();
   }
   
   private CardHand splitInHalf(ListNode deck)
   {
     
      ListNode pointer = deck;
      for (int i = 1; i < 26; i++)
         pointer = pointer.getNext();
   
      ListNode lower = pointer.getNext();
      pointer.setNext(null);
      playerHand = new CardHand(deck);
      
      return new CardHand(lower);
   }

   
   public CardHand getPlayerHand() {
      return playerHand;
   }
   
   public CardHand getComputerHand() {
      return computerHand;
   }
   
   public void setPlayerHand(CardHand p) {
      playerHand = p;
   }
   
   public void setComputerHand(CardHand c) {
      computerHand = c;
   }
   
   
   public void playGame() {
      while (playerHand.getHand() != null && computerHand.getHand() != null) {
         if (turn  == 0)
            System.out.println("Welcome to war!");
         turn++;
         Card playerCard = playerHand.getTopCard(), computerCard = computerHand.getTopCard();
         playTurn(playerCard, computerCard);
      
      }
   }
   
   private String countCards() {
      playerCount = 1; computerCount = 1;
      ListNode pointer;
      
      if (playerHand.getHand() != null) {
         pointer = playerHand.getHand();
         while (pointer != null) {
            pointer = pointer.getNext();
            playerCount++;
         }
      }
      
      if (computerHand.getHand() != null) {
         pointer = computerHand.getHand();
         while (pointer != null) {
            computerCount++;
            pointer = pointer.getNext();
         }
      }
      
      String playerPlural = "s", computerPlural = "s";
      if (playerCount < 2)
         playerPlural = "";
         
      if (computerCount < 2)
         computerPlural = "";   
      return "You have " + playerCount + " card" + playerPlural + " \t\t The computer has " + computerCount + " card" + computerPlural;
   }

   
   private void playTurn(Card playerCard, Card computerCard) {
      System.out.println(countCards());
      System.out.println("Press enter to battle");
      in.nextLine();
      for (int i = 0; i < 15; i++) System.out.println();
      
      System.out.println("You drew a " + playerCard + " \t\t The computer drew a " + computerCard);
      if (playerCard.compareTo(computerCard) == 0)
         tieRound(playerCard, computerCard);
      else if (playerCard.compareTo(computerCard) > 0)
         winRound(true, playerCard, computerCard);
      else
         winRound(false, playerCard, computerCard);
      
   }
   
   private void winRound(boolean isPlayerWinner, Card playerCard, Card computerCard) {
      if (isPlayerWinner) {
         System.out.println("Outstanding victory!");
         computerHand.putAtEnd(playerCard);
         computerHand.putAtEnd(computerCard);
      } else {
         System.out.println("Damn, horrible loss.");
         playerHand.putAtEnd(computerCard);
         playerHand.putAtEnd(playerCard);
      }
      
      System.out.println();
   }

   private void tieRound(Card playerCard, Card computerCard) {
      System.out.println("\nThe battle was too close to call. It's time for war.\nPress enter to discard a card");
      in.nextLine();
      Card playerDiscard = playerHand.getTopCard(), computerDiscard = computerHand.getTopCard();
      for (int i = 0; i < 15; i++) System.out.println();
      System.out.println("And press it again to draw!");
      in.nextLine();
      Card playerDraw = playerHand.getTopCard(), computerDraw =  computerHand.getTopCard();
      
      if (playerCard.compareTo(computerCard) == 0) {

      } else if (playerCard.compareTo(computerCard) > 0) {

      } else {

      }
   }
}