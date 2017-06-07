package com.group4.tickettoride.Game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.graphics.Typeface;
import android.support.annotation.Nullable;

import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group4.shared.Model.Map.City;
import com.group4.shared.Model.Map.PLAYER_COLOR;
import com.group4.shared.Model.Map.RouteSegment;
import com.group4.tickettoride.R;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by Andrew Gill on 5/20/2017.
 */

public class GameMapView extends View {
    private Paint paint = new Paint(); // holds styling information for what gets drawn on the canvas
    boolean tap = false;  //if true display text.  Used only for demonstration purposes

    static private Set<RouteSegment> routeSegments = new HashSet<>();
    static private Set<City> cities = new HashSet<>();

    private float CITY_RADIUS = 15;


    public Set<RouteSegment> getRouteSegments() {
        return routeSegments;
    }

    public GameMapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        getCities();
        getRoutes();

        paint.setFlags(Paint.ANTI_ALIAS_FLAG);

        setBackgroundResource(R.drawable.unitedstatesmap_2);  //set map as background
    }


    /**
     * Call this to update route information
     * @param RouteSegments
     */
    public void setRouteSegments(Set<RouteSegment> RouteSegments){

        this.routeSegments = RouteSegments;
        invalidate();
    }

    private void drawLabels(Canvas canvas){
        Paint strokePaint = new Paint();
        strokePaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        //strokePaint.setARGB(255, 0, 0, 0);
        strokePaint.setColor(Color.BLACK);
        strokePaint.setTextAlign(Paint.Align.CENTER);
        strokePaint.setTextSize(30);
        strokePaint.setTypeface(Typeface.DEFAULT_BOLD);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(2);

        Paint textPaint = new Paint();
        //textPaint.setARGB(255, 255, 255, 255);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(30);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        textPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        for(City c : cities){
            canvas.drawText(c.getName(),c.getLabelX()/100 * getWidth(),c.getLabelY()/100*getHeight(),strokePaint);
            canvas.drawText(c.getName(),c.getLabelX()/100 * getWidth(),c.getLabelY()/100*getHeight(),textPaint);
        }
    }

    private int getPlayerColor(PLAYER_COLOR c){
        switch (c){
            case BLACK:
                return Color.BLACK;
            case GREEN:
                return ContextCompat.getColor(getContext(),R.color.colorGreen);
            case YELLOW:
                return ContextCompat.getColor(getContext(),R.color.colorYellow);
            case BLUE:
                return ContextCompat.getColor(getContext(), R.color.colorBlue);
            case RED:
                return ContextCompat.getColor(getContext(),R.color.colorRed);
        }
        return 0xFFFFFF;
    }

    private void drawRouteSegments(Canvas canvas){

        float width = getWidth();
        float height = getHeight();

        for(RouteSegment r : routeSegments){
            //translating from constraint to pixel value
            if(r.isClaimed()) {
                float x1 = r.getX1Constraint() / 100 * width;
                float y1 = r.getY1Constraint() / 100 * height;
                float x2 = r.getX2Constraint() / 100 * width;
                float y2 = r.getY2Constraint() / 100 * height;

                paint.setColor(getPlayerColor(r.getPlayer_color()));

                canvas.drawLine(x1, y1, x2, y2, paint);
            }
        }
    }

    private double getDistance(Pt pt1, Pt pt2){
        double dist = Math.pow(pt2.x - pt2.y,2) + Math.pow(pt2.y - pt1.y,2);
        dist = Math.sqrt(dist);
        return dist;
    }

    private void drawCities(Canvas canvas){
        float width = getWidth();
        float height = getHeight();

        for(City c : cities){
            //translating from constraint to pixel value
            float x = c.getxConstraint()/100 * width;
            float y = c.getyConstraint()/100 * height;


            paint.setColor(Color.BLACK);



            canvas.drawCircle(x,y,CITY_RADIUS,paint);
        }
    }

    private int getColor(RouteSegment r){
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
            case PINK:
                return ContextCompat.getColor(getContext(),R.color.colorPink);
            case BLUE:
                return ContextCompat.getColor(getContext(),R.color.colorBlue);

        }
        return 0;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        CITY_RADIUS = getWidth() * .009f;
