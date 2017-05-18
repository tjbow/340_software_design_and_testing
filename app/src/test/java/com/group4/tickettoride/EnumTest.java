package com.group4.tickettoride;

import com.group4.shared.Model.GAME_STATUS;
import com.group4.shared.Model.Game;

import org.junit.Test;

/**
 * Created by tyler on 5/17/17.
 */

public class EnumTest
{
    @Test
    public void testEnum()
    {
        Game game = new Game("testgame", 4);
        Game game1 = new Game("testgame1", 4);
        game.setStatus(GAME_STATUS.ONGOING);
        game1.setStatus(GAME_STATUS.WAITING);


        if(game.getStatus().equals(GAME_STATUS.ONGOING))
        {
            System.out.println("it's ongoing");
        }

        if(game.getStatus() == GAME_STATUS.ONGOING)
        {
            System.out.println("two");
        }
    }
}
