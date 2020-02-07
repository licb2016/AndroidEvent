package com.lcb.imageloader;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class MyTextView extends TextView {

    public static final String TAG = "MyTextView";

    public MyTextView(Context context) {
        this(context,null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "dispatchTouchEvent ----------- MotionEvent.ACTION_DOWN  ------------");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "dispatchTouchEvent ----------- MotionEvent.ACTION_MOVE  ------------");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "dispatchTouchEvent ----------- MotionEvent.ACTION_UP  ------------");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "dispatchTouchEvent ----------- MotionEvent.ACTION_CANCEL  ------------");
                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(event);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        if (true) {
//            return false;
//            return super.onTouchEvent(event);
//        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent ----------- MotionEvent.ACTION_DOWN  ------------");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onTouchEvent ----------- MotionEvent.ACTION_MOVE  ------------");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent ----------- MotionEvent.ACTION_UP  ------------");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "onTouchEvent ----------- MotionEvent.ACTION_CANCEL  ------------");
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }




}
