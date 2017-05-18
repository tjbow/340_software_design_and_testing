package com.group4.tickettoride.Game;

import com.group4.shared.Model.Game;

/**
 * Created by tyler on 5/18/17.
 */

public interface IGamePresenter
{
    void endGame(String gameName);

    void onBackPressed();

    Game getGame();
}
