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

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TrainDeckFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TrainDeckFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrainDeckFragment extends Fragment implements ITrainDeckFragment
{
    ITrainDeckPresenter trainPresenter;
    ImageView faceUpImageView0;
    ImageView faceUpImageView1;
    ImageView faceUpImageView2;
    ImageView faceUpImageView3;
    ImageView faceUpImageView4;
    ImageView trainDeckImageView;
    ImageView destDeckImageView;
    TextView trainCardsRemainTextView;
    TextView destCardsRemainTextView;

    private OnFragmentInteractionListener mListener;

    public TrainDeckFragment()
    {
        // Required empty public constructor
        trainPresenter = new TrainDeckPresenter(this);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TrainDeckFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrainDeckFragment newInstance()
    {
        TrainDeckFragment fragment = new TrainDeckFragment();
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
        faceUpImageView0 = (ImageView) v.findViewById(R.id.face_up0);
        faceUpImageView1 = (ImageView) v.findViewById(R.id.face_up1);
        faceUpImageView2 = (ImageView) v.findViewById(R.id.face_up2);
        faceUpImageView3 = (ImageView) v.findViewById(R.id.face_up3);
        faceUpImageView4 = (ImageView) v.findViewById(R.id.face_up4);
        trainDeckImageView = (ImageView) v.findViewById(R.id.train_deck_image);
        destDeckImageView = (ImageView) v.findViewById(R.id.destination_deck_image);
        trainCardsRemainTextView = (TextView) v.findViewById(R.id.train_deck_remain_text);
        destCardsRemainTextView = (TextView) v.findViewById(R.id.dest_deck_remain_text);

        // Initialize to proper values
        setFaceUpCards(trainPresenter.getFaceUpCards());
        setTrainDeckCardsRemaining(trainPresenter.getTrainDeckCardsRemaining());
        setDestCardsRemaining(trainPresenter.getDestDeckCardsRemaining());

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

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setFaceUpCards(List<TrainCard> cards)
    {
        faceUpImageView0.setImageDrawable(getPictureFromColor(cards.get(0).getColor()));
        faceUpImageView1.setImageDrawable(getPictureFromColor(cards.get(1).getColor()));
        faceUpImageView2.setImageDrawable(getPictureFromColor(cards.get(2).getColor()));
        faceUpImageView3.setImageDrawable(getPictureFromColor(cards.get(3).getColor()));
        faceUpImageView4.setImageDrawable(getPictureFromColor(cards.get(4).getColor()));
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
