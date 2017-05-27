package com.group4.shared.command.Client;

import com.group4.shared.Model.Decks;
import com.group4.shared.Model.GAME_STATUS;
import com.group4.shared.Model.Game;
import com.group4.shared.Model.Player;
import com.group4.shared.command.ClientCommand;

import java.util.List;
import java.util.Map;

/**
 * Created by beefhead on 5/12/2017.
 */

public class CUpdateGameCommandData extends ClientCommand
{
    private GAME_STATUS status;
    private Decks deckState;

    public GAME_STATUS getStatus()
    {
        return status;
    }

    public void setStatus(GAME_STATUS status)
    {
        this.status = status;
    }

    public Decks getDeckState()
    {
        return deckState;
    }

    public void setDeckState(Decks deckState)
    {
        this.deckState = deckState;
    }
}
