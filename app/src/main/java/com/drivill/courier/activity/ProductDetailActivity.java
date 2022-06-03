package com.drivill.courier.activity;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.adapter.DeliveryDetailsAdapter;
import com.drivill.courier.databinding.ActivityProductDetailBinding;
import com.drivill.courier.merchantModul.model.NearByHubModel;
import com.drivill.courier.merchantModul.model.ShipmentModel;
import com.drivill.courier.model.model.NearestRiderModel;
import com.drivill.courier.rest.ApiManagerImp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends BaseActivity implements DeliveryDetailsAdapter.AdapterClickEvent {
    ActivityProductDetailBinding mBinding;
    DeliveryDetailsAdapter mAdapter;
    public static String mTag = "";
    int from = 0;
    String mLat, mLon;
    private ShipmentModel mData;

    @Override
    protected void setUp() {
        mAdapter = new DeliveryDetailsAdapter(this, this);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.setAdapter(mAdapter);
        mBinding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (mData.getShipmentNo() != null) {
            mBinding.deliveryIDTxt.setText(mData.getShipmentNo());
        }

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        try {
            List<Address> address = geocoder.getFromLocation(Double.parseDouble(mLat), Double.parseDouble(mLon), 5);
            mBinding.addressDD.setText(address.get(0).getAddressLine(0));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        from = getIntent().getIntExtra("from", 0);
        mLat = getIntent().getStringExtra("lat");
        mLon = getIntent().getStringExtra("lon");
        mData = (ShipmentModel) getIntent().getSerializableExtra("data");
        // MyAnimation.startAnimBottomToTop();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail);
        setUp();

        if (from == 5) {
            mBinding.hub.setText(getString(R.string.hub_nearby));
            getNearestHub(mLat, mLon);
        } else if (from == 6) {
            mBinding.hub.setText(getString(R.string.rider_nearby));
            getNearestRiders(mLat, mLon);
        }

    }

    ////////////// below is Api Call//////////////////////////////////////////////////////////////////////////////
    void getNearestHub(String lat, String lon) {
        showLoading();
        Call<NearByHubModel> call = new ApiManagerImp().riderNearestHub(mBasePreferenceManager.getUserToken(), lat, lon);
        call.enqueue(new Callback<NearByHubModel>() {
            @Override
            public void onResponse(Call<NearByHubModel> call, Response<NearByHubModel> response) {
                Log.d("res", String.valueOf(response));
                hideLoading();
                if (response.body() != null) {
                    NearByHubModel model = response.body();
                    Log.d("res", String.valueOf(response));
                    mTag = "hub";
                    mAdapter.setData(model);

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
            public void onFailure(Call<NearByHubModel> call, Throwable t) {
                hideLoading();
                onError(getString(R.string.try_again));
            }
        });
    }


    void getNearestRiders(String lat, String lon) {
        showLoading();
        Call<JsonObject> call = new ApiManagerImp().riderNearestRider(mBasePreferenceManager.getUserToken(), lat, lon);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("res", String.valueOf(response));
                hideLoading();
                if (response.body() != null) {
                    mTag = "rider";
                    // NearestRiderModel model = response.body();
                    // mAdapter.setRiderData(model);

                    try {
                        JSONObject object = new JSONObject(String.valueOf(response.body()));

                        if (object.has("data")) {

                            ArrayList<NearestRiderModel> arrayList = new ArrayList<>();
                            for (int i = 0; i < object.getJSONArray("data").length(); i++) {
                                NearestRiderModel model = new NearestRiderModel();
                                model.setId(object.getJSONArray("data").getJSONObject(i).getInt("id"));
                                model.setMobile(object.getJSONArray("data").getJSONObject(i).getString("mobile"));
                                model.setPicture(object.getJSONArray("data").getJSONObject(i).getString("picture"));
                                model.setAddress(object.getJSONArray("data").getJSONObject(i).getString("address"));
                                model.setName(object.getJSONArray("data").getJSONObject(i).getString("name"));

                                arrayList.add(model);
                            }

                            mAdapter.setRiderData(arrayList);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
                Log.d("res", String.valueOf(t));
                hideLoading();
                onError(getString(R.string.try_again));
            }
        });

    }

    void transferToRiders(String riderId, String shipNum) {
        showLoading();
        Call<JsonObject> call = new ApiManagerImp().riderTransferShipToRider(mBasePreferenceManager.getUserToken(), shipNum, riderId);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("res", String.valueOf(response));
                hideLoading();
                if (response.body() != null) {
                    try {
                        JSONObject object = new JSONObject(String.valueOf(response.body()));
                        Log.d("res", String.valueOf(response));
                        showMessage(object.getString("message"));
                        Intent intent = new Intent(ProductDetailActivity.this, DashboardActivity.class);
                        startActivity(intent);
                        finishAffinity();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
                Log.d("res", String.valueOf(t));
                hideLoading();
                onError(getString(R.string.try_again));
            }
        });

    }

    void transferToHub(String shipId, String hubId) {
        showLoading();
        Call<JsonObject> call = new ApiManagerImp().riderTransferShipHub(mBasePreferenceManager.getUserToken(), shipId, hubId);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("res", String.valueOf(response));
                hideLoading();
                if (response.body() != null) {
                    try {
                        JSONObject object = new JSONObject(String.valueOf(response.body()));
                        Log.d("res", String.valueOf(response));
                        showMessage(object.getString("message"));
                        Intent intent = new Intent(ProductDetailActivity.this, DashboardActivity.class);
                        startActivity(intent);
                        finishAffinity();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
                Log.d("res", String.valueOf(t));
                hideLoading();
                onError(getString(R.string.try_again));
            }
        });

    }

    @Override
    public void adapterClick(String id, String name) {
        showAlertForTransfer(mData.getId().toString(), id, name);
    }

    public void showAlertForTransfer(String shipId, String id, String name) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.bottom_sheet_dialog_layout);


        TextView okBtn = (TextView) dialog.findViewById(R.id.submitBtn);
        TextView cancelBtn = (TextView) dialog.findViewById(R.id.cancelBtn);
        TextView titleTxt = (TextView) dialog.findViewById(R.id.titleTxt);
        TextView hubName = (TextView) dialog.findViewById(R.id.shipIdTxt);
        titleTxt.setText(getString(R.string.are_you_sure_to_transfer));
        hubName.setText(name);

     /*   if (mSendTo.equals(Constant.MERCHANT)) {
          //  pickBtn.setText(R.string.picked_up);
            tckParcel.setText("Take the parcel from ".concat(mData.getMerchant().getName()) + " and\nsend to " + mData.getReceiverName());
        } else {
           // pickBtn.setText(getString(R.string.delivered));
            tckParcel.setText("Take COD Payments Tk. ".concat(mData.getShipmentCost() + " \nand Handover the parcel to " + mData.getReceiverName()));
        }*/

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mTag.equals("hub")) {
                    transferToHub(shipId, id);
                } else {
                    transferToRiders(id, shipId);
                }

                dialog.dismiss();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    }
}