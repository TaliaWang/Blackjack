import java.util.*;
/**
 * Creates a new game of Blackjack.
 *
 * @Talia Wang
 * @2018-10-29
 */
public class Game
{
    // constants
    private static final String GAME_DIVIDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    
    // global variables
    private static Scanner scan = new Scanner(System.in);
    private String name;
    private double money;
    private double betAmount;
    private Deck deck;
    private Person dealer; 
    private Player player; 
    private boolean playAgain;

    /**
     * Constructor for objects of class Game. 
     */
    public Game()
    {
        // new dealer
        dealer = new Dealer();
        
        // introduce game 
        System.out.println(GAME_DIVIDER + "\n");
        System.out.println("Welcome to Blackjack! My name is " + 
            dealer.getName() + " and I will be your dealer. "
            + "I have $" + String.format("%.2f", dealer.DEFAULT_MONEY) + ".");
        
        // get information from player
        System.out.print("What is your name? ");
        name = scan.nextLine();
        System.out.print("How much money do you have? $");
        money = Double.parseDouble(scan.nextLine());
        System.out.print("How much do you want to bet per round? $");
        betAmount = Double.parseDouble(scan.nextLine());

        System.out.println("");
        
        // new player
        player = new Player (name, money);
               
        // new deck
        deck = new Deck();
        System.out.print("Deck: ");
        deck.printDeck();
        
        // shuffle and print deck.
        deck.shuffle();
        System.out.print("Shuffled deck: ");
        deck.printDeck();
        
        System.out.println("");
        
        runGame();
    }
    
    /**
     * Runs the game.
     */
    public void runGame()
    {
        do
        {
            playAgain = false;
            displayStartingConditions();
            playRounds();
            determineWinner();
            concludeGame();
        } while (playAgain);

        displayAdditionalMessages();
    }
    
    /**
     * Displays starting conditions of the player and dealer. 
     */
    public void displayStartingConditions()
    {
            // player's starting cards (1 extra for starting hand)
            player.takeACard(deck.dealACard());
            playerTurn();
        
            // dealer's starting cards (1 extra for starting hand)
            dealer.takeACard(deck.dealACard());
            dealerTurn();
        
            //print deck
            System.out.print("Current deck: ");
            deck.printDeck();
            System.out.println("");
    }
    
    /**
     * Runs through the rounds in one game.
     */
    public void playRounds()
    {
        // go through every round
        thisLoop: while (!player.hasBusted() && !dealer.hasBusted())
        {
            System.out.print("Hit or stand: \n");
                
            // if player hits
            if (!player.makeDecision(scan.nextLine()))
            {
                System.out.println("");
                
                // player draws another card
                playerTurn();
                System.out.print("Current deck: ");
                deck.printDeck();
                System.out.println("");
               
                // if dealer stands
                if (dealer.getHandValue() > Dealer.MAX_BEFORE_STAND 
                    && dealer.getHandValue() >= player.getHandValue() || player.hasBusted())
                {
                    System.out.println("I've chosen to stand. \n");
                    
                    // keep asking player to hit/stand until they stand or bust
                    while (!player.hasBusted())
                    {
                        System.out.print("Hit or stand: \n");
                        // if player stands
                        if (player.makeDecision(scan.nextLine())) 
                        {
                            System.out.println("");
                            break thisLoop;
                        }
                        System.out.println("");
                        playerTurn();
                        System.out.print("Current deck: ");
                        deck.printDeck();
                        System.out.println("");
                        if (player.hasBusted()) break thisLoop;
                    }
                }

                if (player.hasBusted()) break;
                // dealer draws another card
                dealerTurn();
                System.out.print("Current deck: ");
                deck.printDeck();
                System.out.println("");
                if (dealer.hasBusted()) break;

            }
            // if player stands
            else 
            {
                System.out.println("");
                // dealer keeps drawing cards until they bust or stand
                while (!dealer.hasBusted())
                { 
                    // dealer plays safe and hits only when their hand value is below player's hand value
                    if(dealer.getHandValue() >= player.getHandValue())
                    {
                        System.out.println("I've chosen to stand. \n");
                        break thisLoop;
                    }
                    dealerTurn();
                    System.out.print("Current deck: ");
                    deck.printDeck();
                    System.out.println("");
                    if (dealer.hasBusted()) break thisLoop;
                }
            }
        }
    }
    