//
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(getHeight()/100);
        paint.setTextSize(getWidth() *.01f);

        drawCities(canvas);
        if(tap){
            canvas.drawText("You touched the line",30f,30f,paint);
        }

        for(RouteSegment r : routeSegments) {
            Pt pt1 = new Pt(r.getX1Constraint()/100 * getWidth(),r.getY1Constraint()/100 *getHeight());
            Pt pt2 = new Pt(r.getX2Constraint()/100 * getWidth(),r.getY2Constraint()/100 *getHeight());

            float angle = 0;
            try {

                float slope = (pt2.y - pt1.y) / (pt2.x - pt1.x);
                angle = getAngle(slope);

            }catch (ArithmeticException e){

                angle = 90;
            }

            int color = getColor(r);


            drawAngledRectangle(pt2, pt1, angle, r.getLength(), r.isHighlighted(), color, canvas);
        }

        drawRouteSegments(canvas);

        paint.setColor(Color.LTGRAY);

        canvas.drawRect(0,0,125,100,paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(40);
        canvas.drawText("Chat",25,75,paint);

        drawLabels(canvas);

    }

    private float getAngle(float slope){
        return (float)Math.toDegrees(Math.atan(slope));
    }

    private void drawAngledRectangle(Pt start, Pt end,float angleDeg, int carCount, boolean isHighlighted,
            int color, Canvas canvas){
        double angle = Math.PI * angleDeg / 180;

        if(start.x > end.x){
            Pt temp = start;
            start = end;
            end = temp;
        }

        float y1 = start.y;
        float x1 = start.x;
        float y2 = end.y;
        float x2 = end.x;

        canvas.save();
        canvas.rotate(angleDeg);

        float newY1 =(float)( y1 * Math.cos(angle) -( x1 *Math.sin(angle)));
        float newX1 = (float)(y1*Math.sin(angle) + (x1*Math.cos(angle)));

        float newY2 = (float)(y2 * Math.cos(angle) -( x2 *Math.sin(angle)));
        float newX2 =(float)( y2*Math.sin(angle) + (x2*Math.cos(angle)));
        
        final float RECTANGLE_SCALER = Math.max(getHeight(),getWidth())  *.008f;

        paint.setColor(Color.BLACK);

        float currentX = Math.min(newX1,newX2);
        float xDist = newX2 -newX1;
        float spaceDist = (xDist * .1f) / (carCount +2);

        while (currentX < Math.max(newX2,newX1)){

            if(isHighlighted){
                paint.setColor(Color.parseColor("#fff602"));

            }else {
                paint.setColor(Color.BLACK);
            }

            canvas.drawRect(currentX,newY1+RECTANGLE_SCALER,currentX +( xDist /carCount) -(spaceDist*2) ,newY2 - RECTANGLE_SCALER ,paint);
            paint.setColor(color);
            canvas.drawRect(currentX + 5,newY1+RECTANGLE_SCALER -5,currentX +( (xDist) /carCount) -(2*spaceDist) -5 ,newY2 - RECTANGLE_SCALER +5 ,paint);

            currentX = currentX +( (newX2-newX1) /carCount)  + (spaceDist / 2);
        }
        canvas.restore();  //Important!!! rotates canvas back to original position

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        Pt tapPt = new Pt(event.getX(),event.getY());


        if(x <=150 && y <=100){
            IMapActivity activity = (IMapActivity) getContext();
            activity.onOpenChat();
            return  super.onTouchEvent(event);
        }

        for(City c : cities){
            Pt center = new Pt(c.getxConstraint()/100 * getWidth(),c.getyConstraint()/100 *getHeight());
            if(getDistance(center,tapPt) < CITY_RADIUS){  //if touching city do nothing
                return super.onTouchEvent(event);
            }
        }

        for(RouteSegment r : routeSegments) {
            Pt pt1 = new Pt(r.getX1Constraint()/100 * getWidth(),r.getY1Constraint()/100 *getHeight());
            Pt pt2 = new Pt(r.getX2Constraint()/100 * getWidth(),r.getY2Constraint()/100 *getHeight());

            if(isRouteSegment(pt1,pt2,tapPt) && !r.isClaimed()) {
                ((IMapActivity) getContext()).onClickRoute(r);
            }

//            if (isRouteSegment(pt1, pt2, tapPt)) {
//                tap = !tap;
//                invalidate();
//            }
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
    private boolean isRouteSegment(Pt pt1, Pt pt2, Pt tapPt){

        //final int THRESHOLD = 50;  // max distance from the line
        final int THRESHOLD =Math.max(getHeight(),getWidth()) / Math.min(getHeight(),getWidth()) *10;


        // using algorithm found here
        //https://en.wikipedia.org/wiki/Distance_from_a_point_to_a_line

        double numerator = ((pt2.y - pt1.y)*tapPt.x) - ((pt2.x - pt1.x)*tapPt.y) + (pt2.x * pt1.y) - (pt2.y * pt1.x);
        numerator = Math.abs(numerator);

        double denominator = (Math.pow(pt2.y -pt1.y,2) + Math.pow(pt2.x - pt1.x,2));
        denominator = Math.sqrt(denominator);

        double dist = numerator/denominator;

        if(dist > THRESHOLD){
            return false;
        }

        //Making sure we don't register a click off the end of the line segment
        float xMax = Math.max(pt1.x,pt2.x) + THRESHOLD;
        float xMin = Math.min(pt1.x,pt2.x) - THRESHOLD;
        float yMax = Math.max(pt1.y,pt2.y) + THRESHOLD;
        float yMin = Math.min(pt1.y,pt2.y) - THRESHOLD;

        return !(tapPt.x > xMax || tapPt.x < xMin || tapPt.y > yMax || tapPt.y < yMin);
    }


    /**
     * Only used for testing purposes
     */
    private void getCities(){
        InputStream is = getResources().openRawResource(R.raw.cities);


        ObjectMapper mapper = new ObjectMapper();
        try {
            this.cities = mapper.readValue(is, new TypeReference<Set<City>>() { });
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void getRoutes(){
        InputStream is = getResources().openRawResource(R.raw.routes);


        ObjectMapper mapper = new ObjectMapper();
        try {
            this.routeSegments = mapper.readValue(is, new TypeReference<Set<RouteSegment>>() { });
        } catch (IOException e) {
            e.printStackTrace();
        }


    }





}
