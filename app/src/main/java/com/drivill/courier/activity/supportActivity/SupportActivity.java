package com.drivill.courier.activity.supportActivity;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.databinding.ActivitySupportBinding;
import com.drivill.courier.model.model.SupportModel;

import java.security.spec.ECField;

public class SupportActivity extends BaseActivity {

    ActivitySupportBinding mBinding;
    SupportViewModel mSupportViewModel;
    SupportModel mModelData;

    private void initUI() {
        showLoading();
        mSupportViewModel = new ViewModelProvider(this).get(SupportViewModel.class);
        mSupportViewModel.responseObserver.observe(this, responseObserver);
        mBinding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mBinding.termsRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mModelData != null && mModelData.getTermsOfUse() != null)
                    new SupportBottomSheetDialog(SupportActivity.this,
                            mModelData.getTermsOfUse().getTitle(),
                            mModelData.getTermsOfUse().getContent()).show();
            }
        });
        mBinding.privacyRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mModelData != null && mModelData.getPrivacyPolicy() != null)
                    new SupportBottomSheetDialog(SupportActivity.this,
                            mModelData.getPrivacyPolicy().getTitle(),
                            mModelData.getPrivacyPolicy().getContent()).show();
            }
        });

         mBinding.imgFb.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 try {
                     Intent browserIntent = new Intent(FB_Intent(getApplicationContext().getPackageManager(),getString(R.string.FBPage_url)));
                     startActivity(browserIntent);
                 }catch (Exception e){
                     Log.i("tag**","fbintent*** " + e.toString());
                     startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.FBPage_url))));
                 }

             }
         });

        mBinding.imgInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Uri uri = Uri.parse(getString(R.string.InstaPage_url));
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.instagram.android");
                    startActivity(intent);
                } catch (Exception e) {
                    Log.i("tag**","Instaintent*** " + e.toString());
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(getString(R.string.InstaPage_url))));
                }
            }
        });

        mBinding.imgLinkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.LinkedInPage_url)));
                    intent.setPackage("com.linkedin.android");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } catch (Exception e) {
                    Log.i("tag**","lkdintent*** " + e.toString());
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.LinkedInPage_url))));
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
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_support);
        initUI();

    }

    private Observer<SupportModel> responseObserver = new Observer<SupportModel>() {
        @Override
        public void onChanged(SupportModel model) {
            hideLoading();
            mModelData = model;
            mBinding.mobileTxt.setText(model.getContactNumber());
            mBinding.mailTxt.setText(model.getContactEmail());
        }
    };

    private Intent FB_Intent(PackageManager pm, String url) {
        Uri uri = Uri.parse(url);
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationInfo.enabled) {
                // http://stackoverflow.com/a/24547437/1048340
                uri = Uri.parse("fb://facewebmodal/f?href=" + url);
            }
        } catch (Exception e) {
            Log.i("tag**","fbintent*** " + e.toString());
        }
        return new Intent(Intent.ACTION_VIEW, uri);
    }

}