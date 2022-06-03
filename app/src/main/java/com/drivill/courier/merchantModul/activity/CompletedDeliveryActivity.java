package com.drivill.courier.merchantModul.activity;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.databinding.ActivityCompletedDeliveryBinding;
import com.drivill.courier.merchantModul.model.ShipmentDetailModel;
import com.drivill.courier.rest.ApiManagerImp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompletedDeliveryActivity extends BaseActivity {

    ActivityCompletedDeliveryBinding mBinding;


    void initUI() {
        mBinding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void setUp() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String shipID = getIntent().getStringExtra("pos");
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_completed_delivery);
        initUI();

        if (shipID != null) {
            getShipmentDetail(shipID);
        }

    }

    void getShipmentDetail(String id) {
        showLoading();
        Call<ShipmentDetailModel> call = new ApiManagerImp().merchantShipmentsDetailById(mBasePreferenceManager.getUserToken(), id);
        call.enqueue(new Callback<ShipmentDetailModel>() {
            @Override
            public void onResponse(Call<ShipmentDetailModel> call, Response<ShipmentDetailModel> response) {
                Log.d("res", String.valueOf(response));
                hideLoading();
                if (response.body() != null) {
                    ShipmentDetailModel model = response.body();
                    if (model.getData().getRider() != null && model.getData().getRider().getRider()!=null) {
                        mBinding.riderNameTxt.setText(model.getData().getRider().getRider().getName());
                        mBinding.riderNumberTxt.setText(model.getData().getRider().getRider().getMobile());
                    }

                    mBinding.receiverNameTxt.setText(model.getData().getReceiverName());
                    mBinding.receiverNumberTxt.setText(model.getData().getContactNo());
                    mBinding.receiverAddressTxt.setText(model.getData().getdAddress());
                    if (model.getData().getShipmentNo() != null)
                        mBinding.toolbarTitleTxt.setText(model.getData().getShipmentNo());
                /*    if (model.getData().getProductType() != null) {
                        if (model.getData().getProductType() == 1) {
                            mBinding.productTypeTxt.setText(getResources().getString(R.string.document));
                        } else {
                         mBinding.productTypeTxt.setText(getResources().getString(R.string.heavy_Weight));
                      }
                    }*/
                    if (model.getData().getProductTypes() != null)
                        mBinding.productTypeTxt.setText(model.getData().getProductTypes().getName());
                    if (model.getData().getShipmentType() != null) {
                        if (model.getData().getShipmentType() == 1) {
                            mBinding.shipingTypeTxt.setText(getString(R.string.stndr_delivery));
                        } else {
                            mBinding.shipingTypeTxt.setText(getString(R.string.express_delivery));
                        }
                    }
                    if (model.getData().getProductWeight() != null)
                        mBinding.weightTxt.setText(model.getData().getProductWeight());

                    if (model.getData().getShipmentCost() != null)
                        mBinding.costTxt.setText("Tk. " + model.getData().getShipmentCost());

                    if (model.getData().getCodAmount() != null)
                        mBinding.codAmountTxt.setText("Tk. " + model.getData().getCodAmount());
                    if (model.getData().getNote() != null)
                        mBinding.productNoteTxt.setText(model.getData().getNote());

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
            public void onFailure(Call<ShipmentDetailModel> call, Throwable t) {
                Log.d("res", String.valueOf(t));
                hideLoading();
                onError(getString(R.string.try_again));
            }
        });
    }
}