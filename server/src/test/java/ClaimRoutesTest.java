import com.group4.server.ServerModel.ServerFacade;
import com.group4.shared.Model.Deck.CARD_COLOR;
import com.group4.shared.Model.Deck.TrainCard;
import com.group4.shared.Model.Game.Game;
import com.group4.shared.Model.Map.RouteList;
import com.group4.shared.Model.Map.RouteSegment;
import com.group4.shared.Model.Player;
import com.group4.shared.Model.User;
import com.group4.shared.Proxy.IServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyler on 6/7/17.
 */

public class ClaimRoutesTest
{
    private Game game;
    private RouteList mRouteList;
    private IServer serverFacade;

    @Before
    public void setUp()
    {
        game = new Game("testgame", 2);
        game.importRoutes();
        mRouteList = game.getRoutes();

        User user = new User("tyler", "t");
        game.addPlayer(new Player(user));

        game.getPlayers().forEach(Player::initializeGame);
        game.dealInitialGameCards();
    }

    @After
    public void tearDown()
    {

    }

    @Test
    public void claimRouteTest()
    {
        RouteSegment segment = mRouteList.findRoute("MontrealSaultStMarie");

        List<TrainCard> usedCards = new ArrayList<>();
        for(int i = 0; i < 4; i++)
        {
            usedCards.add(new TrainCard(CARD_COLOR.BLACK, false));
        }

        boolean success = game.playerTurn_claimRoute("tyler", segment, usedCards);

        System.out.println(success);
    }
}
