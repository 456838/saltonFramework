package com.salton123.base.feature;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * User: newSalton@outlook.com
 * Date: 2018/12/25 4:59 PM
 * ModifyTime: 4:59 PM
 * Description:
 */
@SuppressLint("ValidFragment")
public class PermissionFeature extends Fragment implements IFeature {

    private AppCompatActivity mActivity;

    public PermissionFeature(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    private int REQUEST_CODE = 0x101;
    private String[] permissions = getPermissionArr();

    @Override
    public void onBind() {
        mActivity.getSupportFragmentManager()
                .beginTransaction()
                .add(this, "PermissionFeature")
                .commit();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!isPermissionGrant(mActivity)) {
            if (Build.VERSION.SDK_INT >= 23) {
                requestPermissions(permissions, REQUEST_CODE);
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

    }

    private boolean isPermissionGrant(Context context) {
        boolean result = false;
        for (String item : permissions) {
            result &= context.checkPermission(item, Process.myPid(),
                    Process.myUid()) == PackageManager.PERMISSION_GRANTED;
        }
        return result;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == this.REQUEST_CODE) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(mActivity,
                        "请授予全部权限",
                        Toast.LENGTH_LONG).show();
                mActivity.finish();
            }
        }
    }
}
