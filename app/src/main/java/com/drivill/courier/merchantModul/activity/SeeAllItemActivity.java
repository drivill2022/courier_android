package com.drivill.courier.merchantModul.activity;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.drivill.courier.adapter.DeliveryStatusAdapter;
import com.google.gson.JsonObject;
import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.databinding.ActivitySeeAllItemBinding;
import com.drivill.courier.merchantModul.adapter.RecentDeliveryAdapter;
import com.drivill.courier.merchantModul.model.ShipmentModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.Constant;
import com.drivill.courier.utils.DataManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeeAllItemActivity extends BaseActivity implements RecentDeliveryAdapter.RecentAdapterClick {
    String item;
    ActivitySeeAllItemBinding mBinding;
    RecentDeliveryAdapter mAdapter;
    DeliveryStatusAdapter adapter;
    ArrayList<ShipmentModel> mDISPLAYList = new ArrayList<>();

    void initUI() {
        mBinding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        getList("All");

        if (DataManager.from.equals("All")) {
            mBinding.recentTxt.setVisibility(View.GONE);
            getList("All");
            item = "All";
        } else {
            mBinding.allItemToolbar.getMenu().findItem(R.id.deliveredTool).setVisible(false);
            mBinding.allItemToolbar.getMenu().findItem(R.id.pendingTool).setVisible(false);
            mBinding.allItemToolbar.getMenu().findItem(R.id.on_thewayTool).setVisible(false);
            mBinding.allItemToolbar.getMenu().findItem(R.id.notReceivedTool).setVisible(false);
            mBinding.recentTxt.setVisibility(View.VISIBLE);
            getList(Constant.DELIVERED);
            item = Constant.DELIVERED;
        }


        mAdapter = new RecentDeliveryAdapter(SeeAllItemActivity.this, item, SeeAllItemActivity.this);
        mBinding.allItemRV.setLayoutManager(new LinearLayoutManager(SeeAllItemActivity.this));

        mBinding.allItemToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.deliveredTool:
                        getList(Constant.DELIVERED);
                        break;
                    case R.id.pendingTool:
                        getList(Constant.CURRENT);
                        break;
                    case R.id.on_thewayTool:
                        getList(Constant.SHIPPED);
                        break;
                    case R.id.notReceivedTool:
                        getList(Constant.CANCELLED);
                        break;
                }
                return false;
            }
        });

        MenuItem actionMenu = mBinding.allItemToolbar.getMenu().findItem(R.id.action_search);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) actionMenu.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filter(s);
                return true;
            }
        });
    }

    @Override
    protected void setUp() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_see_all_item);
        initUI();
    }

    void getList(String item) {
        //showLoading();
        Call<ArrayList<ShipmentModel>> call = new ApiManagerImp().getShipmentMerchant(mBasePreferenceManager.getUserToken(), item);
        call.enqueue(new Callback<ArrayList<ShipmentModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ShipmentModel>> call, Response<ArrayList<ShipmentModel>> response) {
                hideLoading();
                if (response.body() != null) {
                    if (response.body().size() != 0)
                        mBinding.noDataTxt.setVisibility(View.GONE);
                    else mBinding.noDataTxt.setVisibility(View.VISIBLE);

                    mDISPLAYList = response.body();
                    mAdapter.setData(response.body());
                    adapter= new DeliveryStatusAdapter(SeeAllItemActivity.this,mDISPLAYList);
                    mBinding.allItemRV.setAdapter(adapter);




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
    public void adapterClick(String shipID) {
        showAlertDialogComplain(shipID);
    }

    void showAlertDialogComplain(String shipID) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_complain_layout);


        TextView title, okTxtBtn, cancelBtn;
        EditText amountET;
        okTxtBtn = (TextView) dialog.findViewById(R.id.okTxt);
        cancelBtn = (TextView) dialog.findViewById(R.id.cancelTxt);
        title = (TextView) dialog.findViewById(R.id.titleTxt);
        amountET = (EditText) dialog.findViewById(R.id.amountET);


        String riderAmount, merchantAmount;
        title.setText("Enter Your Feedback");


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        okTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amountET.getText().toString().isEmpty()) {
                    Toast.makeText(SeeAllItemActivity.this, "Please Enter Message", Toast.LENGTH_SHORT).show();
                } else {
                    sendComplain(shipID, amountET.getText().toString().trim());
                    dialog.dismiss();
                }
            }
        });
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

    }

    void sendComplain(String shiId, String cmpTxt) {
        showLoading();
        Call<JsonObject> call = new ApiManagerImp().merchantShipComplain(
                mBasePreferenceManager.getUserToken(), shiId, cmpTxt
        );
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                hideLoading();
                if (response.body() != null) {
                    try {
                        JSONObject object = new JSONObject(String.valueOf(response.body()));
                        showMessage(object.getString("message"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        onError(object.getString("error"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                hideLoading();
                onError(getString(R.string.try_again));
            }
        });
    }


    void filter(String text) {
        ArrayList<ShipmentModel> temp = new ArrayList();

        for (ShipmentModel d : mDISPLAYList) {
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            StringBuilder isIn = new StringBuilder();
            if (d.getReceiverName() != null) {
                if (d.getReceiverName().toLowerCase().contains(text.toLowerCase())) {
                    // temp.add(d);
                    isIn.append(d.getReceiverName());

                }
                if (d.getShipmentNo() != null) {
                    if (d.getShipmentNo().toLowerCase().contains(text.toLowerCase())) {
                        //  temp.add(d);
                        isIn.append(d.getShipmentNo());

                    }
                }
                if (d.getdAddress() != null) {
                    if (d.getdAddress().toLowerCase().contains(text.toLowerCase())) {
                        // temp.add(d);
                        isIn.append(d.getdAddress());

                    }
                }
                if (d.getCreatedAt() != null) {
                    if (d.getCreatedDate().toLowerCase().contains(text.toLowerCase())) {
                        isIn.append(d.getCreatedAt());
                    }
                }
                if (!isIn.toString().isEmpty() && isIn.toString().toLowerCase().contains(text)) {
                    temp.add(d);
                }
            }
          /*  if (isRecivName || isID) {
                temp.add(d);
            }*/
        }
        //update recyclerview
        mAdapter.updateList(temp);
    }
}