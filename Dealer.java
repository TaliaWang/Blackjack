/**
 * A dealer for a game of Blackjack.
 *
 * @Talia Wang
 * @2018-10-29
 */
public class Dealer extends Person
{
    /**
     * Constructor for objects of class Dealer.
     * Constructs a default dealer.
     */
    public Dealer()
    {
        super(DEFAULT_NAME, DEFAULT_MONEY);
    }
    
    /** 
     * Shows the cards in this dealer's hand. 
     * Polymorphism: overrides showHand method in Person class.
     */
    public void showHand()
    {
        for (int i = 0; i < this.hand.size() - 1; i++)
        {
            System.out.print(Deck.intToCard(this.hand.get(i)) + " ");
        }
        System.out.print("?");
    }
}