    /**
     * Determines the winner of the game. 
     * Displays the player's and the dealer's money after the game. 
     */
    public void determineWinner()
    {
        // display entirety of dealer's deck and its value
        System.out.print("Reveal of my hand: ");
        // cannot call showHand method of Person class because dealer has the same method in its class and the method would be overriden (polymorphism)
        for (int i = 0; i < dealer.hand.size(); i++)
        {
            System.out.print(Deck.intToCard(dealer.hand.get(i)) + " ");
        }
        System.out.println("");
        System.out.println("My hand's value is: " + dealer.getHandValue());
        System.out.println("");

        // determine winner
        if (player.hasBusted() && dealer.hasBusted() 
            || player.getHandValue() == dealer.getHandValue()) // tie
        {
            System.out.println("This round is a tie. Let's have a rematch!");
        }
        else 
        {
            if (player.hasBusted() && !dealer.hasBusted()
                || !dealer.hasBusted() &&
                player.getHandValue() < dealer.getHandValue()) // dealer wins
            {
                System.out.println("Sorry, you lose this round.\n");
            
                // give money from player to dealer
                player.setMoney(player.getMoney() - betAmount);
                dealer.setMoney(dealer.getMoney() + betAmount);
                    
            }
            else // player wins 
            {
                System.out.println("Congratulations, you've won this round!\n");
                      
                // give money from dealer to player
                player.setMoney(player.getMoney() + betAmount);
                dealer.setMoney(dealer.getMoney() - betAmount);
            }
            System.out.println("Your money: $" + String.format("%.2f", player.getMoney()));
            System.out.println("My money: $" + String.format("%.2f", dealer.getMoney()));
            System.out.println("");
        }
    }
    
    /**
     * Asks user whether or not they wish to replay.
     */
    public void concludeGame()
    {
        // ask to replay
        if (player.getMoney() < 0) 
        {
            System.out.println("Please reconsider before you play again. You're in debt, friend!");
        }
        System.out.print("Play again? Enter yes or no: ");
        if ((scan.nextLine()).equalsIgnoreCase("yes"))
        {
            playAgain = true;
        }
            
        // aesthetics
        System.out.print("\n" + GAME_DIVIDER + "\n");
            
        // reset player's and dealer's hands.
        player.clearHand();
        dealer.clearHand();
    }
    
     /**
     * Displays additional messages after the game's conclusion.
     */
    public static void displayAdditionalMessages()
    {
        System.out.println("Before you leave, would you like to hear a joke? Yes or no:");
        if (scan.nextLine().equalsIgnoreCase("yes"))
        {
            System.out.println("\n" + Joke.generateJoke());
        }
        else 
        {
            System.out.println("\nAw, you're no fun. :(");
        }   
        System.out.println("\nThank you for playing! \n");
    }
    
    /**
     * Takes a card from the deck for a player. 
     * Displays this player's hand with their hand value. 
     */
    public void playerTurn()
    {
        player.takeACard(deck.dealACard());
        System.out.print(player.getName() + ", your hand is: ");
        player.showHand();
        System.out.println("");
        System.out.println("Your hand's value is: " + player.getHandValue());
        System.out.println("");
    }
    
    /**
     * Takes a card from the deck for the dealer. 
     * Displays this dealer's hand with their hand value. 
     */
    public void dealerTurn()
    {
        dealer.takeACard(deck.dealACard());
        System.out.print("My hand is: ");
        dealer.showHand();
        System.out.println("\n");
    }
}
