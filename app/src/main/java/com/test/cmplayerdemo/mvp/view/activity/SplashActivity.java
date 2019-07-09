package com.test.cmplayerdemo.mvp.view.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.test.cmplayerdemo.R;
import com.test.cmplayerdemo.base.CMBaseActivity;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class SplashActivity extends CMBaseActivity implements EasyPermissions.PermissionCallbacks{

    private static int RC_CAMERA_AND_RECORD_AUDIO = 0x001;
    private static int mColor = Color.BLUE;

    private Button mainStartMain;

    @Override
    protected void showToast(String msg) {

    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_splash);

    }

    @Override
    protected void initView() {
        mainStartMain = (Button) findViewById(R.id.enter_button);
        mainStartMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissions();
            }
        });
    }

    private void startMainActivity() {
        startActivity(new Intent(this,HomeActivity.class));

        this.finish();
    }

    private void requestPermissions(){
        String[] perms = {Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO};
        //判断权限
        if(EasyPermissions.hasPermissions(this,perms)){
            //如果有权限，就继续
            startMainActivity();
        }else{
            //如果没有权限，就去申请权限
            EasyPermissions.requestPermissions(this,"我们要你的权限",RC_CAMERA_AND_RECORD_AUDIO,perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /**
     * @info 申请权限成功
     * @param requestCode
     * @param list
     */
    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        // Some permissions have been granted
        // ...
        if(requestCode == RC_CAMERA_AND_RECORD_AUDIO)
        startMainActivity();
    }

    /**
     * @info 申请权限拒绝
     * @param requestCode
     * @param list
     */
    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        // Some permissions have been denied
        // ...
        if(EasyPermissions.somePermissionPermanentlyDenied(this,list)){
            new AppSettingsDialog.Builder(this)
                    .setTitle("您已经拒绝权限")
                    .setRationale("如果您不打开，无法使用")
                    .setRequestCode(10001)
                    .build()
                    .show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 10001){
            Toast.makeText(this,"从权限设置页面跳转回来",Toast.LENGTH_LONG).show();;
        }
    }
}
