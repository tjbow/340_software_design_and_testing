package com.group4.tickettoride.GameList;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.group4.tickettoride.R;


public class CreateGameFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_create_game, null);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.create_game)
                .setPositiveButton(R.string.create_game, null)
                .setNegativeButton(R.string.cancel, null)
                .create();


    }
}
