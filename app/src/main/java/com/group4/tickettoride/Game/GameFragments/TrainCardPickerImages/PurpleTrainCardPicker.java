package com.group4.tickettoride.Game.GameFragments.TrainCardPickerImages;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.group4.tickettoride.Game.GameFragments.TrainCardPickerFragment;
import com.group4.tickettoride.R;

/**
 * Created by Russell Fitzpatrick on 6/7/2017.
 */

public class PurpleTrainCardPicker extends TrainCardPickerImage{

    public PurpleTrainCardPicker(TrainCardPickerFragment fragment) {
        super(fragment);
        setLayout((LinearLayout) fragment.getView().findViewById(R.id.purpleCard_picker));
        setPlus((Button) fragment.getView().findViewById(R.id.purpleCard_plus));
        setMinus((Button) fragment.getView().findViewById(R.id.purpleCard_minus));
        setCount((TextView) fragment.getView().findViewById(R.id.purpleCard_count));
    }
}
