package com.salton123.saltonframeworkdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.imuxuan.floatingview.FloatingView;
import com.salton123.saltonframeworkdemo.floatingview.FloatingViewManager;

public class FloatActivity extends AppCompatActivity {
    public static final String TAG = "life.FloatActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "[onCreate]");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.i(TAG, "[onStart]");
        super.onStart();
        FloatingViewManager.INSTANCE.attach(this);
    }

    @Override
    public void onResume() {
        Log.i(TAG, "[onResume]");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i(TAG, "[onPause]");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG, "[onStop]");
        super.onStop();
        FloatingViewManager.INSTANCE.detach(this);
        FloatingView.get().detach(this);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "[onDestroy]");
        super.onDestroy();
    }
}
