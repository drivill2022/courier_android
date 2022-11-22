package com.drivill.courier.activity;;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.databinding.ActivityOneItemDetailNewBinding;
import com.drivill.courier.merchantModul.activity.DashboardActivityMerchant;
import com.drivill.courier.merchantModul.activity.OneItemDetailActivity;
import com.drivill.courier.merchantModul.activity.PackagingActivity;
import com.drivill.courier.merchantModul.activity.SentActivity;
import com.drivill.courier.merchantModul.model.ShipmentCreateModel;
import com.drivill.courier.merchantModul.model.ShipmentModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.Constant;
import com.drivill.courier.utils.MyUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDetailedView extends BaseActivity {

    ActivityOneItemDetailNewBinding mBinding;
    int from;
    ShipmentCreateModel model;
    ArrayList<ShipmentModel> dataArr;

    void initUI() {
        mBinding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mBinding.processPickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendToPickup();
            }
        });



        SetUpData();

        getAllShipDemmItem();
    }

    @SuppressLint("SetTextI18n")
    private void SetUpData() {
      /*  if (model.getData().getProductType().equals("1")) {
            mBinding.packageName.setText(getResources().getString(R.string.package_) +" : " + getResources().getString(R.string.document));
        } else {
            mBinding.packageName.setText(getResources().getString(R.string.package_) +" : " + getResources().getString(R.string.heavy_Weight));
        }*/

        mBinding.packageName.setText(getResources().getString(R.string.package_) +" : " + model.getData().getProductType());

        mBinding.prodDesc.setText(model.getData().getNote());
        mBinding.ItemWeight.setText(model.getData().getProductWeight());

        String SenderLocation =  model.getData().getsAddress() +" " + model.getData().getSthana().getName().concat(",")
                .concat(model.getData().getSdistrict().getName().concat(",").concat(model.getData().getSdivision().getName()));

        mBinding.senderAddress.setText(SenderLocation);
        mBinding.senderMobno.setText(model.getData().getMerchant().getMobile());

        String ReceiverLocation = model.getData().getdAddress() +" "+ model.getData().getDthana().getName().concat(",")
                .concat(model.getData().getDdistrict().getName().concat(",").concat(model.getData().getDdivision().getName()));

        mBinding.ReceiverAddress.setText(ReceiverLocation);
        mBinding.receiverMobno.setText(model.getData().getContactNo());

        mBinding.TVShipmentID.setText(model.getData().getShipmentNo());

    }

    @Override
    protected void setUp() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_one_item_detail_new);

        // Intent from PackagingFragment --
        try {
            model = (ShipmentCreateModel) getIntent().getSerializableExtra("data");
            from = getIntent().getIntExtra("from", 0);
        }catch (Exception e){
            Log.e("exc:-",e.toString());
        }

        MyUtil.ChangeStatusBarColor(ItemDetailedView.this,R.color.theme_color);

        initUI();

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

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                Intent intent = new Intent(ItemDetailedView.this, DashboardActivityMerchant.class);
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

    @Override
    public void onBackPressed() {

        if (from == Constant.CREATING_SHIP) {
            showDialogForBackHome();

        } else super.onBackPressed();
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

                    Intent intent = new Intent(ItemDetailedView.this, SentActivity.class);
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
                Log.i("arp", "failure-sendToPickup= "+t);
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
                  //  Log.i("arp",new Gson().toJson(response.body()));
                    dataArr = new ArrayList<>();
                    for (int i = 0; i < response.body().size(); i++) {
                        ShipmentModel model = response.body().get(i);
                        if (model.getStatus() == 0) {
                            dataArr.add(model);
                            Log.i("arp", String.valueOf(model.getStatus()));
                        }
                    }
                } else {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        onError(object.get("error").toString());
                    } catch (JSONException | IOException e) {
                        Log.i("arp",e.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ShipmentModel>> call, Throwable t) {
                hideLoading();
                onError(getString(R.string.try_again));
                Log.i("arp",t.toString());
            }
        });
    }
}