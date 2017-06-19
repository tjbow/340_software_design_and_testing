package com.group4.shared.Model.Game;

import java.io.Serializable;

/**
 * Created by tyler on 6/5/17.
 */

public enum MOVE_STATE implements Serializable
{
    DRAWN_FIRST_TRAIN_CARD,
    NOT_MY_TURN,
    MY_TURN,
    PENDING
}
