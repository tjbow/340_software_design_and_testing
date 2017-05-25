package com.group4.shared.Model;

/**
 * Created by Russell Fitzpatrick on 5/23/2017.
 */

public class TrainCard {

    CARD_COLOR color;
    boolean visible;

    public TrainCard(CARD_COLOR color, boolean visible)
    {
        this.color = color;
        this.visible = visible;
    }

    public CARD_COLOR getColor() {
        return color;
    }

    public void setColor(CARD_COLOR color) {
        this.color = color;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
