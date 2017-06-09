package com.group4.tickettoride.Game.GameFragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.group4.shared.Model.Deck.DestinationCard;
import com.group4.shared.Model.Deck.DestinationCardDeck;
import com.group4.tickettoride.ClientModel.DestinationCardHelper;
import com.group4.tickettoride.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DestCardPickerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DestCardPickerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DestCardPickerFragment extends DialogFragment implements IDestCardPickerFragment
{
    private Dialog mDialog;

    private IDestCardPickerPresenter destPresenter;
    private List<ImageView> destCardImageList;
    private Button confirmButton;
    private List<Integer> selectedImages;
    private List<TextView> textViewList;
    private Button showMapButton;

    public int getMinNumSelected()
    {
        return minNumSelected;
    }

    static private int minNumSelected;

    private OnFragmentInteractionListener mListener;

    public DestCardPickerFragment()
    {
        selectedImages = new ArrayList<>();
        destPresenter = new DestCardPickerPresenter(this);
        destCardImageList = new ArrayList<>();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DestCardPickerFragment.
     */
    public static DestCardPickerFragment newInstance(int inMinNumSelected)
    {
        minNumSelected = inMinNumSelected;
        DestCardPickerFragment fragment = new DestCardPickerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        Window window = getDialog().getWindow();
        Point  size = new Point();

        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);

        int width = size.x;
        int height = size.y;
        window.setLayout((int) (width * 0.75), (int) (height * 0.4));
        window.setGravity(Gravity.CENTER);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_dest_card_picker, null);
        // grab layout items
        destCardImageList.add((ImageView) v.findViewById(R.id.dest_picker0));
        destCardImageList.add((ImageView) v.findViewById(R.id.dest_picker1));
        destCardImageList.add((ImageView) v.findViewById(R.id.dest_picker2));
        setImageViews(v);
        confirmButton = (Button)v.findViewById(R.id.desk_picker_button);
        setTextViews(v);
        showMapButton = (Button) v.findViewById(R.id.showMapButton);

        // set on click listeners
        destCardImageList.get(0).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                handleImageClick(0);
            }
        });

        destCardImageList.get(1).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                handleImageClick(1);
            }
        });

        destCardImageList.get(2).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                handleImageClick(2);
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener()  {
            public void onClick(View v) {
                handleConfirm();
            }
        });

        showMapButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        setDialogVisible(false);
                        break;
                    case MotionEvent.ACTION_UP:
                        setDialogVisible(true);
                        break;
                }
                return true;
            }
        });

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setView(v);
        mDialog = dialogBuilder.create();

        //setDialogVisible(false);
        mDialog.setCanceledOnTouchOutside(false);
        return mDialog;
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setMinNumSelected(int num)
    {
        minNumSelected = num;
    }

    @Override
    public List<Integer> getSelectedCards()
    {
        return selectedImages;
    }

    @Override
    public void setCards(List<DestinationCard> cards)
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
        void onFragmentInteraction(Uri uri);
    }

    /**
     * Sets the image views
     * @param v the current view
     */
    void setImageViews(View v)
    {
        for(int i = 0; i < 3; i++)
        {
            if(destPresenter.getCards().size() > i)
            {
                destCardImageList.get(i).setImageDrawable(
                        DestinationCardHelper.getDestPicture(destPresenter.getCards().get(i), getContext()));
            }
            else
            {
                destCardImageList.get(i).setImageResource(android.R.color.transparent);
            }

        }
    }

    /**
     * Set the text to the proper cities
     */
    void setTextViews(View v)
    {
        textViewList = new ArrayList<>();
        textViewList.add((TextView) v.findViewById(R.id.dest_text0));
        textViewList.add((TextView) v.findViewById(R.id.dest_text1));
        textViewList.add((TextView) v.findViewById(R.id.dest_text2));

        //Set properties
        confirmButton.setEnabled(false);
        List<DestinationCard> destCards = destPresenter.getCards();

        int upperlimit = 3;
        if (destCards.size() < upperlimit)
        {
            upperlimit = destCards.size();
        }
        for(int i = 0; i < upperlimit; i++)
        {
            textViewList.get(i).setText(getDestText(destCards.get(i)));
        }
    }

    /**
     * Creates a string based on destination cities
     * @param card the destination card
     */
    String getDestText(DestinationCard card)
    {
        return card.getCityA() + " to " + card.getCityB();
    }

    /**
     * Handles the confirmation button click
     */
    private void handleConfirm()
    {
        destPresenter.returnSelectedDestCards(getComplimentOfSelCards());
        //destPresenter.setFirstDestCardsSelected();
        dismiss();
    }

    /**
     * Get indices not selected
     */
    private List<Integer> getComplimentOfSelCards()
    {
        List<Integer> notSelected = new ArrayList<>();
        for(int i = 0; i <= 2; i++) // get complement of
        {
            if(!selectedImages.contains(i))
            {
                notSelected.add(i);
            }
        }
        return notSelected;
    }

    /**
     * Handle the click of one of the image views
     * @param num the image that is clicked
     */
    private void handleImageClick(int num)
    {
        setOutline(num);
        toggleSelection(num);
    }

    /**
     * Sets the outline of the image view
     * @param num the imageview number
     */
    private void setOutline(int num)
    {
        ImageView selImage;
        switch(num)
        {
            case 0:
                selImage = destCardImageList.get(0);
                break;
            case 1:
                selImage = destCardImageList.get(1);
                break;
            case 2:
                selImage = destCardImageList.get(2);
                break;
            default:
                throw new IndexOutOfBoundsException();
        }

        // if already selected, deselect
        if(!selectedImages.contains(num))
        {
            selImage.setBackgroundColor(Color.argb(100,0,0,255));
        }
        else
        {
            selImage.setBackgroundColor(Color.argb(0,0,0,0));
        }
    }

    /**
     * Add/remove the num from the selected images
     * @param num the image
     */
    private void toggleSelection(int num)
    {
        if(selectedImages.contains(num))
        {
            selectedImages.remove(selectedImages.indexOf(num));
        }
        else
        {
            selectedImages.add(num);
        }

        //enable/disable confirm button
        if(selectedImages.size() >= minNumSelected)
        {
            confirmButton.setEnabled(true);
        }
        else
        {
            confirmButton.setEnabled(false);
        }

    }

    private void setDialogVisible(Boolean visible)
    {
        if (visible)
        {
            mDialog.getWindow()
                    .setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getActivity(), R.color.colorWhite)));
            setCardsVisible(true);
            confirmButton.setVisibility(View.VISIBLE);
            setCaptionsVisible(true);
            showMapButton.setBackgroundResource(android.R.drawable.btn_default);
            showMapButton.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorBlack));
        }
        else
        {
            mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            setCardsVisible(false);
            confirmButton.setVisibility(View.INVISIBLE);
            setCaptionsVisible(false);
            showMapButton.setBackground(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            showMapButton.setTextColor(Color.TRANSPARENT);
        }
    }

    private void setCardsVisible(Boolean visible)
    {
        //set each cards visibility to invisible
        for (ImageView cardImage : destCardImageList)
        {
            if (visible)
            {
                cardImage.setVisibility(View.VISIBLE);
            }
            else
            {
                cardImage.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void setCaptionsVisible(Boolean visible)
    {
        for (TextView caption : textViewList)
        {
            if (visible)
            {
                caption.setVisibility(View.VISIBLE);
            }
            else
            {
                caption.setVisibility(View.INVISIBLE);
            }
        }
    }
}
