package com.drivill.courier.merchantModul.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;

import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.databinding.ActivityOneItemDetailBinding;
import com.drivill.courier.merchantModul.bottom_dialog.BottomSheetCancelDelivery;
import com.drivill.courier.merchantModul.model.ShipmentCreateModel;
import com.drivill.courier.merchantModul.model.ShipmentModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.Constant;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OneItemDetailActivity extends BaseActivity implements BottomSheetCancelDelivery.BottomSheetEventListener {
    ActivityOneItemDetailBinding mBinding;
    int from;
    ShipmentCreateModel model;
    ArrayList<ShipmentModel> dataArr;
    ShipmentModel mModelEdit;

    @SuppressLint("SetTextI18n")
    void initUI() {
        mBinding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mBinding.addMorePickupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OneItemDetailActivity.this, PackagingActivity.class);
                startActivity(intent);
            }
        });

        mBinding.processPickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendToPickup();


              //  if (DataManager.getINSTANCE().getCurrentItemListSize() >= 2) {
                   /* Intent intent = new Intent(OneItemDetailActivity.this, PickupRequestActivity.class);
                    startActivity(intent);*/
               // } else {
                 //   showMessage(getString(R.string.required_minimum_order));
               // }
            }
        });

        mBinding.editItemTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        if (from == Constant.EditItem || from == Constant.ViewOnly) {
            mBinding.addMorePickupBtn.setVisibility(View.GONE);
            mBinding.processPickup.setVisibility(View.GONE);
            mBinding.LLTandc.setVisibility(View.GONE);

            if (from == Constant.ViewOnly) {
                mBinding.okyBtn.setVisibility(View.GONE);
                mBinding.cancelBtn.setVisibility(View.GONE);
                mBinding.editItemTxt.setVisibility(View.GONE);
            } else {
                mBinding.editItemTxt.setVisibility(View.VISIBLE);
                mBinding.okyBtn.setVisibility(View.VISIBLE);
                mBinding.cancelBtn.setVisibility(View.VISIBLE);
            }

            mBinding.cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new BottomSheetCancelDelivery(OneItemDetailActivity.this, mModelEdit.getShipmentNo()).show();
                }
            });

            mBinding.okyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });

            try {
                mBinding.addressDivTxt.setText(mModelEdit.getSthana().getName().concat(",")
                        .concat(mModelEdit.getSdistrict().getName().concat(",").concat(mModelEdit.getSdivision().getName())));
                mBinding.addressDivTxtDeleivery.setText(mModelEdit.getDthana().getName().concat(",")
                        .concat(mModelEdit.getDdistrict().getName().concat(",").concat(mModelEdit.getDdivision().getName())));

                mBinding.productDetailTxt.setText(mModelEdit.getProductDetail());
                mBinding.productNoteTxt.setText(mModelEdit.getNote());
                mBinding.toolbarTitleTxt.setText(mModelEdit.getShipmentNo());
                mBinding.shipmentTxt.setText(mModelEdit.getShipmentNo());
                mBinding.recieverNameTxt.setText(mModelEdit.getReceiverName());
                mBinding.txtReciverPhonTxt.setText(mModelEdit.getContactNo());
                mBinding.addressExctTxt.setText(mModelEdit.getsAddress());
                mBinding.addressExctTxtDelivery.setText(mModelEdit.getdAddress());
               /* if (mModelEdit.getProductType() == 1) {
                    mBinding.productTypeTxt.setText(getResources().getString(R.string.document));
                } else {
                    mBinding.productTypeTxt.setText(getResources().getString(R.string.heavy_Weight));
                }*/
                mBinding.productTypeTxt.setText(mModelEdit.getProductType());

                if (mModelEdit.getShipmentType() != null)
                    if (mModelEdit.getShipmentType() == 1) {
                        mBinding.shipingTypeTxt.setText(getString(R.string.stndr_delivery));
                    } else {
                        mBinding.shipingTypeTxt.setText(getString(R.string.express_delivery));
                    }

                if (mModelEdit.getShipmentCost() != null)
                    mBinding.costTxt.setText(mModelEdit.getShipmentCost());
                mBinding.weightTxt.setText(mModelEdit.getProductWeight());
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else {

/*
  mBinding.addressDivTxt.setText(mShipmentModel.getsThana().concat(",")
                    .concat(mShipmentModel.getsDistrict().concat(",").concat(mShipmentModel.getsDivision())));
            mBinding.addressDivTxtDeleivery.setText(mShipmentModel.getdThana().concat(",")
                    .concat(mShipmentModel.getdDistrict().concat(",").concat(mShipmentModel.getdDivision())));

            mBinding.productDetailTxt.setText(mShipmentModel.getProductDetail());
            mBinding.productNoteTxt.setText(mShipmentModel.getNote());
            */

            try {
                mBinding.addressDivTxt.setText(model.getData().getSthana().getName().concat(",")
                        .concat(model.getData().getSdistrict().getName().concat(",").concat(model.getData().getSdivision().getName())));
                mBinding.addressDivTxtDeleivery.setText(model.getData().getDthana().getName().concat(",")
                        .concat(model.getData().getDdistrict().getName().concat(",").concat(model.getData().getDdivision().getName())));

                mBinding.productDetailTxt.setText(model.getData().getProductDetail());
                mBinding.productNoteTxt.setText(model.getData().getNote());
                mBinding.toolbarTitleTxt.setText(model.getData().getShipmentNo());
                mBinding.shipmentTxt.setText(model.getData().getShipmentNo());
                mBinding.recieverNameTxt.setText(model.getData().getReceiverName());
                mBinding.txtReciverPhonTxt.setText(model.getData().getContactNo());
                mBinding.addressExctTxt.setText(model.getData().getsAddress());
                mBinding.addressExctTxtDelivery.setText(model.getData().getdAddress());
                if (model.getData().getProductType().equals("1")) {
                    mBinding.productTypeTxt.setText(getResources().getString(R.string.document));
                } else {
                    mBinding.productTypeTxt.setText(getResources().getString(R.string.heavy_Weight));
                }
                if (model.getData().getShipmentType().equals("1")) {
                    mBinding.shipingTypeTxt.setText("Normal Delivery");
                } else
                    mBinding.shipingTypeTxt.setText(model.getData().getShipmentType());

                mBinding.costTxt.setText(model.getData().getCod_amount());
                mBinding.weightTxt.setText(model.getData().getProductWeight());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        getAllShipDemmItem();
    }


    @Override
    protected void setUp() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        from = getIntent().getIntExtra("from", 0);
        if (from == Constant.EditItem || from == Constant.ViewOnly) {
            mModelEdit = (ShipmentModel) getIntent().getSerializableExtra("data");
        } else {
            model = (ShipmentCreateModel) getIntent().getSerializableExtra("data");

        }
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_one_item_detail);
        initUI();
    }

    @Override
    public void bottomSheetClick(int pos) {
        cancelShiping(mModelEdit.getId().toString(), "notGood");
    }


    void cancelShiping(String id, String reason) {
        Call<ShipmentCreateModel> call = new ApiManagerImp().shipmentCancel(mBasePreferenceManager.getUserToken(), id, reason);
        call.enqueue(new Callback<ShipmentCreateModel>() {
            @Override
            public void onResponse(Call<ShipmentCreateModel> call, Response<ShipmentCreateModel> response) {
                if (response.body() != null) {
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                } else if (response.errorBody() != null) {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        onError(object.getString("error"));
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ShipmentCreateModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {


        if (from == Constant.CREATING_SHIP) {
            showDialogForBackHome();

        } else super.onBackPressed();
    }


    public void showDialogForBackHome() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.bottom_sheet_dialog_layout);


        TextView okBtn = (TextView) dialog.findViewById(R.id.submitBtn);
        TextView cancelBtn = (TextView) dialog.findViewById(R.id.cancelBtn);
        TextView titleTxt = (TextView) dialog.findViewById(R.id.titleTxt);
        TextView hubName = (TextView) dialog.findViewById(R.id.shipIdTxt);
        TextView headingTitleTxt = (TextView) dialog.findViewById(R.id.headingTitleTxt);

        titleTxt.setText("");
        headingTitleTxt.setText(getString(R.string.cancel));
        hubName.setText(getString(R.string.are_you_sure_to_home));

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
                dialog.cancel();
                Intent intent = new Intent(OneItemDetailActivity.this, DashboardActivityMerchant.class);
                startActivity(intent);
                finishAffinity();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    void sendToPickup() {
        showLoading();
        HashMap<String, String> map = new HashMap<>();

        if (dataArr != null){
            for (int i = 0; i <dataArr.size(); i++) {
                if (dataArr.get(i).getStatus() != null && dataArr.get(i).getStatus() == 0) {
                    String value = dataArr.get(i).getId().toString();
                    map.put("id[" + i + "]", value);
                }
            }

        } /*else{
            String value = mModel.getId().toString();
            map.put("id[" + 0 + "]", value);
        }
*/

        Call<JsonObject> call = new ApiManagerImp().send_to_pickup(mBasePreferenceManager.getUserToken(), map);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                hideLoading();
                if (response.body() != null) {

                    Intent intent = new Intent(OneItemDetailActivity.this, SentActivity.class);
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
}