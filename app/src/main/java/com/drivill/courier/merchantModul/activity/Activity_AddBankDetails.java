package com.drivill.courier.merchantModul.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.drivill.courier.R;
import com.drivill.courier.model.model.NearestRiderModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.PrefsManager;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_AddBankDetails extends AppCompatActivity {

    private AutoCompleteTextView bankname,Acntno,holdername,branchname;

    private Dialog dialogbox;

    private PrefsManager pf ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank_details);

        pf =  new PrefsManager(Activity_AddBankDetails.this);

        bankname =   findViewById(R.id.bankname);
        Acntno =      findViewById(R.id.Acntno);
        holdername =  findViewById(R.id.holdername);
        branchname =  findViewById(R.id.branchname);

        findViewById(R.id.backBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.SaveData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bankname.getText().toString().trim().length()==0){
                    Toast.makeText(Activity_AddBankDetails.this, "The bank name field is required.", Toast.LENGTH_SHORT).show();
                }
                else if(Acntno.getText().toString().trim().length()==0){
                    Toast.makeText(Activity_AddBankDetails.this, "The account number field is required.", Toast.LENGTH_SHORT).show();
                }
                else if(holdername.getText().toString().trim().length()==0){
                    Toast.makeText(Activity_AddBankDetails.this, "The account holder name field is required.", Toast.LENGTH_SHORT).show();
                }
                else if(branchname.getText().toString().trim().length()==0){
                    Toast.makeText(Activity_AddBankDetails.this, "The branch name field is required.", Toast.LENGTH_SHORT).show();

                }else {

                    SaveData_APi();

                }
            }
        });

    }

    private void SaveData_APi() {

        // token, acc_holder_name,bankname,branch_name,account_number
        showLoading();
        Call<JsonObject> call = new ApiManagerImp().AddMerchantBankDetails(pf.getUserToken(),holdername.getText().toString().trim(),
                bankname.getText().toString().trim(),branchname.getText().toString().trim(), Acntno.getText().toString().trim());

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NotNull Call<JsonObject> call, @NotNull Response<JsonObject> response) {
                Log.i("res", "savedetails= "+response);
                hideLoading();
                if (response.body() != null) {

                    Toast.makeText(Activity_AddBankDetails.this, "Account Details Saved Successfully ", Toast.LENGTH_SHORT).show();

                    finish();

                } else {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        Toast.makeText(Activity_AddBankDetails.this, object.getString("error"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("res", String.valueOf(t));
                hideLoading();
                Toast.makeText(Activity_AddBankDetails.this, getString(R.string.try_again), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void showLoading() {

        dialogbox = new Dialog(Activity_AddBankDetails.this);
        dialogbox.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogbox.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogbox.setCancelable(false);
        dialogbox.setContentView(R.layout.dialog_progress_layout);
        dialogbox.show();
    }

    public void hideLoading() {
        if (dialogbox != null && dialogbox.isShowing()) {
            dialogbox.dismiss();
            // Log.e("dismiss","true");
        } else {
             Log.e("dismiss","false");
        }
    }

}