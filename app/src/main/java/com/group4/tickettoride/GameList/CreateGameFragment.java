package com.group4.tickettoride.GameList;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.group4.tickettoride.R;


public class CreateGameFragment extends DialogFragment {

    private Dialog mDialog;
    private EditText mGameNameEditText;
    private NumberPicker mNumberPicker;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_create_game, null);

        mGameNameEditText = (EditText)v.findViewById(R.id.createGame_gameName);

        mNumberPicker = (NumberPicker)v.findViewById(R.id.createGame_numberPicker);


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setView(v);
        dialogBuilder.setTitle(R.string.create_game);
        dialogBuilder.setPositiveButton(R.string.create_game, new DialogInterface.OnClickListener() { //the anon inner class here is a listener for the positive button
            public void onClick(DialogInterface dialog, int whichButton) {
                //TODO: @John: move to lobby activity from here
                Toast.makeText(getActivity(), "create game button was pressed", Toast.LENGTH_SHORT).show();
                // move to the lobby activity
            }
        });
        dialogBuilder.setNegativeButton(android.R.string.cancel, null); //the null parameter here indicates to do nothing when the button is pressed
        mDialog = dialogBuilder.create();
        mDialog.setCanceledOnTouchOutside(false);

        return mDialog;
    }


}
