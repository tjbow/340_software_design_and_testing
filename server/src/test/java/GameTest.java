import com.group4.shared.Model.Game;
import com.group4.shared.Model.Player;
import com.group4.shared.Model.User;

import org.junit.Test;

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

//        game.dealInitialCards();

        System.out.println();
    }
}
