package com.group4.tickettoride.Game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.support.annotation.Nullable;

import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.group4.shared.Model.ROUTE_COLOR;
import com.group4.shared.Model.Route;
import com.group4.tickettoride.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew Gill on 5/20/2017.
 */

public class GameMapView extends View {
    Paint paint = new Paint(); // holds styling information for what gets drawn on the canvas
    boolean tap = false;  //if true display text.  Used only for demonstration purposes

    List<Route> routes = new ArrayList<>();

    public GameMapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        Route r = new Route(); //hard coding in route information
        r.setX1Constraint(50);
        r.setX2Constraint(60);
        r.setY1Constraint(70);
        r.setY2Constraint(80);
        r.setLength(3);
        r.setRouteColor(ROUTE_COLOR.RED);

        routes.add(r);

        setBackgroundResource(R.drawable.unitedstates_map);  //set map as background
    }

    public void setRoutes(List<Route> routes){

        this.routes = routes;
        invalidate();
    }

    private void drawRoutes(Canvas canvas){

        float width = getWidth();
        float height = getHeight();

        for(Route r : routes){
            //translating from constraint to pixel value
            float x1 = r.getX1Constraint()/100 * width;
            float y1 = r.getY1Constraint()/100 * height;
            float x2 = r.getX2Constraint()/100 * width;
            float y2 = r.getY2Constraint()/100 * height;

            paint.setColor(getColor(r));

            canvas.drawLine(x1,y1,x2,y2,paint);
        }
    }

    private int getColor(Route r){
        switch (r.getRouteColor()){
            case RED:
                return ContextCompat.getColor(getContext(),R.color.colorRed);
            case GREEN:
                return ContextCompat.getColor(getContext(),R.color.colorGreen);
            case BLACK:
                return ContextCompat.getColor(getContext(),R.color.colorBlack);
            case ORANGE:
                return ContextCompat.getColor(getContext(),R.color.colorOrange);
            case YELLOW:
                return ContextCompat.getColor(getContext(),R.color.colorYellow);
            case GRAY:
                return ContextCompat.getColor(getContext(),R.color.colorGray);
            case WHITE:
                return ContextCompat.getColor(getContext(),R.color.colorWhite);
            case PURPLE:
                return ContextCompat.getColor(getContext(),R.color.colorPurple);

        }
        return 0;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(15);
        paint.setTextSize(50);
        drawRoutes(canvas);
        if(tap){
            canvas.drawText("You touched the line",30f,30f,paint);
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

//        Pt pt1 = points.get(0);
//        Pt pt2 = points.get(1);
        Pt tapPt = new Pt(event.getX(),event.getY());

        //Todo: Drew: set city as Dead zone to avoid clicking conflicts

        for(Route r : routes) {
            Pt pt1 = new Pt(r.getX1Constraint()/100 * getWidth(),r.getY1Constraint()/100 *getHeight());
            Pt pt2 = new Pt(r.getX2Constraint()/100 * getWidth(),r.getY2Constraint()/100 *getHeight());

            if (isRoute(pt1, pt2, tapPt)) {  //Place inside of a for loop when we actually work on map
                tap = !tap;
                invalidate();
            }
        }

        return super.onTouchEvent(event);
    }

    /**
     * Used for storing a single point
     */
    private class Pt{
        float x;
        float y;

        public Pt(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * Determines if you are clicking close enough to the line for it to register
     * @param pt1 first endpoint
     * @param pt2 second endopoint
     * @param tapPt contains the cordanates of where the user taped
     * @return true if tapPt is between the two points
     */
    private boolean isRoute(Pt pt1, Pt pt2, Pt tapPt){

        final int threshold = 50;  // max distance from the line


        float slope = (pt1.y - pt2.y)/(pt1.x-pt2.x)*-1;

        // using algorithm found here
        //https://en.wikipedia.org/wiki/Distance_from_a_point_to_a_line

        double numerator = ((pt2.y - pt1.y)*tapPt.x) - ((pt2.x - pt1.x)*tapPt.y) + (pt2.x * pt1.y) - (pt2.y * pt1.x);
        numerator = Math.abs(numerator);

        double denominator = (Math.pow(pt2.y -pt1.y,2) + Math.pow(pt2.x - pt1.x,2));
        denominator = Math.sqrt(denominator);

        double dist = numerator/denominator;

        if(dist > threshold){
            return false;
        }

        //Making sure we don't register a click off the end of the line segment
        float xMax = Math.max(pt1.x,pt2.x) + threshold;
        float xMin = Math.min(pt1.x,pt2.x) - threshold;
        float yMax = Math.max(pt1.y,pt2.y) + threshold;
        float yMin = Math.min(pt1.y,pt2.y) - threshold;

        return !(tapPt.x > xMax || tapPt.x < xMin || tapPt.y > yMax || tapPt.y < yMin);
    }


}
