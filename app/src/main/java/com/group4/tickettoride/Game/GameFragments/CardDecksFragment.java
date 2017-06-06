package com.group4.tickettoride.Game.GameFragments;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.group4.shared.Model.Deck.CARD_COLOR;
import com.group4.shared.Model.Deck.TrainCard;
import com.group4.tickettoride.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CardDecksFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CardDecksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardDecksFragment extends Fragment implements ITrainDeckFragment
{
    ITrainDeckPresenter cardDecksPresenter;
    List<ImageView> faceUpImageViewList;
    //ImageView faceUpImageView0;
    //ImageView faceUpImageView1;
    //ImageView faceUpImageView2;
    //ImageView faceUpImageView3;
    //ImageView faceUpImageView4;
    ImageView trainDeckImageView;
    ImageView destDeckImageView;
    TextView trainCardsRemainTextView;
    TextView destCardsRemainTextView;

    private OnFragmentInteractionListener mListener;

    public CardDecksFragment()
    {
        // Required empty public constructor
        cardDecksPresenter = new CardDecksPresenter(this);
        faceUpImageViewList = new ArrayList<>();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CardDecksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CardDecksFragment newInstance()
    {
        CardDecksFragment fragment = new CardDecksFragment();
        Bundle args = new Bundle();
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
        View v = inflater.inflate(R.layout.fragment_train_deck, container, false);
        // Grab components
        faceUpImageViewList.add((ImageView) v.findViewById(R.id.face_up0));
        faceUpImageViewList.add((ImageView) v.findViewById(R.id.face_up1));
        faceUpImageViewList.add((ImageView) v.findViewById(R.id.face_up2));
        faceUpImageViewList.add((ImageView) v.findViewById(R.id.face_up3));
        faceUpImageViewList.add((ImageView) v.findViewById(R.id.face_up4));
        trainDeckImageView = (ImageView) v.findViewById(R.id.train_deck_image);
        destDeckImageView = (ImageView) v.findViewById(R.id.destination_deck_image);
        trainCardsRemainTextView = (TextView) v.findViewById(R.id.train_deck_remain_text);
        destCardsRemainTextView = (TextView) v.findViewById(R.id.dest_deck_remain_text);

        // Initialize to proper values
        setFaceUpCards(cardDecksPresenter.getFaceUpCards());
        setTrainDeckCardsRemaining(cardDecksPresenter.getTrainDeckCardsRemaining());
        setDestCardsRemaining(cardDecksPresenter.getDestDeckCardsRemaining());

        trainDeckImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardDecksPresenter.drawTrainCard();
            }
        });

        destDeckImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardDecksPresenter.drawDestCards();
            }
        });

        faceUpImageViewList.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFaceUpValid(0);
            }
        });

        faceUpImageViewList.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFaceUpValid(1);
            }
        });

        faceUpImageViewList.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFaceUpValid(2);
            }
        });

        faceUpImageViewList.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFaceUpValid(3);
            }
        });

        faceUpImageViewList.get(4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFaceUpValid(4);
            }
        });

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri)
    {
        if (mListener != null)
        {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void checkFaceUpValid(int position){
        if(position < cardDecksPresenter.getFaceUpCards().size()){
            cardDecksPresenter.drawFaceUpCard(position);
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setFaceUpCards(List<TrainCard> cards)
    {
        for(int i = 0; i < 5; i++)
        {
            if(cards.size() > i) // card to add to view
            {
                faceUpImageViewList.get(i).setImageDrawable(getPictureFromColor(cards.get(i).getColor()));
            }
            else // no card to add set transparent
            {
                faceUpImageViewList.get(i).setImageResource(android.R.color.transparent);
            }
        }
    }

    @Override
    public void setTrainDeckCardsRemaining(int numRemain)
    {
        trainCardsRemainTextView.setText(Integer.toString(numRemain));
    }

    @Override
    public void setDestCardsRemaining(int numRemain)
    {
        destCardsRemainTextView.setText(Integer.toString(numRemain));
    }

    /**
     * Return the drawable card resource from the color
     *
     * @param color the train card color
     * @return the drawable card
     */
    Drawable getPictureFromColor(CARD_COLOR color)
    {
        int id = 0;
        switch (color)
        {
            case RED:
                id = R.drawable.traincardred;
                break;
            case BLUE:
                id = R.drawable.traincardblue;
                break;
            case GREEN:
                id = R.drawable.traincardgreen;
                break;
            case BLACK:
                id = R.drawable.traincardblack;
                break;
            case ORANGE:
                id = R.drawable.traincardorange;
                break;
            case YELLOW:
                id = R.drawable.traincardyellow;
                break;
            case WHITE:
                id = R.drawable.traincardwhite;
                break;
            case PURPLE:
                id = R.drawable.traincardpurple;
                break;
            case RAINBOW:
                id = R.drawable.traincardlocamotive;
                break;
            default:
                throw new RuntimeException("Bad color from card");
        }
        return ContextCompat.getDrawable(getActivity(), id);
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
