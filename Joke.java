/**
 * Creates jokes to entertain the user. 
 *
 * @Talia Wang
 * @2018-10-29
 */
public class Joke
{
    // constants
    private static final int NUM_OF_JOKES = 5;

    /**
     * Generates random jokes
     */
    public static String generateJoke()
    {
        String[] joke = new String[NUM_OF_JOKES];
        
        joke[0] = "Why do Java programmers wear glasses?\nThey can't C#.\n";
        joke[1] = "Why did the programmer quit his job?\nBecause he didn’t get arrays.\n";
        joke[2] = "Why was the computer kicked off the baseball team?\nIt was always throwing errors.\n";
        joke[3] = "What's the object-oriented way to become wealthy?\nInheritance.\n";
        joke[4] = "How did the programmer die in the shower?\nHe read the shampoo bottle instructions: Lather. Rinse. Repeat.\n";
        
        return joke[(int)(Math.random()*5)];
    }
}
