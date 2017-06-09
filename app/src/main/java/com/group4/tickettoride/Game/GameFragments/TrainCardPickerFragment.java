package com.group4.tickettoride.Game.GameFragments;


import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.group4.shared.Model.Deck.CARD_COLOR;
import com.group4.tickettoride.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrainCardPickerFragment extends DialogFragment {

    //Purple Card Views
    private LinearLayout purpleCardPicker;
    private Button purpleCardPlus;
    private Button purpleCardMinus;
    private TextView purpleCardCount;

    //White Card Views
    private LinearLayout whiteCardPicker;
    private Button whiteCardPlus;
    private Button whiteCardMinus;
    private TextView whiteCardCount;

    //Blue Card Views
    private LinearLayout blueCardPicker;
    private Button blueCardPlus;
    private Button blueCardMinus;
    private TextView blueCardCount;

    //Yellow Card Views
    private LinearLayout yellowCardPicker;
    private Button yellowCardPlus;
    private Button yellowCardMinus;
    private TextView yellowCardCount;

    //Orange Card Views
    private LinearLayout orangeCardPicker;
    private Button orangeCardPlus;
    private Button orangeCardMinus;
    private TextView orangeCardCount;

    //Black Card Views
    private LinearLayout blackCardPicker;
    private Button blackCardPlus;
    private Button blackCardMinus;
    private TextView blackCardCount;

    //Red Card Views
    private LinearLayout redCardPicker;
    private Button redCardPlus;
    private Button redCardMinus;
    private TextView redCardCount;

    //Green Card Views
    private LinearLayout greenCardPicker;
    private Button greenCardPlus;
    private Button greenCardMinus;
    private TextView greenCardCount;

    //Locomotive Card Views
    private LinearLayout locomotiveCardPicker;
    private Button locomotiveCardPlus;
    private Button locomotiveCardMinus;
    private TextView locomotiveCardCount;

    private TextView routeTitle;
    private TextView requiredRouteCards;

    private static final String ROUTE_ID = "route_ID";

    private ITrainCardPickerPresenter presenter;
    private List<CARD_COLOR> initializedPickers = new ArrayList<>();

    private Dialog dialog;
    private View v;

    public TrainCardPickerFragment() {
        // Required empty public constructor
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_train_card_picker, null);

        routeTitle = (TextView) v.findViewById(R.id.routeTitle);
        requiredRouteCards = (TextView) v.findViewById(R.id.routeCardRequirements);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());

        dialogBuilder.setView(v);
        dialogBuilder.setTitle(R.string.claim_route);
        dialogBuilder.setPositiveButton(R.string.claim, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.onClaim();
            }
        });
        dialogBuilder.setNegativeButton(R.string.cancel, null);
        dialog = dialogBuilder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                setClaimButtonEnabled(false);
            }
        });

        presenter.afterFragmentCreation();

        return dialog;
    }

    public View getView(){
        return v;
    }

    public void setClaimButtonEnabled(Boolean enabled)
    {
        ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(enabled);
    }

    public void disableAllCurrentPickers()
    {
        for (CARD_COLOR color : initializedPickers)
        {

                switch(color) {
                    case PURPLE:
                        setPurplePickerEnabled(false);
                        break;
                    case WHITE:
                        setWhitePickerEnabled(false);
                        break;
                    case BLUE:
                        setBluePickerEnabled(false);
                        break;
                    case RED:
                        setRedPickerEnabled(false);
                        break;
                    case BLACK:
                        setBlackPickerEnabled(false);
                        break;
                    case YELLOW:
                        setYellowPickerEnabled(false);
                        break;
                    case ORANGE:
                        setOrangePickerEnabled(false);
                        break;
                    case GREEN:
                        setGreenPickerEnabled(false);
                        break;
                    default:
                        break;
                }

        }
    }

    public void setAllCurrentPickersEnabled()
    {
        setPurplePickerEnabled(true);
        setWhitePickerEnabled(true);
        setBluePickerEnabled(true);
        setYellowPickerEnabled(true);
        setOrangePickerEnabled(true);
        setBlackPickerEnabled(true);
        setRedPickerEnabled(true);
        setGreenPickerEnabled(true);
        setLocomotivePickerEnabled(true);
    }

    public void setPresenter(ITrainCardPickerPresenter presenter) {
        this.presenter = presenter;
    }

    public void setRouteTitle(String title)
    {
        this.routeTitle.setText(title);
    }

    public void setRequiredRouteCards(String requirements)
    {
        this.requiredRouteCards.setText(requirements);
    }

    //---------PURPLE CARD PICKER FUNCTIONS
    /**
     * initializes views and sets purpleCardPicker layout to visible
     */
    public void showPurpleCardPicker()
    {
        //Purple Card Views
        purpleCardPicker = (LinearLayout) v.findViewById(R.id.purpleCard_picker);
        purpleCardPicker.setVisibility(View.VISIBLE);
        purpleCardPlus = (Button) v.findViewById(R.id.purpleCard_plus);
        purpleCardPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onPurpleIncrement();
            }
        });
        purpleCardMinus = (Button) v.findViewById(R.id.purpleCard_minus);
        purpleCardMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onPurpleDecrement();
            }
        });
        purpleCardCount = (TextView) v.findViewById(R.id.purpleCard_count);
        initializedPickers.add(CARD_COLOR.PURPLE);
    }

    public int getPurpleCardCount()
    {
        return Integer.parseInt(purpleCardCount.getText().toString());
    }

    public void setPurpleCardCount(String count)
    {
        purpleCardCount.setText(count);
    }

    public void setPurpleCardPlusEnabled(Boolean enabled)
    {
        purpleCardPlus.setEnabled(enabled);
    }

    public void setPurpleCardMinusEnabled(Boolean enabled)
    {
        purpleCardMinus.setEnabled(enabled);
    }

    public void setPurplePickerEnabled(Boolean enabled)
    {
        if (purpleCardPicker != null)
        {
            if (enabled)
            {
                purpleCardPicker.setEnabled(true);
                purpleCardPicker.setForeground(null);
                setPurpleCardPlusEnabled(true);
            }
            else
            {
                purpleCardPicker.setEnabled(false);
                setPurpleCardPlusEnabled(false);
                Drawable drawable = new ColorDrawable(ContextCompat.getColor(getActivity(), R.color.colorTransWhite));
                purpleCardPicker.setForeground(drawable);
            }
        }

    }

    //---------WHITE CARD PICKER FUNCTIONS
    /**
     * initializes views and sets whiteCardPicker layout to visible
     */
    public void showWhiteCardPicker()
    {
        //White Card Views
        whiteCardPicker = (LinearLayout) v.findViewById(R.id.whiteCard_picker);
        whiteCardPicker.setVisibility(View.VISIBLE);
        whiteCardPlus = (Button) v.findViewById(R.id.whiteCard_plus);
        whiteCardPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onWhiteIncrement();
            }
        });
        whiteCardMinus = (Button) v.findViewById(R.id.whiteCard_minus);
        whiteCardMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onWhiteDecrement();
            }
        });
        whiteCardCount = (TextView) v.findViewById(R.id.whiteCard_count);
        initializedPickers.add(CARD_COLOR.WHITE);
    }

    public int getWhiteCardCount()
    {
        return Integer.parseInt(whiteCardCount.getText().toString());
    }

    public void setWhiteCardCount(String count)
    {
        whiteCardCount.setText(count);
    }

    public void setWhiteCardPlusEnabled(Boolean enabled)
    {
        whiteCardPlus.setEnabled(enabled);
    }

    public void setWhiteCardMinusEnabled(Boolean enabled)
    {
        whiteCardMinus.setEnabled(enabled);
    }

    public void setWhitePickerEnabled(Boolean enabled)
    {

        if (whiteCardPicker != null)
        {
            if (enabled)
            {
                whiteCardPicker.setClickable(true);
                setWhiteCardPlusEnabled(true);
                whiteCardPicker.setForeground(null);
            }
            else
            {
                whiteCardPicker.setClickable(false);
                Drawable drawable = new ColorDrawable(ContextCompat.getColor(getActivity(), R.color.colorTransWhite));
                whiteCardPicker.setForeground(drawable);
                setWhiteCardPlusEnabled(false);
            }
        }

    }

    //---------BLUE CARD PICKER FUNCTIONS
    /**
     * initializes views and sets blueCardPicker layout to visible
     */
    public void showBlueCardPicker()
    {
        //Blue Card Views
        blueCardPicker = (LinearLayout) v.findViewById(R.id.blueCard_picker);
        blueCardPicker.setVisibility(View.VISIBLE);
        blueCardPlus = (Button) v.findViewById(R.id.blueCard_plus);
        blueCardPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onBlueIncrement();
            }
        });
        blueCardMinus = (Button) v.findViewById(R.id.blueCard_minus);
        blueCardMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onBlueDecrement();
            }
        });
        blueCardCount = (TextView) v.findViewById(R.id.blueCard_count);
        initializedPickers.add(CARD_COLOR.BLUE);
    }

    public int getBlueCardCount()
    {
        return Integer.parseInt(blueCardCount.getText().toString());
    }

    public void setBlueCardCount(String count)
    {
        blueCardCount.setText(count);
    }

    public void setBlueCardPlusEnabled(Boolean enabled)
    {
        blueCardPlus.setEnabled(enabled);
    }

    public void setBlueCardMinusEnabled(Boolean enabled)
    {
        blueCardMinus.setEnabled(enabled);
    }

    public void setBluePickerEnabled(Boolean enabled)
    {
        if (blueCardPicker != null)
        {
            if (enabled)
            {
                blueCardPicker.setClickable(true);
                blueCardPicker.setForeground(null);
                setBlueCardPlusEnabled(true);
            }
            else
            {
                blueCardPicker.setClickable(false);
                Drawable drawable = new ColorDrawable(ContextCompat.getColor(getActivity(), R.color.colorTransWhite));
                blueCardPicker.setForeground(drawable);
                setBlueCardPlusEnabled(false);
            }
        }

    }

    //---------YELLOW CARD PICKER FUNCTIONS
    /**
     * initializes views and sets yellowCardPicker layout to visible
     */
    public void showYellowCardPicker()
    {
        //Yellow Card Views
        yellowCardPicker = (LinearLayout) v.findViewById(R.id.yellowCard_picker);
        yellowCardPicker.setVisibility(View.VISIBLE);
        yellowCardPlus = (Button) v.findViewById(R.id.yellowCard_plus);
        yellowCardPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onYellowIncrement();
            }
        });
        yellowCardMinus = (Button) v.findViewById(R.id.yellowCard_minus);
        yellowCardMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onYellowDecrement();
            }
        });
        yellowCardCount = (TextView) v.findViewById(R.id.yellowCard_count);
        initializedPickers.add(CARD_COLOR.YELLOW);
    }

    public int getYellowCardCount()
    {
        return Integer.parseInt(yellowCardCount.getText().toString());
    }

    public void setYellowCardCount(String count)
    {
        yellowCardCount.setText(count);
    }

    public void setYellowCardPlusEnabled(Boolean enabled)
    {
        yellowCardPlus.setEnabled(enabled);
    }

    public void setYellowCardMinusEnabled(Boolean enabled)
    {
        yellowCardMinus.setEnabled(enabled);
    }

    public void setYellowPickerEnabled(Boolean enabled)
    {
        if (yellowCardPicker != null)
        {
            if (enabled)
            {
                yellowCardPicker.setClickable(true);
                yellowCardPicker.setForeground(null);
                setYellowCardPlusEnabled(true);
            }
            else
            {
                yellowCardPicker.setClickable(false);
                Drawable drawable = new ColorDrawable(ContextCompat.getColor(getActivity(), R.color.colorTransWhite));
                yellowCardPicker.setForeground(drawable);
                setYellowCardPlusEnabled(false);
            }
        }

    }

    //---------ORANGE CARD PICKER FUNCTIONS
    /**
     * initializes views and sets orangeCardPicker layout to visible
     */
    public void showOrangeCardPicker()
    {
        //Orange Card Views
        orangeCardPicker = (LinearLayout) v.findViewById(R.id.orangeCard_picker);
        orangeCardPicker.setVisibility(View.VISIBLE);
        orangeCardPlus = (Button) v.findViewById(R.id.orangeCard_plus);
        orangeCardPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onOrangeIncrement();
            }
        });
        orangeCardMinus = (Button) v.findViewById(R.id.orangeCard_minus);
        orangeCardMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onOrangeDecrement();
            }
        });
        orangeCardCount = (TextView) v.findViewById(R.id.orangeCard_count);
        initializedPickers.add(CARD_COLOR.ORANGE);
    }

    public int getOrangeCardCount()
    {
        return Integer.parseInt(orangeCardCount.getText().toString());
    }

    public void setOrangeCardCount(String count)
    {
        orangeCardCount.setText(count);
    }

    public void setOrangeCardPlusEnabled(Boolean enabled)
    {
        orangeCardPlus.setEnabled(enabled);
    }

    public void setOrangeCardMinusEnabled(Boolean enabled)
    {
        orangeCardMinus.setEnabled(enabled);
    }

    public void setOrangePickerEnabled(Boolean enabled)
    {
        if (orangeCardPicker != null)
        {
            if (enabled)
            {
                orangeCardPicker.setClickable(true);
                orangeCardPicker.setForeground(null);
                setOrangeCardPlusEnabled(true);
            }
            else
            {
                orangeCardPicker.setClickable(false);
                Drawable drawable = new ColorDrawable(ContextCompat.getColor(getActivity(), R.color.colorTransWhite));
                orangeCardPicker.setForeground(drawable);
                setOrangeCardPlusEnabled(false);
            }
        }

    }

    //---------BLACK CARD PICKER FUNCTIONS
    /**
     * initializes views and sets blackCardPicker layout to visible
     */
    public void showBlackCardPicker()
    {
        //Black Card Views
        blackCardPicker = (LinearLayout) v.findViewById(R.id.blackCard_picker);
        blackCardPicker.setVisibility(View.VISIBLE);
        blackCardPlus = (Button) v.findViewById(R.id.blackCard_plus);
        blackCardPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onBlackIncrement();
            }
        });
        blackCardMinus = (Button) v.findViewById(R.id.blackCard_minus);
        blackCardMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onBlackDecrement();
            }
        });
        blackCardCount = (TextView) v.findViewById(R.id.blackCard_count);
        initializedPickers.add(CARD_COLOR.BLACK);
    }

    public int getBlackCardCount()
    {
        return Integer.parseInt(blackCardCount.getText().toString());
    }

    public void setBlackCardCount(String count)
    {
        blackCardCount.setText(count);
    }

    public void setBlackCardPlusEnabled(Boolean enabled)
    {
        blackCardPlus.setEnabled(enabled);
    }

    public void setBlackCardMinusEnabled(Boolean enabled)
    {
        blackCardMinus.setEnabled(enabled);
    }

    public void setBlackPickerEnabled(Boolean enabled)
    {

        if (blackCardPicker != null)
        {
            if (enabled)
            {
                blackCardPicker.setClickable(true);
                blackCardPicker.setForeground(null);
                setBlackCardPlusEnabled(true);
            }
            else
            {
                blackCardPicker.setClickable(false);
                Drawable drawable = new ColorDrawable(ContextCompat.getColor(getActivity(), R.color.colorTransWhite));
                blackCardPicker.setForeground(drawable);
                setBlackCardPlusEnabled(false);
            }
        }

    }

    //---------RED CARD PICKER FUNCTIONS
    /**
     * initializes views and sets redCardPicker layout to visible
     */
    public void showRedCardPicker()
    {
        //Red Card Views
        redCardPicker = (LinearLayout) v.findViewById(R.id.redCard_picker);
        redCardPicker.setVisibility(View.VISIBLE);
        redCardPlus = (Button) v.findViewById(R.id.redCard_plus);
        redCardPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onRedIncrement();
            }
        });
        redCardMinus = (Button) v.findViewById(R.id.redCard_minus);
        redCardMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onRedDecrement();
            }
        });
        redCardCount = (TextView) v.findViewById(R.id.redCard_count);
        initializedPickers.add(CARD_COLOR.RED);
    }

    public int getRedCardCount()
    {
        return Integer.parseInt(redCardCount.getText().toString());
    }

    public void setRedCardCount(String count)
    {
        redCardCount.setText(count);
    }

    public void setRedCardPlusEnabled(Boolean enabled)
    {
        redCardPlus.setEnabled(enabled);
    }

    public void setRedCardMinusEnabled(Boolean enabled)
    {
        redCardMinus.setEnabled(enabled);
    }

    public void setRedPickerEnabled(Boolean enabled)
    {
        if (redCardPicker != null)
        {
            if (enabled)
            {
                redCardPicker.setClickable(true);
                redCardPicker.setForeground(null);
                setRedCardPlusEnabled(true);
            }
            else
            {
                redCardPicker.setClickable(false);
                Drawable drawable = new ColorDrawable(ContextCompat.getColor(getActivity(), R.color.colorTransWhite));
                redCardPicker.setForeground(drawable);
                setRedCardPlusEnabled(false);
            }
        }

    }

    //---------GREEN CARD PICKER FUNCTIONS
    /**
     * initializes views and sets greenCardPicker layout to visible
     */
    public void showGreenCardPicker()
    {
        //Green Card Views
        greenCardPicker = (LinearLayout) v.findViewById(R.id.greenCard_picker);
        greenCardPicker.setVisibility(View.VISIBLE);
        greenCardPlus = (Button) v.findViewById(R.id.greenCard_plus);
        greenCardPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onGreenIncrement();
            }
        });
        greenCardMinus = (Button) v.findViewById(R.id.greenCard_minus);
        greenCardMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onGreenDecrement();
            }
        });
        greenCardCount = (TextView) v.findViewById(R.id.greenCard_count);
        initializedPickers.add(CARD_COLOR.GREEN);
    }

    public int getGreenCardCount()
    {
        return Integer.parseInt(greenCardCount.getText().toString());
    }

    public void setGreenCardCount(String count)
    {
        greenCardCount.setText(count);
    }

    public void setGreenCardPlusEnabled(Boolean enabled)
    {
        greenCardPlus.setEnabled(enabled);
    }

    public void setGreenCardMinusEnabled(Boolean enabled)
    {
        greenCardMinus.setEnabled(enabled);
    }

    public void setGreenPickerEnabled(Boolean enabled)
    {
        if (greenCardPicker != null)
        {
            if (enabled)
            {
                greenCardPicker.setClickable(true);
                greenCardPicker.setForeground(null);
                setGreenCardPlusEnabled(true);
            }
            else
            {
                greenCardPicker.setClickable(false);
                Drawable drawable = new ColorDrawable(ContextCompat.getColor(getActivity(), R.color.colorTransWhite));
                greenCardPicker.setForeground(drawable);
                setGreenCardPlusEnabled(false);
            }
        }

    }

    //---------LOCOMOTIVE CARD PICKER FUNCTIONS
    /**
     * initializes views and sets locomotiveCardPicker layout to visible
     */
    public void showLocomotiveCardPicker()
    {
        //Locomotive Card Views
        locomotiveCardPicker = (LinearLayout) v.findViewById(R.id.locomotiveCard_picker);
        locomotiveCardPicker.setVisibility(View.VISIBLE);
        locomotiveCardPlus = (Button) v.findViewById(R.id.locomotiveCard_plus);
        locomotiveCardPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLocomotiveIncrement();
            }
        });
        locomotiveCardMinus = (Button) v.findViewById(R.id.locomotiveCard_minus);
        locomotiveCardMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLocomotiveDecrement();
            }
        });
        locomotiveCardCount = (TextView) v.findViewById(R.id.locomotiveCard_count);
        initializedPickers.add(CARD_COLOR.RAINBOW);
    }

    public int getLocomotiveCardCount()
    {
        if (locomotiveCardCount != null){
            return Integer.parseInt(locomotiveCardCount.getText().toString());
        }
        else return 0;
    }

    public void setLocomotiveCardCount(String count)
    {
        locomotiveCardCount.setText(count);
    }

    public void setLocomotiveCardPlusEnabled(Boolean enabled)
    {
        if (locomotiveCardPlus != null){
            locomotiveCardPlus.setEnabled(enabled);
        }
    }

    public void setLocomotiveCardMinusEnabled(Boolean enabled)
    {
        locomotiveCardMinus.setEnabled(enabled);
    }

    public void setLocomotivePickerEnabled(Boolean enabled)
    {
        if (locomotiveCardPicker != null)
        {
            if (enabled)
            {
                locomotiveCardPicker.setClickable(true);
                locomotiveCardPicker.setForeground(null);
                setLocomotiveCardPlusEnabled(true);
            }
            else
            {
                locomotiveCardPicker.setClickable(false);
                Drawable drawable = new ColorDrawable(ContextCompat.getColor(getActivity(), R.color.colorTransWhite));
                locomotiveCardPicker.setForeground(drawable);
                setLocomotiveCardPlusEnabled(false);
            }
        }

    }


}
