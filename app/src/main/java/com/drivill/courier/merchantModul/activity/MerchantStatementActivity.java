package com.drivill.courier.merchantModul.activity;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.databinding.ActivityMerchantStatementBinding;
import com.drivill.courier.fragment.RidingFragment;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.DataManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MerchantStatementActivity extends BaseActivity {
    ActivityMerchantStatementBinding mBinding;

    static TextView availPay, Commission;

    void initUI() {
        availPay = findViewById(R.id.amountTxtDeposit);
        Commission = findViewById(R.id.drivillCommisionTxt);
        SimpleDateFormat curFormater = new SimpleDateFormat("dd-MMM-yyyy");
        Date c = Calendar.getInstance().getTime();
        String dateObj = curFormater.format(c);
        mBinding.dateTxtCode.setText(dateObj);

        mBinding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mBinding.witDroTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (DataManager.getINSTANCE().isNetworkConnected(MerchantStatementActivity.this)) {
                    withdrawApi();
                }
            }
        });
        mBinding.payHistTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MerchantStatementActivity.this, PaymentHistoryActivity.class);
                startActivity(intent);
            }
        });

        switchFragment(new RidingFragment());
    }

    @Override
    protected void setUp() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_merchant_statement);
        initUI();
    }


    void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment, fragment.toString());
        //  fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static void setAvailPay(String str) {
        availPay.setText(str);
    }

    public static void setCommission(String str) {
        Commission.setText(str);
    }


    void withdrawApi() {
        showLoading();
        Call<JsonObject> call = new ApiManagerImp().withdrawRequest(mBasePreferenceManager.getUserToken());
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
                        JSONObject erroObj = new JSONObject(response.errorBody().string());
                        onError(erroObj.getString("error"));
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
}