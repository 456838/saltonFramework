package com.salton123.saltonframeworkdemo.ui.fm;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.salton123.saltonframeworkdemo.R;

/**
 * User: newSalton@outlook.com
 * Date: 2019/4/16 11:22
 * ModifyTime: 11:22
 * Description:
 */
public class LifecycleTestComp extends Fragment {
    public static final String TAG = "life.LifecycleTestComp";

    @Override
    public void onAttach(Activity activity) {
        Log.i(TAG, "[onAttach]");
        super.onAttach(activity);
    }

    @Override
    public void onAttach(Context context) {
        Log.i(TAG, "[onAttach]");
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "[onCreate]");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.i(TAG, "[onStart]");
        super.onStart();
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
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "[onDestroy]");
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.comp_life_cycle, container);
    }

    // @Override
    // public int getLayout() {
    //     return R.layout.comp_life_cycle;
    // }
    //
    // @Override
    // public void initVariable(Bundle savedInstanceState) {
    //
    // }
    //
    // @Override
    // public void initViewAndData() {
    //
    // }
}
