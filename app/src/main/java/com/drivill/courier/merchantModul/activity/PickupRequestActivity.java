package com.drivill.courier.merchantModul.activity;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.databinding.ActivityPickupRequestBinding;
import com.drivill.courier.merchantModul.adapter.RequestPickupItemAdapter;
import com.drivill.courier.merchantModul.model.ShipmentModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PickupRequestActivity extends BaseActivity implements RequestPickupItemAdapter.adapterEventListener {

    ActivityPickupRequestBinding _binding;
    ArrayList<ShipmentModel> dataArr;
    RequestPickupItemAdapter mAdapter;
    int mDeletePos;

    void initUI() {
        // getAllShipItem();

        _binding.addMorePickupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PickupRequestActivity.this, PackagingActivity.class);
                startActivity(intent);
            }
        });
        _binding.sendToPickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PickupRequestActivity.this, SelectHubActivity.class);
                intent.putExtra("dataArray", (Serializable) dataArr);
                startActivity(intent);

            }
        });
        _binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        getAllShipDemmItem();
    }

    @Override
    protected void setUp() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_pickup_request);
        initUI();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constant.EditItem) {
            if (resultCode == Activity.RESULT_OK) {
                dataArr.remove(mDeletePos);
                mAdapter.notifyDataSetChanged();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                // Write your code if there's no result
            }
        }
    }

    /*
            void getAllShipItem() {
                showLoading();
                Call<JsonArray> call = new ApiManagerImp().getShipmentMerchant(mBasePreferenceManager.getUserToken());
                call.enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                        hideLoading();
                        if (response.body() != null) {
                            try {
                                JSONArray object = new JSONArray(String.valueOf(response.body()));
                                Log.d("tt", String.valueOf(object));

                                int i = 0;
                                while (i < object.length()) {

                                    ShipmentModel model = new ShipmentModel();
                                    model.setId(object.getJSONObject(i).getInt("id"));
                                    model.setMerchantId(object.getJSONObject(i).getInt("merchant_id"));
                                    model.setShipmentNo(object.getJSONObject(i).getString("shipment_no"));
                                    model.setReceiverName(object.getJSONObject(i).getString("receiver_name"));
                                    model.setContactNo(object.getJSONObject(i).getString("contact_no"));
                                    model.setProductDetail(object.getJSONObject(i).getString("product_detail"));
                                    model.setProductWeight(object.getJSONObject(i).getString("product_weight"));
                                    model.setProductType(object.getJSONObject(i).getInt("product_type"));
                                    model.setNote(object.getJSONObject(i).getString("note"));
                                    model.setsAddress(object.getJSONObject(i).getString("s_address"));
                                    model.setdAddress(object.getJSONObject(i).getString("d_address"));
                                    model.setShipmentCost(object.getJSONObject(i).getInt("shipment_cost"));
                                    model.setShipmentType(object.getJSONObject(i).getInt("shipment_type"));
                                    model.setPickupDate(object.getJSONObject(i).getString("pickup_date"));
        //Add District
                                   // ShipmentModel.DDistrict dDistrict = new ShipmentModel().new DDistrict();
                                   // dDistrict.setId(object.getJSONObject(i).getInt("id"));
                                  //  model.setdDistrict(dDistrict);

                                    dataArr.add(model);

                                    i++;
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            //   _binding.recyclerPickItem.setLayoutManager(new LinearLayoutManager(PickupRequestActivity.this));
                            //   _binding.recyclerPickItem.setAdapter(new RequestPickupItemAdapter(PickupRequestActivity.this, dataArr));
                        } else {
                            try {
                                JSONObject object = new JSONObject(response.errorBody().string());
                                onError(object.get("error").toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        hideLoading();
                    }
                });
            }*/
    void getAllShipDemmItem() {
        showLoading();
        Call<ArrayList<ShipmentModel>> call = new ApiManagerImp().getShipmentMerchant(mBasePreferenceManager.getUserToken(), Constant.CURRENT);
        call.enqueue(new Callback<ArrayList<ShipmentModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ShipmentModel>> call, Response<ArrayList<ShipmentModel>> response) {
                hideLoading();
                if (response.body() != null) {
                    dataArr = new ArrayList<>();
                    for (int i = 0; i < response.body().size(); i++) {
                        ShipmentModel model = response.body().get(i);
                        if (model.getStatus() == 0) {
                            dataArr.add(model);
                            Log.e("status", String.valueOf(model.getStatus()));
                        }
                    }

                    mAdapter = new RequestPickupItemAdapter(PickupRequestActivity.this, dataArr, (RequestPickupItemAdapter.adapterEventListener) PickupRequestActivity.this, 1);
                    _binding.recyclerPickItem.setLayoutManager(new LinearLayoutManager(PickupRequestActivity.this));
                    _binding.recyclerPickItem.setAdapter(mAdapter);
                } else {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        onError(object.get("error").toString());
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ShipmentModel>> call, Throwable t) {
                hideLoading();
                onError(getString(R.string.try_again));
            }
        });
    }

    @Override
    public void onAdapterItemClick(int pos) {
        mDeletePos = pos;
        Intent intent = new Intent(this, OneItemDetailActivity.class);
        intent.putExtra("from", Constant.EditItem);
        intent.putExtra("data", (Serializable) dataArr.get(pos));
        startActivityForResult(intent, Constant.EditItem);
    }
}