package com.group4.tickettoride.Game.GameFragments;

import android.graphics.drawable.Drawable;
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
import com.group4.shared.Model.TrainCard;
import com.group4.tickettoride.R;

import java.util.ArrayList;
import java.util.List;

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
    private RecyclerView recyclerView;
    private CardAdapter adapter;

    private IPlayerHandPresenter presenter;
    private List<Drawable> cardList;
    private List<CARD_COLOR> cardColors;
    private List<TrainCard> playerTrainCards;


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
        initializeViewLists();

        v = inflater.inflate(R.layout.fragment_player_hand, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.playerHand_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        if (adapter == null)
        {
            adapter = new PlayerHandFragment.CardAdapter(cardColors, cardList);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

        this.presenter = new PlayerHandPresenter(this);

        return v;
    }

    //    ---------INTERFACE IMPLEMENTATION----------------------------------
    @Override
    public void updateTrainCards(List<TrainCard> cards)
    {
        this.playerTrainCards = cards;
        adapter.notifyDataSetChanged();
    }

//    @Override
//    public void removedTrainCards(List<TrainCard> cards)
//    {
//
//    }

    @Override
    public void updateDestinationCards(List<DestinationCard> cards)
    {

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

        public void bindCard(CARD_COLOR color, Drawable newCard)
        {
            this.card = newCard;
            int count = 0;
            for(TrainCard card : playerTrainCards)
            {
                if(card.getColor() == color)
                {
                    count++;
                }
            }
            cardCount.setText(Integer.toString(count));
            trainCard.setImageDrawable(card);
        }

        @Override
        public void onClick(View v) {
            //call presenter's joinGame()
//            presenter.joinGame(game.getGameName());
        }
    }

    private class CardAdapter extends RecyclerView.Adapter<PlayerHandFragment.TrainCardHolder> {
        private List<CARD_COLOR> cardColors;
        private List<Drawable> cards;

        public CardAdapter(List<CARD_COLOR> cardColors, List<Drawable> cards)
        {
            this.cards = cards;
            this.cardColors = cardColors;
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
            CARD_COLOR color = cardColors.get(position);
            holder.bindCard(color, card);
        }
        @Override
        public int getItemCount()
        {
            return cards.size();
        }

//        public void setCards(Map<CARD_COLOR, Drawable> cards)
//        {
//            this.cards = cards;
//        }
    }
    //------------------- END RECYCLERVIEW -----------------------

    private void initializeViewLists()
    {
        cardList = new ArrayList<>();
        cardColors = new ArrayList<>();
        playerTrainCards = new ArrayList<>();

        cardList.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardred));
        cardColors.add(CARD_COLOR.RED);
        cardList.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardblue));
        cardColors.add(CARD_COLOR.BLUE);
        cardList.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardgreen));
        cardColors.add(CARD_COLOR.GREEN);
        cardList.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardblack));
        cardColors.add(CARD_COLOR.BLACK);

        cardList.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardbrown));
        cardColors.add(CARD_COLOR.ORANGE);
        cardList.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardyellow));
        cardColors.add(CARD_COLOR.YELLOW);
        cardList.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardwhite));
        cardColors.add(CARD_COLOR.WHITE);
        cardList.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardpurple));
        cardColors.add(CARD_COLOR.PURPLE);

        cardList.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardlocamotive));
        cardColors.add(CARD_COLOR.RAINBOW);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri)
    {
        if (mListener != null)
        {
            mListener.onFragmentInteraction(uri);
        }
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
