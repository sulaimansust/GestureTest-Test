package com.ipvision.sulaiman.gesturetest_sitepoint;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private LinearLayout linearLayout;
    private TextView textView;
    private ImageView imageView;
    protected LinearLayout.LayoutParams params ;

    private float initialX, initialY;

    //Code for gestuyres

    private GestureDetector gestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = (LinearLayout) findViewById(R.id.linear_layout);
        textView = (TextView) findViewById(R.id.text_view);
        imageView = (ImageView) findViewById(R.id.image_view);

        initialX = imageView.getX();
        initialY = imageView.getY();

        Android_Gesture_Detector androidGestureDetector = new Android_Gesture_Detector();
        gestureDetector = new GestureDetector(this, androidGestureDetector);

//        imageView.setLayoutParams(params);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);

        return true;
        // Return true if you have consumed the event, false if you haven't.
        // The default implementation always returns false.
    }

    public int getDisplayWidth(){
        Display display = getWindowManager().getDefaultDisplay();
        return display.getWidth();
    }
    public int getDisplayHeight(){
        Display display = getWindowManager().getDefaultDisplay();
        return display.getHeight();
    }

    public void startMoveLayoutAnimation(float startPosition, float endPosition){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(startPosition,endPosition);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //3
                float value = (float) animation.getAnimatedValue();
                //4
                linearLayout.setTranslationX(value);
            }
        });

//5
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(500);
//6
        valueAnimator.start();
    }

    class Android_Gesture_Detector implements GestureDetector.OnGestureListener,
            GestureDetector.OnDoubleTapListener {
        private float baseX, baseY;
        int left=0;

        @Override
        public boolean onDown(MotionEvent e) {
            Log.d("Gesture ", " onDown");
            baseX = linearLayout.getX();
            baseY = linearLayout.getY();
            Log.d("base value in onDown ", baseX + " " + baseY);
//            startMoveLayoutAnimation();
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.d("Gesture ", " onSingleTapConfirmed");
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.d("Gesture ", " onSingleTapUp");
            Log.d("Linear",linearLayout.getX()+" ");
            return true;
        }



        @Override
        public void onShowPress(MotionEvent e) {
            Log.d("Gesture ", " onShowPress");
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.d("Gesture ", " onDoubleTap");
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.d("Gesture ", " onDoubleTapEvent");
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.d("Gesture ", " onLongPress");
        }


        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d("onScroll", " " + textView.getX() + " " + textView.getY());

            Log.d("Gesture ", " onScroll e1 = " + e1.getX() + " " + e1.getY());
            Log.d("Gesture ", "onScroll e2 = " + e2.getX() + " " + e2.getY());

            if (e1.getY() < e2.getY()) {
                Log.d("Gesture ", " Scroll Down");
            }
            if (e1.getY() > e2.getY()) {
                Log.d("Gesture ", " Scroll Up");
            }

            if (e2.getX() > e1.getX()) {
                float updatedX = baseX+(e2.getX()-e1.getX());
                linearLayout.setX(updatedX);
            }
            else {
                if (linearLayout.getVisibility() == View.GONE)
                    linearLayout.setVisibility(View.VISIBLE);

                float updatedX = baseX+(e2.getX()-e1.getX());

                if (updatedX>=0)
                    linearLayout.setX(updatedX);
            }


//            imageView.setY(baseY+e2.getY()-e1.getY());
//            if (textView.getVisibility()==View.GONE && e1.getX()-e2.getX()>0){
//                textView.setVisibility(View.VISIBLE);
//                textView.setX(baseX+e2.getX()-e1.getX());
//            }



            Log.d("Linear"," "+linearLayout.getX());
            linearLayout.invalidate();
//            imageView.invalidate();

            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d("Gesture", "onFling");
            if (e1.getX() < e2.getX()) {
                Log.d("Gesture ", "Left to Right swipe: " + e1.getX() + " - " + e2.getX());
                Log.d("Speed ", String.valueOf(velocityX) + " pixels/second");
//                textView.setVisibility(View.GONE);
                startMoveLayoutAnimation(e2.getX(),getDisplayWidth()+20);
//                linearLayout.setVisibility(View.GONE);
            }
            if (e1.getX() > e2.getX()) {
                Log.d("Gesture ", "Right to Left swipe: " + e1.getX() + " - " + e2.getX());
                Log.d("Speed ", String.valueOf(velocityX) + " pixels/second");
                startMoveLayoutAnimation(linearLayout.getX(),0);
            }
            if (e1.getY() < e2.getY()) {
                Log.d("Gesture ", "Up to Down swipe: " + e1.getX() + " - " + e2.getX());
                Log.d("Speed ", String.valueOf(velocityY) + " pixels/second");
            }
            if (e1.getY() > e2.getY()) {
                Log.d("Gesture ", "Down to Up swipe: " + e1.getX() + " - " + e2.getX());
                Log.d("Speed ", String.valueOf(velocityY) + " pixels/second");
            }
            return true;

        }
    }
}
