package com.drivill.courier.activity;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;

import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.databinding.ActivitySettingBinding;
import com.drivill.courier.merchantModul.activity.Activity_AddBankDetails;
import com.drivill.courier.merchantModul.model.SignUpDataModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.AppUtil;
import com.drivill.courier.utils.Constant;
import com.drivill.courier.utils.PrefsManager;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.drivill.courier.utils.Constant.LOCATION_PERMISSION;
import static com.drivill.courier.utils.Constant.NID_CAMERA_PERMISSION;

public class SettingActivity extends BaseActivity {

    ActivitySettingBinding mBinding;
    ArrayList<String> paymentMethod;

    boolean mIsDeny = false;

    private PrefsManager pf;

    private void initUI() {
        pf =  new PrefsManager(SettingActivity.this);

        mBinding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (mBasePreferenceManager.getEnableNotification()) {
            mBinding.switchNotification.setChecked(true);
            mBinding.enableNotificationTxt.setText(R.string.enable);
        }
        mBinding.switchNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mBinding.enableNotificationTxt.setText(R.string.enable);

                } else mBinding.enableNotificationTxt.setText(R.string.disable);

                mBasePreferenceManager.setEnableNotification(b);
            }
        });
        mBinding.switcCamAccess.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (AppUtil.checkPermissionForCamera(SettingActivity.this))
                        mBinding.enableCamTxt.setText(R.string.enable);
                    else {
                        AppUtil.requestPermission(SettingActivity.this,
                                Manifest.permission.CAMERA, Constant.NID_CAMERA_PERMISSION);
                    }


                } else {
                    if (mIsDeny) {
                        mIsDeny = false;
                    } else {
                        gotoAppSetting();
                    }

                }//mBinding.enableCamTxt.setText(R.string.disable);

                // mBasePreferenceManager.setEnableCamera(b);
            }
        });

        mBinding.switchLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (AppUtil.checkPermissionForLocation(SettingActivity.this)) {
                        mBinding.enableLocationTxt.setText(R.string.enable);
                    } else {

                        if (ActivityCompat.shouldShowRequestPermissionRationale(SettingActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)) {

                            try {
                                ActivityCompat.requestPermissions(SettingActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, Constant.LOCATION_PERMISSION);
                            } catch (Exception e) {
                                //     AppUtil.showAlert("Please allow permission for uploading file", activity);
                                e.printStackTrace();
                            }
                        } else {
                            ActivityCompat.requestPermissions(SettingActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, Constant.LOCATION_PERMISSION);
                        }
                    }


                } else {
                    if (mIsDeny) {
                        mIsDeny = false;
                    } else {
                        gotoAppSetting();
                    }

                }

                //mBinding.enableLocationTxt.setText(R.string.disable);

                // mBasePreferenceManager.setEnableLocation(b);
            }
        });

        if (!mBasePreferenceManager.getIsRider()) {
            mBinding.payMethodLL.setVisibility(View.VISIBLE);
            getSignUpData();
        }
        mBinding.addressTxt2.setText(mBasePreferenceManager.getAddress());
        mBinding.mobileTxt.setText(mBasePreferenceManager.get_emergencyNum());

        if(!pf.getIsRider())
            mBinding.AddBAnkDetails.setVisibility(View.VISIBLE);

        mBinding.AddBAnkDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, Activity_AddBankDetails.class));
                finish();
            }
        });

    }

    @Override
    protected void setUp() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_setting);
        initUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (AppUtil.checkPermissionForLocation(SettingActivity.this)) {
            mBinding.switchLocation.setChecked(true);
            mBinding.enableLocationTxt.setText(R.string.enable);
        }
           /* if (mBasePreferenceManager.getEnableLocation()) {

            }*/
        if (AppUtil.checkPermissionForCamera(SettingActivity.this)) {
            mBinding.switcCamAccess.setChecked(true);
            mBinding.enableCamTxt.setText(R.string.enable);
        }
         /*   if (mBasePreferenceManager.getEnableCamera()) {

            }*/
    }

    void getSignUpData() {
        showLoading();
        Call<SignUpDataModel> call = new ApiManagerImp().getSignUpDataMerchant();
        call.enqueue(new Callback<SignUpDataModel>() {
            @Override
            public void onResponse(@NotNull Call<SignUpDataModel> call, @NotNull Response<SignUpDataModel> response) {
                hideLoading();
                if (response.body() != null) {
                    paymentMethod = new ArrayList<>();
                    SignUpDataModel model = response.body();
                   /* Log.i("arp","paymethods= "+new Gson().toJson(model));
                    Log.i("arp","prf-paymethods= "+pf.getPaymentMethod());*/

                    String Paymethod = "" ;

                    for (SignUpDataModel.PaymentMethod pro : model.getPaymentMethod()) {
                        if(pro.getId() == Integer.parseInt(pf.getPaymentMethod())){
                            Paymethod =  pro.getName();
                        }
                    }
                    mBinding.paymethodTxt.setText(Paymethod);

                }
            }

            @Override
            public void onFailure(Call<SignUpDataModel> call, Throwable t) {
                hideLoading();
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            switch (requestCode) {
                case NID_CAMERA_PERMISSION:
                    mBinding.enableCamTxt.setText(R.string.enable);
                    mBinding.switcCamAccess.setChecked(true);

                    break;

                case LOCATION_PERMISSION:
                    mBinding.switchLocation.setChecked(true);
                    mBinding.enableLocationTxt.setText(R.string.enable);
                    break;


            }
        } else {
            mIsDeny = true;
            if (requestCode == NID_CAMERA_PERMISSION) {
                mBinding.switcCamAccess.setChecked(false);
                mBinding.enableCamTxt.setText(R.string.disable);
            } else if (requestCode == LOCATION_PERMISSION) {
                mBinding.switchLocation.setChecked(false);
                mBinding.enableLocationTxt.setText(R.string.disable);
            }
        }

    }

    void gotoAppSetting() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }
}