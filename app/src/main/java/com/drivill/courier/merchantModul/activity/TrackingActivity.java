package com.drivill.courier.merchantModul.activity;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.databinding.ActivityTrackingBinding;
import com.drivill.courier.merchantModul.fragment.TrackingFragment;
import com.drivill.courier.merchantModul.model.TrackingModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackingActivity extends BaseActivity {
    ActivityTrackingBinding binding;

    void initUI() {
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.buttonTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.truckIdET.getText().toString().isEmpty()) {
                    fetchTruckLocation(binding.truckIdET.getText().toString());
                } else {
                    showMessage("Enter Tracking ID");
                }
            }
        });
        String getId = getIntent().getStringExtra("getId");

        fetchTruckLocation(getId);

        //if ()
    }


    @Override
    protected void setUp() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tracking);
        initUI();
    }


    public void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void fetchTruckLocation(String num) {
        if(!num.startsWith("#")){
            num =  "#"+num;
        }
        Log.i("arp","TrackingID= "+num);
        showLoading();
        Call<TrackingModel> call = new ApiManagerImp().getShipmentTracking(mBasePreferenceManager.getUserToken(), num);
        call.enqueue(new Callback<TrackingModel>() {
            @Override
            public void onResponse(Call<TrackingModel> call, Response<TrackingModel> response) {
                hideLoading();
                TrackingModel res =  response.body();
                if (res != null && res.getData().getStatusLogs()!=null) {
                   Log.i("res=", new Gson().toJson(res));
                    TrackingModel model = response.body();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", (Serializable) model);
                    TrackingFragment fragment = new TrackingFragment();
                    fragment.setArguments(bundle);
                    switchFragment(fragment);

                } else {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        onError(object.getString("error"));
                        Log.i("res=", "error=>  "+object.toString());
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<TrackingModel> call, Throwable t) {
                Log.i("res", String.valueOf(t));
                hideLoading();
                onError(getString(R.string.try_again));
            }
        });

    }
}