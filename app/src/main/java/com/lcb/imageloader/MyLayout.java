package com.lcb.imageloader;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

@SuppressLint("AppCompatCustomView")
public class MyLayout extends RelativeLayout {

    public static final String TAG = "MyLayout";

    public MyLayout(Context context) {
        this(context,null);
    }

    public MyLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
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

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onInterceptTouchEvent ----------- MotionEvent.ACTION_DOWN  ------------");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onInterceptTouchEvent ----------- MotionEvent.ACTION_MOVE  ------------");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onInterceptTouchEvent ----------- MotionEvent.ACTION_UP  ------------");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "onInterceptTouchEvent ----------- MotionEvent.ACTION_CANCEL  ------------");
                break;
            default:
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        Log.d(TAG, "requestDisallowInterceptTouchEvent ----------"+disallowIntercept);
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }
}
