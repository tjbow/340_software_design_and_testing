import com.group4.shared.Model.Deck.Decks;

import org.junit.Test;

/**
 * Created by tyler on 6/5/17.
 */

public class DecksTest
{
    @Test
    public void testStartGameDeck()
    {
        for(int i = 0; i < 1000; i++)
        {
            Decks decks = new Decks();

            decks.startGameDeck();
        }
    }
}
