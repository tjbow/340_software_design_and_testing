package com.group4.tickettoride.Game.GameFragments;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.group4.shared.Model.CARD_COLOR;
import com.group4.shared.Model.DestinationCard;
import com.group4.shared.Model.Game;
import com.group4.shared.Model.TrainCard;
import com.group4.tickettoride.GameList.GameListActivity;
import com.group4.tickettoride.R;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlayerHandFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlayerHandFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayerHandFragment extends Fragment implements IPlayerHandFragment
{
    private View v;
    private IPlayerHandPresenter presenter;
    private List<Drawable> cardList;
    private Map<CARD_COLOR, Integer> cardCounts;

    private RecyclerView recyclerView;
    private CardAdapter adapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PlayerHandFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PlayerHandFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayerHandFragment newInstance()
    {
        PlayerHandFragment fragment = new PlayerHandFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        cardList = new ArrayList<>();

        cardList.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardblack));
        cardList.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardgreen));
        cardList.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardblue));
        cardList.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardpurple));

        cardList.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardred));
        cardList.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardbrown));
        cardList.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardwhite));
        cardList.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardyellow));

        cardList.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardlocamotive));


        v = inflater.inflate(R.layout.fragment_player_hand, container, false);
        // Inflate the layout for this fragment

        recyclerView = (RecyclerView) v.findViewById(R.id.playerHand_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        if (adapter == null)
        {
            adapter = new PlayerHandFragment.CardAdapter(cardList);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

        this.presenter = new PlayerHandPresenter(this);

        return v;
    }

    //------------------- RECYCLERVIEW CODE -----------------------

    private class TrainCardHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cardCount;
        ImageView trainCard;

        private Drawable card;

        public TrainCardHolder(View itemView)
        {
            super(itemView);

            itemView.setOnClickListener(this);
            cardCount = (TextView) itemView.findViewById(R.id.trainCardList_card_count);
            trainCard = (ImageView) itemView.findViewById(R.id.trainCardList_traincard_image);
        }

        public void bindCard(Drawable newCard)
        {
            this.card = newCard;
//            cardCount.setText(count);
            trainCard.setImageDrawable(card);
        }

        @Override
        public void onClick(View v) {
            //call presenter's joinGame()
//            presenter.joinGame(game.getGameName());
        }
    }

    private class CardAdapter extends RecyclerView.Adapter<PlayerHandFragment.TrainCardHolder> {
        private List<Drawable> cards;

        public CardAdapter(List<Drawable> cards)
        {
            this.cards = cards;
        }

        @Override
        public PlayerHandFragment.TrainCardHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(PlayerHandFragment.this.getContext());
            View view = layoutInflater.inflate(R.layout.train_card_list_item, parent, false);
            return new PlayerHandFragment.TrainCardHolder(view);
        }

        @Override
        public void onBindViewHolder(PlayerHandFragment.TrainCardHolder holder, int position)
        {
            Drawable card = cards.get(position);
            holder.bindCard(card);
        }
        @Override
        public int getItemCount()
        {
            return cards.size();
        }

        public void setCards(List<Drawable> cards)
        {
            this.cards = cards;
        }
    }
    //------------------- END RECYCLERVIEW -----------------------

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri)
    {
        if (mListener != null)
        {
            mListener.onFragmentInteraction(uri);
        }
    }

//    ---------INTERFACE IMPLEMENTATION----------------------------------
    @Override
    public void addTrainCards(List<TrainCard> cards)
    {

    }

    @Override
    public void removedTrainCards(List<TrainCard> cards)
    {

    }

    @Override
    public void addDestination(DestinationCard card)
    {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
