/**
 * A player in a game of Blackjack. 
 * 
 * @Talia Wang
 * @2018-10-29
 */
public class Player extends Person
{
    /**
     * Constructor for objects of class Player.
     * Constructs a player with a given name and money. 
     */
    public Player(String name, double money)
    {
        super(name, money);
    }
    
    /**
     * Makes a decision to either hit or stand. 
     */
    public boolean makeDecision(String decision)
    {
        if (decision.equalsIgnoreCase("stand"))
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
}
