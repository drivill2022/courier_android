package com.drivill.courier.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.drivill.courier.R;
import com.drivill.courier.model.model.CODStatementModel;
import com.drivill.courier.model.model.RiderPickupListModel;

public class DataManager {

    private static final DataManager INSTANCE = new DataManager();
    public static DataManager getINSTANCE() {
        return INSTANCE;
    }


    public DataManager() {

    }


    private final MutableLiveData<RiderPickupListModel> pickupResponse = new MutableLiveData<>();
    private final MutableLiveData<RiderPickupListModel> deliveryResponse = new MutableLiveData<>();
    private final MutableLiveData<RiderPickupListModel> deliverCompleteResponse = new MutableLiveData<>();
    private final MutableLiveData<CODStatementModel> deliveredData = new MutableLiveData<>();


    private boolean isNetworkConnected = false;
    public static String isDeletePos = "";
    public static String from = "";
    private int currentItemListSize = 0;

    Dialog dialog;


    public boolean isNetworkConnected(Context context) {
        if (!isNetworkConnected)
            Toast.makeText(context, context.getResources().getString(R.string.check_internet), Toast.LENGTH_SHORT).show();


        return isNetworkConnected;
    }

    public void setNetworkConnected(boolean networkConnected) {
        isNetworkConnected = networkConnected;
    }

    public void showProgressBar(Context activity) {
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_progress_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

    }

    public void hideProgressBar() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }


    public int getCurrentItemListSize() {
        return currentItemListSize;
    }

    public void setCurrentItemListSize(int currentItemListSize) {
        this.currentItemListSize = currentItemListSize;
    }

    public MutableLiveData<RiderPickupListModel>    getPickupResponse() {
        return pickupResponse;
    }

    public MutableLiveData<RiderPickupListModel> getDeliveryResponse() {
        return deliveryResponse;
    }

    public MutableLiveData<RiderPickupListModel> getDeliverCompleteResponse() {
        return deliverCompleteResponse;
    }

    public MutableLiveData<CODStatementModel> getDeliveredData() {
        return deliveredData;
    }
}

