package com.group4.tickettoride.Game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.support.annotation.Nullable;

import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.group4.tickettoride.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew Gill on 5/20/2017.
 */

public class GameMapView extends View {
    Paint paint = new Paint(); // holds styling information for what gets drawn on the canvas
    boolean tap = false;  //if true display text
    List<Pt> points = new ArrayList<>();  //we will replace this with a better data structure

    public GameMapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBackgroundResource(R.drawable.map_res);
    }

    private void drawRoute(Canvas canvas){
        //Just hardcoding in some values for now
        points.add(new Pt(300,300));
        points.add(new Pt(450, 300 ));

        canvas.drawLine(points.get(0).x,points.get(0).y,points.get(1).x,points.get(1).y,paint);

    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(15);
        paint.setTextSize(50);
        drawRoute(canvas);
        if(tap){
            canvas.drawText("You touched the line",30f,30f,paint);
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        Pt pt1 = points.get(0);
        Pt pt2 = points.get(1);
        Pt tapPt = new Pt(event.getX(),event.getY());

        if(isRoute(pt1,pt2,tapPt)){  //Place inside of a for loop when we actually work on map
            tap = !tap;
            invalidate();
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
     * @param pt1 first endpoint
     * @param pt2 second endopoint
     * @param tapPt contains the cordanates of where the user taped
     * @return true if tapPt is between the two points
     */
    private boolean isRoute(Pt pt1, Pt pt2, Pt tapPt){

        final int threshold = 50;  // max distance from the line

//        pt1 = new Pt(1,2);  // just a little testing
//        pt2 = new Pt(3,2);
//        tapPt = new Pt(2,4);

        float slope = (pt1.y - pt2.y)/(pt1.x-pt2.x)*-1;

        double numerator = ((pt2.y - pt1.y)*tapPt.x) - ((pt2.x - pt1.x)*tapPt.y) + (pt2.x * pt1.y) - (pt2.y * pt1.x);
        numerator = Math.abs(numerator);

        double denominator = (Math.pow(pt2.y -pt1.y,2) + Math.pow(pt2.x - pt1.x,2));
        denominator = Math.sqrt(denominator);

        double dist = numerator/denominator;


        if(dist > threshold){
            return false;
        }


        //Making sure we don't register a click off the end of the line
        float xMax = Math.max(pt1.x,pt2.x) + threshold;
        float xMin = Math.min(pt1.x,pt2.x) - threshold;
        float yMax = Math.max(pt1.y,pt2.y) + threshold;
        float yMin = Math.min(pt1.y,pt2.y) - threshold;

        return !(tapPt.x > xMax || tapPt.x < xMin || tapPt.y > yMax || tapPt.y < yMin);

    }


}
