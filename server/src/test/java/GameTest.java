import com.group4.shared.Model.DestinationCard;
import com.group4.shared.Model.DestinationCardDeck;
import com.group4.shared.Model.Game;
import com.group4.shared.Model.Player;
import com.group4.shared.Model.User;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by tyler on 5/25/17.
 */

public class GameTest
{
    @Test
    public void testInitGame()
    {
        Game game = new Game("Best Game", 4);

        User user = new User("tyler", "pwd");
        User user1 = new User("john", "pwd");
        User user2 = new User("russ", "pwd");
        User user3 = new User("drew", "pwd");

        game.addPlayer(user.getUsername(), new Player(user));
        game.addPlayer(user1.getUsername(), new Player(user1));
        game.addPlayer(user2.getUsername(), new Player(user2));
        game.addPlayer(user3.getUsername(), new Player(user3));

        for(Map.Entry<String, Player> entry : game.getPlayers().entrySet())
        {
            entry.getValue().initializeGame();
        }

        game.dealInitialGameCards();

        Player player = game.getPlayers().get(user.getUsername());

        List<DestinationCard> playersDeck = player.getDestinationCardDeck().getDestDeck();
        int count = 2;
        List<DestinationCard> returnedCard = new ArrayList<>();
        for(DestinationCard card : playersDeck)
        {
            returnedCard.add(card);
            count--;
            if(count == 0) break;
        }

        game.playerTurn_ReturnDestinationCards(user.getUsername(), returnedCard);

        System.out.println();
    }
}
