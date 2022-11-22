package com.drivill.courier.activity;

import static com.drivill.courier.utils.Constant.MULTIPLE_PERMISSIONS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.drivill.courier.BaseActivity;
import com.drivill.courier.databinding.ActivityStartBinding;
import com.drivill.courier.utils.MyAnimation;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends BaseActivity {
    ActivityStartBinding binding;

    String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    @Override
    protected void setUp() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        checkPermissions();
        MyAnimation.startAnimTopToBottom(this, binding.ivLogo);
        MyAnimation.startAnimTopToBottom(this, binding.layoutMerchant);
        MyAnimation.startAnimTopToBottom(this, binding.layoutRider);
        MyAnimation.startAnimTopToBottom(this, binding.layoutCustomer);


        binding.layoutRider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermissions()) {
                    mBasePreferenceManager.setIsRider(true);
                    Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

            }
        });
        binding.layoutMerchant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermissions()) {
                    mBasePreferenceManager.setIsRider(false);
                    Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        binding.layoutCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermissions()) {
                    Toast.makeText(StartActivity.this,"Coming Soon",Toast.LENGTH_SHORT).show();
                }
            }
        });
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
                        Toast.makeText(StartActivity.this, "Permission Required", Toast.LENGTH_SHORT).show();
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