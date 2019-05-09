package com.salton123.saltonframeworkdemo;

import android.os.Bundle;
import android.view.View;

import com.salton123.base.BaseSupportActivity;
import com.salton123.base.feature.PermissionFeature;
import com.salton123.log.XLog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends BaseSupportActivity {

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initVariable(Bundle savedInstanceState) {
        addFeature(new PermissionFeature(this));
    }

    @Override
    public void initViewAndData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                XLog.i(this,"helllo");
            }
        }).start();
        findViewById(R.id.tvHello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                throw new RuntimeException("hello");
                // StringBuilder stringBuilder = new StringBuilder();
                // stringBuilder.append("hello\n");
                // String path = new File(Environment.getExternalStorageDirectory(), "salton").getPath()
                //         + File.separator + BaseApplication.getInstance().getPackageName();
                // String crashPath = path + File.separator + createFile();
                // FlushWriter flush = new FlushWriter(path + File.separator + "crash_buf",
                //         8192 * 4,
                //         crashPath
                //         , false
                // );
                // flush.changeLogPath(crashPath);
                // flush.write(stringBuilder.toString());
                // flush.flushAsync();
                // flush.release();
            }
        });

    }

    private String createFile() {
        DateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
        String time = formatter.format(new Date());
        return "crash_" + time + ".txt";
    }
}