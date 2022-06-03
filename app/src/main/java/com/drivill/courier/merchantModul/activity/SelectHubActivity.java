package com.drivill.courier.merchantModul.activity;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.databinding.ActivitySelectHubBinding;
import com.drivill.courier.merchantModul.adapter.SendToAdapter;
import com.drivill.courier.merchantModul.model.NearByHubModel;
import com.drivill.courier.merchantModul.model.ShipmentModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectHubActivity extends BaseActivity implements SendToAdapter.SelectedHubAdapter {

    ActivitySelectHubBinding mBinding;
    SendToAdapter mAdapter;
    ShipmentModel mModel;
    String mHubId = "";

    ArrayList<ShipmentModel> arrayList;

    void initUI() {
        mAdapter = new SendToAdapter(SelectHubActivity.this, (SendToAdapter.SelectedHubAdapter) this);
        mBinding.recyclerPickRequestItem.setLayoutManager(new LinearLayoutManager(SelectHubActivity.this));
        mBinding.recyclerPickRequestItem.setAdapter(mAdapter);
        mBinding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mBinding.pickRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mHubId.isEmpty())
                    sendToPickup(mHubId, mBinding.noteET.getText().toString());
            }
        });

    }

    @Override
    protected void setUp() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_select_hub);
        mModel = (ShipmentModel) getIntent().getSerializableExtra("data");
        arrayList = (ArrayList<ShipmentModel>) getIntent().getSerializableExtra("dataArray");
        initUI();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mModel != null)
                    getNearByHubList(mModel.getS_latitude(), mModel.getS_longitude());
                else getNearByHubList(arrayList.get(0).getS_latitude(),arrayList.get(0).getS_longitude());

            }
        });
    }
//Near by hub based on pickup Location ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    void getNearByHubList(String lat, String lon) {
        showLoading();
        Call<NearByHubModel> call = new ApiManagerImp().getNearByHub(mBasePreferenceManager.getUserToken(), lat, lon);
        call.enqueue(new Callback<NearByHubModel>() {
            @Override
            public void onResponse(Call<NearByHubModel> call, Response<NearByHubModel> response) {
                Log.d("", "");
                hideLoading();
                if (response.body() != null) {
                  //  Log.i("res=>", "getNearByHubList=>"+new Gson().toJson(response.body()));
                    NearByHubModel model = response.body();
                    mAdapter.setData(model);


//////////////////////// for top near hub
                    mBinding.locationTxt.setText(model.getData().get(0).getAddress());
                    mBinding.mobileNumTxt.setText(model.getData().get(0).getPhone());
                    mBinding.itemNameTxt.setText(model.getData().get(0).getName());
                    mBinding.kmTxt.setText(model.getData().get(0).getDistance().toString() + " km");
                    mHubId = String.valueOf(model.getData().get(0).getId());
                    Glide.with(SelectHubActivity.this)
                            .load(Constant.getImageUrl(model.getData().get(0).getPicture()))
                            .into(mBinding.itemImg);
                }
            }

            @Override
            public void onFailure(Call<NearByHubModel> call, Throwable t) {
                hideLoading();
                Log.d("err", String.valueOf(t));
            }
        });
    }

    void sendToPickup(String hubId, String note) {
        showLoading();
        HashMap<String, String> map = new HashMap<>();

        if (arrayList != null){
            for (int i = 0; i <arrayList.size(); i++) {
                if (arrayList.get(i).getStatus() != null && arrayList.get(i).getStatus() == 0) {
                    String value = arrayList.get(i).getId().toString();
                    map.put("id[" + i + "]", value);
                }

            }

        } else{
            String value = mModel.getId().toString();
            map.put("id[" + 0 + "]", value);
        }
        Call<JsonObject> call = new ApiManagerImp().send_to_pickup(mBasePreferenceManager.getUserToken(), map);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                hideLoading();
                if (response.body() != null) {

                    Intent intent = new Intent(SelectHubActivity.this, SentActivity.class);
                    startActivity(intent);
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
            public void onFailure(Call<JsonObject> call, Throwable t) {
                hideLoading();
                Log.d("err", String.valueOf(t));
                onError(getString(R.string.try_again));
            }
        });
    }

    @Override
    public void onSelectedHub(NearByHubModel.Datum data) {
        try {
            mBinding.locationTxt.setText(data.getAddress());
            mBinding.mobileNumTxt.setText(data.getPhone());
            mBinding.itemNameTxt.setText(data.getName());
            mBinding.kmTxt.setText(data.getDistance().toString() + " km");
            mHubId = String.valueOf(data.getId());
            Glide.with(SelectHubActivity.this)
                    .load(Constant.getImageUrl(data.getPicture()))
                    .into(mBinding.itemImg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}