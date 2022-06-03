package com.drivill.courier.model.activityModel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.databinding.FragmentHomeBinding;
import com.drivill.courier.model.model.CODStatementModel;
import com.drivill.courier.model.model.RiderPickupListModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiderDashboardViewModel extends ViewModel {
    public final MutableLiveData<Integer> validator = new MutableLiveData<>();
    public final MutableLiveData<RiderPickupListModel> modelMutableLiveData = new MutableLiveData<>();
    public final MutableLiveData<CODStatementModel> codStatementModelRes = new MutableLiveData<>();

    String pickup = "pickup";
    String deliver = "deliver";
    public String totalShipmentSize = "0", totalDelivery = "0", totalcmpPick = "0", totalcmpDelivery = "0";

    public void getList(Context context, String lat, String lon, String status, FragmentHomeBinding binding) {
        Call<RiderPickupListModel> call = new ApiManagerImp().riderShipmentsList(((BaseActivity) context).mBasePreferenceManager.getUserToken(), lat, status, lon);
        call.enqueue(new Callback<RiderPickupListModel>() {
            @Override
            public void onResponse(Call<RiderPickupListModel> call, Response<RiderPickupListModel> response) {
                if (binding != null)
                    binding.swipeRefreshLayout.setRefreshing(false);
                if (response.body() != null) {
                    RiderPickupListModel model = response.body();
                   // Log.i("arp", "list= "+new Gson().toJson(model));
                    totalShipmentSize = String.valueOf(model.getShipments().size());
                    modelMutableLiveData.setValue(model);

                } else {

                    try {
                        JSONObject errObj = new JSONObject(response.errorBody().string());
                        Log.d("", "");
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<RiderPickupListModel> call, Throwable t) {
                Log.d("", "");
                ((BaseActivity) context).onError(context.getString(R.string.try_again));
            }
        });
    }

}
