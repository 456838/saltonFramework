package com.salton123.saltonframeworkdemo.test;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;
import com.salton123.base.BaseSupportActivity;
import com.salton123.base.FragmentDelegate;
import com.salton123.saltonframeworkdemo.R;
import com.salton123.saltonframeworkdemo.SaltonVideoView;
import com.salton123.saltonframeworkdemo.ui.fm.TestPopupComp;

import org.jetbrains.annotations.Nullable;

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/16 下午6:24
 * ModifyTime: 下午6:24
 * Description:
 */
public class DrawTestAty extends BaseSupportActivity {
    ImmersionBar mImmersionBar;
    private SaltonVideoView mVideoView;
    private boolean hasPermission;
    private static final int PERMISSIONS_REQUEST_EXTERNAL_STORAGE = 1;

    @Override
    public int getLayout() {
        return R.layout.draw_test;
    }

    @Override
    public void initVariable(@Nullable Bundle savedInstanceState) {
        mImmersionBar = ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR)
                .transparentBar().transparentNavigationBar();
        mImmersionBar.init();
        Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_LONG).show();
        // openActivity(CrashPanelAty.class, new Bundle());
        // new CoolToast(this).show();
        // CoolToast.displayToast(this);
        // CoolToast.displayToast(this, "hello", R.mipmap.ic_emoji,ScreenUtils.dip2px(this, 100));
        // CoolToast.displayToast(this, "有你便是晴天");
    }

    @Override
    public void initViewAndData() {
        FragmentDelegate.Companion.newInstance(TestPopupComp.class)
                .show(getSupportFragmentManager()
                        , "TestPopupComp");
        // Check permission.
        hasPermission = hasPermission();
        if (!hasPermission) {
            if (shouldShowRequestPermissionRationale()) {
                showPermissionRequestDialog(false);
            } else {
                requestPermission();
            }
        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                hasPermission = grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (!hasPermission) {
                    if (shouldShowRequestPermissionRationale()) {
                        showPermissionRequestDialog(false);
                    } else {
                        showPermissionRequestDialog(true);
                    }
                }
            }
        }
    }

    /**
     * Show a dialog for user to explain about the permission.
     */
    private void showPermissionRequestDialog(final boolean gotoSettings) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.permission_request)
                .setMessage(R.string.permission_explanation)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(gotoSettings ? R.string.go_to_settings : R.string.allow,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (gotoSettings) {
                                    startAppSettings();
                                } else {
                                    requestPermission();
                                }
                            }
                        })
                .show();
    }

    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }
}
