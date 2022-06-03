package com.drivill.courier.model.activityModel;

import android.content.Context;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.drivill.courier.activity.notificationActivity.NotificationActivity;
import com.drivill.courier.databinding.ActivityNotoficationBinding;
import com.drivill.courier.activity.notificationActivity.adapter.NotificationAdapter;
import com.drivill.courier.merchantModul.model.MerchantNotificationModel;
import com.drivill.courier.rest.ApiManagerImp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationViewModel extends BaseObservable {
    Context mContext;
    ActivityNotoficationBinding mBinding;
    NotificationAdapter mAdapter;
    MerchantNotificationModel mMerchantNotificationModel;

    public NotificationViewModel(Context context, ActivityNotoficationBinding binding) {
        mContext = context;
        mBinding = binding;
    }

    public void getNotification(String token) {
        ((NotificationActivity) mContext).showLoading();
        Call<MerchantNotificationModel> call = new ApiManagerImp().getMerchantNotificationList(token);
        call.enqueue(new Callback<MerchantNotificationModel>() {
            @Override
            public void onResponse(Call<MerchantNotificationModel> call, Response<MerchantNotificationModel> response) {
                Log.i("res=", "getMerchantNotif=> "+ new Gson().toJson(response.body()));
                ((NotificationActivity) mContext).hideLoading();
                if (response.body() != null) {
                    mMerchantNotificationModel = response.body();
                    mAdapter = new NotificationAdapter(mContext, mMerchantNotificationModel,
                            new NotificationAdapter.NotificationAdapterClick() {
                                @Override
                                public void notificationAdapterClick(int pos) {
                                    readNotification(((NotificationActivity) mContext).mBasePreferenceManager.getUserToken(), pos);
                                }
                            });
                    mBinding.notificationRY.setLayoutManager(new LinearLayoutManager(mContext));
                    mBinding.notificationRY.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<MerchantNotificationModel> call, Throwable t) {
                Log.d("fel", ""+t.getMessage());
                ((NotificationActivity) mContext).hideLoading();
            }
        });
    }

    private void readNotification(String token, int pos) {

        Call<JsonObject> call = new ApiManagerImp().notificationUpdateMerchant(token, String.valueOf(mMerchantNotificationModel.getData().get(pos).getId()));
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.body() != null && mAdapter != null) {
                    mAdapter.statusUpdated(pos);
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("fel", "");
            }
        });
    }

    public void getRiderNotification(String token) {
        ((NotificationActivity) mContext).showLoading();
        Call<MerchantNotificationModel> call = new ApiManagerImp().getRiderNotificationList(token);
        call.enqueue(new Callback<MerchantNotificationModel>() {
            @Override
            public void onResponse(Call<MerchantNotificationModel> call, Response<MerchantNotificationModel> response) {
              //  Log.i("res=", "getRiderNotif=> "+new Gson().toJson(response.body()));
                ((NotificationActivity) mContext).hideLoading();
                if (response.body() != null) {
                    mMerchantNotificationModel = response.body();
                    mAdapter = new NotificationAdapter(mContext, mMerchantNotificationModel,
                            new NotificationAdapter.NotificationAdapterClick() {
                                @Override
                                public void notificationAdapterClick(int pos) {
                                    readRiderNotification(((NotificationActivity) mContext).mBasePreferenceManager.getUserToken(), pos);
                                }
                            });
                    mBinding.notificationRY.setLayoutManager(new LinearLayoutManager(mContext));
                    mBinding.notificationRY.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<MerchantNotificationModel> call, Throwable t) {
                Log.d("fel", "");
                ((NotificationActivity) mContext).hideLoading();
            }
        });
    }

    private void readRiderNotification(String token, int pos) {

        Call<JsonObject> call = new ApiManagerImp().notificationUpdateRider(token, String.valueOf(mMerchantNotificationModel.getData().get(pos).getId()));
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
              //  Log.i("res=", "readRiderNotif=> "+new Gson().toJson(response.body()));

                if (response.body() != null && mAdapter != null) {
                    mAdapter.statusUpdated(pos);
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("fel", "");

            }
        });
    }
}
