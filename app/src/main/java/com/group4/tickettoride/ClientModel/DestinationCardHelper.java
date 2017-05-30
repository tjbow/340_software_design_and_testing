package com.group4.tickettoride.ClientModel;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.group4.shared.Model.Deck.DestinationCard;
import com.group4.tickettoride.R;

/**
 * Created by Tom on 5/29/2017.
 */

public final class DestinationCardHelper
{
    /**
     * Get the picture resource for the destination card
     * @param card the card for the resource
     * @param context context from the current activity
     * @return the drawable destination card
     */
    public static Drawable getPictureId(DestinationCard card, Context context)
    {
        int id = 0;
        switch(card.getId())
        {
            case 0: // "Los Angeles", "New York", 21
                id = R.drawable.la_ny;
                break;
            case 1: //"Duluth", "Houston", 8
                id = R.drawable.duluth_houston;
                break;
            case 2: //"Sault St. Marie", "Nashville", 8
                id = R.drawable.saultstemarie_nashville;
                break;
            case 3: //"New York", "Atlanta", 6
                id = R.drawable.ny_atlanta;
                break;
            case 4: //"Portland", "Nashville", 17
                id = R.drawable.portland_nashville;
                break;
            case 5: //"Vancouver", "Montreal", 20
                id = R.drawable.vancouver_montreal;
                break;
            case 6: //"Duluth", "El Paso", 10
                id = R.drawable.duluth_elpaso;
                break;
            case 7: //"Toronto", "Miami", 10
                id = R.drawable.toronto_miami;
                break;
            case 8: //"Portland", "Phoenix", 11
                id = R.drawable.portland_phoenix;
                break;
            case 9: //"Dallas", "New York", 11
                id = R.drawable.dallas_ny;
                break;
            case 10: //"Calgary", "Salt Lake City", 7
                id = R.drawable.calgary_slc;
                break;
            case 11: //"Calgary", "Phoenix", 13
                id = R.drawable.calgary_phoenix;
                break;
            case 12: //"Los Angeles", "Miami", 20
                id = R.drawable.la_miami;
                break;
            case 13: //"Winnipeg", "Little Rock", 11
                id = R.drawable.winnipeg_littlerock;
                break;
            case 14: //"San Francisco", "Atlanta", 17
                id = R.drawable.sanfrancisco_atlanta;
                break;
            case 15: //"Kansas City", "Houston", 5
                id = R.drawable.kansascity_houston;
                break;
            case 16: //"Los Angeles", "Chicago", 16
                id = R.drawable.la_chicago;
                break;
            case 17: //"Denver", "Pittsburgh", 11
                id = R.drawable.denver_pittsburgh;
                break;
            case 18: //"Chicago", "Santa Fe", 9
                id = R.drawable.chicago_santafe;
                break;
            case 19: //"Vancouver", "Santa Fe", 13
                id = R.drawable.vancouver_santafe;
                break;
            case 20: //"Boston", "Miami", 12
                id = R.drawable.boston_miami;
                break;
            case 21: //"Chicago", "New Orleans", 7
                id = R.drawable.chicago_neworleans;
                break;
            case 22: //"Montreal", "Atlanta", 9
                id = R.drawable.montreal_atlanta;
                break;
            case 23: //"Seattle", "New York", 22
                id = R.drawable.seattle_ny;
                break;
            case 24: //"Denver", "El Paso", 4
                id = R.drawable.denver_elpaso;
                break;
            case 25: //"Helena", "Los Angeles", 8
                id = R.drawable.helena_la;
                break;
            case 26: //"Winnipeg", "Houston", 12
                id = R.drawable.winnipeg_houston;
                break;
            case 27: //"Montreal", "New Orleans", 13
                id = R.drawable.montreal_newlorleans;
                break;
            case 28: //"Sault St. Marie", "Oklahoma City", 9
                id = R.drawable.saultstemarie_oklahomacity;
                break;
            case 29: //"Seattle", "Los Angeles", 9
                id = R.drawable.seattle_la;
                break;
            default:
                throw new RuntimeException("Bad destination card id");
        }
        return ContextCompat.getDrawable(context, id);
    }
}
