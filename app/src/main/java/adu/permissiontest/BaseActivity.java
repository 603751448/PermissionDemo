package adu.permissiontest;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：  PermissionTest
 * 包名：    adu.permissiontest
 * 文件名:   BaseActivity
 * 创建者:   dpc
 * 创建时间:  2016/12/27 22:44
 * 描述：    TODO
 */

public class BaseActivity extends AppCompatActivity {

    private static PermissionListener mListener;
    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }


    @Override protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    public static void requestRuntimePermission(String [] permissions,PermissionListener listener){
        Activity topActivity = ActivityCollector.getTopActivity();
        if(topActivity == null) {
            return;
        }
        mListener = listener;
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions){
            if (ContextCompat.checkSelfPermission(topActivity,permission) != PackageManager.PERMISSION_GRANTED){
                permissionList.add(permission);
            }
        }
        if(!permissionList.isEmpty()){
            ActivityCompat.requestPermissions(topActivity,permissionList.toArray(new String[permissionList.size()]),1);
        }else {
            mListener.onGranted();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){

            case 1:
                    if(grantResults.length > 0){
                        List<String> deniedpermissions = new ArrayList<>();
                        for(int i = 0;i < grantResults.length; i++) {
                            int grantResult = grantResults[i];
                            String permission = permissions[i];
                            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                                deniedpermissions.add(permission);
                            }
                        }
                        if(deniedpermissions.isEmpty()){
                            mListener.onGranted();
                        } else {
                            mListener.onDenied(deniedpermissions);
                        }
                    }
                break;
        }
    }
}
