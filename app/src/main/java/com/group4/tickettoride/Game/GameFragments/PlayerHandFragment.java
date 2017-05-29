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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.group4.shared.Model.Deck.CARD_COLOR;
import com.group4.shared.Model.Deck.DestinationCard;
import com.group4.shared.Model.Deck.PlayerHand;
import com.group4.shared.Model.Deck.TrainCard;
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
    private IPlayerHandPresenter presenter;

    //traincard variables
    private RecyclerView trainCardView;
    private TrainCardAdapter trainAdapter;
    private List<Drawable> trainCardImages;
    private List<CARD_COLOR> trainCardColors;
    private List<TrainCard> playerTrainCards;

    //dest card variables
    private RecyclerView destCardView;
    private DestinationCardAdapter destAdapter;
    private List<Drawable> destCardImages;
    private List<DestinationCard> playerDestCards;

    private Button testFunctionButton;


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

        this.presenter = new PlayerHandPresenter(this);
        PlayerHand hand = presenter.getPlayerHandCards();
        this.playerTrainCards = hand.getTrainCards().getCardDeck();
        this.playerDestCards = hand.getDestinationCards().getDestDeck();

        trainCardView = (RecyclerView) v.findViewById(R.id.playerHand_traincardlist);
        trainCardView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        if (trainAdapter == null)
        {
            trainAdapter = new TrainCardAdapter(trainCardColors, trainCardImages);
            trainCardView.setAdapter(trainAdapter);
            trainAdapter.notifyDataSetChanged();
        }

        destCardView = (RecyclerView) v.findViewById(R.id.playerHand_destcardlist);
        destCardView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        if(destAdapter == null)
        {
            destAdapter = new DestinationCardAdapter(destCardImages, playerDestCards);
            destCardView.setAdapter(destAdapter);
            destAdapter.notifyDataSetChanged();
        }

        testFunctionButton = (Button) v.findViewById(R.id.test_function_button);
        testFunctionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                testAction();
            }
        });

        return v;
    }



    //    ---------INTERFACE IMPLEMENTATION----------------------------------
    @Override
    public void updateTrainCards(List<TrainCard> cards)
    {
        this.playerTrainCards = cards;
        trainAdapter.notifyDataSetChanged();
    }

//    @Override
//    public void removedTrainCards(List<TrainCard> cards)
//    {
//
//    }

    @Override
    public void updateDestinationCards(List<DestinationCard> cards)
    {
        this.playerDestCards = cards;
        destAdapter.notifyDataSetChanged();
        destCardView.setAdapter(new DestinationCardAdapter(destCardImages, playerDestCards));
        destCardView.invalidate();
    }

    private void testAction()
    {
        presenter.testAction();
    }

    //------------------- TRAIN CARD RECYCLERVIEW CODE ------------------------------------

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

    private class TrainCardAdapter extends RecyclerView.Adapter<PlayerHandFragment.TrainCardHolder> {
        private List<CARD_COLOR> cardColors;
        private List<Drawable> cards;

        public TrainCardAdapter(List<CARD_COLOR> cardColors, List<Drawable> cards)
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
    }
    //------------------- END TRAINCARD RECYCLERVIEW ---------------------------------------------

    //    ------------DESTINATION CARD RECYCLER---------------------------------------------------
    
    private class DestinationCardAdapter extends RecyclerView.Adapter<PlayerHandFragment.DestinationCardHolder> {
        private List<DestinationCard> cards;
        private List<Drawable> cardImages;

        public DestinationCardAdapter(List<Drawable> cardImages, List<DestinationCard> cards)
        {
            this.cardImages = cardImages;
            this.cards = cards;
        }

        @Override
        public PlayerHandFragment.DestinationCardHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(PlayerHandFragment.this.getContext());
            View view = layoutInflater.inflate(R.layout.dest_card_list_item, parent, false);
            return new PlayerHandFragment.DestinationCardHolder(view);
        }

        @Override
        public void onBindViewHolder(PlayerHandFragment.DestinationCardHolder holder, int position)
        {
            DestinationCard card = cards.get(position);
            Drawable cardImage = cardImages.get(position);
            holder.bindCard(card, cardImage);
        }
        @Override
        public int getItemCount()
        {
            return cards.size();
        }
    }
//    ------------DESTINATION CARD HOLDER---------------------------------------------------
    private class DestinationCardHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
        TextView cardInfo;
        ImageView cardImageView;

        public DestinationCardHolder(View itemView)
        {
            super(itemView);

            itemView.setOnClickListener(this);
            cardInfo = (TextView) itemView.findViewById(R.id.destCardList_card_info);
            cardImageView = (ImageView) itemView.findViewById(R.id.destCardList_destcard_image);
        }

        public void bindCard(DestinationCard newCard, Drawable cardImage)
        {
            String cardText = newCard.getCityA() + " to " +
                                newCard.getCityB() + ", " +
                            newCard.getPoints() + "pts";

            cardInfo.setText(cardText);
            cardImageView.setImageDrawable(cardImage);
        }

        @Override
        public void onClick(View v) {
            //call presenter's joinGame()
//            presenter.joinGame(game.getGameName());
        }
    }
//    ----------END DESTCARD RECYCLER------------------------------

    private void initializeViewLists()
    {
        //TRAIN CARDS
        trainCardImages = new ArrayList<>();
        trainCardColors = new ArrayList<>();
        playerTrainCards = new ArrayList<>();

        trainCardImages.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardred));
        trainCardColors.add(CARD_COLOR.RED);
        trainCardImages.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardblue));
        trainCardColors.add(CARD_COLOR.BLUE);
        trainCardImages.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardgreen));
        trainCardColors.add(CARD_COLOR.GREEN);
        trainCardImages.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardblack));
        trainCardColors.add(CARD_COLOR.BLACK);

        trainCardImages.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardorange));
        trainCardColors.add(CARD_COLOR.ORANGE);
        trainCardImages.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardyellow));
        trainCardColors.add(CARD_COLOR.YELLOW);
        trainCardImages.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardwhite));
        trainCardColors.add(CARD_COLOR.WHITE);
        trainCardImages.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardpurple));
        trainCardColors.add(CARD_COLOR.PURPLE);

        trainCardImages.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardlocamotive));
        trainCardColors.add(CARD_COLOR.RAINBOW);


        //DESTINATION CARDS
        destCardImages = new ArrayList<>();
        playerDestCards = new ArrayList<>();
        destCardImages.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardlocamotive));
        destCardImages.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardlocamotive));
        destCardImages.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardlocamotive));
        destCardImages.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardlocamotive));
        destCardImages.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardlocamotive));
        destCardImages.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardlocamotive));
        destCardImages.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardlocamotive));
        destCardImages.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardlocamotive));
        destCardImages.add(ContextCompat.getDrawable(this.getContext(), R.drawable.traincardlocamotive));

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
