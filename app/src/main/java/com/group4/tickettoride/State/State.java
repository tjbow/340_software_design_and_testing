package com.group4.tickettoride.State;

import com.group4.shared.Model.Deck.TrainCard;
import com.group4.shared.Model.Game.MOVE_STATE;
import com.group4.tickettoride.Game.GameFragments.CardDecksPresenter;
import com.group4.tickettoride.Game.GamePresenter;

import java.util.List;

/**
 * Created by Russell Fitzpatrick on 6/5/2017.
 */

public class State {
    GamePresenter gamePresenter;
    CardDecksPresenter cardDecksPresenter;

    public static State SINGLETON = new State();

    public State(GamePresenter gamePresenter, CardDecksPresenter cardDecksPresenter) {
        this.gamePresenter = gamePresenter;
        this.cardDecksPresenter = cardDecksPresenter;
    }

    private State(){}

    public void updateState(MOVE_STATE state){
        switch (state){
            case DRAWN_FIRST_TRAIN_CARD:
                if(gamePresenter.getState().getClass() != DrawnFirstCardState.class) {
                    gamePresenter.setState(new DrawnFirstCardState(gamePresenter, cardDecksPresenter));
                    cardDecksPresenter.setState(new DrawnFirstCardState(gamePresenter, cardDecksPresenter));
                }
                break;
            case MY_TURN:
                if(gamePresenter.getState().getClass() != MyTurnState.class) {
                    gamePresenter.setState(new MyTurnState(gamePresenter, cardDecksPresenter));
                    cardDecksPresenter.setState(new MyTurnState(gamePresenter, cardDecksPresenter));
                }
                break;
            case PENDING:
                gamePresenter.setState(new PendingState(gamePresenter, cardDecksPresenter));
                cardDecksPresenter.setState(new PendingState(gamePresenter, cardDecksPresenter));
                break;
            default:
                if(gamePresenter.getState().getClass() != NotMyTurnState.class) {
                    gamePresenter.setState(new NotMyTurnState(gamePresenter, cardDecksPresenter));
                    cardDecksPresenter.setState(new NotMyTurnState(gamePresenter, cardDecksPresenter));
                }
                break;
        }
    }

    public void setGamePresenter(GamePresenter gamePresenter) {
        this.gamePresenter = gamePresenter;
    }

    public void setCardDecksPresenter(CardDecksPresenter cardDecksPresenter) {
        this.cardDecksPresenter = cardDecksPresenter;
    }

    public String error = "Action not Available: Not your turn";

    public void setError(String error){
        this.error = error;
    }

    public void drawTrainCard() {gamePresenter.displayError(error);}
    public void drawFaceUpTrainCard(int num) {gamePresenter.displayError(error);}
    public void drawDestCards() {gamePresenter.displayError(error);}
    public void claimRoute(String routeId, List<TrainCard> usedCards) {gamePresenter.displayError(error);}

}
