package com.drivill.courier.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.drivill.courier.utils.Constant;
import com.drivill.courier.utils.DataManager;

public class NetworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean status = Constant.isConnectingToInternet(context);
        DataManager.getINSTANCE().setNetworkConnected(status);
        Log.d("isConnected", String.valueOf(status));
       /* if (!status) {
            View parentLayout =((BaseActivity)context).findViewById(android.R.id.content);
            Snackbar.make(parentLayout, "Internet Not Available", Snackbar.LENGTH_INDEFINITE)
                    .setAction("retry!", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    })
                    .setActionTextColor(((BaseActivity)context).getResources().getColor(android.R.color.holo_red_light ))
                    .show();
        }*/
    }
}
