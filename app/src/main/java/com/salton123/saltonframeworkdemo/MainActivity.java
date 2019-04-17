package com.salton123.saltonframeworkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends FloatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_main);
        findViewById(R.id.tvOpen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
    }
}
