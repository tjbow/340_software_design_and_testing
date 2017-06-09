package com.group4.tickettoride.Game.GameFragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.group4.shared.Model.Deck.CARD_COLOR;
import com.group4.shared.Model.Deck.TrainCard;
import com.group4.shared.Model.Map.RouteSegment;
import com.group4.tickettoride.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Russell Fitzpatrick on 6/8/2017.
 */

public class TrainCardPickerFragment2 extends DialogFragment implements ITrainCardPickerFragment{

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

    public TrainCardPickerFragment2() {
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

    public void setClaimButtonEnabled(Boolean enabled)
    {
        ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(enabled);
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

    @Override
    public void setColor(CARD_COLOR color) {

    }

    @Override
    public void setCardNumber(int number) {

    }

    @Override
    public void setSelectedRoute(RouteSegment route) {

    }

    @Override
    public List<TrainCard> getSelectedCards() {
        return null;
    }
}
