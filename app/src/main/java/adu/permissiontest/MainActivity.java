package adu.permissiontest;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.List;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.btn);
    }
    public void btnClick(View view){
        Toast.makeText(MainActivity.this,"同2222",Toast.LENGTH_SHORT).show();
        requestRuntimePermission(new String[] { Manifest.permission.CAMERA,Manifest.permission.CALL_PHONE}, new PermissionListener() {
            @Override public void onGranted() {
                Toast.makeText(MainActivity.this,"同意了所有权限",Toast.LENGTH_SHORT).show();
            }


            @Override public void onDenied(List<String> deniedPermission) {
                for (String permission : deniedPermission){

                    Toast.makeText(MainActivity.this,"被拒绝权限："+permission,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
