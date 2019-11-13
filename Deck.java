import java.util.*;
/**
 * A deck in a game of Blackjack.
 * 
 * @Talia Wang
 * @2018-10-29
 */
public class Deck
{
    // constants
    private static final int MAXCARDS = 52;
    private static final int TIMES_SWAPPED = 20; 
    
    // global variables
    private static String suit[];
    private int cards[];
    private int cardsLeft;
    private int numC;

    /**
     * Constructor for objects of class Deck.
     */
    public Deck()
    {
        numC = MAXCARDS;
        cards = new int[numC];
        for (int i = 0; i < numC; i++)
        {
            cards[i] = i;
        }
        cardsLeft = numC;
        
        suit = new String[4];
        suit[0] = "♥";
        suit[1] = "◆";
        suit[2] = "♣";
        suit [3] = "♠";
    }
    
    /**
     * Prints out the current deck.  
     */
    public void printDeck()
    {
        if (cardsLeft < 5) 
        {
            for (int i = 0; i < numC; i++)
            {
            cards[i] = i;
            }
            cardsLeft = numC;
            shuffle();
        }
        
        for (int i = 0; i < cardsLeft; i++)
        {
            System.out.print(intToCard(cards[i]) + " ");
        }
        System.out.println("");
    }
    
    /**
     * Returns the card dealt. 
     * Calculates the number of cards left.
     */
    public int dealACard()
    {
        return cards[--cardsLeft];
    }
    
    /**
     * Shuffles the deck.
     */
    public void shuffle()
    {
        // number of times two cards are swapped (10 to TIMES_SWAPPED + 10).
        int timesSwapped = (int)(Math.random()*TIMES_SWAPPED + 10); // ensures cards swap at least 10 times
        int currentSwap = 0;
        
        // swap two random cards a random number of times. 
        while (currentSwap < timesSwapped)
        {
            int index1 = (int)(Math.random()*52);
            int temp = cards[index1];
            
            int index2 = (int)(Math.random()*52);
            cards[index1] = cards[index2];
            cards[index2] = temp;
            
            currentSwap++;;
        }
    }
    
    /**
     * Returns string value of a card in the deck. 
     */
    public static String intToCard(int numInList)
    {
        int cardType = numInList%13;
        char cardNum;
        String cardIs = "";
        //If card is A, K, J, or Q, sets it as such, otherwise card is equal to number%13
        if (cardType == 0){
            cardNum = 'K';
        }
        else if (cardType==1){
            cardNum='A';
        }
        else if (cardType==11){
            cardNum = 'J';
        }
        else if (cardType==12){
            cardNum='Q';
        }
        else {
            cardNum = (char)(cardType+'0');
        }
        //determine suit (characters don't show up on my computer, so I copied the symbols into an array)
        char cardSuit = (char)(numInList/13); 
        if (cardType!=10){
			cardIs=""+cardNum+suit[cardSuit];
		}
		else {
			cardIs="10"+suit[cardSuit];
		}
        return cardIs;
        }
        
    /**
     * Returns value of a card
     */
    public static int cardToValue(int numOfList)
    {
        int cardValue;
        //If card is K,J,Q, returns value of 10
        if (numOfList%13==0||(numOfList%13>=10&&numOfList%13<=12)){
            cardValue=10;
        }
        //If ace, return value of 11
        else if (numOfList%13==1){
            cardValue=11;
        }
        //All other cards values are their number%13
        else{
            cardValue=numOfList%13;
        }
        return cardValue;
    }
}
