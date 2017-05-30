package com.group4.tickettoride.Game;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.group4.tickettoride.Game.GameFragments.DestCardPickerFragment;
import com.group4.tickettoride.R;

public class testActivity extends AppCompatActivity implements IMapActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // run the destination card picker at the beginning of play
        //DialogFragment destPickerDialog = DestCardPickerFragment.newInstance(2);
        //destPickerDialog.show(this.getSupportFragmentManager(), "dialog");
    }

    @Override
    public void onOpenChat() {
       
    }
}
