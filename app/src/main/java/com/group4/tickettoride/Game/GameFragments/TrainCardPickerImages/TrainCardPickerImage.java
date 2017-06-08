package com.group4.tickettoride.Game.GameFragments.TrainCardPickerImages;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.group4.shared.Model.Deck.CARD_COLOR;
import com.group4.tickettoride.Game.GameFragments.TrainCardPickerFragment;
import com.group4.tickettoride.R;

/**
 * Created by Russell Fitzpatrick on 6/7/2017.
 */

public class TrainCardPickerImage{

    private TrainCardPickerFragment fragment;

    private LinearLayout layout;
    private Button plus;
    private Button minus;
    private TextView count;
    private CARD_COLOR color;

    public TrainCardPickerImage(TrainCardPickerFragment fragment) {
        this.fragment = fragment;
    }

    public void setAsEnabled(boolean bool) {
        if (bool)
        {
            layout.setClickable(true);
            layout.setForeground(null);
        }
        else
        {
            layout.setClickable(false);
            Drawable drawable = new ColorDrawable(ContextCompat.getColor(fragment.getActivity(), R.color.colorTransWhite));
            layout.setForeground(drawable);
        }
    }

    public void showCard() {
        layout.setVisibility(View.VISIBLE);
    }

    public void enablePlus(boolean bool) {
        plus.setEnabled(bool);
    }

    public void enableMinus(boolean bool) {
        minus.setEnabled(bool);
    }

    public int getCount() {
        return Integer.parseInt(count.getText().toString());
    }

    public void setValue(int count){
        this.count.setText(count);
    }

    public CARD_COLOR getColor(){
        return color;
    }

    public void setLayout(LinearLayout layout) {
        this.layout = layout;
    }

    public void setPlus(Button plus) {
        this.plus = plus;
    }

    public void setMinus(Button minus) {
        this.minus = minus;
    }

    public void setCount(TextView count){
        this.count = count;
    }
}
