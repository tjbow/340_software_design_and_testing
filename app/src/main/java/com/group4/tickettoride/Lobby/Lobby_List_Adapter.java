package com.group4.tickettoride.Lobby;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.group4.shared.Model.Player;
import com.group4.tickettoride.R;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import java.util.List;

/**
 * Created by abgill on 5/16/2017.
 */

public class Lobby_List_Adapter extends RecyclerView.Adapter<Lobby_List_Adapter.MyViewHolder> {

    private List<Player> playerList;
    Context context;

    public Lobby_List_Adapter(Context context, List<Player> playerList) {
        this.playerList = playerList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView playerName;
        public ImageView trainIcon;

        public MyViewHolder(View view) {
            super(view);
            playerName = (TextView) view.findViewById(R.id.player_name);
            trainIcon =(ImageView) view.findViewById(R.id.train_icon);
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

        int color = getColorId(player.getColor());
        Drawable icon = new IconDrawable(context, FontAwesomeIcons.fa_train).colorRes(color);
        holder.trainIcon.setImageDrawable(icon);

        //holder.colorSquare.setBackgroundColor(getColorId(player.getColor()));
    }

    private int getColorId(String color){


        switch (color){
            case "blue":
                return R.color.colorBlue;
            case "red":
                return R.color.colorRed;
            case "green":
                return R.color.colorGreen;
            case "yellow":
                return R.color.colorYellow;
            default:
                return R.color.colorBlack;
        }
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }
}