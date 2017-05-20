package com.group4.tickettoride.Game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.group4.tickettoride.R;

/**
 * Created by Andrew Gill on 5/20/2017.
 */

public class GameMapView extends View {
    Paint paint = new Paint(); // holds styling information for what gets drawn on the canvas
    boolean tap = false;  //if true display text

    public GameMapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBackgroundResource(R.drawable.paperbackground);
    }

    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        canvas.drawRect(300, 300, 80, 80, paint);

        if(tap){
            paint.setTextSize(45f);
            canvas.drawText("hi",400f,400f,paint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        if(x >= 80 && x <=380){  //checking if you touch the square
            if(y >=80 && y <=380){
                tap = !tap;
                invalidate();  //redraws canvas
            }
        }


        return super.onTouchEvent(event);
    }


}
