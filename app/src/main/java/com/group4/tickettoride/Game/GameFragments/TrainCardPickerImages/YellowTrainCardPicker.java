package com.group4.tickettoride.Game.GameFragments.TrainCardPickerImages;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.group4.tickettoride.Game.GameFragments.TrainCardPickerFragment;
import com.group4.tickettoride.Game.GameFragments.TrainCardPickerPresenter;
import com.group4.tickettoride.R;

/**
 * Created by Russell Fitzpatrick on 6/7/2017.
 */

public class YellowTrainCardPicker extends TrainCardPickerImage{

    public YellowTrainCardPicker(TrainCardPickerFragment fragment, TrainCardPickerPresenter presenter) {
        super(fragment, presenter);
        setLayout((LinearLayout) fragment.getView().findViewById(R.id.yellowCard_picker));
        setPlus((Button) fragment.getView().findViewById(R.id.yellowCard_plus));
        setMinus((Button) fragment.getView().findViewById(R.id.yellowCard_minus));
        setCount((TextView) fragment.getView().findViewById(R.id.yellowCard_count));
    }
}
