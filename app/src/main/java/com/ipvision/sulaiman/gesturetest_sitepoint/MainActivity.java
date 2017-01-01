package com.ipvision.sulaiman.gesturetest_sitepoint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private LinearLayout linearLayout;
    private TextView textView;
    private ImageView imageView;
    protected RelativeLayout.LayoutParams imageParams;

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

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(params);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);

        return true;
        // Return true if you have consumed the event, false if you haven't.
        // The default implementation always returns false.
    }


    class Android_Gesture_Detector implements GestureDetector.OnGestureListener,
            GestureDetector.OnDoubleTapListener {
        private float baseX, baseY;

        @Override
        public boolean onDown(MotionEvent e) {
            Log.d("onDown", textView.getX() + " " + textView.getY());
            Log.d("Gesture ", " onDown");
            baseX = imageView.getX();
            baseY = imageView.getY();
            imageView.setVisibility(View.VISIBLE);
            Log.d("base value in onDown ", baseX + " " + baseY);
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
                textView.setX(baseX + e2.getX() - e1.getX());
//                linearLayout.
            }
//            imageView.setY(baseY+e2.getY()-e1.getY());
//            if (textView.getVisibility()==View.GONE && e1.getX()-e2.getX()>0){
//                textView.setVisibility(View.VISIBLE);
//                textView.setX(baseX+e2.getX()-e1.getX());
//            }

            linearLayout.invalidate();
            imageView.invalidate();

            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d("Gesture", "onFling");
            if (e1.getX() < e2.getX()) {
                Log.d("Gesture ", "Left to Right swipe: " + e1.getX() + " - " + e2.getX());
                Log.d("Speed ", String.valueOf(velocityX) + " pixels/second");
//                textView.setVisibility(View.GONE);
            }
            if (e1.getX() > e2.getX()) {
                Log.d("Gesture ", "Right to Left swipe: " + e1.getX() + " - " + e2.getX());
                Log.d("Speed ", String.valueOf(velocityX) + " pixels/second");
                textView.setVisibility(View.VISIBLE);
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
