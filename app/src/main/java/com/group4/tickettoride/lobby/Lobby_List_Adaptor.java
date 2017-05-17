package com.group4.tickettoride.Lobby;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.group4.shared.Model.Player;
import com.group4.tickettoride.R;

import java.util.List;

/**
 * Created by abgill on 5/16/2017.
 */

public class Lobby_List_Adaptor extends RecyclerView.Adapter<Lobby_List_Adaptor.MyViewHolder> {

    private List<Player> playerList;

    public Lobby_List_Adaptor(List<Player> playerList) {
        this.playerList = playerList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView playerName;
        public View colorSquare;

        public MyViewHolder(View view) {
            super(view);
            playerName = (TextView) view.findViewById(R.id.player_name);
            colorSquare = (View)view.findViewById(R.id.color_square);
        }
    }




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lobby_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Player player = playerList.get(position);
        holder.playerName.setText(player.getUserName());
        holder.colorSquare.setBackgroundColor(getColorId(player.getColor()));
    }

    private int getColorId(String color){
        //TODO: DREW: pull colors into xml file

        switch (color){
            case "red":
                return Color.parseColor("#ff0000");
            case "blue":
                return Color.parseColor("#4b8bf2");
            case "green":
                return Color.parseColor("#29a815");
            case "yellow":
                return Color.parseColor("#fcf800");
            default:
                return Color.parseColor("#000000");
        }
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }
}