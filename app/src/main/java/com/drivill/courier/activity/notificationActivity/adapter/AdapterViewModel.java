package com.drivill.courier.activity.notificationActivity.adapter;

import android.content.Context;

import com.drivill.courier.merchantModul.model.MerchantNotificationModel;

public class AdapterViewModel {
    Context mContext;
    MerchantNotificationModel.Datum merchantNotificationModel;

    public AdapterViewModel(Context context, MerchantNotificationModel.Datum model) {
        this.mContext = context;
        this.merchantNotificationModel = model;
    }


    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public MerchantNotificationModel.Datum getMerchantNotificationModel() {
        return merchantNotificationModel;
    }

    public void setMerchantNotificationModel(MerchantNotificationModel.Datum merchantNotificationModel) {
        this.merchantNotificationModel = merchantNotificationModel;
    }
}
