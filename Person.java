import java.util.ArrayList;
/**
 * A person in a game of Blackjack. 
 * 
 * @Talia Wang
 * @2018-10-29
 */
public abstract class Person
{ 
    // constants
    protected static final int MAX_BEFORE_STAND = 16; 
    protected static final String DEFAULT_NAME = "Talia";
    protected static final double DEFAULT_MONEY = 2000;
    
    // global variables
    protected ArrayList<Integer> hand;
    private String name;
    private double money;
    private int handValue;

    /**
     * Constructor for objects of class Person.
     * Constructs a person with a given name and money. 
     */
    public Person(String name, double money)
    {
        this.name = name;
        this.money = money;
        hand = new ArrayList<Integer>();
        handValue = 0; 
    }

    /**
     * Returns this person's name.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Returns this person's money. 
     */
    public double getMoney()
    {
        return money;
    }
    
    /**
     * Calculates the appropriate hand value if aces are present.
     * Returns this person's hand value. 
     */
    public int getHandValue()
    {
        handValue = 0;
        int numberAces = 0;
        
        // determine if ace is present
        for (int i = 0; i < hand.size(); i++)
        {
            handValue = handValue + Deck.cardToValue(hand.get(i));
            if (Deck.cardToValue(hand.get(i)) == 11)
            {
                numberAces = numberAces + 1;
            }
        }
       
        // change value of ace to 1 if hand value is greater than 21 
        while (handValue > 21 && numberAces > 0)
        {
            handValue = handValue - 10;
            numberAces = numberAces - 1;
        }
        return handValue;
    }
    
     /**
     * Sets this person's hand value. 
     */
    public void setHandValue(int h)
    {
        handValue = h;
    }
    
    /**
     * Sets this person's money.
     */
    public void setMoney(double m)
    {
        money = m;
    }
    
    /**
     * Sets this person's name.
     */
    public void setName(String n)
    {
        name = n;
    }
    
    /**
     * Clears this person's hand.
     */
    public void clearHand()
    {
        hand.clear();
        handValue = 0;
    }
    
    /**
     * Determines whether this person's hand has busted.
     */
    public boolean hasBusted()
    {
        return handValue > 21;
    }
    
    /** 
     * Shows the cards in this person's hand. 
     */
    public void showHand()
    {
        for (int i = 0; i < hand.size(); i++)
        {
            System.out.print(Deck.intToCard(hand.get(i)) + " ");
        }
    }
    
    /**
     * Takes a card dealt from the deck.
     */
    public void takeACard(int card)
    {
        hand.add(card);
    }
}
