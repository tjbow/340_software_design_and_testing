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

public class RedTrainCardPicker extends TrainCardPickerImage{

    public RedTrainCardPicker(TrainCardPickerFragment fragment, TrainCardPickerPresenter presenter) {
        super(fragment, presenter);
        setLayout((LinearLayout) fragment.getView().findViewById(R.id.redCard_picker));
        setPlus((Button) fragment.getView().findViewById(R.id.redCard_plus));
        setMinus((Button) fragment.getView().findViewById(R.id.redCard_minus));
        setCount((TextView) fragment.getView().findViewById(R.id.redCard_count));
    }
}
