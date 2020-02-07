package com.lcb.imageloader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv).setOnClickListener(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
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
        return super.dispatchTouchEvent(ev);
    }


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
    public void onClick(View v) {
        Log.d(TAG, "----------- onClick  ------------");

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouch ----------- MotionEvent.ACTION_DOWN  ------------");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onTouch ----------- MotionEvent.ACTION_MOVE  ------------");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouch ----------- MotionEvent.ACTION_UP  ------------");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "onTouch ----------- MotionEvent.ACTION_CANCEL  ------------");
                break;
            default:
                break;
        }
        return false;
    }


}
