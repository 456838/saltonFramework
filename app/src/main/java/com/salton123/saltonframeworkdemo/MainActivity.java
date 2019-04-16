package com.salton123.saltonframeworkdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    public static final String TAG ="life.MainActivity";
    //

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"[onCreate]");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_main);
    }

    @Override
    public void onStart() {
        Log.i(TAG,"[onStart]");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i(TAG,"[onResume]");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i(TAG,"[onPause]");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG,"[onStop]");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"[onDestroy]");
        super.onDestroy();
    }
}
