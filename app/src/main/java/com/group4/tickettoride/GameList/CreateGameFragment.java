package com.group4.tickettoride.GameList;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.group4.tickettoride.R;


public class CreateGameFragment extends DialogFragment implements ICreateGameFragment{

    private Dialog mDialog;
    private EditText mGameNameEditText;
    private NumberPicker mNumberPicker;
    private IGameListPresenter mPresenter;

    private final int MAX_PLAYERS = 5;
    private final int MIN_PLAYERS = 2;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_create_game, null);

        mPresenter = new GameListPresenter(this);

        mGameNameEditText = (EditText)v.findViewById(R.id.createGame_gameName);
        mGameNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals(""))
                {
                    setCreateGameButtonEnabled(true);
                }
                else
                {
                    setCreateGameButtonEnabled(false);
                }
            }
        });

        mNumberPicker = (NumberPicker)v.findViewById(R.id.createGame_numberPicker);
        mNumberPicker.setMinValue(MIN_PLAYERS);
        mNumberPicker.setMaxValue(MAX_PLAYERS);
        mNumberPicker.setWrapSelectorWheel(false);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setView(v);
        dialogBuilder.setTitle(R.string.create_game);
        dialogBuilder.setPositiveButton(R.string.create_game, new DialogInterface.OnClickListener() { //the anon inner class here is a listener for the positive button
            public void onClick(DialogInterface dialog, int whichButton) {

                //call presenter's createGame()
                mPresenter.createGame();

            }
        });
        dialogBuilder.setNegativeButton(android.R.string.cancel, null); //the null parameter here indicates to do nothing when the button is pressed
        mDialog = dialogBuilder.create();
        mDialog.setCanceledOnTouchOutside(false);
        //this is so the createGame button will be false at first (no title)
        mDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                setCreateGameButtonEnabled(false);
            }
        });


        return mDialog;
    }

    private void setCreateGameButtonEnabled(Boolean enabled)
    {
        ((AlertDialog) mDialog).getButton(AlertDialog.BUTTON_POSITIVE)
                .setEnabled(enabled);
    }


    @Override
    public int getPlayerCount() {
        return mNumberPicker.getValue();
    }

    @Override
    public String getGameName() {
        return mGameNameEditText.getText().toString();
    }
}
