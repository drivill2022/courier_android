package com.drivill.courier.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.adapter.DashBoardAdapter;
import com.drivill.courier.databinding.ActivityOnBoardingBinding;
import com.drivill.courier.merchantModul.model.ShipmentDetailModel;
import com.drivill.courier.model.model.SplashModelItem;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnBoardingActivity extends BaseActivity {
    ActivityOnBoardingBinding binding;
    DashBoardAdapter adapter;

    @Override
    protected void setUp() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOnBoardingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getShipmentDetail();
        /*adapter=new DashBoardAdapter(this, Utils.INSTANCE.getPagerData());
        binding.viewPager.setAdapter(adapter);*/

    }


    void getShipmentDetail() {
        showLoading();
        Call<ArrayList<SplashModelItem>> call = new ApiManagerImp().getSplashItem();
        call.enqueue(new Callback<ArrayList<SplashModelItem>>() {
            @Override
            public void onResponse(Call<ArrayList<SplashModelItem>> call, Response<ArrayList<SplashModelItem>> response) {
                Log.d("res", String.valueOf(response));
                hideLoading();
                if (response.body() != null) {
                    ArrayList<SplashModelItem> model = response.body();
                    adapter=new DashBoardAdapter(OnBoardingActivity.this, model);
                    binding.viewPager.setAdapter(adapter);

                } else {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        onError(object.getString("error"));
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SplashModelItem>> call, Throwable t) {
                Log.d("res", String.valueOf(t));
                hideLoading();
                onError(getString(R.string.try_again));
            }
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}