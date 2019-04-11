package com.salton123.base.feature;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * User: newSalton@outlook.com
 * Date: 2018/12/25 4:59 PM
 * ModifyTime: 4:59 PM
 * Description:
 */

public class PermissionFeature implements IFeature {

    private String[] permissions = getPermissionArr();
    private AppCompatActivity mActivity;
    private RxPermissions mPermissions;

    public PermissionFeature(AppCompatActivity activity) {
        this.mActivity = activity;

    }

    @SuppressLint("CheckResult")
    @Override
    public void onBind() {
        mPermissions = new RxPermissions(mActivity);
        if (!isPermissionGrant(mActivity)) {
            if (Build.VERSION.SDK_INT >= 23) {
                mPermissions.requestEachCombined(permissions)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Permission>() {
                            @Override
                            public void accept(Permission permission) throws Exception {
                                if (!permission.granted) {
                                    Toast.makeText(mActivity,
                                            "请授予全部权限",
                                            Toast.LENGTH_LONG).show();
                                    mActivity.finish();
                                }
                            }
                        });
            }
        }
    }

    public String[] getPermissionArr() {
        return new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE
        };
    }

    @Override
    public void onUnBind() {
        if (mPermissions != null) {
            mPermissions = null;
        }
    }

    private boolean isPermissionGrant(Context context) {
        boolean result = true;
        for (String item : permissions) {
            result &= context.checkPermission(item, Process.myPid(),
                    Process.myUid()) == PackageManager.PERMISSION_GRANTED;
        }
        return result;
    }

}
