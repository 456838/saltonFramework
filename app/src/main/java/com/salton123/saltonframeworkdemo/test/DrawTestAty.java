package com.salton123.saltonframeworkdemo.test;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;
import com.salton123.base.BaseActivity;
import com.salton123.base.feature.ImmersionFeature;
import com.salton123.base.feature.PermissionFeature;
import com.salton123.saltonframeworkdemo.R;
import com.salton123.saltonframeworkdemo.SaltonVideoView;
import com.salton123.saltonframeworkdemo.ui.fm.TestPopupComp;
import com.salton123.util.FragmentUtils;

import org.jetbrains.annotations.Nullable;

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/16 下午6:24
 * ModifyTime: 下午6:24
 * Description:
 */
public class DrawTestAty extends BaseActivity {

    private SaltonVideoView mVideoView;

    private static final int PERMISSIONS_REQUEST_EXTERNAL_STORAGE = 1;

    @Override
    public int getLayout() {
        return R.layout.draw_test;
    }

    @Override
    public void initVariable(@Nullable Bundle savedInstanceState) {
        addFeature(new ImmersionFeature(this) {
            @Override
            public ImmersionBar getImmersionBar() {
                return super.getImmersionBar()
                        .statusBarDarkFont(true)
                        .hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR)
                        .transparentBar().transparentNavigationBar();
            }
        });
        addFeature(new PermissionFeature(this) {
            @Override
            public String[] getPermissionArr() {
                return new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.RECORD_AUDIO
                };
            }
        });
        Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_LONG).show();
        // openActivity(CrashPanelAty.class, new Bundle());
        // new CoolToast(this).show();
        // CoolToast.displayToast(this);
        // CoolToast.displayToast(this, "hello", R.mipmap.ic_emoji,ScreenUtils.dip2px(this, 100));
        // CoolToast.displayToast(this, "有你便是晴天");
    }

    @Override
    public void initViewAndData() {
        FragmentUtils.newInstance(TestPopupComp.class)
                .show(getSupportFragmentManager()
                        , "TestPopupComp");
    }

    private boolean hasPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }

    private boolean shouldShowRequestPermissionRationale() {
        return ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                PERMISSIONS_REQUEST_EXTERNAL_STORAGE);
    }

    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }
}
