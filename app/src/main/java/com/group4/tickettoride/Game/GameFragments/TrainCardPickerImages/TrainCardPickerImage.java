package com.group4.tickettoride.Game.GameFragments.TrainCardPickerImages;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.group4.shared.Model.Deck.CARD_COLOR;
import com.group4.tickettoride.Game.GameFragments.ITrainCardPickerPresenter;
import com.group4.tickettoride.Game.GameFragments.TrainCardPickerFragment;
import com.group4.tickettoride.Game.GameFragments.TrainCardPickerPresenter;
import com.group4.tickettoride.R;

/**
 * Created by Russell Fitzpatrick on 6/7/2017.
 */

public class TrainCardPickerImage{

    private TrainCardPickerFragment fragment;
    private ITrainCardPickerPresenter presenter;

    private LinearLayout layout;
    private Button plus;
    private Button minus;
    private TextView count;
    private CARD_COLOR color;

    public TrainCardPickerImage(TrainCardPickerFragment fragment, ITrainCardPickerPresenter presenter, CARD_COLOR color) {
        this.fragment = fragment;
        this.presenter = presenter;
        this.color = color;
    }

    public void setAsEnabled(boolean bool) {
        if (bool)
        {
            layout.setClickable(true);
            layout.setForeground(null);
            enablePlus(true);
            enableMinus(false);
        }
        else
        {
            enablePlus(false);
            enableMinus(false);
            layout.setClickable(false);
            Drawable drawable = new ColorDrawable(ContextCompat.getColor(fragment.getActivity(), R.color.colorTransWhite));
            layout.setForeground(drawable);
        }
    }

    public void showCard() {
        layout.setVisibility(View.VISIBLE);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue(getCount() + 1);
                presenter.onIncrement(color);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue(getCount() - 1);
                presenter.onDecrement(color);
            }
        });

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
        this.count.setText(Integer.toString(count));
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
