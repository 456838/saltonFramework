package com.salton123.saltonframeworkdemo;

import android.os.Bundle;
import android.os.Debug;
import android.view.View;

import com.salton123.feature.PermissionFeature;
import com.salton123.log.XLog;
import com.salton123.ui.base.BaseActivity;
import com.salton123.ui.business.TitleActivity;

import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends TitleActivity {

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
        XLog.i(this, "titlebar one:" + getTitleBar());
        findViewById(R.id.tvHello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // throw new RuntimeException("hello");
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
        XLog.i(this, "titlebar:" + getTitleBar());
        //Debug.stopMethodTracing();
    }

    private String createFile() {
        DateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
        String time = formatter.format(new Date());
        return "crash_" + time + ".txt";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XLog.i(this, "titlebar two:" + getTitleBar());
    }
}
