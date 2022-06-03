package com.drivill.courier.activity;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.databinding.ActivityLetsStartBinding;
import com.drivill.courier.utils.MyAnimation;
import com.drivill.courier.utils.MyUtil;

import java.util.ArrayList;
import java.util.List;

import static com.drivill.courier.utils.Constant.MULTIPLE_PERMISSIONS;

public class LetsStartActivity extends BaseActivity {

    ActivityLetsStartBinding mBinding;

    //Multi permission request array
    String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };


    void initUI() {

        mBinding.menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mBinding.menuBtn.setEnabled(false);

            }
        });
        mBinding.riderImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermissions()) {
                    mBasePreferenceManager.setIsRider(true);
                    Intent intent = new Intent(LetsStartActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

            }
        });
        mBinding.marchImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermissions()) {
                    mBasePreferenceManager.setIsRider(false);
                    Intent intent = new Intent(LetsStartActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void setUp() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyAnimation.noAnimation(this);
        MyUtil.getFullScreen(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_lets_start);
        initUI();
        checkPermissions();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean allDone=true;

        if (requestCode == MULTIPLE_PERMISSIONS) {
            if (grantResults.length > 0) {
                String permissionsDenied = "";
                for (String per : permissions) {
                    if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                        permissionsDenied += "\n" + per;
                        allDone=false;
                        Toast.makeText(LetsStartActivity.this, "Permission Required", Toast.LENGTH_SHORT).show();
                        break;
                    }

                }
                // Show permissionsDenied
                if (allDone){

                }

            }
        }
    }

    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {

            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

}