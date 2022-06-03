package com.drivill.courier.activity.notificationActivity;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.databinding.ActivityNotoficationBinding;
import com.drivill.courier.model.activityModel.NotificationViewModel;

public class NotificationActivity extends BaseActivity{
    ActivityNotoficationBinding mBinding;
    NotificationViewModel mNotificationViewModel;

    void initUI() {
        mNotificationViewModel = new NotificationViewModel(this, mBinding);
        mBinding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

     /*   if (!mBasePreferenceManager.getIsRider())
            mNotificationViewModel.getNotification(mBasePreferenceManager.getUserToken());
        else mNotificationViewModel.getRiderNotification(mBasePreferenceManager.getUserToken())*/;

        if(!mBasePreferenceManager.getIsRider()){
            mNotificationViewModel.getNotification(mBasePreferenceManager.getUserToken());  // Merchant--
        }else {
            mNotificationViewModel.getRiderNotification(mBasePreferenceManager.getUserToken());  // Rider--
        }
    }

    @Override
    protected void setUp() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_notofication);
        initUI();
    }


}